package wcg.shared.cards;

import java.io.Serializable;

/**
 * @author spila
 *
 */
public enum CardSuit implements Comparable<CardSuit>, Serializable {
	CLUBS("â™Ł"),DIAMONDS("â™¦"),HEARTS("â™Ą"),SPADES("â™ ");
	private java.lang.String symbol;
	CardSuit(String string) {
		this.symbol = string;
	}

	@Override
	public java.lang.String toString(){
		return symbol;
	}
}
