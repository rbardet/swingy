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
	public static final String prompt_movement = "Enter your next movement [N/W/E/S] : ";

	public Controller(int p_player_x, int p_player_y) {
		this.player_x = p_player_x;
		this.player_y = p_player_y;
	}

	public void moveNorth() { 
		this.player_y--;
		Map.movePlayer(player_y, player_y, DIR.N.name());
	}

	public void moveWest() { 
		this.player_x--;
		Map.movePlayer(player_y, player_y, DIR.W.name());
	}

	public void moveEast() { 
		this.player_x++;
		Map.movePlayer(player_y, player_y, DIR.E.name());
	}

	public void moveSouth() { 
		this.player_y++; 
		Map.movePlayer(player_y, player_y, DIR.S.name());
	}

	public int getPlayerX() {
		return this.player_x;
	}

	public int getPlayerY() {
		return this.player_y;
	}

	public void Movement() {
		String mov;
		do {
			System.out.print(prompt_movement);
			mov = Game.STD_IN.nextLine();
		} while (!mov.matches(validMovement));

		switch (mov) {
			case "N" -> moveNorth();
			case "W" -> moveWest();
			case "E" -> moveEast();
			case "S" -> moveSouth();
		}
	}
}
