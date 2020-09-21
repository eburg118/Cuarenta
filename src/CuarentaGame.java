import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

public class CuarentaGame {
	public static int player1Points;
	public static int player2Points;

    public static void main(String[] args) {

        System.out.println("CUARENTA CARD GAME");
        Deck activeDeck = new Deck();
        activeDeck.makeDeck();
        activeDeck.shuffle();
        Deck player1Hand = new Deck();
        Deck player2Hand = new Deck();
        Deck tablePile = new Deck();
        Deck pointsPile = new Deck();
        Scanner playerInput = new Scanner(System.in);

        System.out.println("Dealing cards . . .");

        /*try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            Logger.getLogger(CuarentaGame.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        player1Hand.drawHand(activeDeck);
        player2Hand.drawHand(activeDeck);

        while(!player1Hand.isEmpty() && !player2Hand.isEmpty()) {
        	//System.out.println("Player1 Hand: " + player2Hand);
        	if (tablePile.isEmpty()) {
        		boolean emptyTable = true;
        		p1Turn(player1Hand, tablePile, playerInput, emptyTable);
        		System.out.println("Player 1 POINTS: " + player1Points );
        	}
        	else if (!tablePile.isEmpty()) {
        		boolean emptyTable = false;
        		p1Turn(player1Hand, tablePile, playerInput, emptyTable);
        		System.out.println("Player 1 POINTS: " + player1Points );
        	}
            p2Turn(player2Hand, tablePile, playerInput);
        }
    }


    public static void p1Turn(Deck player1Hand, Deck tablePile, Scanner playerInput, Boolean isTurnWithEmptyTable) {
    	System.out.println("***Player 1's Turn***\n");
    	System.out.println("P1 Deck Size: " + player1Hand.deckSize());
    	System.out.println("Player1 Hand: " + player1Hand + "\n");
        //System.out.println("Player2 Hand: " + player2Hand);
        //System.out.println("Player1 first card value : " + player1Hand.getCard(0).getNumVal());
        System.out.println("Which card # would you like to play? ");
        int rp = playerInput.nextInt();
        System.out.println("P1 of card playing: " + rp);
        System.out.println("Player 1 played: " + player1Hand.getCard(rp - 1));
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
        	player1Points += checkTable(tablePile, player1Hand, rp, isTurnWithEmptyTable);
        }
        else if (!isTurnWithEmptyTable) {
        	player1Points += checkTable(tablePile, player1Hand, rp, isTurnWithEmptyTable);
        }
        //see if play is possible
    }

    public static void p2Turn(Deck player2Hand, Deck tablePile, Scanner playerInput) {
        System.out.println("***Player 2's Turn***\n");
        System.out.println("P2 Deck Size: " + player2Hand.deckSize());
        System.out.println("Player 2 Hand: " + player2Hand + "\n");
        Random rand = new Random();
        int rp = rand.nextInt(player2Hand.deckSize());
        rp += 1;
        System.out.println("P2 playing card # " + rp + " from hand");
        System.out.println("Player 2 played: " + player2Hand.getCard(rp-1));
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


        System.out.println("Table Pile cards:" + tablePile + "\n");

        player2Hand.removeCard(rp-1);
    }

    public static int checkTable(Deck tablePile, Deck playerHand, int rp, boolean emptyTable) {
    	System.out.println("CHECKING TABLE FOR MATCHES . ." + "\n");
    	int points = 0;
    	// Check to see if table is empty first before starting compare logic
    	if(!emptyTable) {

	        int mostRecent = tablePile.getCard(tablePile.deckSize() - 2).getNumVal();
	        System.out.println("mostRecent card on table: " + mostRecent);
	        int playerMatchCard = playerHand.getCard(rp-1).getNumVal();
	        System.out.println("players card we are analyzing: " + playerMatchCard);

	        //caida check
	        if (playerMatchCard == mostRecent) {

	            System.out.println("CAIDA!- " + "Player card: "+ playerHand.getCard(rp-1) +
	            " " + playerMatchCard + " matches most recent card on table "
	            + tablePile.getCard(tablePile.deckSize() - 2) + " " + mostRecent);
	            // Remove from hand
	            playerHand.removeCard(rp-1);
	            // Remove most recent from pile
	            tablePile.removeCard(tablePile.deckSize() - 2);
	            // Remove your card played on pile
	            tablePile.removeCard(tablePile.deckSize() - 1);
	            //play the matched cards, +2 points, caida

	            points =  2;

	            if(tablePile.isEmpty()){
	                System.out.println("Caido Y Limpia!, +4");
	                points += 2;
	            }

	            return points;
	        }

	        //see if any card on the table matches the players chosen play card
	        // Need to work on this - what if there's more than 1 card that matches?? - there shouldn't be any tbh
	        else if(playerMatchCard != mostRecent) {
	            for (int i = 0; i < tablePile.deckSize() - 1; i++) {
	                if (playerMatchCard == tablePile.getCard(i).getNumVal()) {
	                    System.out.println(playerHand.getCard(rp-1) + " matches " + tablePile.getCard(i));
	                    playerHand.removeCard(rp-1);
	                    tablePile.removeCard(tablePile.deckSize() - 1);
	                    tablePile.removeCard(i);
	                    //play the matched cards
	                    return points;
	                }
	            }
	        }

    	}
    	System.out.println("No Matches :( ");
    	playerHand.removeCard(rp-1);
    	return points;
    }
}
