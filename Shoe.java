// TODO: Implement the Shoe class in this file
import java.util.LinkedList;
import java.util.Collections;
public class Shoe 
{
    //Declaration of private variable using LinkedList
    private LinkedList<Card> cards;
    //Constructor of class Show with a parameter
    //Throws a card exception on error
    public Shoe(int decks) throws CardException 
    {
        //Checks for the number of decks
        if (decks != 6 && decks != 8) 
        {
            //Throws a card exception if the conditon is true
            throw new CardException("Invalid number of decks: " + decks);
        }
        //Stores the values of the List in a variabale
        cards = new LinkedList<>();
        //Looping through a number of decks
        for (int i = 0; i < decks; i++) 
        {
            //Looping through the values of Suit
            for (BaccaratCard.Suit suit : BaccaratCard.Suit.values()) 
            {
                //Looping through the values of the Rank
                for (BaccaratCard.Rank rank : BaccaratCard.Rank.values()) 
                {
                    //Creating an object of BaccaratCard with rank and suit
                    BaccaratCard card = new BaccaratCard(rank, suit);
                    //Adds the value of the rank and suit to the list variable cards
                    cards.add(card);
                }
            }
        }
    }
    //Function to return the number of cards in the list
    public int size() 
    {
        //Returns the number of cards in the list
        return cards.size();
    }
    //Function to return the shuffled deck of cards
    public void shuffle() 
    {
        //Returns the shuffled cards
        Collections.shuffle(cards);
    }
    //Function to deal the cards
    //Throws a CardException on error
    public Card deal() throws CardException 
    {
        //Checks for an empty list
        if (cards.isEmpty()) 
        {
            //Throws a CardException if the above condition is true
            throw new CardException("The shoe is empty");
        }
        //If the list is non-empty, then it removes the first card
        else
        {
            //Object to store the first card
            Card card = cards.get(0);
            //Removes the first card
            cards.remove(0);
            //Returns the removed card
            return card;
        }
    }
}