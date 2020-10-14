/**
* This is the FaceValue Enum class used to build Card object
* 
* @author Eric Burgos
* @version 1.0
* @since 2020-09-24
* @see FaceValue
*/
public enum FaceValue {

	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), JACK(8), QUEEN(9), KING(10);
	
	/** Making integer to store number value of card*/
	private int numValue;
	/** Constructor of FaceValue class*/
	private FaceValue (int value) {
		this.numValue = value;
	}
	/** Public method to return the number value of card*/
	public int getNumValue() {
		return numValue;
	}
	
}
