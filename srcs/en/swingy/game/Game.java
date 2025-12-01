package en.swingy.game;

import en.swingy.entity.Entity;
import en.swingy.entity.entityclass.Archer;
import en.swingy.entity.entityclass.EntityClass;
import en.swingy.entity.entityclass.Tank;
import en.swingy.entity.entityclass.Warrior;
import en.swingy.hero.Hero;
import java.util.Scanner;

public class Game {
	private static final String[] prompt_start = {
		"Create a new character",
		"Load a character",
		"Exit the game"
	};

	private static final String[] prompt_class = {
		"Archer",
		"Tank",
		"Warrior"
	};

	private static final EntityClass[] heroClass = {
		new Archer(),
		new Tank(),
		new Warrior(),
	};

	private static final String ASK_NAME = "Enter your hero name : ";
	private static final String ASK_CLASS = "Enter your hero class : ";
	private static final Scanner STD_IN = new Scanner(System.in);
	private static final int INFO_WIDTH = 32;

	private Game() {}

	public static void clearTerminal() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void openGUI() {
		return ;
	}

	public static int askOption(String[] opt) {
		try {
			String choice;
			do {
				clearTerminal();
				for(int i = 0; i < opt.length; i++) {
					System.out.println(i + 1 + " : " + opt[i]);
				}
				choice = STD_IN.nextLine();
			} while (!choice.matches("[1-3]"));
			int ret = Integer.parseInt(choice);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}

		exitGame();
		return -1;
	}

	public static Hero createNewChar() {
		clearTerminal();
		System.out.print(ASK_NAME);
		String name;
		do {
			name = STD_IN.nextLine();
		} while (name.isEmpty());
		System.out.print(ASK_CLASS);
		int i = askOption(prompt_class);
		Hero player = new Hero(name, heroClass[i - 1]);
		return player;
	}
	
	public static void loadChar() {}
	
	private static String formatLine(String content, int width) {
		return String.format("┃ %-"+width+"s ┃", content);
	}

	public static void printInfo(Hero player) {
		System.out.println("┏" + "━".repeat(INFO_WIDTH + 2) + "┓");
		System.out.println(formatLine("Username: " + player.getName(), INFO_WIDTH));
		System.out.println(formatLine("Level: " + player.getLevel(), INFO_WIDTH));
		System.out.println(formatLine("XP: " + player.getXP(), INFO_WIDTH));
		System.out.println(formatLine("Attack: " + player.getAttack(), INFO_WIDTH));
		System.out.println(formatLine("Defense: " + player.getDefense(), INFO_WIDTH));
		System.out.println(formatLine("HP: " + player.getHP(), INFO_WIDTH));
		System.out.println("┗" + "━".repeat(INFO_WIDTH + 2) + "┛");
	}

	public static void exitGame() { System.exit(0); }

	public static void runGame(Hero player) {
		clearTerminal();
		printInfo(player);
	}

	public static void startGame(boolean gui) {
		if (gui) {
			openGUI();
		}

		// fetchSave() need to use db to fetch last games
		// displaySave() show last games
		int opt = askOption(prompt_start);

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
		runGame(player);
	}
}