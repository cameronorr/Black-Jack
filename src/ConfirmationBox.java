import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ConfirmationBox {
	
	static boolean answer;
	
	public static boolean display(String title, String message){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.initStyle(StageStyle.UTILITY);
		window.setTitle(title);
		window.setMinWidth(250);
		window.setHeight(125);
		
		Label label = new Label(message);
		
		//create two buttons
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		
		yesButton.setOnAction(e -> {
			answer = true;
			window.close();
		});
		
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		VBox layout = new VBox(20);
		HBox hLayout = new HBox(20);
		hLayout.getChildren().addAll(yesButton, noButton);
		hLayout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(label, hLayout);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		
		//display this window, and before returning, wait to be closed
		window.showAndWait();
		
		
		return answer;
		
	}

}

