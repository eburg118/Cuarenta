public class Card{

	private Suit suit;
	private FaceValue faceValue;

	public Card(Suit suit, FaceValue faceValue) {
		this.suit = suit;
		this.faceValue = faceValue;
	}
	// Get face value of card
	public FaceValue getValue() {
		return this.faceValue;
	}
	// Get number value of card
	public int getNumVal() {
		return this.faceValue.getNumValue();
	}
	// Get suit value of card
	public Suit getSuit() {
		return this.suit;
	}

	// Get concat string of suit and face value
	public String toString(){
		return this.suit.toString() + "-" + this.faceValue.toString();
	}
	// adding comment

}
