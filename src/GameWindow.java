import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class contains all of the setup for the game window, and all of the logic for what happens with the game, and everything in the game
 * @author Cameron Orr
 *
 */
public class GameWindow {
	private static Card cardOne, cardTwo;
	private static HBox cardsHBox = new HBox(10), buttonsHBox = new HBox(100);
	private static VBox scoreOne = new VBox(20), scoreTwo = new VBox(20);
	private static int playerTurn = 1;
	private static int playerOneHandValue, playerTwoHandValue, playerOneScore, playerTwoScore, turnNumber = 1;
	private static Button hitButton, stayButton, doneButton, nextButton, backButton;
	private static ArrayList<Card> deckArray = new ArrayList<>();
	private static Boolean endHand = false;
	private static ImageView newCardImg;
	private static Label playerOneScoreLabel, playerTwoScoreLabel;
	
	public static void show(String username, String password) {
		Stage window = new Stage();
		window.setTitle("Game");
		
		VBox vLayout = new VBox(30);
		vLayout.setPadding(new Insets(20, 10, 10, 25));
		vLayout.setAlignment(Pos.CENTER);
		VBox buttonsVBox = new VBox(15);
		
		
		Label modeLabel = new Label(LoginWindow.getAnswer() + "Mode Activated");
		
		scoreOne.getChildren().clear();
		scoreTwo.getChildren().clear();
		playerTwoScoreLabel = new Label("Player 2 Wins: " + Integer.toString(playerTwoScore));
		playerOneScoreLabel = new Label("Player 1 Wins: " + Integer.toString(playerOneScore));
		scoreOne.getChildren().add(playerOneScoreLabel);
		scoreOne.setAlignment(Pos.BOTTOM_LEFT);
		scoreTwo.getChildren().add(playerTwoScoreLabel);
		scoreTwo.setAlignment(Pos.TOP_RIGHT);		
		
		
		hitButton = new Button("Hit");
		stayButton = new Button("Stay");
		doneButton = new Button("Done");
		nextButton = new Button("Next");
		backButton = new Button("Back");
		
		buttonsHBox.getChildren().clear();
		buttonsHBox.getChildren().addAll(backButton, doneButton);
		buttonsHBox.setAlignment(Pos.CENTER);
		buttonsVBox.getChildren().addAll(stayButton, hitButton, nextButton);
		
		Deck newDeck = new Deck();
		deckArray = newDeck.createDeck();
		
		if(LoginWindow.getAnswer().equals("aI")) {
			cardOne = newDeck.draw();
			playerOneHandValue += cardOne.value;
			ImageView cardOneImg = cardOne.getImage();
			cardTwo = newDeck.draw();
			playerOneHandValue += cardTwo.value;
			ImageView cardTwoImg = cardTwo.getImage();
			cardsHBox.getChildren().addAll(cardOneImg, cardTwoImg);
			playerTwoHandValue += newDeck.aiDraw() + newDeck.aiDraw();
		}
		
		
		
		cardsHBox.setAlignment(Pos.CENTER);
		vLayout.getChildren().addAll(scoreTwo, modeLabel, buttonsVBox, cardsHBox, scoreOne, buttonsHBox);
		Scene loginScene = new Scene(vLayout, 700, 510);
		loginScene.getStylesheets().add("application.css");
		vLayout.setId("game");
		window.setScene(loginScene);
		window.show();
		
		gameLogic(newDeck);
		
		doneButton.setOnAction(e-> {
			done(window, username, password);
		});
		backButton.setOnAction(e-> {
			back(window);
		});
	}
	public static void done(Stage window, String username, String password) {
		ConfirmationBox.display("Confirm", "Are you done playing?");
		if(ConfirmationBox.answer == true) {
			LeaderboardsMenu.show(window);
				Write.createFile("Score.txt", username, password, playerOneScore, playerTwoScore);
		}
	}
	public static void back(Stage window) {
		ConfirmationBox.display("Confirm", "Are you sure you want to exit, and lose your data?");
		if(ConfirmationBox.answer == true) {
			window.close();
		}
	}
	public static boolean gameLogic(Deck newDeck) {
		if(LoginWindow.getAnswer().equals("aI")) {
			if(playerTurn == 1) {
					AlertBox.display("Turn", "Your Turn Donkey", "Nice");
					hitButton.setOnAction(e-> {
						deckArray = newDeck.createDeck();
						Card newCard = newDeck.draw();
						playerOneHandValue += newCard.value;
						newCardImg = newCard.getImage();
						cardsHBox.getChildren().add(newCardImg);
						
						if(playerOneHandValue > 21) {
							AlertBox.display("Alert", "AI wins this round bro, sorry for your luck", "Shoot");
							playerTurn = 2;
							turnNumber = 1;
							playerTwoScore ++;
							endHand = true;
						}
						if(turnNumber == 2) {
							if(playerOneHandValue > playerTwoHandValue) {
								AlertBox.display("Alert", "Player One wins the hand!", "WOOOOO");
								playerOneScore ++;
								turnNumber = 1;
								playerTurn = 2;
								endHand = true;
							}else if(playerOneHandValue < playerTwoHandValue) {
								AlertBox.display("Alert", "Sorry Baud, AI Wins the hand!", "Crap");
								playerTwoScore ++;
								turnNumber = 1;
								playerTurn = 2;
								endHand = true;
							}else if(playerOneHandValue == playerTwoHandValue) {
								AlertBox.display("Alert", "Oof, Tie Game", "Thats Fine!");
								turnNumber = 1;
								playerTurn = 2;
								endHand = true;
							}
						}
						if(endHand == true) {
							scoreOne.getChildren().clear();
							scoreTwo.getChildren().clear();
							playerOneScoreLabel = new Label("Player 1 Wins: " + Integer.toString(playerOneScore));
							playerTwoScoreLabel = new Label("Player 2 Wins: " + Integer.toString(playerTwoScore));
							scoreOne.getChildren().add(playerOneScoreLabel);
							scoreTwo.getChildren().add(playerTwoScoreLabel);
							playerOneHandValue = 0;
							playerTwoHandValue = 0;
							cardsHBox.getChildren().clear();
							cardOne = newDeck.draw();
							playerOneHandValue += cardOne.value;
							ImageView cardOneImg = cardOne.getImage();
							cardTwo = newDeck.draw();
							playerOneHandValue += cardTwo.value;
							ImageView cardTwoImg = cardTwo.getImage();
							deckArray = newDeck.createDeck();
							cardsHBox.getChildren().addAll(cardOneImg, cardTwoImg);
							playerTwoHandValue += newDeck.aiDraw() + newDeck.aiDraw();
							endHand = false;
						}
					});
					stayButton.setOnAction(e-> {
						if(playerTurn == 1) {
							if(playerOneHandValue > 21) {
								AlertBox.display("Loss", "Sorry, you lose this one", "Oh No");
								playerTurn = 2;
								turnNumber = 1;
								playerTwoScore ++;
								endHand = true;
							}
							if(turnNumber == 1) {
								playerTurn = 2;
								turnNumber = 2;
							}else if(turnNumber == 2) {
								if(playerOneHandValue > playerTwoHandValue) {
									AlertBox.display("Winner", "You Win the Hand!", "Awesome");
									playerTurn = 2;
									turnNumber = 1;
									playerOneScore ++;
									endHand = true;
								}else if(playerOneHandValue < playerTwoHandValue) {
									AlertBox.display("Oof", "Sorry man, not this time", "Dang");
									playerTurn = 2;
									turnNumber = 1;
									playerTwoScore++;
									endHand = true;
								}else if(playerOneHandValue == playerTwoHandValue) {
									AlertBox.display("Tie", "Rats, tie game", "Ok");
									playerTurn = 2;
									turnNumber = 1;
									endHand = true;
								}
							}
						}
						
						if(endHand == true) {
							scoreOne.getChildren().clear();
							scoreTwo.getChildren().clear();
							playerOneScoreLabel = new Label("Player 1 Wins: " + Integer.toString(playerOneScore));
							playerTwoScoreLabel = new Label("Player 2 Wins: " + Integer.toString(playerTwoScore));
							scoreOne.getChildren().add(playerOneScoreLabel);
							scoreTwo.getChildren().add(playerTwoScoreLabel);
							playerOneHandValue = 0;
							playerTwoHandValue = 0;
							cardsHBox.getChildren().clear();
							cardOne = newDeck.draw();
							playerOneHandValue += cardOne.value;
							ImageView cardOneImg = cardOne.getImage();
							cardTwo = newDeck.draw();
							playerOneHandValue += cardTwo.value;
							ImageView cardTwoImg = cardTwo.getImage();
							deckArray = newDeck.createDeck();
							cardsHBox.getChildren().addAll(cardOneImg, cardTwoImg);
							playerTwoHandValue += newDeck.aiDraw() + newDeck.aiDraw();
							endHand = false;
						}
					});	
					nextButton.setOnAction(e-> {
						if(playerTurn == 2) {
							if(playerTwoHandValue >= 17 && playerTwoHandValue < 22) {
								if(turnNumber == 1) {
									AlertBox.display("Alert", "Player's Turn", "Nice");
									playerTurn = 1;
									turnNumber = 2;
								}else if(turnNumber == 2) {
									if(playerTwoHandValue > playerOneHandValue) {
										AlertBox.display("Alert", "The AI Wins this hand!", "Dang");
										playerTwoScore ++;
										playerTurn = 1;
										turnNumber = 1;
										endHand = true;
									}else if(playerTwoHandValue < playerOneHandValue) {
										AlertBox.display("Alert", "Congrats bud, you won this hand", "Hoorah");
										playerOneScore ++;
										playerTurn = 1;
										turnNumber = 1;
										endHand = true;
									}else if(playerTwoHandValue == playerOneHandValue) {
										AlertBox.display("Alert", "Ooou, close call.. TIE ROUND", "oof");
										playerTurn = 1;
										turnNumber = 1;
										endHand = true;
									}
								}
							}else if(playerTwoHandValue < 17) {
								playerTwoHandValue += newDeck.aiDraw();
								System.out.println(playerTwoHandValue);
							}else if(playerTwoHandValue >= 21) {
								AlertBox.display("Alert", "Player has won this hand", "Awesome");
								playerOneScore ++;
								playerTurn = 1;
								turnNumber = 1;
								endHand = true;
							}
						if(endHand == true) {
							scoreOne.getChildren().clear();
							scoreTwo.getChildren().clear();
							playerOneScoreLabel = new Label("Player 1 Wins: " + Integer.toString(playerOneScore));
							playerTwoScoreLabel = new Label("Player 2 Wins: " + Integer.toString(playerTwoScore));
							scoreOne.getChildren().add(playerOneScoreLabel);
							scoreTwo.getChildren().add(playerTwoScoreLabel);
							playerOneHandValue = 0;
							playerTwoHandValue = 0;
							cardsHBox.getChildren().clear();
							cardOne = newDeck.draw();
							playerOneHandValue += cardOne.value;
							ImageView cardOneImg = cardOne.getImage();
							cardTwo = newDeck.draw();
							playerOneHandValue += cardTwo.value;
							ImageView cardTwoImg = cardTwo.getImage();
							deckArray = newDeck.createDeck();
							cardsHBox.getChildren().addAll(cardOneImg, cardTwoImg);
							playerTwoHandValue += newDeck.aiDraw() + newDeck.aiDraw();
							endHand = false;
						}
					}
				});
			
		}
			
	
		
	}
		
		return false;
}
}
