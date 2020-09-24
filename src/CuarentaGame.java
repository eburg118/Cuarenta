import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class CuarentaGame {
	public static int player1Points;
	public static int player1PointsPile;
	public static int player2Points;
	public static int player2PointsPile;
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

        /*try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            Logger.getLogger(CuarentaGame.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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

		    	// Should this also take  playerInput scanner object??
		        p2Turn(player2Hand, tablePile);
		        System.out.println("Player 2 POINTS: " + player2Points + "\n" );
	            }

		    }
		    //System.out.println("Active deck size: " + activeDeck.deckSize());
		    System.out.println("Player's hands are empty");
		    System.out.println("Dealing cards . . .");
		    player1Hand.drawHand(activeDeck);
		    //System.out.println("Active deck size: " + activeDeck.deckSize());
		    //System.out.println("Active deck cards: " + activeDeck);

		    // drawHand method giving issues so manually drew 5 cards at the end here
		    //player2Hand.drawHand(activeDeck);
		    player2Hand.draw(activeDeck);
		    player2Hand.draw(activeDeck);
		    player2Hand.draw(activeDeck);
		    player2Hand.draw(activeDeck);
		    player2Hand.draw(activeDeck);
		    //System.out.println("Active deck size: " + activeDeck.deckSize());
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

    public static void p1Turn(Deck player1Hand, Deck tablePile, int rp, Boolean isTurnWithEmptyTable, PlayerPoints pPointPile) {
    	PlayerPoints p1PointPile = pPointPile;
        //System.out.println("P1 playing: " + rp);
        //System.out.println("Player 1 played: " + player1Hand.getCard(rp - 1));
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
        //player1Hand.removeCard(rp-1);
        if (isTurnWithEmptyTable) {
        	player1Points += checkTable(tablePile, player1Hand, rp, isTurnWithEmptyTable, p1PointPile);

        	//p1PointsPile += p1PointsPile;
        }
        else if (!isTurnWithEmptyTable) {
        	player1Points += checkTable(tablePile, player1Hand, rp, isTurnWithEmptyTable, p1PointPile);
        	//p1PointsPile += p1PointsPile;
        }
        //see if play is possible
    }

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
        /* try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            Logger.getLogger(CuarentaGame.class.getName()).log(Level.SEVERE, null, ex);
        }*/


        System.out.println("Cards on table:" + tablePile + "\n");

        player2Hand.removeCard(rp-1);
    }

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
