package wcg.games.hearts;

import java.util.Arrays;
import java.util.List;

import wcg.games.CardCollection;
import wcg.games.GameBot;
import wcg.games.GamePlayingStrategy;
import wcg.shared.cards.Card;
import wcg.shared.cards.CardSuit;

public class HeartsGameStrategy implements GamePlayingStrategy {

	@Override
	public List<Card> pickCards(GameBot bot) {
		CardCollection valid = bot.getHand();
		CardSuit topSuit = null;
		CardSuit lowSuit = null;
		int mostCards = 0;
		int leastCards = 13;
		for(CardSuit s : CardSuit.values()) {
			int size = valid.getCardsFromSuit(s).size();
			if(size > mostCards) {
				topSuit = s;
				mostCards = size;
			}
			if(size < leastCards) {
				leastCards = size;
				lowSuit = s;
			}
		}
		if(bot.getNickWithTurn() == bot.getNick()) {
			return Arrays.asList(valid.getCardsFromSuit(topSuit).getLowestCard());
		}
		valid = bot.getHand().getCardsFromSuit(bot.getSuitToFollow());
		if(valid.isEmpty()) {
			return Arrays.asList(bot.getHand().getCardsFromSuit(lowSuit).getHighestCard());
		}
		
		if(valid.getCardsSmallerThan(bot.getAllCardsOnTable().getHighestCard()).isEmpty()) {
			return Arrays.asList(valid.getHighestCard());
		}
		
		return Arrays.asList(valid.getCardsSmallerThan(bot.getAllCardsOnTable().getHighestCard()).getHighestCard());
	}

}
