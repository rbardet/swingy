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

	public Controller(int p_player_x, int p_player_y) {
		this.player_x = p_player_x;
		this.player_y = p_player_y;
	}

	public void moveNorth(String[][] m) { 
		this.player_y--;
	}

	public void moveWest(String[][] m) {
		this.player_x--;
	}

	public void moveEast(String[][] m) { 
		this.player_x++;
	}

	public void moveSouth(String[][] m) { 
		this.player_y++; 
	}

	public int getPlayerX() {
		return this.player_x;
	}

	public int getPlayerY() {
		return this.player_y;
	}

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

	public void goBack(String dir, String[][] m) {
		switch (dir) {
			case "w" -> moveSouth(m);
			case "a" -> moveEast(m);
			case "d" -> moveWest(m);
			case "s" -> moveNorth(m);
		}

	}
}
