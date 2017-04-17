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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MainViewController {
	String scrollSelected = "LS";

	@FXML
	private AnchorPane root;

	@FXML
	private ImageView scrollSelectedIV;
	
	DropShadow scrollShadow= new DropShadow();

	@FXML
	private ImageView starIV;

	@FXML
	private ImageView monsterIV;

	@FXML
	private StackPane monsterStackPane;
	
	@FXML
	private ListView<HBox> scrollList;

	@FXML
	private Button summonButton;

	@FXML
	private ListView<ImageView> historyList;

	ObservableList<ImageView> monsterHistory = FXCollections.observableArrayList();

    @FXML
    private Label threeStarCountLabel;

    @FXML
    private Label fourStarCountLabel;

    @FXML
    private Label fiveStarCountLabel;
    
    int threeStarCount = 0;
    int fourStarCount = 0;
    int fiveStarCount = 0;
    
	@FXML
	private void summonButtonMouseClick() {
		monsterIV.setVisible(true);
		
		Timeline tick0 = new Timeline();
		tick0.setCycleCount(Timeline.INDEFINITE);
		tick0.getKeyFrames().add(new KeyFrame(new Duration(15), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				root.setOpacity(root.getOpacity() - 0.03);
				if (root.getOpacity() < 0.01) {
					tick0.stop();

					int star = -1;
					int count = -1;
					int index = -1;
					int chance = (int) (Math.random() * 200 + 1);

					if (scrollSelected.equals("LS")) {
						if (chance <= 188) {
							star = 4;
							count = 93;
						}
						else {
							star = 5;
							count = 55;
						}

						index = (int) (Math.random() * count + 1);

						monsterIV.setImage(new Image("application/images/monsters/LS/ls_unawaken_" + star + "star (" + index + ").png"));
						starIV.setImage(new Image("application/images/" + star + "star.png"));

						ImageView monsterSummoned = new ImageView(
								new Image("application/images/monsters/LS/ls_unawaken_" + star + "star (" + index + ").png"));
						monsterHistory.add(0, monsterSummoned);
						historyList.setItems(monsterHistory);
					}
					else if (scrollSelected.equals("LD")) {
						if (chance <= 184) {
							star = 3;
							count = 74;
						}
						else if (chance <= 199) {
							star = 4;
							count = 61;
						}
						else {
							star = 5;
							count = 36;
						}

						index = (int) (Math.random() * count + 1);

						monsterIV.setImage(new Image("application/images/monsters/LD/ld_unawaken_" + star + "star (" + index + ").png"));
						starIV.setImage(new Image("application/images/" + star + "star.png"));

						ImageView monsterSummoned = new ImageView(
								new Image("application/images/monsters/LD/ld_unawaken_" + star + "star (" + index + ").png"));
						monsterHistory.add(0, monsterSummoned);
						historyList.setItems(monsterHistory);
					}
					else if (scrollSelected.equals("WaS")) {
						if (chance <= 184) {
							star = 3;
							count = 32;
						}
						else if (chance <= 199) {
							star = 4;
							count = 31;
						}
						else {
							star = 5;
							count = 18;
						}

						index = (int) (Math.random() * count + 1);

						monsterIV.setImage(new Image("application/images/monsters/WaS/water_unawaken_" + star + "star (" + index + ").png"));
						starIV.setImage(new Image("application/images/" + star + "star.png"));

						ImageView monsterSummoned = new ImageView(
								new Image("application/images/monsters/WaS/water_unawaken_" + star + "star (" + index + ").png"));
						monsterHistory.add(0, monsterSummoned);
						historyList.setItems(monsterHistory);
					}
					else if (scrollSelected.equals("FS")) {
						if (chance <= 184) {
							star = 3;
							count = 34;
						}
						else if (chance <= 199) {
							star = 4;
							count = 31;
						}
						else {
							star = 5;
							count = 18;
						}

						index = (int) (Math.random() * count + 1);

						monsterIV.setImage(new Image("application/images/monsters/FS/fire_unawaken_" + star + "star (" + index + ").png"));
						starIV.setImage(new Image("application/images/" + star + "star.png"));

						ImageView monsterSummoned = new ImageView(
								new Image("application/images/monsters/FS/fire_unawaken_" + star + "star (" + index + ").png"));
						monsterHistory.add(0, monsterSummoned);
						historyList.setItems(monsterHistory);
					}
					else if (scrollSelected.equals("WiS")) {
						if (chance <= 184) {
							star = 3;
							count = 29;
						}
						else if (chance <= 199) {
							star = 4;
							count = 31;
						}
						else {
							star = 5;
							count = 18;
						}

						index = (int) (Math.random() * count + 1);

						monsterIV.setImage(new Image("application/images/monsters/WiS/wind_unawaken_" + star + "star (" + index + ").png"));
						starIV.setImage(new Image("application/images/" + star + "star.png"));

						ImageView monsterSummoned = new ImageView(
								new Image("application/images/monsters/WiS/wind_unawaken_" + star + "star (" + index + ").png"));
						monsterHistory.add(0, monsterSummoned);
						historyList.setItems(monsterHistory);
					}
					else if (scrollSelected.equals("MS")) {
						if (chance <= 184) {
							star = 3;
							count = 95;
						}
						else if (chance <= 199) {
							star = 4;
							count = 93;
						}
						else {
							star = 5;
							count = 54;
						}

						index = (int) (Math.random() * count + 1);

						monsterIV.setImage(new Image("application/images/monsters/MS/ms_unawaken_" + star + "star (" + index + ").png"));
						starIV.setImage(new Image("application/images/" + star + "star.png"));

						ImageView monsterSummoned = new ImageView(
								new Image("application/images/monsters/MS/ms_unawaken_" + star + "star (" + index + ").png"));
						monsterHistory.add(0, monsterSummoned);
						historyList.setItems(monsterHistory);
					}
					monsterStackPane.setVisible(true);
					
					if (star == 3) {
						threeStarCount++;
					}
					else if (star == 4) {
						fourStarCount++;
					}
					else {
						fiveStarCount++;
					}
					threeStarCountLabel.setText(Integer.toString(threeStarCount));
					fourStarCountLabel.setText(Integer.toString(fourStarCount));
					fiveStarCountLabel.setText(Integer.toString(fiveStarCount));
					
					root.setOpacity(1.0);
				}
			}
		}));
		tick0.play();
	}

	@FXML
	private void scrollListMouseClick() {
		scrollSelected = (String) scrollList.getSelectionModel().getSelectedItem().getChildren().get(1).getUserData();
		scrollSelectedIV.setImage(new Image("application/images/scrolls/" + scrollSelected + ".png"));
		if (scrollSelected.equals("LS")) {
			scrollShadow.setColor(Color.web("#590578"));
		}
		else if (scrollSelected.equals("LD")) {
			scrollShadow.setColor(Color.web("#C6BD96"));
		}
		else if (scrollSelected.equals("WaS")) {
			scrollShadow.setColor(Color.web("#0DCEFB"));
		}
		else if (scrollSelected.equals("FS")) {
			scrollShadow.setColor(Color.web("#EB162B"));
		}
		else if (scrollSelected.equals("WiS")) {
			scrollShadow.setColor(Color.web("#D8E644"));
		}
		else if (scrollSelected.equals("MS")) {
			scrollShadow.setColor(Color.web("#7C1B32"));
		}
		scrollSelectedIV.setEffect(scrollShadow);
	}

	@FXML
	private void initialize() {
		monsterStackPane.setVisible(false);
		monsterIV.setVisible(false);
		
		scrollSelectedIV.setImage(new Image("application/images/scrolls/LS.png"));
		
		scrollShadow.setOffsetY(0f);
		scrollShadow.setOffsetX(0f);
		scrollShadow.setColor(Color.web("#590578"));
		scrollShadow.setWidth(200);
		scrollShadow.setHeight(200);
		scrollSelectedIV.setEffect(scrollShadow);

		// scroll list section
		Map<String, String> scrollOptions = new LinkedHashMap<String, String>();
		scrollOptions.put("LS", "Legendary Scroll");
		scrollOptions.put("LD", "Light & Darkness Scroll");
		scrollOptions.put("WaS", "Water Scroll");
		scrollOptions.put("FS", "Fire Scroll");
		scrollOptions.put("WiS", "Wind Scroll");
		scrollOptions.put("MS", "Mystical Scroll");

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
	}
}
