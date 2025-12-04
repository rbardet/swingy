package en.swingy.game;

import java.sql.SQLException;

public class Controller {
	String validMovement = "w|a|s|d|q";

	private int player_x;
	private int player_y;

	public final String MOV_PROMPT = GamePrint.BOLD +
	"""
	Enter your next movement [w/a/s/d] : 
	""" + GamePrint.COLOR_RESET;

	/**
	 * Constructor for Controller that sets the player's starting position.
	 *
	 * @param p_player_x Initial X position of the player
	 * @param p_player_y Initial Y position of the player
	 */
	public Controller(int p_player_x, int p_player_y) {
		this.player_x = p_player_x;
		this.player_y = p_player_y;
	}

	/**
	 * Moves the player one cell north.
	 *
	 * @param m The current map
	 */
	public void moveNorth(String[][] m) { 
		this.player_y--;
	}

	/**
	 * Moves the player one cell west.
	 *
	 * @param m The current map
	 */
	public void moveWest(String[][] m) {
		this.player_x--;
	}

	/**
	 * Moves the player one cell east.
	 *
	 * @param m The current map
	 */
	public void moveEast(String[][] m) { 
		this.player_x++;
	}

	/**
	 * Moves the player one cell south.
	 *
	 * @param m The current map
	 */
	public void moveSouth(String[][] m) { 
		this.player_y++; 
	}

	/**
	 * Returns the current X position of the player.
	 *
	 * @return Player's X coordinate
	 */
	public int getPlayerX() {
		return this.player_x;
	}

	/**
	 * Returns the current Y position of the player.
	 *
	 * @return Player's Y coordinate
	 */
	public int getPlayerY() {
		return this.player_y;
	}

	/**
	 * Handles the player's movement input.
	 * Updates the map by clearing the previous player cell and moving the player in the chosen direction.
	 * If 'q' is entered, restarts the game.
	 *
	 * @param g Current game instance
	 * @param m The current map
	 * @return The movement direction as a string ("w", "a", "s", "d", or "q")
	 * @throws SQLException If database operations are required (e.g., restarting the game)
	 */
	public String Movement(Game g,String[][] m) throws SQLException {
		String mov;
		do {
			System.out.print(MOV_PROMPT);
			mov = GamePrint.STD_IN.nextLine();
		} while (!mov.matches(validMovement));

		m[this.player_y][this.player_x] = Map.EMPTY_CELL;
		switch (mov) {
			case "w" -> moveNorth(m);
			case "a" -> moveWest(m);
			case "d" -> moveEast(m);
			case "s" -> moveSouth(m);
			case "q" -> g.startGame();
		}

		return mov;
	}

	/**
	 * Reverts the player's last movement, used when fleeing from an enemy.
	 *
	 * @param dir The last movement direction
	 * @param m The current map
	 */
	public void goBack(String dir, String[][] m) {
		switch (dir) {
			case "w" -> moveSouth(m);
			case "a" -> moveEast(m);
			case "d" -> moveWest(m);
			case "s" -> moveNorth(m);
		}
	}
}
