/**
 * 
 */
package wcg.shared.events;

import java.io.Serializable;

/**
 * @author spila
 *
 */
public abstract class GameEvent implements Serializable {

	private static final long serialVersionUID = 8422192657875321153L;
	protected String gameId;
	
	
	public GameEvent() {
		super();
	}
	public GameEvent(String gameId) {
		this.gameId = gameId;
	}
	/**
	 * @return the gameId
	 */
	public String getGameID() {
		return gameId;
	}
}
