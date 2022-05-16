package wcg.games;

import java.util.List;

import wcg.shared.CardGameException;
import wcg.shared.cards.Card;
import wcg.shared.cards.CardSuit;
import wcg.shared.cards.CardValue;

/**
 * Simple game mater for testing the abstract class {@link GameMaster} and
 * {@link GameBot} (that requires a game master). 
 * This game has the following rules. 
 * 
 * <ul>
 *    <li> 2 players
 *    <li> 3 cards
 *    <li> played in turns
 *    <li> highest value card wins each round
 *    <li> winning player starts next round
 *    <li> player with most winning rounds wins the game 
 * </ul>
 */
public class TestGameMaster extends GameMaster {
	static final String GAME_NAME = "TestGame";

	@Override
	protected String getGameName() {
		return GAME_NAME;
	}

	@Override
	protected int getNumberOfPlayers() {
		return 2;
	}

	@Override
	protected int getCardsPerPlayer() {
		return 3;
	}

	@Override
	protected boolean isWithTurns() {
		return true;
	}

	@Override
	protected void startGame() {			
	}
	
	@Override
	protected void checkCards(String nick, List<Card> cards) throws CardGameException {
		if(cards.size() != 1)
			throw new CardGameException("Just one card expected");
	}
	
	String nextTurn = null;
	
	@Override
	protected void beforeRoundConclusion() {
		Card highest = new Card(CardSuit.CLUBS,CardValue.V02);
		
		nextTurn = null;
		for(String nick: getPlayerNicks()) {
			Card card = getCardOnTable(nick);
			
			if( getCardComparator().compare(card,highest) > 0) {
				highest = card;
				nextTurn = nick;
			}
		}
	}

	@Override
	protected int getRoundPoints(String nick) {

		return nick.equals(nextTurn) ? 1 : 0;
	}

	@Override
	protected String initialTurnInRound() {
		return nextTurn;
	}

	@Override
	protected boolean hasEnded() {
		return getRoundsCompleted() == 3;
	}

	@Override
	protected String getWinner() {
		return nickWithMostPoints();
	}

	@Override
	protected GamePlayingStrategy getCardGameStrategy() {
		return new GamePlayingStrategy() {

			@Override
			public List<Card> pickCards(GameBot bot) {
				return bot.getHand().takeFirstCards(1).asList();
			}
			
		};
	}
	
}

