package en.swingy.game;

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

	public void moveNorth() { 
		this.player_y--;
	}

	public void moveWest() {
		this.player_x--;
	}

	public void moveEast() { 
		this.player_x++;
	}

	public void moveSouth() { 
		this.player_y++; 
	}

	public int getPlayerX() {
		return this.player_x;
	}

	public int getPlayerY() {
		return this.player_y;
	}

	public String askMovement() {
		String mov;
		do {
			System.out.print(MOV_PROMPT);
			mov = GamePrint.STD_IN.nextLine();
		} while (!mov.matches(validMovement));
		return mov;
	}

	public String Movement(Game g, String[][] m, String mov) {
		if (!Game.GUI) {
			mov = askMovement();
		}

		m[this.player_y][this.player_x] = Map.EMPTY_CELL;
		switch (mov) {
			case "w" -> moveNorth();
			case "a" -> moveWest();
			case "d" -> moveEast();
			case "s" -> moveSouth();
			case "q" -> {
				if (!Game.GUI) {
					g.startGame();
				}
			}
		}
		return mov;
	}

	public void goBack(String dir) {
		switch (dir) {
			case "w" -> moveSouth();
			case "a" -> moveEast();
			case "d" -> moveWest();
			case "s" -> moveNorth();
		}
	}
}
