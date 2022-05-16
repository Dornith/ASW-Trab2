package wcg.games;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import wcg.shared.cards.Card;
import wcg.shared.cards.CardSuit;
import wcg.shared.cards.CardValue;

public class CardCollection implements Iterable<Card> {
	private LinkedList<Card> cards;
	private CardComparator comparator;
	
	public CardCollection() {
		super();
		this.comparator = DefaultCardComparator.getInstance();
		this.cards = new LinkedList<Card>();
	}

	public CardCollection(CardComparator comparator) {
		super();
		this.cards = new LinkedList<Card>();
		this.comparator = comparator;
	}

	public CardCollection(List<Card> cards) {
		super();
		this.cards = new LinkedList<Card>(cards);
		this.comparator = DefaultCardComparator.getInstance();
	}

	public CardCollection(CardComparator comparator, List<Card> cards) {
		super();
		this.cards = new LinkedList<Card>(cards);
		this.comparator = comparator;
	}
	public static CardCollection getFullDeck(){
		var newDeck = populateDeck();
		newDeck.add(new Card(null,null));
		newDeck.add(new Card(null,null));
		return new CardCollection(newDeck);
		
	}
	public static CardCollection getFullDeck(CardComparator comparator) {
		var newDeck = populateDeck();
		newDeck.add(new Card(null,null));
		newDeck.add(new Card(null,null));
		return new CardCollection(comparator, newDeck);
	}
	public static CardCollection getDeck() {
		var newDeck = populateDeck();
		return new CardCollection(newDeck);
	}
	public static CardCollection getDeck(CardComparator comparator) {
		var newDeck = populateDeck();
		return new CardCollection(comparator, newDeck);
	}

	private static LinkedList<Card> populateDeck() {
		var newDeck = new LinkedList<Card>();
		var suits = CardSuit.values();
		var values = CardValue.values();
		for(CardValue value : values) {
			for(CardSuit suit : suits) {
				newDeck.add(new Card(suit,value));
			}
		}
		return newDeck;
	}
	
	public CardComparator getCardComparator() {
		return this.comparator;
	}
	public java.util.List<Card> asList(){
		var cardsAsList = new LinkedList<Card>(cards);
		return cardsAsList;
		
	}
	
	public CardCollection shuffle() {
		Collections.shuffle(cards);
		return this;
	}
	
	public CardCollection removeRepeated() {
		var firstFoundCards = new LinkedList<Card>();
		for(Card card : cards) {
			if(!firstFoundCards.contains(card)) {
				firstFoundCards.add(card);
			}
		}
		this.cards = firstFoundCards;
		return this;
	}
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	public CardCollection clearCards() {
		if(!cards.isEmpty()) cards.clear();
		return this;
	}
	
	public int size() {
		return cards.size();
	}
	
	public CardCollection addCard(Card card) {
		cards.add(card);
		return this;
	}
	
	public CardCollection addCard(CardSuit suit,
            CardValue value) {
		Card newCard = new Card(suit,value);
		this.addCard(newCard);
		return this;
	}
	
	public CardCollection addAllCards(java.util.List<Card> cards) {
		this.cards.addAll(cards);
		return this;
	}
	
	public CardCollection addCardCollection(CardCollection collection) {
		this.cards.addAll(collection.cards);
		return this;
	}
	
	public boolean containsCard(Card card) {
		return this.cards.contains(card);
	}
	
	public Card getFirstCard() {
		try {
			var firstCard = cards.getFirst();
			return firstCard;
		}
		catch(java.util.NoSuchElementException e) {
			return null;
		}
	}
	
	public Card getLastCard() {
		try {
			var lastCard = cards.getLast();
			return lastCard;
		}
		catch(java.util.NoSuchElementException e) {
			return null;
		}
	}
	
