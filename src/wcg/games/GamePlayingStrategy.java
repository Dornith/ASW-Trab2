package wcg.games;

import wcg.shared.cards.Card;

public interface GamePlayingStrategy {
	java.util.List<Card> pickCards(GameBot bot);
}
