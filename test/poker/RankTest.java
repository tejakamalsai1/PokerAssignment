package poker;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

public class RankTest extends TestCase {
	private ArrayList<PokerCard> royalFlush;
	private ArrayList<PokerCard> strigtFlush;
	private ArrayList<PokerCard> fourOfKind;
	private ArrayList<PokerCard> fullHouse;
	private ArrayList<PokerCard> flush;
	private ArrayList<PokerCard> stright;
	private ArrayList<PokerCard> threeOfKind;
	private ArrayList<PokerCard> twoPair;
	private ArrayList<PokerCard> pair;

	private PokerRank royalFlushRank;
	private PokerRank strightFlushRank;
	private PokerRank fourOfKindRank;
	private PokerRank fullHouseRank;
	private PokerRank flushRank;
	private PokerRank strightRank;
	private PokerRank threeOfKindRank;
	private PokerRank twoPairRank;
	private PokerRank pairRank;

	public RankTest() {
		royalFlush = new ArrayList<>();
		royalFlush.add(new PokerCard("AS"));
		royalFlush.add(new PokerCard("KS"));
		royalFlush.add(new PokerCard("QS"));
		royalFlush.add(new PokerCard("JS"));
		royalFlush.add(new PokerCard("10S"));

		strigtFlush = new ArrayList<>();
		strigtFlush.add(new PokerCard("KS"));
		strigtFlush.add(new PokerCard("QS"));
		strigtFlush.add(new PokerCard("JS"));
		strigtFlush.add(new PokerCard("10S"));
		strigtFlush.add(new PokerCard("9S"));

		fourOfKind = new ArrayList<>();
		fourOfKind.add(new PokerCard("KS"));
		fourOfKind.add(new PokerCard("QD"));
		fourOfKind.add(new PokerCard("QC"));
		fourOfKind.add(new PokerCard("QS"));
		fourOfKind.add(new PokerCard("QH"));

		fullHouse = new ArrayList<>();
		fullHouse.add(new PokerCard("JS"));
		fullHouse.add(new PokerCard("JD"));
		fullHouse.add(new PokerCard("QC"));
		fullHouse.add(new PokerCard("QS"));
		fullHouse.add(new PokerCard("JH"));

		flush = new ArrayList<>();
		flush.add(new PokerCard("JH"));
		flush.add(new PokerCard("10H"));
		flush.add(new PokerCard("QH"));
		flush.add(new PokerCard("5H"));
		flush.add(new PokerCard("4H"));

		stright = new ArrayList<>();
		stright.add(new PokerCard("10C"));
		stright.add(new PokerCard("KS"));
		stright.add(new PokerCard("QD"));
		stright.add(new PokerCard("9H"));
		stright.add(new PokerCard("JS"));

		threeOfKind = new ArrayList<>();
		threeOfKind.add(new PokerCard("KS"));
		threeOfKind.add(new PokerCard("10D"));
		threeOfKind.add(new PokerCard("QC"));
		threeOfKind.add(new PokerCard("QS"));
		threeOfKind.add(new PokerCard("QH"));

		twoPair = new ArrayList<>();
		twoPair.add(new PokerCard("10S"));
		twoPair.add(new PokerCard("10D"));
		twoPair.add(new PokerCard("JC"));
		twoPair.add(new PokerCard("JS"));
		twoPair.add(new PokerCard("QH"));

		pair = new ArrayList<>();
		pair.add(new PokerCard("5S"));
		pair.add(new PokerCard("10D"));
		pair.add(new PokerCard("9C"));
		pair.add(new PokerCard("QS"));
		pair.add(new PokerCard("QH"));
		

		royalFlushRank = new PokerRank(royalFlush);
		strightFlushRank = new PokerRank(strigtFlush);
		fourOfKindRank = new PokerRank(fourOfKind);
		fullHouseRank = new PokerRank(fullHouse);
		flushRank = new PokerRank(flush);
		strightRank = new PokerRank(stright);
		threeOfKindRank = new PokerRank(threeOfKind);
		twoPairRank = new PokerRank(twoPair);
		pairRank = new PokerRank(pair);
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}

	/**
	 * Test TwoPair.
	 */
	public void testTwoPair() {
		assertEquals(PokerRank.TWO_PAIR, twoPairRank.getHandType());
	}
	
	/**
	 * Test Royal Flush .
	 */
	public void testRoyalFlush() {
		assertEquals(PokerRank.ROYAL_FLUSH, royalFlushRank.getHandType());
	}

	
	/**
	 * Test Stright Flush.
	 */
	public void testStrightFlush() {
		assertEquals(PokerRank.STRAIGHT_FLUSH, strightFlushRank.getHandType());
	}

	/**
	 * Test FourofKind.
	 */
	public void testFourOfKind() {
		assertEquals(PokerRank.FOUR_OF_A_KIND, fourOfKindRank.getHandType());
	}

	/**
	 * Test Fullhouse.
	 */
	public void testFullHouse() {
		assertEquals(PokerRank.FULL_HOUSE, fullHouseRank.getHandType());
	}

	/**
	 * Test Flush.
	 */
	public void testFlush() {
		assertEquals(PokerRank.FLUSH, flushRank.getHandType());
	}

	/**
	 * Test Stright.
	 */
	public void testStright() {
		assertEquals(PokerRank.STRAIGHT, strightRank.getHandType());
	}
	
	/**
	 * Test ThreeOfKind.
	 */
	public void testThreeOfKind() {
		assertEquals(PokerRank.TRIPLE, threeOfKindRank.getHandType());
	}
	
	

	/**
	 * Test Pair.
	 */
	public void testPair() {
		assertEquals(PokerRank.PAIR, pairRank.getHandType());
	}
	

}
