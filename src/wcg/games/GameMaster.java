/**
 * 
 */
package wcg.games;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import wcg.shared.CardGameException;
import wcg.shared.GameInfo;
import wcg.shared.cards.Card;
import wcg.shared.cards.CardSuit;
import wcg.shared.events.GameEndEvent;
import wcg.shared.events.RoundConclusionEvent;
import wcg.shared.events.RoundUpdateEvent;
import wcg.shared.events.SendCardsEvent;

/**
 * @author spila
 *
 */
public abstract class GameMaster extends ObservableGame {
	private static final int _NOPLAYERS = 0;
	public static int COUNTER = 1;
	final static int TEN_MINUTES = 600000;
	private String gameId;
	private static long expirationTime = TEN_MINUTES;
	private GameInfo info;
	private CardCollection cardDeck;
	private GameStage stage;
	private HashMap<String,CardCollection> playerDecks;
	private int playerOnTurnIndex;
	private List<String> listOfPlayers;
	private Map<String,Boolean> hasPlayed;
	private Map<String,CardCollection> onTableCards;
	private String mode;
	private int roundsCompleted;
	private java.util.Map<java.lang.String,java.lang.Integer> points;
	
	
	// ------ ABSTRACT METHODS ------
	protected abstract void beforeRoundConclusion();
	protected abstract void checkCards(String nick,List<Card> cards)throws CardGameException;
	protected abstract GamePlayingStrategy getCardGameStrategy();
	protected abstract int getCardsPerPlayer();
	protected abstract String getGameName();
	protected abstract int getNumberOfPlayers();
	protected abstract int getRoundPoints(String nick);
	protected abstract String getWinner();
	protected abstract boolean hasEnded();
	protected abstract String initialTurnInRound();
	protected abstract boolean isWithTurns();
	protected abstract void startGame();
	
	//  ------------------------------
	static enum GameStage 
	implements java.lang.Comparable<GameMaster.GameStage>{
		CONCLUDED,PLAYING,PREPARING;
	}
	
	protected GameMaster() {
		super();
		this.gameId = makeGameId();
		cardDeck = this.getDeck();
		playerDecks = new HashMap<String,CardCollection>();
		onTableCards = new HashMap<String,CardCollection>();
		listOfPlayers = new ArrayList<String>();
		stage = GameStage.PREPARING;
		info = new GameInfo(getGameId(),getGameName(),_NOPLAYERS,new Date(),new Date());
		points = new HashMap<>();
		hasPlayed = new HashMap<>();
	}
	
	public String getGameId() {
		return gameId;
	}
	public static long getExpirationtime() {
		return GameMaster.expirationTime;
	}
	public static void setExpirationtime(long expirationTime) {
		GameMaster.expirationTime = expirationTime;
	}
	public final GameInfo getInfo() {
		return info;
	}
	
