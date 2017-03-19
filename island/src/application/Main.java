package application;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
	private String selected;
	
	ListView<HBox> scrollList = new ListView<HBox>();

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
					selected = "ef";
					event.consume();
				}
			});

			Label monsterSummoned = new Label("no monster summoned");

			// summon button
			Button summonButton = new Button();
			summonButton.setText("Summon");

			// scroll section
			VBox scrollVBox = new VBox(10);

			Label scrollSelectedLabel = new Label("Please select a scroll");
			scrollSelectedLabel.setTranslateY(30);
			scrollVBox.getChildren().add(scrollSelectedLabel);

			ToggleGroup scrollTG = new ToggleGroup();

			Map<String, String> scrollOptions = new HashMap<String, String>();
			scrollOptions.put("LD", "Light & Dark Scroll");
			scrollOptions.put("MS", "Mystical Scroll");
			scrollOptions.put("LS", "Legendary Scroll");

			for (Map.Entry<String, String> entry : scrollOptions.entrySet()) {
				HBox scrollHBox = new HBox(5);

				Image scrollImage = new Image("application/images/" + entry.getKey() + ".png");
				ImageView scrollIV = new ImageView(scrollImage);

				RadioButton rb = new RadioButton(entry.getValue());
				rb.setToggleGroup(scrollTG);
				rb.setUserData(entry.getKey());
				rb.getStyleClass().remove("radio-button");
				rb.getStyleClass().add("toggle-button");

				HBox.setHgrow(rb, Priority.ALWAYS);
				rb.setMaxWidth(Double.MAX_VALUE);
				rb.setTranslateY(40);

				scrollHBox.getChildren().addAll(scrollIV, rb);
				scrollHBox.setTranslateY(75);
				scrollVBox.getChildren().add(scrollHBox);
			}

			// summon button event
			EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					System.out.println("clicked summon button and " + selected);

					if (scrollTG.getSelectedToggle() != null) {
						if (scrollTG.getSelectedToggle().getUserData().toString().contains("Light")) {
							monsterIV.setImage(new Image("application/images/Valkyrja_(Light)_Icon.png"));
							monsterSummoned.setText("Summoned valk");
							System.out.println("1");
						}
						else if (scrollTG.getSelectedToggle().getUserData().toString().contains("Mystical")) {
							monsterIV.setImage(new Image("application/images/oracle.png"));
							monsterSummoned.setText("Summoned oracle");
							System.out.println("2");
						}
					}
				}
			};

			// Registering the event filter
			summonButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

			scrollTG.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
					if (scrollTG.getSelectedToggle() != null) {
						scrollSelectedLabel.setText(scrollTG.getSelectedToggle().getUserData().toString() + " Selected");
					}
				}
			});

			// layout
			AnchorPane root = new AnchorPane();

			root.getChildren().add(monsterIV);

			monsterSummoned.setLayoutX(150);
			monsterSummoned.setLayoutY(300);
			root.getChildren().add(monsterSummoned);

			summonButton.setLayoutX(150);
			summonButton.setLayoutY(350);
			root.getChildren().add(summonButton);

			scrollVBox.setLayoutX(550);
			scrollVBox.setLayoutY(10);
			root.getChildren().add(scrollVBox);

			Scene scene = new Scene(root, 1000, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.setTitle("Summon Simulator");
			primaryStage.show();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
