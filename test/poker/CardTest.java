package poker;

import org.junit.Test;

import junit.framework.TestCase;

public class CardTest extends TestCase {

	public CardTest() { }

	protected void setUp() { }

	protected void tearDown() { }
	
	public void testValue() {
		PokerCard c1 = new PokerCard("JS");
		assertEquals("Jack", c1.getValueAsString());
	}
	
	public void testSuite() {
		PokerCard c1 = new PokerCard("JS");
		assertEquals("Spades", c1.getSuitAsString());
	}
	
	/**
	 * Test stronger card.
	 */
	public void testStrongerCard() {
		PokerCard c1 = new PokerCard("KS");
		PokerCard c2 = new PokerCard("2H");
		
		assertEquals(1, c1.compareTo(c2));
	}

	/**
	 * Test weaker card.
	 */
	public void testWeakerCard() {
		PokerCard c1 = new PokerCard("2H");
		PokerCard c2 = new PokerCard("KS");
		
		assertEquals(-1, c1.compareTo(c2));
	}
	
	/**
	 * Test if equals method works.
	 */
	public void testEquals() {
		PokerCard c1 = new PokerCard("2H");
		PokerCard c2 = new PokerCard("2S");
		
		assertEquals(true, c1.equals(c2));
	}
	
}
