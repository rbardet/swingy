package en.swingy.game;

import en.swingy.entity.entityclass.EntityClass;
import en.swingy.hero.Hero;

public class Game {
	private static final String[] prompt_start = {
		"Create a new character",
		"Load a character",
		"Exit the game"
	};

	private static final String ASK_NAME = "Enter your hero name : ";
	private static final String ASK_CLASS = "Enter your hero class : ";
	public static Boolean GUI;

	private Game() {}

	public static void openGUI() {
		return ;
	}

	public static String askPlayerName() {
		GamePrint.clearTerminal();
		System.out.print(ASK_NAME);
		String name;
		do {
			name = GamePrint.STD_IN.nextLine();
		} while (name.isEmpty());
		return name;
	}
	public static int askPlayerClass() {
		GamePrint.clearTerminal();
		System.out.println(ASK_CLASS);
		int idx = GamePrint.askOption(EntityClass.prompt_class);
		return idx;
	}

	public static Hero createNewChar() {
		String name = askPlayerName();
		int idx = askPlayerClass();
		Hero player = new Hero(name, EntityClass.E_CLASS[idx - 1]);
		return player;
	}
	
	public static void loadChar() {}

	public static void exitGame() { System.exit(0); }

	public static void runGame(Hero player) {
		Map m = new Map();
		m.setMapSize(player.getLevel());
		m.generateMap();
		m.initController();
		do {
			if (!GUI) {
				GamePrint.clearTerminal();
				GamePrint.playerInfo(player);
				m.printMap();
			}
			m.playerAction(player);
		} while (!m.Clear());
	}

	public static void startGame(boolean gui) {
		GUI = gui;

		// fetchSave() need to use db to fetch last games
		// displaySave() show last games
		GamePrint.clearTerminal();
		GamePrint.printTitle();
		int opt = GamePrint.askOption(prompt_start);

		Hero player = null;
		switch (opt) {
			case 1:
				player = createNewChar();
				break;
			case 2:
				loadChar();
				break;
			default:
				exitGame();
		}

		while (true) {
			runGame(player);
		}
	}
}