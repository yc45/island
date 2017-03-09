package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Image monsterPic = new Image("application/images/sw.png");
			ImageView iv1 = new ImageView();
			iv1.setImage(monsterPic);
			
			Button summonBtn = new Button();
			summonBtn.setText("Summon");
			
			Label scrollSelected = new Label("Please select a scroll");
			
			AnchorPane root = new AnchorPane();
			root.getChildren().add(iv1);
			summonBtn.setLayoutX(150);
			summonBtn.setLayoutY(350);
			root.getChildren().add(summonBtn);
			scrollSelected.setLayoutX(600);
			scrollSelected.setLayoutY(50);
			root.getChildren().add(scrollSelected);
			
			Scene scene = new Scene(root,800,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Summon Simulator");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