	public final boolean expired() {
		long tmp = Math.abs(info.getLastAccessDate().getTime() - new Date().getTime());
		if(tmp > getExpirationtime()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	protected CardComparator getCardComparator() {
		return DefaultCardComparator.getInstance();
	}
	
	protected CardCollection getDeck() {
		if(cardDeck == null) { 
			cardDeck = CardCollection.getDeck();
			return cardDeck;
		}
		return cardDeck;
		
	}
	
	public boolean acceptsPlayers() {
		if(stage == GameStage.PREPARING) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public final void addPlayer(Player player)
            throws CardGameException{
		if(observers.containsKey(player.getNick())
				|| stage != GameStage.PREPARING) {
			throw new CardGameException();
		}
		this.addObserver(player.getNick(),player);
		playerDecks.put(player.getNick(),
				new CardCollection(this.getCardComparator()));
		listOfPlayers.add(player.getNick());
		onTableCards.put(player.getNick(), new CardCollection(this.getCardComparator()));
		points.put(player.getNick(), 0);
		info.setLastAccessDate(new Date());
		
		info.setPlayersCount(listOfPlayers.size());
		hasPlayed.put(player.getNick(), Boolean.FALSE);
		if(listOfPlayers.size() == this.getNumberOfPlayers()) this.startPlaying();
		
	}
	
	final void startPlaying() {
		this.getDeck().shuffle();
		stage = GameStage.PLAYING;
		dealCards();
		HashMap<String, List<Card>> newOnTable = retypeOnTable();
		broadcast(new RoundUpdateEvent(this.getGameId(),newOnTable,getNickWithTurn(),this.getRoundsCompleted(),getMode()));
		startGame();
		
	}
	
	final void dealCards() {
		playerDecks.forEach((nick,hand) ->{
			var cardList = new ArrayList<Card>();
			for(int i = 0; i < this.getCardsPerPlayer(); i++) {
				Card card = cardDeck.takeFirstCard();
				hand.addCard(card);
				cardList.add(card);
			}

			this.notify(nick, new SendCardsEvent(this.getGameId(), cardList));
		});
		
	}
	
	protected final void sendCards(java.lang.String nick,
            CardCollection cards) {
		this.notify(nick, new SendCardsEvent(this.getGameId(), cards.asList()));
		playerDecks.get(nick).addCardCollection(cards);
	}
	
	public final void playCard(java.lang.String nick,
            Card card) throws CardGameException {
		try {
			var a = new ArrayList<Card>();
			a.add(card);
			checkCards(nick,a);
			Card cardToPlay = check_throw(nick, card);
			onTableCards.get(nick).addCard(cardToPlay);
			info.setLastAccessDate(new Date());
			hasPlayed.put(nick, Boolean.TRUE);
			
			playerOnTurnIndex = listOfPlayers.indexOf(nextInTurn());
			System.out.println(playerOnTurnIndex);
			HashMap<String, List<Card>> newOnTable = retypeOnTable();
			broadcast(new RoundUpdateEvent(this.getGameId(),newOnTable,getNickWithTurn(),this.getRoundsCompleted(),getMode()));
			if(isTheRoundOver()) concludeRound(); 
			
		}
		catch(CardGameException e) {
			throw(e);
		}
		
		
		
	}
	
	public synchronized final void playCards(java.lang.String nick,
            java.util.List<Card> cards) throws CardGameException{
		try {
			checkCards(nick,cards);
			for(Card c : cards) {
				Card cardToPlay = check_throw(nick, c);
				onTableCards.get(nick).addCard(cardToPlay);
				info.setLastAccessDate(new Date());
				hasPlayed.put(nick, Boolean.TRUE);
				playerOnTurnIndex = listOfPlayers.indexOf(nextInTurn());
				if(isTheRoundOver()) concludeRound(); 
			}
		}
		catch(CardGameException e) {
			throw(e);
		}
	}
	
	private Card check_throw(java.lang.String nick, Card card) throws CardGameException {
		if(nick == null) {
			throw new CardGameException("nick is null");
		}
		if(listOfPlayers.contains(nick) == false) {
			throw new CardGameException(nick+" is not in this game");
		}
		if(stage != GameStage.PLAYING) {
			throw new CardGameException("game is not in PLAYING stage");
		}
		Card cardToPlay = playerDecks.get(nick).takeCard(card);
		if(cardToPlay == null) {
			throw new CardGameException("Invalid card not in players "+ nick+ " hand");
		}
		if(isWithTurns() && listOfPlayers.get(playerOnTurnIndex) != nick) {
			throw new CardGameException("It is not your turn");
		}
		return cardToPlay;
	}
	
	public java.lang.String nextInTurn(){
		if(!this.isWithTurns()) return null;
		if(playerOnTurnIndex == listOfPlayers.size()-1) {
			return listOfPlayers.get(0);
		}
		return listOfPlayers.get(playerOnTurnIndex+1);
	}
	private boolean isTheRoundOver(){
		for(Map.Entry<String, Boolean> entry : hasPlayed.entrySet()) {
		    Boolean value = entry.getValue();
		    if(value == Boolean.FALSE) return false;
		}
		return true;
	}
	
	public void concludeRound() {
		beforeRoundConclusion();
		if(isWithTurns()) {
			swapNextFirstPlayer();
		}
		for(String nick : listOfPlayers) {
			int pointsThisRound = this.getRoundPoints(nick);
			int pointsBefore = this.points.get(nick);
			this.points.put(nick, pointsThisRound + pointsBefore);
		}
		HashMap<String, List<Card>> newOnTable = retypeOnTable();
		this.broadcast(new RoundConclusionEvent(getGameId(),
				newOnTable,this.getRoundsCompleted(),this.points));
		for(String nick : listOfPlayers) {
			hasPlayed.put(nick, false);
		}
		playerOnTurnIndex = 0;
		roundsCompleted++;
		if(hasEnded()){
			broadcast(new GameEndEvent(getGameId(),newOnTable,getRoundsCompleted(),getWinner(),points));
			stage = GameStage.CONCLUDED;
		}
	}
	private HashMap<String, List<Card>> retypeOnTable() {
		HashMap<String,List<Card>> newOnTable = new HashMap<>();
		for(String nick : listOfPlayers) {
			var retypeList = new LinkedList<Card>(onTableCards.get(nick).asList());
			newOnTable.put(nick,retypeList);
		}
		return newOnTable;
	}
	private void swapNextFirstPlayer() {
		String nextFirstPlayer = initialTurnInRound();
		int tmp = listOfPlayers.indexOf(nextFirstPlayer);
		String formerFirst = listOfPlayers.get(0);
		listOfPlayers.set(tmp, formerFirst);
		listOfPlayers.set(0, nextFirstPlayer);
	}
	
	protected CardCollection newCardCollection() {
		return new CardCollection(this.getCardComparator());
	}
	
	protected java.util.List<java.lang.String> getPlayerNicks(){
		return listOfPlayers;
	}
	
	protected java.lang.String getNickWithTurn(){
		return listOfPlayers.get(playerOnTurnIndex);
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	protected CardCollection getHand(java.lang.String nick) {
		return playerDecks.get(nick);
	}
	
	protected CardCollection getCardsOnTable(java.lang.String nick) {
		return onTableCards.get(nick);
	}
	
	protected Card getCardOnTable(java.lang.String nick) {
		return onTableCards.get(nick).takeLastCard();
	}
	
	public CardSuit getSuitToFollow() {
		var trump = onTableCards.get(listOfPlayers.get(0)).getFirstCard(); //get the first card that the first player played
		if(trump == null) return null;
		return trump.getSuit();
	}
	protected int getRoundsCompleted() {
		return roundsCompleted;
	}
	protected java.lang.String nickWithMostPoints(){
		int mostPoints = 0;
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
	
	java.lang.String makeGameId(){
		String ID = getGameName()+String.valueOf(COUNTER);
		COUNTER++;
		return ID;
	}
	public java.util.Map<java.lang.String, java.lang.Integer> getPoints() {
		return points;
	}
}
