/**
 * 
 */
package wcg.shared.events;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import GameEvent;
import wcg.shared.cards.Card;

/**
 * @author spila
 *
 */
public class GameEndEvent extends GameEvent implements Serializable {


	private static final long serialVersionUID = -1830108011333111448L;
	//private java.lang.String gameId;
    private java.util.Map<java.lang.String,java.util.List<Card>> onTable;
    private int roundsCompleted;
    private java.lang.String winner;
    private java.util.Map<java.lang.String,java.lang.Integer> points;
    
	public GameEndEvent() {
		super();
	}

	public GameEndEvent(String gameId, Map<String, List<Card>> onTable, int roundsCompleted, String winner,
			Map<String, Integer> points) {
		super(gameId);
		this.onTable = onTable;
		this.roundsCompleted = roundsCompleted;
		this.winner = winner;
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
	 * @return the winner
	 */
	public java.lang.String getWinner() {
		return winner;
	}

	/**
	 * @return the points
	 */
	public java.util.Map<java.lang.String, java.lang.Integer> getPoints() {
		return points;
	}
    

}
