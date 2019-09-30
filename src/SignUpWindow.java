import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * This class holds all of the code you will need, for the sign up window in the program. 
 * Separated into methods; setSignUpScene(), where you will find everything being set from the actual look of the window, and the text fields etc.. 
 * in logic(), (called when the user clicks the sign up button in the LoginWindow class) you will find all of the logic for signing up for a new account. 
 * All of it had to be in one method, due to there being a "success" variable which allowed for a more efficient way of telling if the account was verified.
 * And finally the back button method that will be called when the back button is clicked, and will ask the user if they want to go back to the login window, and if they say yes;
 * the window will close.
 * @author Cameron Orr
 **
 */
public class SignUpWindow{
	static Scene signUpScene;
	static Button signUpButton, backButton, loginButton;
	static TextField usernameInput;
	static PasswordField passwordInput, confirmInput;
	static Label usernameLabel, passwordLabel, confirmPasswordLabel;
	static String usernameInputData, passwordInputData, confirmPasswordInputData;
	static CheckBox passwordBox, confirmBox;
	
	/**
	 * This method sets up the entire sign up scene; creating the buttons, text fields, and labels, adding them on to the layout, and adding that layout onto the scene.
	 * Then calling the other methods with logic for what happens when these buttons are pressed.
	 */
	public static void setSignUpScene() {
		Stage window = new Stage();
	
		HBox hSignUpLayout = new HBox(20);
		VBox vSignUpLayout = new VBox(20);
		HBox usernameLayout = new HBox(20);	
		HBox passwordLayout = new HBox(20);
		HBox confirmPasswordLayout = new HBox(20);
		
		Label signUpLabel = new Label("Sign Up");
		signUpButton = new Button("Sign Up");
		
		backButton = new Button("Back");
		
		usernameLabel = new Label("Enter a Username:");
		passwordLabel = new Label("Enter a Password:");
		confirmPasswordLabel = new Label("Confirm Password:");
		usernameInput = new TextField("");
		passwordInput = new PasswordField();
		confirmInput = new PasswordField();
		passwordBox = new CheckBox("Show Field");
		confirmBox = new CheckBox("Show Field");
		confirmInput.setVisible(true);
		
		usernameLayout.getChildren().addAll(usernameLabel, usernameInput);
		passwordLayout.getChildren().addAll(passwordLabel, passwordInput);
		confirmPasswordLayout.getChildren().addAll(confirmPasswordLabel, confirmInput);
		hSignUpLayout.getChildren().addAll(backButton, signUpButton);
		vSignUpLayout.getChildren().addAll(signUpLabel, usernameLayout, passwordLayout, confirmPasswordLayout, hSignUpLayout);
		signUpScene = new Scene(vSignUpLayout, 375, 275);
		vSignUpLayout.setPadding(new Insets(20, 10, 10, 25));
		signUpScene.getStylesheets().add("application.css");
		vSignUpLayout.setId("signLayout");
		window.setScene(signUpScene);
		window.show();
		
		signUpButton.setOnAction(e-> {
			
			logic(usernameInput, passwordInput, confirmInput, window);
		});
		backButton.setOnAction(e-> {
			backButtonLogic(window);
		});
		
		
	}
	
