package en.swingy.game;

public class Controller {
	String validMovement = "n|w|e|s";

	private int player_x;
	private int player_y;
	public final String prompt_movement = "Enter your next movement [n/w/e/s] : ";

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

	public String Movement(String[][] m) {
		String mov;
		do {
			System.out.print(prompt_movement);
			mov = GamePrint.STD_IN.nextLine();
		} while (!mov.matches(validMovement));

		m[this.player_y][this.player_x] = Map.EMPTY_CELL;
		switch (mov) {
			case "n" -> moveNorth(m);
			case "w" -> moveWest(m);
			case "e" -> moveEast(m);
			case "s" -> moveSouth(m);
		}

		return mov;
	}

	public void goBack(String dir, String[][] m) {
		switch (dir) {
			case "n" -> moveSouth(m);
			case "w" -> moveEast(m);
			case "e" -> moveWest(m);
			case "s" -> moveNorth(m);
		}

	}
}
