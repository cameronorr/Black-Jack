//package blackJack;
import java.io.File;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.*;

/**
 * @author wrightty
 * Creates a playing card with a name, suit, value, and image
 * The playing card can in or out of the deck 
 * The playing card can be face down or face up (image changes accordingly)
 * 
 */
public class Card {
	String suit;
	int value = 0;
	String name;
	boolean inDeck = true;
	int imageXLocation;
	int imageYLocation;
	int imageCardHeight = 98;
	int imageCardWidth = 73;
	static File file = new File("cards.png");
	static Image image = new Image(file.toURI().toString());
	ImageView view;
	boolean faceDown = true;

	/**
	 * Creates the card object and sets its image location
	 * based on its name and suit.
	 * @param name of the card (i.e. "King", "Nine")
	 * @param suit (i.e. "Clubs", "Spades")
	 * @param value of card (game specific)
	 */
	public Card(String name, String suit, int value) {
		this.name = name;
		this.suit = suit;
		this.value = value;
		setImageLocation();
	}
	
	public String getSuit() {
		return suit;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public String printOut() {
		return name + " of " + suit;
	}

	public void setValue(int newValue) {
		value = newValue;
	}

	public void setInDeck(boolean status) {
		inDeck = status;
	}

	/**
	 * If the card is not supposed to be face down,
	 * creates an image of the individual card (face up)
	 * otherwise, creates an image using the back of the card
	 * @return an ImageView object of the individual card.
	 */
	public ImageView draw() {
		if(!faceDown) {
		view = new ImageView(image);
		Rectangle2D viewportRect = new Rectangle2D(getImageX(), getImageY(), imageCardWidth, imageCardHeight);
		view.setViewport(viewportRect);
		}else {
			view = new ImageView(new Image(new File("playing-card-back.jpg").toURI().toString()));
			view.setFitHeight(imageCardHeight);
			view.setFitWidth(imageCardWidth);
		}
		return view;
	}

	public ImageView getImage() {
		return view;
	}



	/**
	 * Using the Card's name attribute, an image x location is given
	 * Using the Card's suit attribute, an image y location is given
	 */
	private void setImageLocation() {
		switch (name) {
		case "Ace":
			imageXLocation = 0;
			break;
		case "Two":
			imageXLocation = imageCardWidth;
			break;
		case "Three":
			imageXLocation = imageCardWidth * 2;
			break;
		case "Four":
			imageXLocation = imageCardWidth * 3;
			break;
		case "Five":
			imageXLocation = imageCardWidth * 4;
			break;
		case "Six":
			imageXLocation = imageCardWidth * 5;
			break;
		case "Seven":
			imageXLocation = imageCardWidth * 6;
			break;
		case "Eight":
			imageXLocation = imageCardWidth * 7;
			break;
		case "Nine":
			imageXLocation = imageCardWidth * 8;
			break;
		case "Ten":
			imageXLocation = imageCardWidth * 9;
			break;
		case "Jack":
			imageXLocation = imageCardWidth * 10;
			break;
		case "Queen":
			imageXLocation = imageCardWidth * 11;
			break;
		case "King":
			imageXLocation = imageCardWidth * 12;
			break;
		default:
			System.out.println("Card Name Error");
			System.exit(0);
		}

		switch (suit) {
		case "Clubs":
			imageYLocation = 0;
			break;
		case "Spades":
			imageYLocation = imageCardHeight;
			break;
		case "Hearts":
			imageYLocation = imageCardHeight * 2;
			break;
		case "Diamonds":
			imageYLocation = imageCardHeight * 3;
		}

	}

	/**
	 * flips over the card and redraws it to reset its ImageView
	 */
	public void flipCardOver() {
		if (faceDown) {
			faceDown = false;
		} else {
			faceDown = true;
		}
		draw();
	}

	private int getImageX() {
		return imageXLocation;
	}

	private int getImageY() {
		return imageYLocation;
	}

}