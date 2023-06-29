// TODO: Implement the BaccaratHand class in this file
import java.util.ArrayList;
import java.util.List;

public class BaccaratHand 
{
    //Declaration of a private variable to store the cards using LinkedList
    private List<BaccaratCard> cards;
    //Constructor for BaccaratHand
    public BaccaratHand() 
    {
        //Store the list in the variable cards
        cards = new ArrayList<>();
    }
    //Function for number of cards in the list
    public int size() 
    {
        //Returns the number of cards in the list
        return cards.size();
    }
    //Parameterized Function to add the cards in the list
    public void add(BaccaratCard card) 
    {
        //Adds the cards in the list
        cards.add(card);
    }
    //Function for the value of the card 
    public int value()
    {
        //Variable to store the value of the card
        int total = 0;
        //Looping through the cards
        for (BaccaratCard card : cards) 
        {
            //Stores the values the values of the cards
            int sum = 0;
            //Switch condition to check the rank of the card
            switch (card.getRank()) 
            {
                //Stores the value in sum according to the value of card
                case ACE:
                    sum = 1;
                    break;
                case TWO:
                    sum = 2;
                    break;
                case THREE:
                    sum = 3;
                    break;
                case FOUR:
                    sum = 4;
                    break;
                case FIVE:
                    sum = 5;
                    break;
                case SIX:
                    sum = 6;
                    break;
                case SEVEN:
                    sum = 7;
                    break;
                case EIGHT:
                    sum = 8;
                    break;
                case NINE:
                    sum = 9;
                    break;
                case TEN:
                    sum = 0;
                    break;
                //Stores the value 0 if the card has a value more than NINE
                default:
                    sum = 0;
                    break;
            }
            //Stores the final value of the card in the variable total
            total += sum;
        }
        //Returns the value
        return total % 10;
    }
    //Function to check for the conditon of the hand being a Natural
    public boolean isNatural() 
    {
        //Returns the value to check for the conditon of the hand being a Natural
        return cards.size() == 2 && (value() == 8 || value() == 9);
    }
    //Overriding the function
    @Override
    //Function for building the String representation of the suits
    public String toString() 
    {
        //Creating an object of StringBuilder to append the values
        StringBuilder sb = new StringBuilder();
        //Looping through the number of cards
        for(int i=0; i<cards.size(); i++) 
        {
            //Appends the card to the StringBuilder object
            sb.append(cards.get(i).toString()).append(" ");
        }
        //Returns the string representation of the card by removing whitespaces
        return sb.toString().trim();
    }
}