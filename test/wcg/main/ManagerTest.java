package wcg.main;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wcg.WebCardGameTest;
import wcg.shared.CardGameException;

/**
 * This test class is incomplete. You have to code it!
 * You may (and should) add other tests and define auxiliary methods 
 * and even inner classes.
 */
class ManagerTest extends WebCardGameTest {
	
	static Manager manager;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//TODO implement this
	}

	@BeforeEach
	void setUp() throws Exception {
		//TODO implement this
	}
	
	@AfterAll
	static void cleanUpAfterClass() throws Exception {
		//TODO implement this
	}
	
	/**
	 * Check that an instance is available 
	 */
	@Test
	void testGetInstance() {
		fail("Not implemented yet");
	}
	
	/**
	 * Check if the names of the implemented games are 
	 * reported by getGameNames() 
	 */
	@Test
	void testGetGameNames() {
		fail("Not implemented yet");	}
	
	/**
	 * Check if an empty list of games is reported 
	 */
	@Test
	void testGetAvailableGames() {
		fail("Not implemented yet");
	}
	
	/**
	 * Create several games and check that number of available games
	 * increases accordingly
	 * 
	 * @throws CardGameException
	 */
	@Test
	void testGetAvailableGames_afterCreateGame() throws CardGameException {
		fail("Not implemented yet");
	}

	/**
	 * Check that game creation returns a game id
	 *  
	 * @throws CardGameException 
	 */
	@Test
	void testCreateGame() throws CardGameException {
		fail("Not implemented yet");
	}
	
	/**
	 * Check that an invalid game name raises an exception 
	 */
	@Test
	void testCreateGame_invalid() {
		fail("Not implemented yet");
	}
	
	/**
	 * Check adding a player, without registering, with invalid password
	 * @throws CardGameException
	 */
	@Test
	void testAddPlayer() throws CardGameException {
		fail("Not implemented yet");
	}
	
	/**
	 * 
	 * @throws CardGameException
	 */
	@Test
	void testAddBot() throws CardGameException {
		fail("Not implemented yet");
	}
		
	/**
	 * Create a game but ignore the id when adding a bot. 
	 * It should raise an exception.
	 * 
	 * @throws CardGameException
	 */
	@Test
	void testAddBot_invalid() throws CardGameException {
		fail("Not implemented yet");
	}

	/**
	 * Check that players added to a game receive events from that game.
	 * 
	 * @throws CardGameException
	 */
	@Test
	void testGetRecentEvents() throws CardGameException {
		fail("Not implemented yet");
	}	
	
	/**
	 * Test playing WAR a do a invalid move:3 cards as first play
	 * @throws CardGameException
	 */
	void testPlayCards_WAR_invalidPlay() throws CardGameException {		
		fail("Not implemented yet");
	}
	
	
	/**
	 * Test playing WAR for a few rounds (or the end of the game)
	 * @throws CardGameException
	 */
	@Test
	void testPlayCards_WAR() throws CardGameException {		
		fail("Not implemented yet");
	}
	

	/**
	 * Test playing HERTS to the end
	 * 
	 * @throws CardGameException
	 */
	@Test
	void testPlayCards_HEARTS() throws CardGameException {		
		fail("Not implemented yet");
	}

}
