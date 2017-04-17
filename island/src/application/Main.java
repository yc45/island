package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		AnchorPane root = (AnchorPane) FXMLLoader.load(Main.class.getResource("MainView.fxml"));
		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Summoning Island");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