	public CardCollection getCardsFromSuit(CardSuit suit) {
		var newDeck = new LinkedList<Card>();
		for(Card card : cards) {
			if(card.getSuit().equals(suit)) {
				newDeck.add(card);
			}
		}
		return new CardCollection(this.getCardComparator(),newDeck);
	}
	
	public CardCollection getCardsNotFromSuit(CardSuit suit) {
		var newDeck = new LinkedList<Card>();
		for(Card card : cards) {
			if(!card.getSuit().equals(suit)) {
				newDeck.add(card);
			}
		}
		return new CardCollection(comparator, newDeck);
	}
	
	public CardCollection getCardsLargerThan(Card limit) {
		var newDeck = new LinkedList<Card>();
		for(Card card : cards) {
			if(comparator.compare(card, limit) > 0) {
				newDeck.add(card);
			}	
		}
		return new CardCollection(comparator, newDeck);
	}
	public CardCollection getCardsSmallerThan(Card limit) {
		var newDeck = new LinkedList<Card>();
		for(Card card : cards) {
			if(comparator.compare(card, limit) < 0) {
				newDeck.add(card);
			}	
		}
		return new CardCollection(comparator, newDeck);
	}
	
	public CardCollection getHighestValueCards() {
		var newDeck = new LinkedList<Card>();
		var listOfValues = CardValue.values();
		for(int i = listOfValues.length-1; i >= 0; i--) {
			for(Card card : cards) {
				if(comparator.compare(card.getValue(), listOfValues[i] ) == 0) {
					newDeck.add(card);
				}
			}
			if(!newDeck.isEmpty()) {
				return new CardCollection(comparator, newDeck);
			}
		}
		return new CardCollection(comparator);
	}
	
	public CardCollection getLowestValueCards() {
		var newDeck = new LinkedList<Card>();
		var listOfValues = CardValue.values();
		for(int i = 0; i < listOfValues.length; i++) {
			for(Card card : cards) {
				if(comparator.compare(card.getValue(), listOfValues[i] ) == 0) {
					newDeck.add(card);
				}
			}
			if(!newDeck.isEmpty()) {
				return new CardCollection(comparator, newDeck);
			}
		}
		return new CardCollection(comparator);
	}
	
	public CardCollection getCardsWithValue(CardValue value) {
		var newDeck = new LinkedList<Card>();
		for(Card card : cards) {
			if(comparator.compare(card.getValue(), value ) == 0) {
				newDeck.add(card);
			}
		}
		return new CardCollection(comparator,newDeck);
	}
	
	public Card getHighestCard() {
		if(cards.isEmpty()) return null;
		return Collections.max(cards, comparator);
	}
	
	public Card getLowestCard() {
		if(cards.isEmpty()) return null;
		return Collections.min(cards, comparator);
	}
	
	public Card getRandomCard() {
		if(cards.isEmpty()) return null;
		Random rand = new Random();
		int rand_index = rand.nextInt(cards.size());
		return cards.get(rand_index);
	}
	
	public Card takeFirstCard() {
		return cards.pollFirst();
	}
	
	public Card takeLastCard() {
		return cards.pollLast();
	}
	
	public CardCollection takeFirstCards(int count) {
		var newDeck = new LinkedList<Card>();
		for(int i = 0; i < count; i++){
			var card = this.takeFirstCard();
			if(card == null) break;
			newDeck.add(card);
		}
		return new CardCollection(comparator,newDeck);
	}
	
	public Card takeCard(Card card) {
		try {
			var theCard = cards.get(cards.indexOf(card));
			cards.remove(cards.indexOf(card));
			return theCard;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public java.util.Iterator<Card> iterator(){
		return cards.iterator();
	}

	@Override
	public int hashCode() {
		return Objects.hash(cards, comparator);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardCollection other = (CardCollection) obj;
		return Objects.equals(cards, other.cards) && Objects.equals(comparator, other.comparator);
	}

	@Override
	public String toString() {
		return "CardCollection [cards=" + cards + ", comparator=" + comparator + "]";
	}
	
}
