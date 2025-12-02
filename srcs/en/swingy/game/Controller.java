package en.swingy.game;

public class Controller {
	public enum DIR {
		N,
		W,
		E,
		S
	}

	String validMovement = "N|W|E|S";

	private int player_x;
	private int player_y;
	public final String prompt_movement = "Enter your next movement [N/W/E/S] : ";

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

	public void Movement(String[][] m) {
		String mov;
		do {
			System.out.print(prompt_movement);
			mov = Game.STD_IN.nextLine();
		} while (!mov.matches(validMovement));

		m[this.player_y][this.player_x] = Map.EMPTY_CELL;
		switch (mov) {
			case "N" -> moveNorth(m);
			case "W" -> moveWest(m);
			case "E" -> moveEast(m);
			case "S" -> moveSouth(m);
		}
		m[this.player_y][this.player_x] = Map.PLAYER_CELL;
	}
}
