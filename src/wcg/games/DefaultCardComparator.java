/**
 * 
 */
package wcg.games;

import wcg.shared.cards.Card;
import wcg.shared.cards.CardSuit;
import wcg.shared.cards.CardValue;

/**
 * @author spila
 *
 */
public class DefaultCardComparator implements CardComparator {
	
	private static DefaultCardComparator instance = null;
	
	protected DefaultCardComparator() {
		super();
	}
	
	public static DefaultCardComparator getInstance() {
		if(instance == null) {
			return instance = new DefaultCardComparator();
			
		}
		else {
			return instance;
		}
	}

	
	
	@Override
	public int compare(Card o1, Card o2) {
		return o1.compareTo(o2);
	}

	@Override
	public int compare(CardSuit o1, CardSuit o2) {
		if(o1.equals(null) && o2.equals(null)) return 0;
		if(o1.equals(null)) return -1;
		if(o2.equals(null)) return 1;
		
		return o1.compareTo(o2);
	}

	@Override
	public int compare(CardValue o1, CardValue o2) {
		if(o1.equals(null) && o2.equals(null)) return 0;
		if(o1.equals(null)) return -1;
		if(o2.equals(null)) return 1;
		
		return o1.compareTo(o2);
	}

}
