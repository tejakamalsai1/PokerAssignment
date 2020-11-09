package poker;

import java.util.ArrayList;

public class PokerRank {

	private ArrayList<PokerCard> cards = new ArrayList<PokerCard>(); // The cards in this hand.

	public static final int NOTHING = 0;
	public static final int PAIR = 1;
	public static final int TWO_PAIR = 2;
	public static final int TRIPLE = 3;
	public static final int STRAIGHT = 4;
	public static final int FLUSH = 5;
	public static final int FULL_HOUSE = 6;
	public static final int FOUR_OF_A_KIND = 7;
	public static final int STRAIGHT_FLUSH = 8;
	public static final int ROYAL_FLUSH = 9;

	private int rank = -1;
	private String description;
	private String longDescription;

	public PokerRank(ArrayList<PokerCard> cards) {
		if (cards != null) {
			for (PokerCard c : cards)
				add(c);
		}
	}

	public void add(PokerCard card) {
		if (card == null)
			throw new IllegalArgumentException("Cards can't be null");
		if (card.getSuit() == PokerCard.JOKER)
			throw new IllegalArgumentException("It does not support jokers.");
		if (cards.size() == 5)
			throw new IllegalArgumentException("It does not support hands with more than five cards.");
		cards.add(card);
		rank = -1;
	}

	public int getRank() {
		if (rank == -1)
			computeRank();
		return rank;
	}

	public String getDescription() {
		if (rank == -1)
			computeRank();
		return description;
	}

	public String getLongDescription() {
		if (rank == -1)
			computeRank();
		return longDescription;
	}

	public int getHandType() {
		if (rank == -1)
			computeRank();
		return rank >> 20;
	}

	public String getHandTypeAsString() {
		if (cards.size() == 0)
			return "Empty Hand";
		int type = getHandType();
		if (type == PAIR)
			return "Pair";
		if (type == TWO_PAIR)
			return "Two pairs";
		if (type == TRIPLE)
			return "Triple";
		if (type == STRAIGHT)
			return "Straight";
		if (type == FLUSH)
			return "Flush";
		if (type == FULL_HOUSE)
			return "Full House";
		if (type == FOUR_OF_A_KIND)
			return "Four of a kind";
		if (type == STRAIGHT_FLUSH)
			return "Straight Flush";
		if (type == ROYAL_FLUSH)
			return "Royal Flush";
		return "Nothing";
	}

	public ArrayList<PokerCard> getCards() {
		if (rank == -1)
			computeRank();
		return new ArrayList<PokerCard>(cards);
	}

	public String toString() {
		return getDescription();
	}

	private String valueName(PokerCard c) {
		switch (c.getValue()) {
		case 2:
			return "Two";
		case 3:
			return "Three";
		case 4:
			return "Four";
		case 5:
			return "Five";
		case 6:
			return "Six";
		case 7:
			return "Seven";
		case 8:
			return "Eight";
		case 9:
			return "Nine";
		case 10:
			return "Ten";
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		case 13:
			return "King";
		default:
			return "Ace";
		}
	}

	private String pluralValueName(PokerCard c) {
		if (c.getValue() == 6)
			return "Sixes";
		else
			return valueName(c) + "s";
	}

	private String cardValueNames() {
		StringBuffer s = new StringBuffer(valueName(cards.get(0)));
		for (int i = 1; i < cards.size(); i++) {
			s.append(',');
			s.append(valueName(cards.get(i)));
		}
		return s.toString();
	}

