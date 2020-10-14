import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.*;


/**
* This is the class that will hold our main function.
* It will handle the whole game logic.
* 
* @author Eric Burgos
* @version 1.0
* @since 2020-09-24
* @see CuarentaGame
*/
public class CuarentaGame {
	/** Player public integer to keep track of total points */
	public static int player1Points;
	/** Player public integer to keep track of total points */
	public static int player2Points;	
	
	
	/**
	* Main method holding our game logic.
	* 
	* @param args
	* @see main
	*/
    public static void main(String[] args) {

        System.out.println("CUARENTA CARD GAME");
        Deck activeDeck = new Deck();
        Deck player1Hand = new Deck();
        Deck player2Hand = new Deck();
        Deck tablePile = new Deck();
        PlayerPoints p1PointPile = new PlayerPoints(0, 0);
        PlayerPoints p2PointPile = new PlayerPoints(0, 0);
        
        activeDeck.makeDeck();
        activeDeck.shuffle();
        Scanner playerInput = new Scanner(System.in);

        System.out.println("Dealing cards . . .");

        
        player1Hand.drawHand(activeDeck);
        player2Hand.drawHand(activeDeck);        	
        
        main: {
        while(!activeDeck.isEmpty()) {
        
		    while(!player1Hand.isEmpty() && !player2Hand.isEmpty()) {
		    	//System.out.println("Player1 Hand: " + player2Hand);
		    	//Scanner playerInput = new Scanner(System.in);
		    	if ((p1PointPile.showCardPoints() + player1Points) > 40) {
		    		System.out.println("Player 1 has 40 points - YOU WIN");
		    		break main;
		    	}
		    	
		    	System.out.println("***Player 1's Turn***\n");
		    	System.out.println("Player 1 cardPoints: " + p1PointPile.showCardPoints());
	    		System.out.println("Player 1 POINTS: " + player1Points + "\n" );
	        	System.out.println("Player1 Hand: " + player1Hand + "\n");
	        	//System.out.println("Player1 decksize: " + player1Hand.deckSize() );
	            System.out.println("Which card # would you like to play? ");
	            
	            int rp = playerInput.nextInt();
	            if (rp  == 0 || rp > player1Hand.deckSize()) {
	            	//throw new ArithmeticException("You must play card that is in your hand ");
	            	System.out.println("You must play card that is in your hand");
	            }
	            else {
	      
			    	if (tablePile.isEmpty()) {
			    		boolean emptyTable = true;
			    		p1Turn(player1Hand, tablePile, rp, emptyTable, p1PointPile);
			    		System.out.println("Player 1 cardPoints: " + p1PointPile.showCardPoints());
			    		System.out.println("Player 1 POINTS: " + player1Points + "\n" );
			    	} 
			    	else if (!tablePile.isEmpty()) {
			    		boolean emptyTable = false;
			    		p1Turn(player1Hand, tablePile, rp, emptyTable, p1PointPile);
			    		System.out.println("Player 1 cardPoints: " + p1PointPile.showCardPoints());		
			    		//System.out.println("p1 pointPile size: " + p1PointsPile.deckSize());
			    		System.out.println("Player 1 POINTS: " + player1Points + "\n" );
			    	} 
	    
		        p2Turn(player2Hand, tablePile);
		        System.out.println("Player 2 POINTS: " + player2Points + "\n" );
	            }
		        
		    }
		    System.out.println("Player's hands are empty");
		    System.out.println("Dealing cards . . .");
		    player1Hand.drawHand(activeDeck);
		    
		    // drawHand method giving issues so manually drew 5 cards at the end here
		    //player2Hand.drawHand(activeDeck);
		    player2Hand.draw(activeDeck);
		    player2Hand.draw(activeDeck);
		    player2Hand.draw(activeDeck);
		    player2Hand.draw(activeDeck);
		    player2Hand.draw(activeDeck);
		    if (activeDeck.isEmpty()) {
		    	System.out.println("END OF ROUND - WE ARE RESHUFFLING, CLEARING TABLE AND PASSING OUT NEXT ROUND HAND" + "\n");
		    	tablePile.removeAll();
		    	activeDeck.makeDeck();
		        activeDeck.shuffle();
		    }
		}
        }
        
        playerInput.close();
    }

    /**
	* Method to handle player 1's turn.
	* This is a real player, p2Turn method will be computer player.
	* 
	* @param player1Hand Player 1 Card object array
	* @param tablePile The cards in the table as Card object array
	* @param rp Response number aka which card is being played from user input
	* @param isTurnWithEmptyTable Boolean to check if we are playing card into empty table
	* @param pPointsPile Object holding player's points
	* @see p1Turn
	*/
    public static void p1Turn(Deck player1Hand, Deck tablePile, int rp, Boolean isTurnWithEmptyTable, PlayerPoints pPointPile) {
    	PlayerPoints p1PointPile = pPointPile;
        switch (rp) {
        case 1:
            tablePile.playCard(player1Hand, 0);
            break;
        case 2:
            tablePile.playCard(player1Hand, 1);
            break;
        case 3:
            tablePile.playCard(player1Hand, 2);
            break;
        case 4:
            tablePile.playCard(player1Hand, 3);
            break;
        case 5:
            tablePile.playCard(player1Hand, 4);
            break;
    }
        if (isTurnWithEmptyTable) {
        	player1Points += checkTable(tablePile, player1Hand, rp, isTurnWithEmptyTable, p1PointPile);
        	
        	//p1PointsPile += p1PointsPile;
        }
        else if (!isTurnWithEmptyTable) {
        	player1Points += checkTable(tablePile, player1Hand, rp, isTurnWithEmptyTable, p1PointPile);
        	//p1PointsPile += p1PointsPile;
        }
    }
    
