/**
* This is the class that is used to build our Card object.
* 
* @author Eric Burgos
* @version 1.0
* @since 2020-09-24
* @see Card
*/

public class Card{

	/** Using Suit Enum class to build card object */
	private Suit suit;
	/** Using FaceValue Enum class to build card object */
	private FaceValue faceValue;

	/** Class constructor initializing
	* @param suit Suit of card specified in Suit Enum class
	* @param facevalue Facevalue of car specified in FaceValue Enum class
	*/
	public Card(Suit suit, FaceValue faceValue) {
		this.suit = suit;
		this.faceValue = faceValue;
	}
	/** Get face value of card */
	public FaceValue getValue() {
		return this.faceValue;
	}
	/** Get number value of card */
	public int getNumVal() {
		return this.faceValue.getNumValue();
	}
	/** Get suit value of card */
	public Suit getSuit() {
		return this.suit;
	}

	/** Give me a string representation of the cards suit and facevalue */
	public String toString(){
		return this.suit.toString() + "-" + this.faceValue.toString();
	}

}
