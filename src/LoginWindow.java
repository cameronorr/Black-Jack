import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class is contains the setup for window to login with. Also contains all of the logic for buttons pressed,
 * within the login page.
 * @author Cameron Orr
 *
 */
public class LoginWindow extends Application{
	
	private static RadioButton aiMode;
	private static Button instructions, next, signUp, score;
	private static Scene loginScene;
	private static TextField usernameTextField, twoUsernameTextField, twoPasswordTextField, passwordTextField;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		window.setTitle("Login");
		
		Label blackJack = new Label("BlackJack");
		blackJack.setFont(Font.font("timesnewroman", 40));
		Label usernameLabel = new Label("Enter a Username:");
		Label passwordLabel = new Label("Enter a password:");
		Label secondPasswordLabel = new Label("Enter a Second Password");
		Label secondUsernameLabel = new Label("Enter a Second Username");
		
		usernameTextField = new TextField("");
		usernameTextField.setMaxWidth(150);
		twoUsernameTextField = new TextField("");
	    twoPasswordTextField = new TextField("");
		passwordTextField = new TextField("");
		instructions = new Button("Instructions");
		next = new Button("Next");
		signUp = new Button("Sign Up");
		score = new Button("Score Menu");
		
		HBox optionHBox = new HBox(10);
		HBox buttonsHBox = new HBox(35);
		HBox usernameHBox = new HBox(10);
		HBox passwordHBox = new HBox(10);
		HBox twoPasswordHBox = new HBox(10);
		HBox twoUsernameHBox = new HBox(10);
		
		VBox twoPlayerVBox = new VBox(20);
		VBox twoPlayerOptionVBox = new VBox(10);
		VBox vLayout = new VBox(20);
		
		twoUsernameHBox.getChildren().addAll(secondUsernameLabel, twoUsernameTextField);
		twoPasswordHBox.getChildren().addAll(secondPasswordLabel, twoPasswordTextField);
		passwordHBox.getChildren().addAll(passwordLabel, passwordTextField);
		usernameHBox.getChildren().addAll(usernameLabel, usernameTextField);
		
		twoPlayerOptionVBox.setVisible(false);
		final ToggleGroup radioGroup = new ToggleGroup();
		aiMode = new RadioButton("AI Mode");
		aiMode.setToggleGroup(radioGroup);
		
		twoPlayerOptionVBox.getChildren().addAll(twoUsernameHBox, twoPasswordHBox);
		optionHBox.getChildren().addAll(aiMode, twoPlayerVBox);
		buttonsHBox.getChildren().addAll(instructions, signUp, score, next);
		vLayout.getChildren().addAll(blackJack, usernameHBox, passwordHBox, optionHBox, buttonsHBox);
		
		vLayout.setPadding(new Insets(20, 10, 10, 25));
		
		loginScene = new Scene(vLayout, 420, 345);
		window.setScene(loginScene);
		window.show();
		
		instructions.setOnAction(e-> {
			InstructionsPage.updateScreen();
		});
		next.setOnAction(e-> {
			nextButton();
		});
		signUp.setOnAction(e-> {
			signUpButton();
		});
		score.setOnAction(e-> {
			scoresButton();
		});
	}
	
	
	public static String getAnswer() {
		if (aiMode.isSelected()) {
			return "aI";
		}
		return null;
	}
	public void scoresButton() {
		String username = usernameTextField.getText();
		if(Read.compareInput(username, "Score.txt") == true) {
			ScoreMenu.show(username);
		}
	}
	public void nextButton() {
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		
		if(aiMode.isSelected()){
			if(Read.compareInput(username, "Score.txt") == true && Read.compareInput(password, "Score.txt") == true && username.length() >= 6) {
				AlertBox.display("Success", "You are successfully logged in!", "Sweet");
				GameWindow.show(username, password);
			}else {
				AlertBox.display("Error", "There is a problem with the username or password", "Ok");
			}
		}else {
			AlertBox.display("Error", "Please select a game mode!", "Ok");
		}
	}
	public void signUpButton() {
		SignUpWindow.setSignUpScene();
	}
}
