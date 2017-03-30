package application;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Main extends Application {
	String scrollSelected = "LS";
	ImageView monsterIV;
	Button summonButton;

	AnchorPane root1;
	Scene scene1;
	Stage stage;

	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		
		// scroll image
		Image scrollSelectedImage = new Image("application/images/scrolls/LS.png");

		ImageView scrollSelectedIV = new ImageView(scrollSelectedImage);
		scrollSelectedIV.setFitWidth(100);
		scrollSelectedIV.setFitHeight(100);

		// summon button
		summonButton = new Button();
		summonButton.setText("Summon");
		summonButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Timeline tick0 = new Timeline();
				tick0.setCycleCount(Timeline.INDEFINITE);
				tick0.getKeyFrames().add(new KeyFrame(new Duration(15), new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						root1.setOpacity(root1.getOpacity() - 0.03);
						if (root1.getOpacity() < 0.01) {
							tick0.stop();
							
							if (scrollSelected.equals("LD")) {
								int random = (int) (Math.random() * 200 + 1);
								int star;
								int count;
								
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
								
								int index = (int) (Math.random() * count + 1);
								
								monsterIV.setImage(new Image("application/images/monsters/ld/ld_unawaken_" + star + "star (" + index + ").png"));
								System.out.println(monsterIV.getId());
							}
							root1.setOpacity(1.0);
						}
					}
				}));
				tick0.play();
			}
		});

		// scroll section
		Map<String, String> scrollOptions = new LinkedHashMap<String, String>();
		scrollOptions.put("LS", "Legendary Scroll");
		scrollOptions.put("LD", "Light & Darkness Scroll");
		scrollOptions.put("WaS", "Water Scroll");
		scrollOptions.put("FS", "Fire Scroll");
		scrollOptions.put("WiS", "Wind Scroll");
		scrollOptions.put("MS", "Mystical Scroll");

		ListView<HBox> scrollList = new ListView<HBox>();
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

		// layout for scene 1
		root1 = new AnchorPane();

		scrollSelectedIV.setLayoutX(160);
		scrollSelectedIV.setLayoutY(25);
		root1.getChildren().add(scrollSelectedIV);

		Image monsterImage = new Image("application/images/blank.png");
		monsterIV = new ImageView(monsterImage);
		monsterIV.setLayoutX(160);
		monsterIV.setLayoutY(225);
		root1.getChildren().add(monsterIV);

		summonButton.setLayoutX(165);
		summonButton.setLayoutY(475);
		root1.getChildren().add(summonButton);

		scrollList.setPrefWidth(300);
		scrollList.setLayoutX(550);
		scrollList.setLayoutY(80);
		root1.getChildren().add(scrollList);

		scene1 = new Scene(root1, 900, 550);
		scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setScene(scene1);
		primaryStage.setTitle("Summon Island");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
