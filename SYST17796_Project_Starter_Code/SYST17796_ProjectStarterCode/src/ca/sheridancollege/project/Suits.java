package ca.sheridancollege.project;

/**
 *
 * @author Ryan Henry
 */
public enum Suits {
    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    SPADES("Spades"),
    CLUBS("Clubs");
    
    private final String suitName;
    
    private Suits(String suitName){
        this.suitName = suitName;
    }
    
}
