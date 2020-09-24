public class PlayerPoints {

	private int points;
	private int cardPoints;

	public PlayerPoints(int points, int cardPoints) {
		this.points = points;
		this.cardPoints = cardPoints;
	}

	public void addPoints(int points) {
        this.points += points;

    }
	public void addCardPoints(int cardPoints) {
        this.cardPoints += cardPoints;
    }

	public int showPoints() {
		return this.points;
	}
	public int showCardPoints() {
		return this.cardPoints;
	}
}
