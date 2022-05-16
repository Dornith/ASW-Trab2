/**
 * 
 */
package wcg.shared.events;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import wcg.shared.cards.Card;

/**
 * @author spila
 *
 */
public class RoundConclusionEvent extends GameEvent implements Serializable {


	private static final long serialVersionUID = -7824538013566793646L;
	private java.util.Map<java.lang.String,java.util.List<Card>> onTable;
	private int roundsCompleted;
	private java.util.Map<java.lang.String,java.lang.Integer> points;
	
	public RoundConclusionEvent() {
		super();
	}
	
	public RoundConclusionEvent(String gameId, Map<String, List<Card>> onTable, int roundsCompleted,
			Map<String, Integer> points) {
		super(gameId);
		this.onTable = onTable;
		this.roundsCompleted = roundsCompleted;
		this.points = points;
	}

	/**
	 * @return the cardsOnTable
	 */
	public java.util.Map<java.lang.String, java.util.List<Card>> getCardsOnTable() {
		return onTable;
	}

	/**
	 * @return the roundsCompleted
	 */
	public int getRoundsCompleted() {
		return roundsCompleted;
	}

	/**
	 * @return the points
	 */
	public java.util.Map<java.lang.String, java.lang.Integer> getPoints() {
		return points;
	}

	
	
}
