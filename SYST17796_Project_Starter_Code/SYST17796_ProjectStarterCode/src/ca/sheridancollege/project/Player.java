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
    private final CardHand cardHand; //the unique ID for this player
    private int matches;
    private int[] values;

    /**
     * A constructor that allows you to set the player's unique ID
     *
     * @param name the unique ID to assign to this player.
     * @param DECK
     */
    public Player(String name, Deck DECK) {
        playerID = name;
        cardHand = new CardHand(DECK);
        matches = 0;
    }

    public CardHand getCardHand() {
        return cardHand;
    }

    /**
     * @return the playerID
     */
    public String getPlayerID() {
        return playerID;
    }

    public void drawCard() {
        cardHand.drawCard();
    }

    public void askGoFishQuestion(Player otherPlayer) {
        ArrayList<Card> n = new ArrayList<Card>();
    }

    public void makeMatches() {
        values = new int[cardHand.size()];
        int index = 0;
        for (Card card : cardHand) {
            values[index++] = card.getValue().ordinal() + 1;
        }
        int temp = 0;
        for (int value : values) {
            ArrayList<Card> tempArrayList = new ArrayList<>();
            for (Card card : cardHand) {
                if (value == card.getValue().ordinal() + 1){
                    temp++;
                    tempArrayList.add(card);
                }
            }
            if (temp == 4){
                matches++;
                cardHand.removeAll(tempArrayList);
            }
            temp = 0;
            tempArrayList.clear();
        }
    }

    public int getMatches() {
        return matches;
    }
    
    public ArrayList<Card> hasCard(Values value){
        ArrayList<Card> returnList = new ArrayList<>();
        for (Card card : cardHand) {
            if (card.getValue().equals(value)){
                returnList.add(card);
            }
        }
        cardHand.removeAll(returnList);
        return returnList;
    }
    
    public void giveCards(ArrayList<Card> a){
        cardHand.addAll(a);
    }
    
    public void printCardHand(){
        for (Card card : cardHand) {
            System.out.println(card);
        }
    }
}
