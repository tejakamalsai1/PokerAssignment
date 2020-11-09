package poker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class PokerCard implements Serializable {

	public final static int SPADES = 0;
	public final static int HEARTS = 1;
	public final static int DIAMONDS = 2;
	public final static int CLUBS = 3;
	public final static int JOKER = 4;

	public final static int ACE = 14;
	public final static int JACK = 11;
	public final static int QUEEN = 12;
	public final static int KING = 13;

	static ArrayList<String> suitelist = new ArrayList<String>(Arrays.asList("S", "H", "D", "C", "J"));
	static ArrayList<String> valueList = new ArrayList<String>(Arrays.asList("A", "K", "Q", "J"));
	private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	private final int suit;
	private final int value;

	public PokerCard() {
		suit = JOKER;
		value = 1;
	}

	public PokerCard(String cardValue) {
		int theValue = getCardAsInt(cardValue.substring(0, cardValue.length() - 1).toUpperCase());
		int theSuit = getSuitValueAsInt(cardValue.substring(cardValue.length() - 1).toUpperCase());
		if (theSuit != SPADES && theSuit != HEARTS && theSuit != DIAMONDS && theSuit != CLUBS && theSuit != JOKER)
			throw new IllegalArgumentException("Illegal playing card suit");
		if (theSuit != JOKER && (theValue < 1 || theValue > 15))
			throw new IllegalArgumentException("Illegal playing card value");
		value = theValue;
		suit = theSuit;
	}

	public int getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}

	public String getSuitAsString() {
		switch (suit) {
		case SPADES:
			return "Spades";
		case HEARTS:
			return "Hearts";
		case DIAMONDS:
			return "Diamonds";
		case CLUBS:
			return "Clubs";
		default:
			return "Joker";
		}
	}

	public int getCardAsInt(String value) {

		if (isNumeric(value)) {
			return Integer.parseInt(value);
		} else {
			if (!valueList.contains(value)) {
				throw new IllegalArgumentException("Illegal playing card value" + "");
			} else {
				switch (value) {
				case "A":
					return 14;
				case "K":
					return 13;
				case "Q":
					return 12;
				case "J":
					return 11;
				default:
					return 14;
				}
			}
		}
	}

	public int getSuitValueAsInt(String suit) {
		if (!suitelist.contains(suit)) {
			throw new IllegalArgumentException("Illegal playing card suit");
		} else {
			switch (suit) {
			case "S":
				return 0;
			case "H":
				return 1;
			case "D":
				return 2;
			case "C":
				return 3;
			case "J":
				return 4;
			default:
				return 4;
			}
		}
	}

	public String getValueAsString() {
		if (suit == JOKER)
			return "" + value;
		else {
			switch (value) {
			case 2:
				return "2";
			case 3:
				return "3";
			case 4:
				return "4";
			case 5:
				return "5";
			case 6:
				return "6";
			case 7:
				return "7";
			case 8:
				return "8";
			case 9:
				return "9";
			case 10:
				return "10";
			case 11:
				return "Jack";
			case 12:
				return "Queen";
			case 13:
				return "King";
			case 14:
				return "Ace";
			default:
				return "Ace";
			}
		}
	}

	public int compareTo(PokerCard c) {
		int result = 0;

		if (this.getValue() > c.getValue()) {
			result = 1;
		} else if (c.getValue() > this.getValue()) {
			result = -1;
		} else {
			// Let it be.
		}

		return result;
	}

	public String toString() {
		if (suit == JOKER) {
			if (value == 1)
				return "Joker";
			else
				return "Joker #" + value;
		} else
			return getValueAsString() + " of " + getSuitAsString();
	}

	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof PokerCard))
			return false;
		PokerCard that = (PokerCard) obj;
		return (this.value == that.value);
	}

	public boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		return pattern.matcher(strNum).matches();
	}
}
