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
public class RoundUpdateEvent extends GameEvent implements Serializable {

	
	private static final long serialVersionUID = -5088868723897288680L;
	private java.util.Map<java.lang.String,java.util.List<Card>> cardsOnTable;
	private java.lang.String mode;
	private java.lang.String nickWithTurn;
	private int	roundsCompleted;
	
	
	public RoundUpdateEvent() {
		super();
	}
	public RoundUpdateEvent(String gameId, Map<String, List<Card>> cardsOnTable, String nickWithTurn,
			int roundsCompleted, String mode) {
		super(gameId);
		this.cardsOnTable = cardsOnTable;
		this.mode = mode;
		this.nickWithTurn = nickWithTurn;
		this.roundsCompleted = roundsCompleted;
	}
	/**
	 * @return the cardsOnTable
	 */
	public java.util.Map<java.lang.String, java.util.List<Card>> getCardsOnTable() {
		return cardsOnTable;
	}
	/**
	 * @return the mode
	 */
	public java.lang.String getMode() {
		return mode;
	}
	/**
	 * @return the nickWithTurn
	 */
	public java.lang.String getNickWithTurn() {
		return nickWithTurn;
	}
	/**
	 * @return the roundsCompleted
	 */
	public int getRoundsCompleted() {
		return roundsCompleted;
	}
}
