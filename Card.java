/**
 * Representation of a playing card.
 *
 * <p>Provided for use in COMP1721 Coursework 2.</p>
 *
 * @author Nick Efford
 */
public class Card implements Comparable<Card> {

  public enum Rank {
    ACE('A'), TWO('2'), THREE('3'), FOUR('4'), FIVE('5'),
    SIX('6'), SEVEN('7'), EIGHT('8'), NINE('9'), TEN('T'),
    JACK('J'), QUEEN('Q'), KING('K');

    private final char symbol;

    Rank(char s) { symbol = s; }

    public char getSymbol() { return symbol; }

    @Override
    public String toString() {
      return name().charAt(0) + name().substring(1).toLowerCase();
    }
  }

  public enum Suit {
    CLUBS('\u2663'), DIAMONDS('\u2666'),
    HEARTS('\u2665'), SPADES('\u2660');

    private final char symbol;

    Suit(char s) {
      symbol = s;
    }

    public char getSymbol() { return symbol; }

    @Override
    public String toString() {
      return name().charAt(0) + name().substring(1).toLowerCase();
    }
  }

  private Rank rank;
  private Suit suit;

  /**
   * Creates a Card object.
   *
   * @param r Rank of the card
   * @param s Suit of the card
   */
  public Card(Rank r, Suit s) {
    rank = r;
    suit = s;
  }

  /**
   * Creates a Card object, given its name as a string.
   *
   * <p>Name can either be given in full - e.g., "Ace of Clubs" - or
   * be abbreviated to a two-character code - e.g., "AC".</p>
   *
   * @param name Name of card
   * @throws CardException if string is invalid
   */
  public Card(String name) {
    if (name.length() > 2) {
      parseLongName(name);
    }
    else {
      parseRank(name);
      parseSuit(name);
    }
  }

  private void parseLongName(String name) {
    String[] parts = name.split("\\s+");
    if (parts.length == 3 && parts[1].toLowerCase().equals("of")) {
      rank = Rank.valueOf(parts[0].toUpperCase());
      suit = Suit.valueOf(parts[2].toUpperCase());
    }
    else {
      throw new CardException("Invalid card name format");
    }
  }

  private void parseRank(String name) {
    for (Rank r: Rank.values()) {
      if (r.getSymbol() == name.charAt(0)) {
        rank = r;
        break;
      }
    }

    if (rank == null) {
      throw new CardException("Unrecognised rank");
    }
  }

  private void parseSuit(String name) {
    for (Suit s: Suit.values()) {
      if (s.getSymbol() == name.charAt(1)) {
        suit = s;
        break;
      }
    }

    if (suit == null) {
      throw new CardException("Unrecognised suit");
    }
  }

  /**
   * Provides the rank of this card.
   *
   * @return The rank
   */
  public Rank getRank() {
    return rank;
  }

  /**
   * Provides the suit of this card.
   *
   * @return The suit
   */
  public Suit getSuit() {
    return suit;
  }

  /**
   * Computes the hash code for this card.
   *
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return java.util.Objects.hash(rank, suit);
  }

  /**
   * Tests whether this card is equal to another object.
   *
   * @param thing Object with which this card is being compared
   * @return true if thing is equal to this card, false otherwise
   */
  @Override
  public boolean equals(Object thing) {
    if (thing == this) {
      return true;
    }
    else if (thing instanceof Card) {
      final Card card = (Card) thing;
      return rank == card.rank && suit == card.suit;
    }
    return false;
  }

  /**
   * Creates a two-character string representation of this card.
   *
   * <p>The first character represents rank, the second represents suit.</p>
   *
   * @return String representation of this card
   */
  @Override
  public String toString() {
    return String.format("%c%c", rank.getSymbol(), suit.getSymbol());
  }

  /**
   * Generates this card's full name - e.g., "Ace of Spades".
   *
   * @return Full name of this card, as a string
   */
  public String fullName() {
    return String.format("%s of %s", rank, suit);
  }

  /**
   * Compares this card to another, using their natural ordering
   * (by suit, then by rank).
   *
   * @return A negative integer if this card comes before the other, 0 if
   *   they are the same, a positive integer if this card comes after
   */
  @Override
  public int compareTo(Card other) {
    int difference = suit.compareTo(other.suit);

    if (difference == 0) {
      difference = rank.compareTo(other.rank);
    }

    return difference;
  }

  /**
   * Computes the value of this card.
   *
   * <p>Value is based on rank and disregards suit. Aces score 1
   * and picture cards all score 10.</p>
   *
   * @return Card value
   */
  public int value() {
    return Math.min(rank.ordinal() + 1, 10);
  }
}
