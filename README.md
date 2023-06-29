# Baccarat-Game-Simulation
This project is a simulation of the card game Baccarat, specifically the 'punto banco' variant. The simulation is implemented in Java and consists of several classes that represent different aspects of the game.

1. Introduction
   
The objective of this project is to implement a simulation of the card game Baccarat, specifically the 'punto banco' variant. The implementation should adhere to the provided Java classes: Card, CardCollection, and CardException. These classes serve as the foundation for the solution.

2. Basic Solution
   
The basic solution requires implementing the following classes:

BaccaratCard: Represents a single playing card in Baccarat.
BaccaratHand: Represents a hand of cards in Baccarat.
Shoe: Represents the 'shoe' from which cards are dealt in Baccarat.
Additionally, a small program should be implemented in Baccarat.java that utilizes the above classes

3. Main Program
   
The main program in Baccarat.java should perform the following steps:

Create a shoe containing 6 full decks of cards and shuffle it.
Create hands for the banker and a single player.
Deal a card to the player and then the banker, repeating this process until each hand has two cards.
Display the contents and value of each hand.
Inform the user if the player or banker has a Natural.
To run the program, use the following command:

4 Interactive Mode

Running the program with the command line argument -i or --interactive enables interactive mode. In this mode, the program continues playing as long as there are at least six cards remaining in the shoe and the user indicates the desire to continue. At the end of each round, the program asks the user if they want to play another round. Entering 'y' or 'Y' continues the game, while any other response ends the game. After the game ends, the program displays the number of rounds played, the number of player wins, banker wins, and tied rounds before terminating.

5 Non-Interactive Mode

Running the program without command line arguments activates non-interactive mode. In this mode, the program plays rounds of the game continuously until there are fewer than six cards remaining in the shoe. Then, it displays the end-of-game statistics, including the number of rounds played, the number of player wins, banker wins, and tied rounds, before terminating.
