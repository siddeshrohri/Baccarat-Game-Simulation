// Correctness testing for COMP1721 Coursework 2 (Basic Solution)

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class Basic {
  private static final int DECK_SIZE = 52;
  private static final int SMALL_SHOE_SIZE = 6 * DECK_SIZE;
  private static final int LARGE_SHOE_SIZE = 8 * DECK_SIZE;

  private BaccaratCard aceClubs;
  private BaccaratCard twoClubs;
  private BaccaratCard threeClubs;
  private BaccaratCard aceHearts;
  private BaccaratCard nineDiamonds;
  private BaccaratCard tenHearts;
  private BaccaratCard kingSpades;

  private BaccaratHand hand;
  private BaccaratHand handOneCard;
  private BaccaratHand handTwoCards;

  private Shoe smallShoe;
  private Shoe largeShoe;

  @BeforeEach
  public void perTestSetup() {
    aceClubs = new BaccaratCard(Card.Rank.ACE, Card.Suit.CLUBS);
    twoClubs = new BaccaratCard(Card.Rank.TWO, Card.Suit.CLUBS);
    threeClubs = new BaccaratCard(Card.Rank.THREE, Card.Suit.CLUBS);
    nineDiamonds = new BaccaratCard(Card.Rank.NINE, Card.Suit.DIAMONDS);
    aceHearts = new BaccaratCard(Card.Rank.ACE, Card.Suit.HEARTS);
    tenHearts = new BaccaratCard(Card.Rank.TEN, Card.Suit.HEARTS);
    kingSpades = new BaccaratCard(Card.Rank.KING, Card.Suit.SPADES);

    hand = new BaccaratHand();
    handOneCard = new BaccaratHand();
    handTwoCards = new BaccaratHand();
    handOneCard.add(nineDiamonds);
    handTwoCards.add(nineDiamonds);
    handTwoCards.add(twoClubs);

    smallShoe = new Shoe(6);
    largeShoe = new Shoe(8);
  }

  // BaccaratCard

  @Test
  @DisplayName("BaccaratCard objects are created properly")
  public void cardCreation() {
    assertAll(
      () -> assertThat(aceClubs.getRank(), is(Card.Rank.ACE)),
      () -> assertThat(aceClubs.getSuit(), is(Card.Suit.CLUBS))
    );
  }

  @Test
  @DisplayName("BaccaratCard has the correct string representation")
  public void cardStringRepresentation() {
    assertAll(
      () -> assertThat(aceClubs.toString(), is("A\u2663")),
      () -> assertThat(nineDiamonds.toString(), is("9\u2666")),
      () -> assertThat(tenHearts.toString(), is("T\u2665")),
      () -> assertThat(kingSpades.toString(), is("K\u2660"))
    );
  }

  @Test
  @DisplayName("BaccaratCard objects can be tested for equality")
  public void cardEquality() {
    assertAll(
      () -> assertThat(aceClubs.equals(aceClubs), is(true)),
      () -> assertThat(aceClubs.equals(new BaccaratCard(Card.Rank.ACE, Card.Suit.CLUBS)), is(true)),
      () -> assertThat(aceClubs.equals(twoClubs), is(false)),
      () -> assertThat(aceClubs.equals(aceHearts), is(false)),
      () -> assertThat(aceClubs.equals("Ace of Clubs"), is(false))
    );
  }

  @Test
  @DisplayName("BaccaratCard objects can be compared & ordered")
  public void cardComparison() {
    assertAll(
      () -> assertThat(aceClubs.compareTo(aceClubs), is(0)),
      () -> assertThat(aceClubs.compareTo(twoClubs), lessThan(0)),
      () -> assertThat(twoClubs.compareTo(aceClubs), greaterThan(0)),
      () -> assertThat(aceClubs.compareTo(aceHearts), lessThan(0)),
      () -> assertThat(aceHearts.compareTo(aceClubs), greaterThan(0)),
      () -> assertThat(nineDiamonds.compareTo(aceHearts), lessThan(0)),
      () -> assertThat(tenHearts.compareTo(kingSpades), lessThan(0))
    );
  }

  @Test
  @DisplayName("BaccaratCard values are computed correctly")
  public void cardValue() {
    assertAll(
      () -> assertThat(aceClubs.value(), is(1)),
      () -> assertThat(twoClubs.value(), is(2)),
      () -> assertThat(nineDiamonds.value(), is(9)),
      () -> assertThat(tenHearts.value(), is(0)),
      () -> assertThat(kingSpades.value(), is(0))
    );
  }

  // BaccaratHand

  @Test
  @DisplayName("Empty hand created correctly")
  public void emptyHandCreation() {
    assertThat(hand.size(), is(0));
  }

  @Test
  @DisplayName("Cards added to hand correctly")
  public void addingCardsToHand() {
    assertAll(
      () -> assertThat(handOneCard.size(), is(1)),
      () -> assertThat(handTwoCards.size(), is(2))
    );
  }

  @Test
  @DisplayName("BaccaratHand values computed correctly")
  public void handValue() {
    assertAll(
      () -> assertThat(hand.value(), is(0)),
      () -> assertThat(handOneCard.value(), is(9)),
      () -> assertThat(handTwoCards.value(), is(1))
    );
  }

  @Test
  @DisplayName("Correct detection of whether a BaccaratHand is a 'natural'")
  public void naturalHand() {
    BaccaratHand nat1 = new BaccaratHand();
    nat1.add(new BaccaratCard(Card.Rank.THREE, Card.Suit.HEARTS));
    nat1.add(new BaccaratCard(Card.Rank.FIVE, Card.Suit.CLUBS));

    BaccaratHand nat2 = new BaccaratHand();
    nat2.add(new BaccaratCard(Card.Rank.EIGHT, Card.Suit.SPADES));
    nat2.add(new BaccaratCard(Card.Rank.JACK, Card.Suit.HEARTS));

    assertAll(
      () -> assertThat(hand.isNatural(), is(false)),
      () -> assertThat(handOneCard.isNatural(), is(false)),
      () -> assertThat(handTwoCards.isNatural(), is(false)),
      () -> assertThat(nat1.isNatural(), is(true)),
      () -> assertThat(nat2.isNatural(), is(true))
    );
  }

  @Test
  @DisplayName("BaccaratHand has the correct string representation")
  public void handStringRepresentation() {
    assertAll(
      () -> assertThat(hand.toString(), is("")),
      () -> assertThat(handOneCard.toString(), is("9\u2666")),
      () -> assertThat(handTwoCards.toString(), is("9\u2666 2\u2663"))
    );
  }

  // Shoe

  @Test
  @DisplayName("Shoes are created with the correct numbers of cards")
  public void shoeCreation() {
    assertAll(
      () -> assertThat(smallShoe.size(), is(SMALL_SHOE_SIZE)),
      () -> assertThat(largeShoe.size(), is(LARGE_SHOE_SIZE))
    );
  }

  @Test
  @DisplayName("CardException when creating a Shoe with wrong number of decks")
  public void shoeCreationException() {
    assertAll(
      () -> assertThrows(CardException.class, () -> new Shoe(5)),
      () -> assertThrows(CardException.class, () -> new Shoe(7)),
      () -> assertThrows(CardException.class, () -> new Shoe(9))
    );
  }

  @Test
  @DisplayName("Cards dealt correctly from unshuffled Shoe")
  public void dealFromShoe() {
    Card card1 = smallShoe.deal();
    Card card2 = smallShoe.deal();
    Card card3 = smallShoe.deal();
    assertAll(
      () -> assertThat(smallShoe.size(), is(SMALL_SHOE_SIZE - 3)),
      () -> assertThat(card1 instanceof BaccaratCard, is(true)),
      () -> assertThat(card1, equalTo(aceClubs)),
      () -> assertThat(card2, equalTo(twoClubs)),
      () -> assertThat(card3, equalTo(threeClubs))
    );
  }

  @Test
  @DisplayName("Shoe can be shuffled")
  public void shuffle() {
    smallShoe.shuffle();
    Card[] cards = new Card[] { smallShoe.deal(), smallShoe.deal(), smallShoe.deal() };
    Card[] unshuffled = new Card [] { aceClubs, twoClubs, threeClubs };
    assertThat(cards, not(unshuffled));
  }

  @Test
  @DisplayName("CardException when dealing from empty shoe")
  public void dealFromEmptyShoeException() {
    for (int i = 0; i < SMALL_SHOE_SIZE; ++i) {
      smallShoe.deal();
    }
    assertThrows(CardException.class, () -> smallShoe.deal());
  }
}