	private void computeRank() {
		if (cards.size() == 0) {
			rank = 0;
			description = longDescription = "Empty Hand";
			return;
		}

		ArrayList<PokerCard> newCards = new ArrayList<PokerCard>();
		while (cards.size() > 0) {
			PokerCard maxCard = cards.get(0);
			for (int i = 1; i < cards.size(); i++)
				if (cards.get(i).getValue() > maxCard.getValue()
						|| cards.get(i).getValue() == maxCard.getValue() && cards.get(i).getSuit() > maxCard.getSuit())
					maxCard = cards.get(i);
			cards.remove(maxCard);
			newCards.add(maxCard);
		}
		cards = newCards;
		try {
			boolean isFlush = false;
			if (cards.size() == 5) {
				isFlush = cards.get(0).getSuit() == cards.get(1).getSuit()
						&& cards.get(1).getSuit() == cards.get(2).getSuit()
						&& cards.get(1).getSuit() == cards.get(3).getSuit()
						&& cards.get(1).getSuit() == cards.get(4).getSuit();
			}
			boolean isStraight = false;
			if (cards.size() == 5) {
				if (cards.get(0).getValue() == PokerCard.ACE && cards.get(1).getValue() == 5
						&& cards.get(2).getValue() == 4 && cards.get(3).getValue() == 3
						&& cards.get(4).getValue() == 2) {
					isStraight = true;
					cards.add(cards.remove(0)); // Move the ace to the end, by removing it then adding it.
				} else { // An ordinary straight.
					isStraight = cards.get(0).getValue() == cards.get(1).getValue() + 1
							&& cards.get(1).getValue() == cards.get(2).getValue() + 1
							&& cards.get(2).getValue() == cards.get(3).getValue() + 1
							&& cards.get(3).getValue() == cards.get(4).getValue() + 1;
				}
			}
			if (isFlush) {
				if (isStraight) {
					if (cards.get(0).getValue() == PokerCard.ACE) {
						rank = ROYAL_FLUSH;
						description = longDescription = "Royal Flush";
					} else {
						rank = STRAIGHT_FLUSH;
						description = longDescription = valueName(cards.get(0)) + "-high Straight Flush";
					}
				} else {
					rank = FLUSH;
					description = "Flush";
					longDescription = "Flush (" + cardValueNames() + ")";
				}
				return;
			}
			if (isStraight) {
				rank = STRAIGHT;
				description = longDescription = valueName(cards.get(0)) + "-high Straight";
				return;
			}

			if (cards.size() >= 4) {
				if (cards.get(0).getValue() == cards.get(1).getValue()
						&& cards.get(1).getValue() == cards.get(2).getValue()
						&& cards.get(2).getValue() == cards.get(3).getValue()) {
					rank = FOUR_OF_A_KIND;
					description = longDescription = "Four " + pluralValueName(cards.get(0));
					if (cards.size() == 5)
						longDescription = description + " (plus " + valueName(cards.get(4)) + ")";
					return;
				}
			}
			if (cards.size() == 5 && cards.get(1).getValue() == cards.get(2).getValue()
					&& cards.get(2).getValue() == cards.get(3).getValue()
					&& cards.get(3).getValue() == cards.get(4).getValue()) {
				cards.add(cards.remove(0)); // Move first card -- not part of the Quad -- to the end.
				rank = FOUR_OF_A_KIND;
				description = "Four " + pluralValueName(cards.get(0));
				longDescription = description + " (plus " + valueName(cards.get(4)) + ")";
				return;
			}

			int tripleValue = 0; // If greater than 0, then there is a triple with this value.
			int tripleLocation = -1; // If tripleValue is greater than 0, this gives the index in cards of the first
										// card in the triple.
			for (int i = 0; i <= cards.size() - 3; i++) {
				if (cards.get(i).getValue() == cards.get(i + 1).getValue()
						&& cards.get(i + 1).getValue() == cards.get(i + 2).getValue()) {
					tripleLocation = i;
					tripleValue = cards.get(i).getValue();
					break;
				}
			}
			int pairValue1 = 0; // If greater than 0, then there is a pair with this value. If two pairs, this
								// is the first (so highest) value.
			int pairLoc1 = -1; // If pairValue1 is greater than 0, then this is the index in cards of the first
								// card in the pair.
			int pairValue2 = 0; // If greater than 0, there are two pairs and this is the value of the cards in
								// the second pair.
			int pairLoc2 = -1; // If pairValue2 is greater than 0, this is the index in cards of the first card
								// in the second pair.
			for (int i = 0; i <= cards.size() - 2; i++) {
				// Look for a pair at position i. Be careful not to count two cards that
				// are part of a triple as being a pair
				if (cards.get(i).getValue() == cards.get(i + 1).getValue() && cards.get(i).getValue() != tripleValue) {
					// Found a pair at position i. Record it and look a second pair later in the
					// hand.
					pairValue1 = cards.get(i).getValue();
					pairLoc1 = i;
					for (int j = i + 2; j <= cards.size() - 2; j++) {
						// Found a second pair.
						if (cards.get(j).getValue() == cards.get(j + 1).getValue()
								&& cards.get(j).getValue() != tripleValue) {
							pairValue2 = cards.get(j).getValue();
							pairLoc2 = j;
							break;
						}
					}
					break;
				}
			}

			if (tripleValue == 0 && pairValue1 == 0) {
				// No triple or pair in the hand. The hand is ranked primarily on its high card.
				rank = NOTHING;
				description = "High Card (" + valueName(cards.get(0)) + ")";
				longDescription = "High Card (" + cardValueNames() + ")";
				return;
			}

			if (tripleValue > 0) {
				// There is a triple.
				for (int i = 0; i < tripleLocation; i++) {
					cards.add(cards.remove(0));
				}
				if (pairValue1 > 0) {
					rank = FULL_HOUSE;
					description = longDescription = "Full House, " + pluralValueName(cards.get(0)) + " and "
							+ pluralValueName(cards.get(4));
					return;
				} else {
					rank = TRIPLE;
					description = longDescription = "Three " + pluralValueName(cards.get(0));
					if (cards.size() == 4)
						longDescription = description + " (plus " + valueName(cards.get(3)) + ")";
					else if (cards.size() == 5)
						longDescription = description + " (plus " + valueName(cards.get(3)) + " and "
								+ valueName(cards.get(4)) + ")";
					return;
				}
			}

			if (pairLoc1 > 0) {
				PokerCard p2 = cards.remove(pairLoc1 + 1);
				PokerCard p1 = cards.remove(pairLoc1);
				cards.add(0, p2);
				cards.add(0, p1);
			}

			if (pairValue2 == 0) { // There was only one pair.
				rank = PAIR;
				description = longDescription = "Pair of " + pluralValueName(cards.get(0));
				if (cards.size() == 5)
					longDescription = description + " (plus " + valueName(cards.get(2)) + "," + valueName(cards.get(3))
							+ "," + valueName(cards.get(4)) + ")";
				else if (cards.size() == 4)
					longDescription = description + " (plus " + valueName(cards.get(2)) + "," + valueName(cards.get(3))
							+ ")";
				else if (cards.size() == 3)
					longDescription = description + " (plus " + valueName(cards.get(2)) + ")";
				return;
			}

			if (pairLoc2 > 2) {
				PokerCard p2 = cards.remove(pairLoc2 + 1);
				PokerCard p1 = cards.remove(pairLoc2);
				cards.add(2, p2);
				cards.add(2, p1);
			}

			rank = TWO_PAIR;
			description = longDescription = "Two Pairs, " + pluralValueName(cards.get(0)) + " and "
					+ pluralValueName(cards.get(2));
			if (cards.size() == 5)
				longDescription = description + " (plus " + valueName(cards.get(4)) + ")";

		} finally {

			rank <<= 20;
			for (int i = 0; i < cards.size(); i++) {
				rank |= cards.get(i).getValue() << 4 * (4 - i);
			}

		}

	}

}