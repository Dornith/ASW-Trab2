/**
 * 
 */
package wcg.games;

import wcg.shared.events.GameEndEvent;
import wcg.shared.events.RoundConclusionEvent;
import wcg.shared.events.RoundUpdateEvent;
import wcg.shared.events.SendCardsEvent;

/**
 * @author spila
 *
 */
public interface GameObserver {
	void notify(SendCardsEvent event);
	void notify(RoundUpdateEvent event);
	void notify(RoundConclusionEvent event);
	void notify(GameEndEvent event);
}
