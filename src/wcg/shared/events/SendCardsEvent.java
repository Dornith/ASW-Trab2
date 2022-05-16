package wcg.shared.events;

import java.io.Serializable;
import java.util.List;

import wcg.shared.cards.Card;

public class SendCardsEvent extends GameEvent implements Serializable {

	private static final long serialVersionUID = -5805479026524243209L;
	private java.util.List<Card> cards;
	public SendCardsEvent() {
		super();
	}
	public SendCardsEvent(String gameId, List<Card> cards) {
		super(gameId);
		this.cards = cards;
	}
	public String getGameId() {
		return gameId;
	}
	/**
	 * @return the cards
	 */
	public java.util.List<Card> getCards() {
		return cards;
	}
}
