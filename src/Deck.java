import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains the code for the deck, and allows to store the amount of cards, and then is used to return a card by 
 * removing it from the arraylist.
 * @author Cameron Orr
 *
 */
public class Deck {
	private static ArrayList<Card> cards = new ArrayList<>();
	private static Card drawnCard;
	
	public ArrayList<Card> createDeck() {
		
		int num[] = {2,3,4,5,6,7,8,9,10,10,10,10,11};
		String suits[] = {"Diamonds", "Clubs", "Hearts", "Spades"};
		String names[] = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
		
		
		for(int i = 0; i < suits.length; i++) {
			for(int j = 0; j < names.length; j++) {
				cards.add(new Card(names[j], suits[i], num[j]));
				
			}
		}
		Collections.shuffle(cards);
		
		return cards;
	}
	public Card draw() {
		drawnCard = cards.remove(0);
		
		drawnCard.flipCardOver();
		//ImageView image = drawnCard.getImage();
		return drawnCard;
	}
	public int cardValue() {
		int cardValue = drawnCard.getValue();
		return cardValue;
	}
	public int aiDraw() {
		Card drawnCard = cards.remove(0);
		int cardValue = drawnCard.getValue();
		return cardValue;
	}
}
