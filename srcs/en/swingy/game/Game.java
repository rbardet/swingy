package en.swingy.game;

import en.swingy.entity.entityclass.EntityClass;
import en.swingy.hero.Hero;
import java.util.Scanner;

public class Game {
	private static final String[] prompt_start = {
		"Create a new character",
		"Load a character",
		"Exit the game"
	};

	private static final String[] prompt_class = {
		"Barbarian",
		"Crusader",
		"Demon Hunter",
		"Monk",
		"Necromancer",
		"Witch Doctor",
		"Wizard"
	};

	public static final Scanner STD_IN = new Scanner(System.in);
	private static final String ASK_NAME = "Enter your hero name : ";
	private static final String ASK_CLASS = "Enter your hero class : ";
	private static final int INFO_WIDTH = 40;

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
		int i = EntityClass.askClass(prompt_class);
		Hero player = new Hero(name, EntityClass.E_CLASS[i - 1]);
		return player;
	}
	
	public static void loadChar() {}
	
	private static String formatLine(String content, int width) {
		return String.format("┃ %-" + width + "s ┃", content);
	}

	public static void printInfo(Hero player) {

		String[] lines = {
			"Username: " + player.getName(),
			"Class: " + EntityClass.getType(player.e_class),
			"Level: " + player.getLevel(),
			"XP: " + player.getXP(),
			"Attack: " + player.e_class.getAttack()+ " + " + player.weapon.getAttack(),
			"Defense: " + player.e_class.getDefense() + " + " + player.helm.getDefense(),
			"HP: " + player.e_class.getHP() + " + " + player.helm.getHP(),
			"Weapon: " + player.getWeapon(),
			"Armor: " + player.getArmor(),
			"Helm: " + player.getHelm()
		};

		System.out.println("┏" + "━".repeat(INFO_WIDTH + 2) + "┓");
		for (String line : lines)
			System.out.println(formatLine(line, INFO_WIDTH));
		System.out.println("┗" + "━".repeat(INFO_WIDTH + 2) + "┛");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}

	public static void exitGame() { System.exit(0); }

	public static void runGame(Hero player) {
		Map m = new Map();
		m.setMapSize(player.getLevel());
		m.generateMap();
		m.initController();
		do {
			clearTerminal();
			printInfo(player);
			m.printMap();
			m.playerAction(player);
		} while (!m.Clear());

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

		while (true) {
			runGame(player);
		}
	}
}