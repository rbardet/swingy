package en.swingy.game;

public class Game {
	private static final String[] options = {
		"Create a new character",
		"Load a character",
		"Exit the game"
	};

	private Game() {}

	public static void clearTerminal() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void openGUI() {
		return ;
	}

	public static void askOption() {
		for(int i = 0; i < options.length; i++) {
			System.out.println(i + 1 + " : " + options[i]);
		}
	}

	public static void createNewChar() {

	}
	
	public static void loadChar() {
		
	}

	public static void exitGame() {
		System.exit(0);
	}

	public static void startGame(boolean gui) {
		clearTerminal();
		if (gui) {
			openGUI();
		}

		// fetchSave() need to use db to fetch last games
		// displaySave() show last games
		askOption();
		while (true) {
			try {
				int opt = System.in.read();
				switch (opt) {
					case '1':
						createNewChar();
						break;
					case '2':
						loadChar();
						break;
					case '3':
						exitGame();
						break;
					default:
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}