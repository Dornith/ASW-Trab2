/**
 * 
 */
package wcg.shared.cards;

import java.io.Serializable;

/**
 * @author spila
 *
 */
public enum CardValue implements Serializable, Comparable<CardValue> {
	V02("2"),
	V03("3"),
	V04("4"),
	V05("5"),
	V06("6"),
	V07("7"),
	V08("8"),
	V09("9"),
	V10("10"),
	QUEEN("Q"),
	JACK("J"),
	KING("K"),
	ACE("A");

	private String stringRepresentation;

	CardValue(String string) {
		this.stringRepresentation = string;
	}
	
	@Override
	public java.lang.String toString(){
		return stringRepresentation;
	}
	
}
