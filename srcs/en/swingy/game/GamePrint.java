package en.swingy.game;

import java.util.Scanner;

import en.swingy.entity.entityclass.EntityClass;
import en.swingy.hero.Hero;

public class GamePrint {
	private static final int INFO_WIDTH = 40;
	public static final Scanner STD_IN = new Scanner(System.in);

	public static void clearTerminal() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void printTitle() {
		String title =
			"██████╗ ██╗ █████╗ ██████╗ ██╗      ██████╗     ██████╗ \n" +
			"██╔══██╗██║██╔══██╗██╔══██╗██║     ██╔═══██╗    ╚════██╗\n" +
			"██║  ██║██║███████║██████╔╝██║     ██║   ██║     █████╔╝\n" +
			"██║  ██║██║██╔══██║██╔══██╗██║     ██║   ██║     ╚═══██╗\n" +
			"██████╔╝██║██║  ██║██████╔╝███████╗╚██████╔╝    ██████╔╝\n" +
			"╚═════╝ ╚═╝╚═╝  ╚═╝╚═════╝ ╚══════╝ ╚═════╝     ╚═════╝ \n" +
			"\t\t(Terminal edition)";

		System.out.println(title);
	}

	private static String formatLine(String content, int width) {
		return String.format("┃ %-" + width + "s ┃", content);
	}

	public static void playerInfo(Hero player) {

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

	public static int askOption(String[] opt) {
		String regex = "[1-" + opt.length + "]";
		try {
			String choice;
			do {
				for(int i = 0; i < opt.length; i++) {
					System.out.println(i + 1 + " : " + opt[i]);
				}
				choice = STD_IN.nextLine();
			} while (!choice.matches(regex));
			int ret = Integer.parseInt(choice);

			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}

		Game.exitGame();
		return -1;
	}

}
