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

    private static ArrayList<Player> players;
    private static Deck DECK;
    private static int numPlayers;
    private static Scanner in;
    

    public Game() {
        players = new ArrayList();
        DECK = Deck.getInstance();
        
        //Collections.shuffle(DECK);
    }

    public static void main(String[] args) {
        in = new Scanner(System.in);
        System.out.println("Welcome to Go Fish by the Asylum.");
        System.out.println("Each player starts with 4 cards.");
        System.out.println("A match is four cards of the same value.");
        System.out.print("Would you like to start a new game? [Y] \n>");
        if (in.nextLine().equalsIgnoreCase("y")) {
            // a method that starts the game
            Game game = new Game();
            Game.createPlayers();
            play();
            //System.out.println("Game play here");
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
                    if (!(numPlayers >= 2 && numPlayers <= 6)) { //change back
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
    private static void createPlayers() {
        int n = getNumPlayers();
        System.out.println("Play order is according to name entry order.");
        System.out.println("Player names must be one word.");
        System.out.println("Spaces between names can be used as delimiters.");
        System.out.println("For example: 'name1 name2 name3' would be three "
                + "different player names");
        for (int i = 0; i < n; i++) {
            boolean check = true;
            do {
                System.out.print((i + 1) + ". Please enter your player name: ");
                String newName = in.next();
                try {
                    if (newName.contains(" ")){
                        throw new IllegalArgumentException("Invalid name, try "
                                + "again.");
                    }
                    for (Player player : players) {
                        if (player.getPlayerID().equals(newName)) {
                            throw new IllegalArgumentException("Name taken, try"
                                    + " again.");
                        }
                    }
                    players.add(new Player(newName, DECK));
                    check = false;
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                }
            } while (check);
        }
        for (Player player : players) {
            player.makeMatches();
        }
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
    public static void play() {
        while (DECK.size() > 0) {
            for (Player player : players) {
                player.makeMatches();
                System.out.println(player.getPlayerID() + ", it is your turn."
                        + " You have " + player.getMatches() + " matches.");
                if (player.getCardHand().isEmpty()) {
                    if (DECK.size() < 7) {
                        for (int i = 0; i < DECK.size(); i++) {
                            player.drawCard();
                        }
                        System.out.println("You drew " + DECK.size() + " cards."
                        );
                        System.out.println("That was the last of the deck.");
                    } else {
                        for (int i = 0; i < 7; i++) {
                            player.drawCard();
                        }
                        System.out.println("You drew 7 cards.");
                    }
                } else if (player.getCardHand().size() < 7) {
                    for (int i = 0; i < 7 - player.getCardHand().size(); i++) {
                        player.drawCard();
                    }
                    System.out.println("You drew " + (7 - player.getCardHand()
                            .size()) + " cards.");
                }
                String inputName;
                boolean valid = true;
                do {
                    System.out.println("Which player would you like to ask for "
                            + "cards?");
                    ArrayList<String> playerNames = new ArrayList<>();
                    for (Player player1 : players) {
                        if (!player1.getPlayerID().equals(player.getPlayerID())) 
                        {
                            System.out.println(player1.getPlayerID());
                        }
                        playerNames.add(player1.getPlayerID());
                    }
                    System.out.print(">");
                    boolean check = true;
                    inputName = in.next();
                    do {
                        boolean validcheck = true;
                        if (inputName.equals(player.getPlayerID())) {
                            System.out.println("You can't ask yourself.");
                            check = false;
                        } else if (playerNames.contains(inputName)) {
                            System.out.println(player.getPlayerID()
                                    + ", here are your cards:");
                            player.printCardHand();
                            do {
                                System.out.println("Which card would you like to"
                                        + " ask " + inputName + " for?");
                                System.out.print("For example: 'sevens'.\n>");
                                String inputValue = in.next();
                                if (Values.getValueNames().contains(inputValue)) {
                                    ArrayList<Card> transferCards = players.get(
                                            playerNames.indexOf(inputName))
                                            .hasCard(Values
                                                    .getValueOf(inputValue));
                                    if (transferCards.size() > 0) {
                                        System.out.println(inputName + " has "
                                                + "your card.");
                                        player.giveCards(transferCards);
                                        System.out.println(player.getPlayerID()
                                                + ", you recieved: ");
                                        for (Card transferCard : transferCards) {
                                            System.out.println(transferCard);
                                        }
                                        player.makeMatches();
                                        System.out.println("You have " + player
                                                .getMatches() + " matches.");
                                        System.out.println(player.getPlayerID()
                                                + ", here are your cards now:");
                                        player.printCardHand();
                                        validcheck = false;
                                        check = false;
                                    } else {
                                        System.out.println("Go Fish.");
                                        validcheck = false;
                                        check = false;
                                        valid = false;
                                    }
                                    //the chosen player gives the current player 
                                    //their cards
                                } else {
                                    System.out.println("Invalid card value.");
                                }
                            } while (validcheck);
                        } else {
                            //infinite loop here
                            System.out.println("Invalid name.");
                            check = false;
                        }
                    } while (check);
                } while (valid);
            }
        }
        declareWinner();
    }

    /**
     * When the game is over, use this method to declare and display a winning
     * player.
     */
    public static void declareWinner() {
        Player winner = players.get(0);
        for (Player player : players) {
            if (winner.getMatches() < player.getMatches()) {
                winner = player;
            }
            System.out.println(player.getPlayerID() + " finished with "
                    + player.getMatches() + " matches.");
        }
        System.out.println(winner.getPlayerID() + " is the winner!");
    }

    public void showRules() {
        // TODO - implement Game.showRules
        throw new UnsupportedOperationException();
    }

}//end class
