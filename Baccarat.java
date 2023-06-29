// TODO: Implement the Baccarat class in this file
import java.util.Scanner;

public class Baccarat
{
    //Declaring a main function
    public static void main(String[] args) 
    {
        //Creation of a scanner object to accept values
        Scanner scanner = new Scanner(System.in);
        //Number of decks
        int decks = 6;
        //Checks for the condition to be interactive
        boolean interactive = false;
        //Checks for which argument is accepted, whether it is interactive or non-interactive
        if (args.length > 0 && (args[0].equals("--interactive") || args[0].equals("-i" ))) 
        {
            //If the above condition is true, the value of the variable is true
            interactive = true;
        }
        //Calling the Game function
        game(decks, interactive, scanner);
    }
    //Declaration of a function to play the game
    public static void game(int decks, boolean interactive, Scanner scanner) 
    {
        //Creating a shoe contaning 6 full decks of cards
        Shoe shoe = new Shoe(decks);
        //Shuffle the cards
        shoe.shuffle();
        //Declaring the variables with intitial value 0
        int roundsPlayed = 0;
        //Declaring the variables with intitial value 0
        int ties = 0;
        //Declaring the variables with intitial value 0
        int bankerwins = 0;
        //Declaring the variables with intitial value 0
        int playerwins = 0;
        //Playing until the user decides to stop or there are not enough cards in the shoe
        boolean continuePlay = true;
        //Loop to continue playing according to the user
        while (continuePlay) 
        {
            //Incrementing the number of rounds played
            roundsPlayed++;
            //Creating object hands for the Banker and the Player
            BaccaratHand player = new BaccaratHand();
            BaccaratHand banker = new BaccaratHand();
            //Method to deal cards to the Player and Banker
            deals(shoe, player, banker);
            // //Printing the round info
            roundinfo(roundsPlayed, player, banker);
            //Stores the value of the Player
            int playervalue = player.value();
            //Stores the value of the Banker
            int bankervalue = banker.value();
            //Checks for the value of the Player
            if (playervalue <= 5) 
            {
                //If the condition is true then deal a third card to player
                player.add((BaccaratCard) shoe.deal());
                //Prints the statement
                System.out.println("Dealing third card to player...");
                //Displays the card dealt to the Player along with the rank and suit
                System.out.println("Player: " + player.toString() + " = " + player.value());
                //Displays the card dealt to the Player along with the rank and suit
                System.out.println("Banker: " + banker.toString() + " = " + banker.value());
            }
            // Deal third card to banker
            int bankerThirdCardValue = 0;
            if (player.size() == 3) 
            {
                switch (bankervalue) 
                {
                    case 0:
                    case 1:
                    case 2:
                        banker.add((BaccaratCard) shoe.deal());
                        break;
                    case 3:
                        if (playervalue != 8) 
                        {
                            banker.add((BaccaratCard) shoe.deal());
                        }
                        break;
                    case 4:
                        if (playervalue >= 2 && playervalue <= 7) 
                        {

                            banker.add((BaccaratCard) shoe.deal());
                        }
                        break;
                    case 5:
                        if (playervalue >= 4 && playervalue <= 7) 
                        {
                            banker.add((BaccaratCard) shoe.deal());
                        }
                        break;
                    case 6:
                        if (playervalue == 6 || playervalue == 7) 
                        {
                            banker.add((BaccaratCard) shoe.deal());
                        }
                        break;
                    default:
                        break;
                }
            } 
            else if (bankervalue <= 5) 
            {
                banker.add((BaccaratCard) shoe.deal());
            }

            System.out.println("Dealing third card to banker...");
            System.out.println("Player: " + player.toString() + " = " + player.value());
            System.out.println("Banker: " + banker.toString() + " = " + banker.value());

            //Stores the value of the Player
            playervalue = player.value();
            //Stores the value of the Banker
            bankervalue = banker.value();
            if (playervalue > bankervalue) 
            {
                //if the above condition is true
                //Increments the Player wins 
                playerwins++;
                //Prints the statement
                System.out.println("Player win!");
            }
            //Checks whether the value of the Player is lesser than Banker
            else if (playervalue < bankervalue) 
            {
                //if the above condition is true
                //Increments the Banker wins 
                bankerwins++;

                System.out.println("Banker win!");
            }
            //If both dont win then it is a tie
            else 
            {
                //Increments the tie
                ties++;
                //Prints the statement
                System.out.println("Tie");
            }
            //To ask to continue to play the game
            continuePlay = continues(interactive, scanner, shoe);
        }
        //Prints the results of the rounds
        results(roundsPlayed, playerwins, bankerwins, ties);
    }
    //Deals the cards
    public static void deals(Shoe shoe, BaccaratHand player, BaccaratHand banker) 
    {
        //Looping to deal cards to the Banker and the Player
        for (int i = 0; i < 2; i++) 
        {
            //Dealing card to Player
            player.add((BaccaratCard) shoe.deal());
            //Dealing card to Banker
            banker.add((BaccaratCard) shoe.deal());
        }
    }
    //Prints the round info
    public static void roundinfo(int roundsPlayed, BaccaratHand player, BaccaratHand banker) 
    {
        //Prints a new line
        System.out.println();
        //Prints the round number
        System.out.println("Round " + roundsPlayed);
        //Prints the value of the player along with card and suit
        System.out.println("Player: " + player.toString() + " = " + player.value());
        //Prints the value of the banker along with card and suit
        System.out.println("Banker: " + banker.toString() + " = " + banker.value());
    }
    //Checks to continue the play
    public static boolean continues(boolean interactive, Scanner scanner, Shoe shoe) 
    {
        //Checks whether the player wants to continue
        if (interactive) 
        {
            //prints the statement
            System.out.print("Another round? (y/n): ");
            //Takes the input from the user
            String input = scanner.next();
            //Returns the input if "y" is entered
            return input.toLowerCase().startsWith("y");
        }
        //If the number of cards are not enough 
        else 
        {
            //Returns the value if the number of cards are not enough
            return shoe.size() >= 6;
        }
    }

    public static void results(int roundsPlayed, int playerwins, int bankerwins, int ties) 
    {
        //Displaying the final counts 
        System.out.println();
        //Prints the number of rounds played
        System.out.println(roundsPlayed + " rounds played");
        //Prints the number of Player wins
        System.out.println(playerwins + " player wins");
        //Prints the number of Banker wins
        System.out.println(bankerwins + " banker wins");
        //Prints the number of Tied rounds
        System.out.println(ties + " ties");
    }
}