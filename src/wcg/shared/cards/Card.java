/**
 * 
 */
package wcg.shared.cards;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author spila
 *
 */
public class Card implements Serializable, Comparable<Card> {

	private static final long serialVersionUID = -427546672716027079L;
	private CardSuit suit;
	private CardValue value;
	public Card(CardSuit suit, CardValue value) {
		super();
		this.suit = suit;
		this.value = value;
	}
	@Override
	public int hashCode() {
		return Objects.hash(suit, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return suit == other.suit && value == other.value;
	}
	/**
	 * @return the suit
	 */
	public CardSuit getSuit() {
		return suit;
	}
	/**
	 * @return the value
	 */
	public CardValue getValue() {
		return value;
	}
	@Override
	public int compareTo(Card o) {
		if(value == null && suit == null && o.suit == null && o.value == null) {
			return 0; // both same jokers
		}
		if(suit == null && o.suit != null) {
			return -1;
		}
		if(suit != null && o.suit == null) {
			return 1;
		}
		if(value != null && o.value == null) {
			return 1;
		}
		if(value == null && o.value != null) {
			return -1;
		}
		if(!o.suit.equals(suit)) {
			for (CardSuit c : CardSuit.values()) {
				if(c.toString().equals(suit.toString())) return -1;
				if(c.toString().equals(o.suit.toString())) return 1;
			}
		}
		var thisValue = this.value.toString();
		var otherValue = o.value.toString();
		if(thisValue.equals(otherValue)) return 0; //equal values
		for (CardValue c : CardValue.values()) {
			if(c.toString().equals(thisValue)) return -1;
			if(c.toString().equals(otherValue)) return 1;
		}
		    
		return 0;
	}
	@Override
	public java.lang.String toString(){
		if(value == null && suit == null){
			return "*-";
		}
		if(value == null) {
			return "*"+suit.toString();
		}
		if(suit == null) {
			return value.toString()+"-";
		}
		return (value.toString()+suit.toString());
	}

}
