import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * This class is used to display the instructions read from a file using the read class. 
 * displays by using a label added in from a for loop.
 * @author Cameron Orr
 *
 */
public class InstructionsPage {
	private static ArrayList<Label> labelList = new ArrayList<Label>();
	private static VBox vLayout = new VBox(10);
	private static Scene loginScene;
	private static Button backButton;
	
	public static void updateScreen() {
		Stage window = new Stage();
		
		for(int i = 0; i < Read.getInput("Instructions.txt").size(); i++) {
			if(Read.getInput("Instructions.txt") != null){
				labelList.add(new Label(Read.getInput("Instructions.txt").get(i)));
			}else {
				labelList.add(new Label("No Instructions as of now!"));
			}
			
		}
		backButton = new Button("Back");
		
		vLayout.getChildren().addAll(labelList);
		vLayout.getChildren().addAll(backButton);

		vLayout.setPadding(new Insets(20, 10, 10, 25));
		
		loginScene = new Scene(vLayout, 350, 320);
		window.setScene(loginScene);
		window.show();
		
		backButton.setOnAction(e-> window.close());
	}
}
