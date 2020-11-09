package poker;

import java.util.ArrayList;
import java.util.Scanner;

public class PokerMain {

	public static void main(String[] args) {
		ArrayList<PokerCard> cards = new ArrayList<PokerCard>();
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 5; i++) {
			System.out.print("Enter your " + (i + 1) + " Cardvalue: ");
			String cardValue = scanner.next();
			if (cardValue.length() < 2 && cardValue.length() > 3) {
				throw new IllegalArgumentException("Illegal playing card");
			} else {
				cards.add(new PokerCard(cardValue));
			}
		}
		System.out.println(new PokerRank(cards).getLongDescription());
		System.out.println(new PokerRank(cards).getDescription());
		System.out.println(new PokerRank(cards).getCards());
		System.out.println(new PokerRank(cards).getHandType());
		System.out.println(new PokerRank(cards).getHandTypeAsString());
		
	}

}
