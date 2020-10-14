/**
* This is the PlayerPoints class used to handle the player's points
* 
* @author Eric Burgos
* @version 1.0
* @since 2020-09-24
* @see PlayerPoints
*/
public class PlayerPoints {

	/** Interger to store points*/
	private int points;
	/** Interger to store card points*/
	private int cardPoints;
	
	/** Class constructor of PlayerPoints class
	* @param points Main point for player
	* @param cardPoints Card points for player
	*/
	public PlayerPoints(int points, int cardPoints) {
		this.points = points;
		this.cardPoints = cardPoints;
	}
	
	/** Add points to player's points */
	public void addPoints(int points) {
        this.points += points;
        
    }
	/** Add card points to player's cardpoints */
	public void addCardPoints(int cardPoints) {
        this.cardPoints += cardPoints;
    }
	/** Show player's points */
	public int showPoints() {
		return this.points;
	}
	/** Show player's cardpoints */
	public int showCardPoints() {
		return this.cardPoints;
	}
}
