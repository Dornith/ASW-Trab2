package wcg.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import wcg.games.GameMaster;
import wcg.games.hearts.HeartsGameMaster;
import wcg.shared.CardGameException;
import wcg.shared.GameInfo;

public class GamePool {
	private List<String> gameNames;
	private Timer t;
	private List<GameMaster> gmList;
	public GamePool() {
		gameNames = new ArrayList<String>();
		gmList = new ArrayList<GameMaster>();
		gameNames.add("HEARTS");
		t = new Timer();  
		TimerTask task = new Task();
		t.scheduleAtFixedRate(task,(long)0.0,GameMaster.getExpirationtime());
	}
	
	class Task extends TimerTask
	{
	    public void run() {
	    	removeExpiredGames();
	    }
	     
	}
	
	public List<String> getGameNames(){
		return gameNames;
	}
	public java.lang.String createGame(java.lang.String name)
            throws CardGameException{
		if(!gameNames.contains(name)) throw new CardGameException("No such game exists");
		if(name == "HEARTS") {
			GameMaster gm = new HeartsGameMaster();
			gmList.add(gm);
			return gm.getGameId();
		}
		return null;
	}
	
	public GameMaster getGameMaster(java.lang.String gameId)
            throws CardGameException{
		for(GameMaster gm : gmList) {
			if(gm.getGameId() == gameId) return gm;
		}
		throw new CardGameException("Invalid id");
	}
	
	public java.util.List<GameInfo> getAvailableGameInfos(){
		List<GameInfo> infos = new ArrayList<GameInfo>();
		for(int i = 0; i < gmList.size(); i++) {
			infos.add(gmList.get(i).getInfo());
		}
		return infos;
	}
	
	public void removeExpiredGames() {
		var it = gmList.iterator();
		while(it.hasNext()) {
			if(it.next().expired()) it.remove();
			
		}
	}
	@SuppressWarnings("unused")
	private void reset() {
		t = new Timer();
		gmList.clear();
	}

}
