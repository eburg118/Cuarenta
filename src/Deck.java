import java.util.ArrayList;
import java.util.Random;

/**
* This is the class that is used to build our Deck object.
* 
* @author Eric Burgos
* @version 1.0
* @since 2020-09-24
* @see Deck
*/
public class Deck {

	/** Initializing array list of card objects */
    private ArrayList<Card> cards;

    
    /** Class constructor for Deck */
    public Deck() {
        this.cards = new ArrayList<Card>();

    }
    /** Make the deck using Suit and FaceValue values */
    public void makeDeck() {
        for (Suit cardSuit : Suit.values()) {
            //Loop through Values
            for (FaceValue cardValue : FaceValue.values()) {
                //Add new card to the mix
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }

    }
    /** Used to shuffle any deck object */
    public void shuffle() {
        ArrayList<Card> tmpDeck = new ArrayList<Card>();
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        for (int i = 0; i < originalSize; i++) {
            randomCardIndex = random.nextInt((this.cards.size() - 1 - 0) + 1) + 0;
            tmpDeck.add(this.cards.get(randomCardIndex));
            this.cards.remove(randomCardIndex);
        }
        this.cards = tmpDeck;
    }

    /** Remove card from deck */
    public void removeCard(int i) {
        this.cards.remove(i);
    }
    /** Remove all cards from deck */
    public void removeAll() {
        this.cards.removeAll(this.cards);
    }
    /** Get card */
    public Card getCard(int i) {
        return this.cards.get(i);
    }
    /** Add card to a deck */
    public void addCard(Card addCard) {
        this.cards.add(addCard);
    }

    /** Play card to a deck
    * @param comingFrom Which deck are we getting card from
    * @param which Index of card on that Card array
    */
    public void playCard(Deck comingFrom, int which) {
        this.cards.add(comingFrom.getCard(which));
        // comingFrom.removeCard(which);
    }
    
    /** Draw card from top of deck
    * @param comingFrom Which deck are we getting card from
    */
    public void draw(Deck comingFrom) {
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    /** Draw a hand of 5 cards from top of deck
    * @param comingFrom Which deck are we getting card from
    */
    public void drawHand(Deck comingFrom) {
        for (int i = 0; i < 5; i++) {
            this.cards.add(comingFrom.getCard(i));
            comingFrom.removeCard(i);
        }
    }
    /** Output deck as string*/
    public String toString() {
        String cardListOutput = "";
        int i = 1;
        for (Card aCard : this.cards) {
            cardListOutput += "\n" + "(" + i + ")" + " " + aCard.toString() + " " + aCard.getNumVal();
            i++;
        }
        return cardListOutput;
    }
    /** Get size of deck*/
    public int deckSize() {
        return this.cards.size();
    }
    /** Check if deck is empty*/
    public boolean isEmpty(){
        return this.cards.isEmpty();
    }
}
