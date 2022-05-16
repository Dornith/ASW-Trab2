package wcg.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import wcg.shared.CardGameException;
import wcg.shared.cards.Card;
import wcg.shared.cards.CardSuit;
import wcg.shared.events.GameEndEvent;
import wcg.shared.events.RoundConclusionEvent;
import wcg.shared.events.RoundUpdateEvent;
import wcg.shared.events.SendCardsEvent;

public class GameBot extends Thread implements Player,java.lang.Runnable {
	
	private final List<String> listOfRobotNames = Arrays.asList("Tera","Spark","Rust","Corion","Isi","Okoh","Uhi","Eveator");
	public boolean hasEventToProcess;
	public boolean hasToPlay;
	private GameMaster gm;
	private GamePlayingStrategy strategy;
	private String nick;
	private String mode;
	private CardCollection playedCards;
	private CardCollection hand;
	private String winner;
	public Map<String, Integer> points;
	private  Map<String, List<Card>> myOnTable;
	
	public GameBot(GameMaster gameMaster) {
		this.gm = gameMaster;
		this.nick = generateNick(this.gm);
		this.hand = new CardCollection(getCardComparator());
		strategy = gm.getCardGameStrategy();
		//this.start();
		
	}
	
	public synchronized void run() {
		while(!gameEnded()) {
			try {
				waitForEventToProcess();
				if(this.getNickWithTurn() ==  this.getNick() && hasEventToProcess == true) {
					var cards = this.getStrategy().pickCards(this);
					System.out.println(cards);
					gm.playCards(getNick(),cards);
					System.out.println(this.getAllCardsOnTable());
					hasEventToProcess = false;
				}else {
					hasEventToProcess = false;
					notifyAll();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (CardGameException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	synchronized void waitForEventToProcess() throws InterruptedException {
		wait();
	}
	
	synchronized void notifyEventArrived() {
		hasEventToProcess = true;
		notifyAll();
		
	}
	@Override
	public void notify(SendCardsEvent event) {
		hand.addAllCards(event.getCards());
		//notifyEventArrived();
	}

	@Override
	synchronized public void notify(RoundUpdateEvent event) {
		myOnTable = event.getCardsOnTable();
		mode = event.getMode();
		notifyEventArrived();
	}

	@Override
	public void notify(RoundConclusionEvent event) {
		points = event.getPoints();
		//notifyEventArrived();
		System.out.println(points);
		
	}

	@Override
	public void notify(GameEndEvent event) {
		winner = event.getWinner();
		notifyEventArrived();
	}

	@Override
	public String getNick() {
		return nick;
	}
	public java.util.Map<java.lang.String,java.lang.Integer> getPoints(){
		return points;
	}
	public int getRoundsCompleted() {
		return gm.getRoundsCompleted();
	}
	protected java.lang.String generateNick(GameMaster gameMaster){
		for(String name : listOfRobotNames) {
			if(!gameMaster.getPlayerNicks().contains(name)) return name;
		}
		return null;
	}

	public GamePlayingStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(GamePlayingStrategy strategy) {
		this.strategy = strategy;
	}
	public CardSuit getSuitToFollow() {
		return gm.getSuitToFollow();
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	public CardCollection getPlayedCards() {
		return playedCards;
	}
	public CardCollection getAllCardsOnTable() {
		var newCollection = new CardCollection(gm.getCardComparator());
		for(String nick : gm.getPlayerNicks()) {
			Card c = gm.getCardOnTable(nick);
			if(c != null) {
				newCollection.addCard(c);
			}
		}
		return newCollection;
	}
	public Card getCardOnTable(java.lang.String nick) {
		if(myOnTable.get(nick).isEmpty())return null;
		return myOnTable.get(nick).get(0);
	}
	public boolean noCardsOnTable() {
		return getAllCardsOnTable().isEmpty();
	}
	public java.lang.String getNickWithTurn(){
		return gm.isWithTurns() ? gm.getNickWithTurn() : null;
	}
	public CardComparator getCardComparator() {
		return gm.getCardComparator();
	}
	
	public void setHand(CardCollection hand) {
		this.hand = hand;
	}
	
	public CardCollection getHand() {
		return hand;
	}
	public java.lang.String getWinner(){
		return winner;
	}
	public boolean gameEnded() {
		return winner !=null;
	}
	
	
	
}