	/**
	 * this method holds all of the logic for when the sign up button is clicked, and acts based on which logic the situation falls under
	 * The success variable allows the code to go through each condition, and see whether anything will set it to false. Since it starts off as true, if none
	 * of the conditions set it to false; then it will finally allow the account to be made, since the success = true
	 * @param usernameInput - > text field that will allow you to get whatever input is in this text field (username) and compare it to the logic conditions.
	 * @param passwordInput - > password field that will allow you to get whatever input is in this field (password) and check whether it passes the logic conditions.
	 * @param confirmInput - > password field that will allow you to get whatever input is in this field (confirming password) and check whether it passes the logic conditions.
	 * @param window - > this parameter passes the stage, allowing the stage to be closed when needed.
	 */
	public static void logic(TextField usernameInput, TextField passwordInput, TextField confirmInput, Stage window) {
		usernameInputData = usernameInput.getText();
		passwordInputData = passwordInput.getText();
		confirmPasswordInputData = confirmInput.getText();
		boolean success = true;
		
		
		if(usernameInputData.length() < 6 || usernameInputData.length() > 10) {
			usernameLabel.setTextFill(Color.web("#FF0000"));
			usernameLabel.setId("userLabelFont");
			AlertBox.display("Username Error", "Please enter a username between 6-10 characters", "OK");
			success = false;
		}else if(usernameInputData.contains(",")) {
			usernameLabel.setTextFill(Color.web("#FF0000"));
			usernameLabel.setId("userLabelFont");
			AlertBox.display("Username Error", "Please no commas in your username", "Ok");
			success = false;
		}else if(usernameInputData.contains("$")) {
			usernameLabel.setTextFill(Color.web("FF0000"));
			usernameLabel.setId("userLabelFont");
			AlertBox.display("Username Error", "Please don't use $", "Ok");
		}
		if(Read.compareInput(usernameInputData, "Login info.txt") == true) {
			usernameLabel.setTextFill(Color.web("#FF0000"));
			usernameLabel.setId("userLabelFont");
			AlertBox.display("Username Error", "This username is already taken.. Please enter a new one!", "Ok");
			success = false;
		}
		if(passwordInputData.length() < 6 || passwordInputData.length() > 10) {
			passwordLabel.setTextFill(Color.web("#FF0000"));
			passwordLabel.setId("userLabelFont");
			AlertBox.display("Password Error", "Please enter a password between 6-10 characters", "Ok");
			success = false;
		}else if(passwordInputData.contains(",")) {
			passwordLabel.setTextFill(Color.web("#FF0000"));
			passwordLabel.setId("userLabelFont");
			AlertBox.display("Password Error", "Please no commas in your password", "Ok");
			success = false;
		}else if(passwordInputData.contains("$")) {
			passwordLabel.setTextFill(Color.web("FF0000"));
			passwordLabel.setId("userLabelFont");
			AlertBox.display("Password Error", "Please don't use $", "Ok");
		}
		if(!(passwordInputData.contains("!") || passwordInputData.contains("@") || passwordInputData.contains("#") || passwordInputData.contains("%") || passwordInputData.contains("^") || passwordInputData.contains("&") || passwordInputData.contains("*") || passwordInputData.contains("(") || passwordInputData.contains(")") || passwordInputData.contains("-") || passwordInputData.contains("_") || passwordInputData.contains("=") || passwordInputData.contains("+") || passwordInputData.contains("[") || passwordInputData.contains("]") || passwordInputData.contains("{") || passwordInputData.contains("}") || passwordInputData.contains("|") || passwordInputData.contains(";") || passwordInputData.contains(":") || passwordInputData.contains("'") || passwordInputData.contains(".") || passwordInputData.contains("/") || passwordInputData.contains("?") || passwordInputData.contains("<") || passwordInputData.contains(">") || passwordInputData.contains("`") || passwordInputData.contains("~"))){
			passwordLabel.setTextFill(Color.web("#FF0000"));
			passwordLabel.setId("userLabelFont");
			AlertBox.display("Password Error", "Please use a symbol in your password!", "Ok");
			success = false;
		}
		if(!confirmPasswordInputData.equals(passwordInputData)) {
			confirmPasswordLabel.setTextFill(Color.web("#FF0000"));
			confirmPasswordLabel.setId("userLabelFont");
			AlertBox.display("Confirm Password Error", "Please make sure the two password fields match", "Ok");
			success = false;
		}
		if (success == true) {
			Write.createFile("Login info.txt", usernameInputData, passwordInputData, 0, 0);
			AlertBox.display("Account Success", "Your account has been successfully created! Now LOGIN!!", "Thank You!");
			window.close();
		}
		
	}
	
	/**
	 * this method allows for the logic of when the back button is pressed. It will display a confirmation box, that offers a yes or no confirmation to the user;
	 * and if the user clicks "yes" then the sign up window closes.
	 * @param window - > stage that is passed through, allowing the stage to be closed when the user clicks yes in the confirmation box.
	 */
	public static void backButtonLogic(Stage window) {
		ConfirmationBox.display("Back Confirmation", "Are you sure you would like to go back, and lose all of your data?!");
		if(ConfirmationBox.answer == true) {
			window.close();
		}
	}
	

}
