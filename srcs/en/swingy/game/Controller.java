package en.swingy.game;

public class Controller {
	String movementRegex = NORTH_MOV + "|" + SOUTH_MOV +
		"|" + EAST_MOV + "|" + WEST_MOV + "|" + QUIT_OPT;

	private int player_x;
	private int player_y;

	public final String VALID_MOV = 
		"[" + NORTH_MOV + "/" + WEST_MOV + "/" + SOUTH_MOV + "/" + EAST_MOV + "]";

	public final String MOV_PROMPT = GamePrint.BOLD +
	"""
	Enter your next movement 
	""" + VALID_MOV + " : " + GamePrint.COLOR_RESET;

	public static final String QUIT_OPT = "q";
	public static final String NORTH_MOV = "w";
	public static final String SOUTH_MOV = "s";
	public static final String EAST_MOV = "d";
	public static final String WEST_MOV = "a";

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
		} while (!mov.matches(movementRegex));
		return mov;
	}

	public String Movement(String[][] m, String mov) {
		if (!Game.GUI_MODE) {
			mov = askMovement();
		}

		m[this.player_y][this.player_x] = Map.EMPTY_CELL;
		switch (mov) {
			case NORTH_MOV -> moveNorth();
			case WEST_MOV -> moveWest();
			case EAST_MOV -> moveEast();
			case SOUTH_MOV -> moveSouth();
			case QUIT_OPT -> {
				if (!Game.GUI_MODE) {
					Game.getInstance().startGame();
				}
			}
		}
		return mov;
	}

	public void goBack(String dir) {
		switch (dir) {
			case NORTH_MOV -> moveSouth();
			case WEST_MOV -> moveEast();
			case EAST_MOV -> moveWest();
			case SOUTH_MOV -> moveNorth();
		}
	}
}
