package application;

import java.util.LinkedHashMap;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

public class MainViewController {
	String scrollSelected = "LS";

	@FXML
	private AnchorPane root;

	@FXML
	private ImageView scrollSelectedIV;

	@FXML
	private ImageView monsterIV;

	@FXML
	private ListView<HBox> scrollList;

	@FXML
	private Button summonButton;

	@FXML
	private ListView<ImageView> historyList;
	
	ObservableList<ImageView> monsterHistory = FXCollections.observableArrayList();
	
	@FXML
	private void initialize() {
		scrollSelectedIV.setImage(new Image("application/images/scrolls/LS.png"));

		// summon button
		summonButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Timeline tick0 = new Timeline();
				tick0.setCycleCount(Timeline.INDEFINITE);
				tick0.getKeyFrames().add(new KeyFrame(new Duration(15), new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						root.setOpacity(root.getOpacity() - 0.03);
						if (root.getOpacity() < 0.01) {
							tick0.stop();

							int star;
							int count;
							int index;

							if (scrollSelected.equals("LD")) {
								int random = (int) (Math.random() * 200 + 1);

								if (random <= 184) {
									star = 3;
									count = 74;
								}
								else if (random <= 199) {
									star = 4;
									count = 61;
								}
								else {
									star = 5;
									count = 36;
								}

								index = (int) (Math.random() * count + 1);

								monsterIV.setImage(new Image("application/images/monsters/ld/ld_unawaken_" + star + "star (" + index + ").png"));

								ImageView monsterSummoned = new ImageView(
										new Image("application/images/monsters/ld/ld_unawaken_" + star + "star (" + index + ").png"));
								monsterHistory.add(0, monsterSummoned);
								historyList.setItems(monsterHistory);
							}
							root.setOpacity(1.0);
						}
					}
				}));
				tick0.play();
			}
		});

		// scroll list section
		Map<String, String> scrollOptions = new LinkedHashMap<String, String>();
		scrollOptions.put("LS", "Legendary Scroll");
		scrollOptions.put("LD", "Light & Darkness Scroll");
		scrollOptions.put("WaS", "Water Scroll");
		scrollOptions.put("FS", "Fire Scroll");
		scrollOptions.put("WiS", "Wind Scroll");
		scrollOptions.put("MS", "Mystical Scroll");

		scrollList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				scrollSelected = (String) scrollList.getSelectionModel().getSelectedItem().getChildren().get(1).getUserData();
				scrollSelectedIV.setImage(new Image("application/images/scrolls/" + scrollSelected + ".png"));
			}
		});

		ObservableList<HBox> items = FXCollections.observableArrayList();

		for (Map.Entry<String, String> entry : scrollOptions.entrySet()) {
			HBox scrollHBox = new HBox(5);

			Image scrollImage = new Image("application/images/scrolls/" + entry.getKey() + ".png");
			ImageView scrollIV = new ImageView(scrollImage);

			Label label = new Label(entry.getValue());
			label.setUserData(entry.getKey());

			HBox.setHgrow(label, Priority.ALWAYS);
			label.setMaxWidth(Double.MAX_VALUE);

			scrollHBox.getChildren().addAll(scrollIV, label);
			scrollHBox.setAlignment(Pos.CENTER);
			items.add(scrollHBox);
		}
		scrollList.setItems(items);
		scrollList.getSelectionModel().select(0);
		scrollList.getFocusModel().focus(0);

		// history list section

	}
}
