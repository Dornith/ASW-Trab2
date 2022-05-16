/**
 * 
 */
package wcg.games;

import java.util.Comparator;

import wcg.shared.cards.Card;
import wcg.shared.cards.CardSuit;
import wcg.shared.cards.CardValue;

/**
 * @author spila
 *
 */
public interface CardComparator extends Comparator<Card> {
	int compare(CardSuit o1, CardSuit o2);
	int compare(CardValue o1, CardValue o2);
}
