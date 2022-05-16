/**
 * 
 */
package wcg.games;

import java.util.ArrayList;
import java.util.List;

import wcg.shared.CardGameException;

/**
 * @author spila
 *
 */
public class GameFactory implements AbstractGameFactory {
	
	private List<String> gameList;
	
	public GameFactory() {
		super();
		this.gameList = new ArrayList<String>();
		gameList.add("HEARTS");
		gameList.add("WAR");
	}

	@Override
	public List<String> getAvailableGames() {
		return getGameList();
	}

	@Override
	public GameMaster makeGameMaster(String name) throws CardGameException {
		GameMaster gm;
		if(name.equals("HEARTS")) return gm;
		if(name.equals("WAR")) return gm;
		throw new CardGameException();
	}

	/**
	 * @return the gameList
	 */
	public List<String> getGameList() {
		return gameList;
	}
	
}
