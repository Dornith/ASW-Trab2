/**
 * 
 */
package wcg.games;

import java.util.Hashtable;
import java.util.Iterator;

import wcg.shared.events.GameEndEvent;
import wcg.shared.events.RoundConclusionEvent;
import wcg.shared.events.RoundUpdateEvent;
import wcg.shared.events.SendCardsEvent;

/**
 * @author spila
 *
 */
public class ObservableGame {
	
	protected Hashtable<String, GameObserver> observers = new Hashtable<String, GameObserver>();
	
	public ObservableGame() {
		
	}
	
	protected void addObserver(java.lang.String nick,
            GameObserver observer) {
		observers.put(nick, observer);
		
	}
	protected void notify(java.lang.String nick,
            SendCardsEvent event) {
		observers.get(nick).notify(event);
	}
	
	protected void broadcast(RoundUpdateEvent event) {
		observers.forEach((key,value) 
		-> value.notify(event));
	}
	
	protected void broadcast(RoundConclusionEvent event) {
		observers.forEach((key,value) 
		-> value.notify(event));
	}
	
	protected void broadcast(GameEndEvent event) {
		observers.forEach((key,value) 
		-> value.notify(event));
	}
}
