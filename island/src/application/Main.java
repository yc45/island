package application;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// monster image
			Image monsterImage = new Image("application/images/sw.png");
			ImageView monsterIV = new ImageView(monsterImage);

			Label monsterSummoned = new Label("no monster summoned");

			// summon button
			Button summonButton = new Button();
			summonButton.setText("Summon");

			// scroll section
			VBox scrollVBox = new VBox();

			Label scrollSelectedLabel = new Label("Please select a scroll");

			ToggleGroup scrollTG = new ToggleGroup();

			RadioButton scrollRB1 = new RadioButton("Light & Darkness");
			scrollRB1.setToggleGroup(scrollTG);
			scrollRB1.setUserData("Light & Darkness Scroll");
			RadioButton scrollRB2 = new RadioButton("Mystical");
			scrollRB2.setToggleGroup(scrollTG);
			scrollRB2.setUserData("Mystical Scroll");

			scrollVBox.getChildren().addAll(scrollSelectedLabel, scrollRB1, scrollRB2);
			scrollVBox.setMargin(scrollRB1, new Insets(100, 0, 20, 0));

			// summon button event
			EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
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

			Scene scene = new Scene(root, 800, 400);
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
