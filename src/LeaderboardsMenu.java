import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This function displays the leaderboards by reading a file, and displaying the line read onto a label using a label
 * only showing the name, wins, and losses.
 * @author Cameron Orr
 *
 */
public class LeaderboardsMenu {
	private static ArrayList<Label> leaderboardLabels = new ArrayList<>();
	private static ArrayList<String> stringLabels = new ArrayList<>();
	
	public static void show(Stage gameStage) {
		Stage window = new Stage();
		
		VBox vLayout = new VBox(20);
		
		stringLabels = Read.getLeaderboard("Score.txt");
		Label layout = new Label("Name | Wins | Losses");
		layout.setId("leader");
		for(int i = 0; i < stringLabels.size(); i ++) {
			if(stringLabels.size() != 0) {
				Label leaderLabel = new Label(stringLabels.get(i));
				leaderboardLabels.add(leaderLabel);
			}
		}
		vLayout.getChildren().add(layout);
		vLayout.getChildren().addAll(leaderboardLabels);
		vLayout.setPadding(new Insets(20, 10, 10, 25));
		vLayout.setAlignment(Pos.CENTER);
		
		Scene loginScene = new Scene(vLayout, 350, 320);
		window.setScene(loginScene);
		window.show();
		gameStage.close();
		
	}
}
