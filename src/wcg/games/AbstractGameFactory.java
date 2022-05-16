/**
 * 
 */
package wcg.games;

import wcg.shared.CardGameException;

/**
 * @author spilasdsa
 *
 */
public interface AbstractGameFactory {
	java.util.List<java.lang.String> getAvailableGames();
	GameMaster makeGameMaster(java.lang.String name)
            throws CardGameException;
}
