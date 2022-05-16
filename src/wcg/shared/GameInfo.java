package wcg.shared;

import java.io.Serializable;
import java.util.Date;

public class GameInfo implements Serializable {
	private static final long serialVersionUID = -1707878427424792763L;
	private java.lang.String gameId;
	private java.lang.String gameName;
	private int playersCount;
	private java.util.Date startDate;
	private java.util.Date lastAccessDate;
	/**
	 * @param gameId
	 * @param gameName
	 * @param playersCount
	 * @param startDate
	 * @param lastAccessDate
	 */
	public GameInfo(String gameId, String gameName, int playersCount, Date startDate, Date lastAccessDate) {
		super();
		this.gameId = gameId;
		this.gameName = gameName;
		this.playersCount = playersCount;
		this.startDate = startDate;
		this.lastAccessDate = lastAccessDate;
	}
	public GameInfo() {
		super();
	}
	/**
	 * @return the gameId
	 */
	public java.lang.String getGameId() {
		return gameId;
	}
	/**
	 * @return the gameName
	 */
	public java.lang.String getGameName() {
		return gameName;
	}
	/**
	 * @return the playersCount
	 */
	public int getPlayersCount() {
		return playersCount;
	}
	/**
	 * @return the startDate
	 */
	public java.util.Date getStartDate() {
		return startDate;
	}
	/**
	 * @return the lastAccessDate
	 */
	public java.util.Date getLastAccessDate() {
		return lastAccessDate;
	}
	public void setLastAccessDate(Date accessTime) {
		this.lastAccessDate = accessTime;
	}
	public void setPlayersCount(int count) {
		this.playersCount = count;
	}
}
