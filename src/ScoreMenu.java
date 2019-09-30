import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScoreMenu {
	private static ArrayList<String> score = new ArrayList<>();
	
	public static void show(String username) {
		Stage window = new Stage();
		
		VBox vLayout = new VBox(20);
		
		score = Read.getScore(username, "Score.txt");
		
		Label scoreLabel = new Label(score.get(score.size() - 1));
		
		vLayout.getChildren().add(scoreLabel);
		vLayout.setAlignment(Pos.CENTER);
		vLayout.setPadding(new Insets(20, 10, 10, 25));
		Scene loginScene = new Scene(vLayout);
		window.setScene(loginScene);
		window.show();
	}
	
}
