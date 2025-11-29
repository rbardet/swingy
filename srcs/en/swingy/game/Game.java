package en.swingy.game;

import en.swingy.hero.Hero;

public class Game {
	private static final String[] options = {
		"Create a new character",
		"Load a character",
		"Exit the game"
	};
	
	private static final String[] heroClass = {
		"Archer",
		"Tank",
		"Warrior"
	};

	private static final String ASK_NAME = "Enter your hero name";

	private Game() {}

	public static void clearTerminal() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void openGUI() {
		return ;
	}

	public static int askOption(String[] opt) {
		clearTerminal();
		for(int i = 0; i < opt.length; i++) {
			System.out.println(i + 1 + " : " + opt[i]);
		}

		try {
			int choice = System.in.read();
			return choice;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static Hero createNewChar() {
		System.out.println(ASK_NAME);
		String name = System.in.toString();
		int i = askOption(heroClass);
		Hero player = new Hero(name, heroClass[i]);
		return player;
	}
	
	public static void loadChar() {
	}

	public static void exitGame() {
		System.exit(0);
	}

	public static void startGame(boolean gui) {
		if (gui) {
			openGUI();
		}

		// fetchSave() need to use db to fetch last games
		// displaySave() show last games
		int opt = askOption(options);
		Hero player;
		switch (opt) {
			case '1':
				player = createNewChar();
				System.out.println(player);
				break;
			case '2':
				loadChar();
				break;
			default:
				exitGame();
		}
	}
}