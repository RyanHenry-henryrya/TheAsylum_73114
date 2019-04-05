/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The class that models your game. You should create a more specific child of
 * this class and instantiate the methods given.
 *
 * @author dancye, 2018
 */
public class Game {

    private final String gameName;//the title of the game
    private static ArrayList<Player> players = new ArrayList();
    private static final Deck DECK = Deck.getInstance();// the players of the game
    private static int numPlayers;
    private static Scanner in = new Scanner(System.in);
    
    public Game(String givenName) {
        gameName = givenName;
        players = new ArrayList();
    }

    public static void main(String[] args) {
        
        System.out.println("Welcome to Go Fish by the Asylum.");
        System.out.println("Each player starts with 4 cards.");
        System.out.println("A match is four cards of the same value.");
        System.out.println("Would you like to start a new game? [Y]");
        if (in.nextLine().equalsIgnoreCase("y")) {
            // a method that starts the game
            createPlayers();
            System.out.println("Game play here");
            
        } else {
            System.out.println("Ok bye.");
            System.exit(0);
        }
    }

    private static int getNumPlayers() {
        boolean check = true;
        do {
            try {
                System.out.print("Please enter the number of players (2-6): ");

                String input = in.next();

                if (input.matches("\\D+") || input.contains(".")) {
                    throw new InputMismatchException("Invalid input, please "
                            + "try again.");
                } else {
                    numPlayers = Integer.parseInt(input);
                    if (!(numPlayers >= 2 && numPlayers <= 6)) {
                        throw new NumberFormatException("2-6 players only.");
                    }
                }

                // if the above code executes without errors exit the 
                // player number loop and go to game play
                check = false;
            } catch (InputMismatchException | NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }

        } while (check);
        return numPlayers;
    }
    
    /**
     * 
     */
    private static void createPlayers(){
        int n = getNumPlayers();
        System.out.println("Play order is according to name entry order.");
        System.out.println("Player names must be one word.");
        for (int i = 0; i < n; i++){
            boolean check = true;
            do {
                System.out.print((i+1) + ". Please enter your player name: ");
                String newName = in.next();
                try{
                    for (Player player : players) {
                        if (player.getPlayerID().equals(newName)){
                            throw new IllegalArgumentException("Name taken, try "
                                    + "again.");
                        }
                    } 
                    players.add(new Player(newName, DECK));
                    check = false;
                } catch (IllegalArgumentException ex){
                    System.out.println(ex.getMessage());
                }
            } while (check);
        }
        for (Player player : players) {
            player.makeMatches();
            System.out.println(player.getPlayerID() + " you have " + 
                    player.getMatches());
        }
    }

    /**
     * @return the gameName
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * @return the players of this game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players of this game
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Play the game. This might be one method or many method calls depending on
     * your game.
     */
    public void play(){
        
    }

    /**
     * When the game is over, use this method to declare and display a winning
     * player.
     */
    public void declareWinner(){}

    public void showRules() {
        // TODO - implement Game.showRules
        throw new UnsupportedOperationException();
    }

}//end class
