// TODO: Implement the BaccaratCard class in this file
public class BaccaratCard extends Card
{
    //This class extends the class Card
    //Declartion of two private variables rank and suit
    private Rank rank;
    private Suit suit;
    //Constructor for the class
    public BaccaratCard(Rank r, Suit s) 
    {
        //Importing the variables from parent class
        super(r, s);
        //Assigning the values of the parameters to the variables
        this.rank = r;
        //Assigning the values of the parameters to the variables
        this.suit = s;
    }
    //Function for returning the rank
    public Rank getRank() 
    {
        //Returning the rank
        return rank;
    }
    //Function for returning the suit
    public Suit getSuit() 
    {
        //Returning the suit
        return suit;
    }
    //Overriding the function
    @Override
    //Function for the String representation of the suits
    public String toString() 
    {
        //Variable to convert the rank to Strng representation
        String ranktoString = getRank().toString();
        //Checks for the condition of the rank whether it is TEN, JACK, QUEEN or KING
        if (getRank() == Rank.JACK || getRank() == Rank.TEN || 
            getRank() == Rank.KING || getRank() == Rank.QUEEN)
        {
            //The declared variable stores the substring value of the Rank enum
            ranktoString = ranktoString.substring(0, 1);
        }
        //Returns the value in the String form with the Rank as well as the Symbol
        return String.valueOf(getRank().getSymbol()) + getSuit().getSymbol();
    }
    //Function for checkng whether the given cards as equal
    public boolean equals(Object obj) 
    {
        //Condition
        if (obj == this) 
        {
            //Returns true
            return true;
        }
        //Returns the class object and compares it to the class object of BaccaratCard class
        if (obj.getClass() != BaccaratCard.class) 
        {
            //returns false
            return false;
        }
        //Creates an object of BaccaratCard with variable other
        BaccaratCard other = (BaccaratCard) obj;
        //This determines if the two objects are equal 
        return this.getRank() == other.getRank() && this.getSuit() == other.getSuit();
    }
    //Function for comparing the cards to determine their ordering in a sorted sequence
    public int compareTo(BaccaratCard other) 
    {
        //Checks if the suits of the two cards are different
        if (this.suit != other.suit) 
        {
            //Returning the result of calling the method of the Suit enum
            return this.suit.compareTo(other.suit);
        }
        //Returning the result of calling the method of the Rank enum
        return this.rank.compareTo(other.rank);
    }
    //Function for accepting the value of the card
    public int value() 
    {
        //Switch condition to check the rank of the card
        switch (rank) 
        {
            //Returns the value according to the value of card
            case ACE:
                return 1;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            //Returns the value 0 if the card has a value more than NINE
            default:
                return 0;
        }
    }
}   