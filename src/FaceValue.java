public enum FaceValue {

	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), JACK(11), QUEEN(12), KING(13);

	private int numValue;
	private FaceValue (int value) {
		this.numValue = value;
	}
	public int getNumValue() {
		return numValue;
	}
}
