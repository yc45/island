package application;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Main extends Application {
	String scrollSelected = "None";

	@Override
	public void start(Stage primaryStage) {
		try {
			// monster image
			Image monsterImage = new Image("application/images/sw.png");

			ImageView monsterIV = new ImageView(monsterImage);

			monsterIV.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					System.out.println("clicked picture");
					scrollSelected = "ef";
					event.consume();
				}
			});

			Label monsterSummoned = new Label("no monster summoned");

			// summon button
			Button summonButton = new Button();
			summonButton.setText("Summon");

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

			// summon button event
			summonButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					System.out.println("clicked summon button and scroll is " + scrollSelected);

					monsterIV.setImage(new Image("application/images/sw.png"));
					monsterSummoned.setText("Summoned valk");
				}
			});

			// layout
			AnchorPane root = new AnchorPane();

			monsterIV.setLayoutX(80);
			monsterIV.setLayoutY(25);
			root.getChildren().add(monsterIV);

			monsterSummoned.setLayoutX(200);
			monsterSummoned.setLayoutY(300);
			root.getChildren().add(monsterSummoned);

			summonButton.setLayoutX(230);
			summonButton.setLayoutY(475);
			root.getChildren().add(summonButton);

			scrollList.setPrefWidth(300);
			scrollList.setLayoutX(550);
			scrollList.setLayoutY(40);
			root.getChildren().add(scrollList);

			Scene scene = new Scene(root, 900, 550);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.setTitle("Summon Island");
			primaryStage.show();

		}
		catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
