package wcg.main;

import java.util.ArrayList;
import java.util.List;

import wcg.games.hearts.HeartsGameMaster;
import wcg.shared.CardGameException;
import wcg.shared.GameInfo;

public class Manager {
	
	private static Manager instance;
	List<String> gameNames;
	List<GameInfo> availableGameInfos;
	GamePool pool; // facade??
	
	private Manager() {
		gameNames = new ArrayList<String>();
		gameNames.add("HEARTS");
		availableGameInfos = new ArrayList<GameInfo>();
		availableGameInfos.add(new GameInfo())
		
	}
	
	static Manager getInstance()
            throws CardGameException{
		try {
			if(instance == null) {
				instance = new Manager();
				return instance;
			}
			else {
				return instance;
			}
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	void reset() {
		
	}
	
	public java.util.List<java.lang.String> getGameNames(){
		return gameNames;
	}
	public java.lang.String createGame(java.lang.String name)
            throws CardGameException{
		if(!getGameNames().contains(name)) throw new CardGameException();
		if(name == "HEARTS") {
			var gm = new HeartsGameMaster();
			return gm.getGameId();
		}
		return null;
	}
	public java.util.List<GameInfo> getAvailableGameInfos(){
		return availableGameInfos;
	}
}
