package wcg.games.hearts;

import java.util.List;
import java.util.Map;

import wcg.games.CardCollection;
import wcg.games.GameMaster;
import wcg.games.GamePlayingStrategy;
import wcg.shared.CardGameException;
import wcg.shared.cards.Card;
import wcg.shared.cards.CardSuit;

public class HeartsGameMaster extends GameMaster {
	
	private boolean strategySwitch = true;
	private int countHeartsInTurn;
	private int pointsLastTrick = 0;
	private String winner;
	private String next;
	private java.util.Map<java.lang.String,java.lang.Integer> points;
	private CardCollection onTable;
	@Override
	protected void beforeRoundConclusion() {
		onTable = new CardCollection(this.getCardComparator());
		Card highest = new Card(null,null);
		String winnerNick = this.getPlayerNicks().get(0);
		for(String nick : this.getPlayerNicks()) {
			Card nextCard = this.getCardOnTable(nick);
			if(this.getCardComparator().compare(highest, nextCard)<0){
				highest = nextCard;
				winnerNick = nick;
			}
			onTable.addCard(nextCard);
		}
		next = winnerNick;
		countHeartsInTurn = onTable.getCardsFromSuit(CardSuit.HEARTS).size();
		int pt = points.get(next);
		points.put(next, pt - countHeartsInTurn);
		
		pointsLastTrick = countHeartsInTurn;
		countHeartsInTurn = 0;
	}

	@Override
	protected void checkCards(String nick, List<Card> cards) throws CardGameException {
		if(cards.size() != 1)
			throw new CardGameException("Just one card expected");
		if(this.getRoundsCompleted() < 2 && cards.get(0).getSuit() == CardSuit.HEARTS) {
			throw new CardGameException("cannot play hearts");
		}
		if(nick != this.getPlayerNicks().get(0)) {
			if(cards.get(0).getSuit() != this.getSuitToFollow() && !this.getHand(nick).getCardsFromSuit(getSuitToFollow()).isEmpty()) {
				throw new CardGameException("Invalid suit");
				
			}
		}
	}

	@Override
	protected GamePlayingStrategy getCardGameStrategy() {
		GamePlayingStrategy strategy = strategySwitch ? new HeartsGameSimpleStrategy() : new HeartsGameStrategy();
		strategySwitch = strategySwitch? false : true;
		return strategy;
	}

	@Override
	protected int getCardsPerPlayer() {
		return 13;
	}

	@Override
	protected String getGameName() {
		return "HEARTS";
	}

	@Override
	protected int getNumberOfPlayers() {
		return 4;
	}

	@Override
	protected int getRoundPoints(String nick) {
		return nick == next ? -countHeartsInTurn : 0;
	}

	@Override
	protected String getWinner() {
		System.out.println(points);
		int mostPoints = -13;
		String winner = this.getPlayerNicks().get(0);
		for(Map.Entry<String, Integer> entry : points.entrySet()) {
			String nick = entry.getKey();
		    int value = entry.getValue();
		    if(value > mostPoints) {
		    	mostPoints = value;
		    	winner = nick;
		    }
		}
		return winner;
	}

	@Override
	protected boolean hasEnded() {
		return this.getRoundsCompleted() == 13;
	}

	@Override
	protected String initialTurnInRound() {
		return next;
	}

	@Override
	protected boolean isWithTurns() {
		return true;
	}

	@Override
	protected void startGame() {
		points = this.getPoints();
	}

}
