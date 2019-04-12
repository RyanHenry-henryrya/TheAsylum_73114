/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A class that models each Player in the game. Players have an identifier,
 * which should be unique.
 *
 * @author dancye, 2018
 */
public class Player {

    private final String playerID;
    private final CardHand playerHand; //the unique ID for this player
    private int matches;

    /**
     * A constructor that allows you to set the player's unique ID
     *
     * @param name the unique ID to assign to this player.
     * @param DECK
     */
    public Player(String name, Deck DECK) {
        playerID = name;
        playerHand = new CardHand(DECK);
        matches = 0;
    }

    /**
     * @return the playerID
     */
    public String getPlayerID() {
        return playerID;
    }

    public void drawCard() {
        playerHand.drawCard();
    }

    public void askGoFishQuestion(Player otherPlayer) {
        ArrayList<Card> n = new ArrayList<Card>();
    }
    
    public void makeMatches(){
        /*
        playerHand.contains(new Card(Values.valueOf(String.valueOf()),Suits.valueOf(playerID))));
        for (int i = 0; i < playerHand.size(); i++){
            int x = playerHand.get(i).getValue().ordinal();
            int count = 0;
            for (int j = 0; j < playerHand.size(); j++){
                if (playerHand.get(j).getValue().ordinal() == x){
                    count++;
                }
            }
            if (count == 4){
                for(int k = 0; k < playerHand.size(); k++){
                    if (playerHand.get(k).getValue().ordinal() == x){
                        playerHand.remove(playerHand.get(k));
                    }
                }
            }
        }
        */
        /*
        System.out.println("This is " + getPlayerID() + "'s CardHand:");
        for (Card card : playerHand) {
            System.out.print(card);
        }*/
        Suits[] suits = Suits.values();
        Values[] values = Values.values();
        ArrayList<Card> match = new ArrayList<>();
        System.out.println(playerHand);
        for (Values value : values) {
            for (Suits suit : suits) {
                if (playerHand.contains(new Card(value, suit)))
                    System.out.println("contains works");
                match.add(new Card(value,suit));
            }
            //System.out.println(match);
            if (playerHand.contains(match)){
                System.out.println("match!");
                matches++;
                for (Suits suit : suits) {
                    playerHand.remove(new Card(value,suit));
                }
            }
            
            match.clear();
            
            
            for (Card card : playerHand) {
                //if (playerHand.subList(playerHand.indexOf(card), playerHand.));
            }
            
            for (Card card : playerHand) {
                
            }
        }
        
        //System.out.println(match);
        
        /*
        new approach:
        create a list of 
        */
        
    }//enum .values(), .equals()
    
    public void match(){
        Card[] hand = (Card[]) playerHand.toArray();
        
    }

    public int getMatches() {
        return matches;
    }
}
