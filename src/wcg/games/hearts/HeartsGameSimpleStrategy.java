package wcg.games.hearts;

import java.util.Arrays;
import java.util.List;

import wcg.games.CardCollection;
import wcg.games.GameBot;
import wcg.games.GamePlayingStrategy;
import wcg.shared.cards.Card;
import wcg.shared.cards.CardSuit;

public class HeartsGameSimpleStrategy implements GamePlayingStrategy {

	@Override
	public List<Card> pickCards(GameBot bot) {
		CardCollection valid = bot.getHand();
		if(!(bot.getNickWithTurn() == bot.getNick())) {
			if(bot.getSuitToFollow() != null) {
				valid.getCardsFromSuit(bot.getSuitToFollow());
			}
		}
		valid = bot.getRoundsCompleted() < 2 ? valid.getCardsNotFromSuit(CardSuit.HEARTS) : valid ;
		if(!valid.getCardsNotFromSuit(CardSuit.HEARTS).isEmpty()) {
			valid = valid.getCardsNotFromSuit(CardSuit.HEARTS);
		}
		if(valid.isEmpty()) {
			return Arrays.asList(bot.getHand().getRandomCard());
		}
		return Arrays.asList(valid.getRandomCard());
	}

}