    /**
	* Method that will handle player 2's (computer) turn.
	* 
	* @param player2Hand Card array for players hand
	* @param tablePile The cards in the table as Card object array
	* @see p2Turn
	*/
    public static void p2Turn(Deck player2Hand, Deck tablePile) {
        System.out.println("***Player 2's Turn***\n");
        //System.out.println("P2 Deck Size: " + player2Hand.deckSize());
        //System.out.println("Player 2 Hand: " + player2Hand + "\n");
        Random rand = new Random();
        int rp = rand.nextInt(player2Hand.deckSize());
        rp += 1;
        //System.out.println("P2 playing card # " + rp + " from hand");
        System.out.println("Player 2 played: " + player2Hand.getCard(rp-1) + "\n");
        switch (rp) {
            case 1:
                tablePile.playCard(player2Hand, 0);
                break;
            case 2:
                tablePile.playCard(player2Hand, 1);
                break;
            case 3:
                tablePile.playCard(player2Hand, 2);
                break;
            case 4:
                tablePile.playCard(player2Hand, 3);
                break;
            case 5:
                tablePile.playCard(player2Hand, 4);
                break;
        }
        
        System.out.println("Cards on table:" + tablePile + "\n");
        player2Hand.removeCard(rp-1);
    }
    

    /**
	* Method that will handle main logic of actual game play after player plays a card.
	* 
	* @param tablePile The cards in the table as Card object array
	* @param playerHand Card array for players hand that just played card
	* @param rp Player's index of card they played, used mainly to keep track of player's hand when discarding
	* @param emptyTable Boolean to check if we player played card on empty table
	* @param pointsPile Point pile of player that played card
	* @see checkTable
	* @return Returns players points in order to add points if we have match, caida, etc
	*/
    public static int checkTable(Deck tablePile, Deck playerHand, int rp, boolean emptyTable, PlayerPoints pointsPile) {
    	System.out.println("CHECKING TABLE FOR MATCHES . ." + "\n");
    	int points = 0;
    	//int pilePoints = 0;
    	// Check to see if table is empty first before starting compare logic
    	if(!emptyTable) {
    		int mostRecent = tablePile.deckSize() - 2;
	        int mostRecentVal = tablePile.getCard(tablePile.deckSize() - 2).getNumVal();
	        //System.out.println("mostRecent card on table: " + mostRecentVal);
	        int playerMatchCard = playerHand.getCard(rp-1).getNumVal();
	        //System.out.println("players card we are analyzing: " + playerMatchCard);
	
	        //caida check
	        if (playerMatchCard == mostRecentVal) {
	            
	            System.out.println("CAIDA!- " + "Player card: "+ playerHand.getCard(rp-1) +
	            " " + playerMatchCard + " matches most recent card on table "
	            + tablePile.getCard(tablePile.deckSize() - 2) + " " + mostRecentVal);
	            // Remove from hand
	            playerHand.removeCard(rp-1);
	            
	            // Remove most recent from pile
	            //pointsPile.playCard(tablePile, mostRecent);
	            tablePile.removeCard(mostRecent);
	            pointsPile.addCardPoints(2);
	            //pointsPile += 2;
	            
	            // Remove your card played on pile
	            //pointsPile.playCard(tablePile, tablePile.deckSize() - 1);
	            tablePile.removeCard(tablePile.deckSize() - 1);
	            //play the matched cards, +2 points, caida
	            
	            points =  2;
	            
	            if(tablePile.isEmpty()){
	                System.out.println("Caida Y Limpia!, +4");
	                points += 2;
	            }
	                
	            return points;
	        } 
	        
	        //see if any card on the table matches the players chosen play card 
	        // Need to work on this - what if there's more than 1 card that matches?? - there shouldn't be any tbh
	        else if(playerMatchCard != mostRecentVal) {
	            for (int i = 0; i < tablePile.deckSize() - 1; i++) {
	                if (playerMatchCard == tablePile.getCard(i).getNumVal()) {
	                    System.out.println(playerHand.getCard(rp-1) + " matches " + tablePile.getCard(i));
	                    playerHand.removeCard(rp-1);
	                    //pointsPile.playCard(tablePile, tablePile.deckSize() - 1);
	                    tablePile.removeCard(tablePile.deckSize() - 1);
	                    //pointsPile.playCard(tablePile, i);
	                    tablePile.removeCard(i);
	                    //play the matched cards
	                    pointsPile.addCardPoints(2);
	                    //pointsPile += 2;
	                    return points;
	                    
	                }
	            }
	        }
	
    	}
    	System.out.println("No Matches :( " + "\n");
    	playerHand.removeCard(rp-1);
    	return points;
    }
}
