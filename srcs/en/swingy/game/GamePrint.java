package en.swingy.game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import en.swingy.db.DB;
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
			"Id: " + player.getId(),
			"Username: " + player.getName(),
			"Class: " + EntityClass.getType(player.getEClass()),
			"Level: " + player.getLevel(),
			"XP: " + player.getXP(),
			"Attack: " + player.getEClass().getAttack()+ " + " + player.getWeapon().getAttack(),
			"Defense: " + player.getEClass().getDefense() + " + " + player.getArmor().getDefense(),
			"HP: " + player.getEClass().getHP() + " + " + player.getHelm().getHP(),
			"Weapon: " + player.getWeapon().getName(),
			"Armor: " + player.getArmor().getName(),
			"Helm: " + player.getHelm().getName()
		};

		System.out.println("┏" + "━".repeat(INFO_WIDTH + 2) + "┓");
		for (String line : lines)
			System.out.println(formatLine(line, INFO_WIDTH));
		System.out.println("┗" + "━".repeat(INFO_WIDTH + 2) + "┛");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
	}

	public static void displaySave(ResultSet rs) throws SQLException {
		System.out.println("Player save :");
		ResultSet save = rs;
		while (save.next()) {
			String[] lines = {
				"Id: " + save.getInt(DB.ID_VAR),
				"Username: " + save.getString(DB.NAME_VAR),
				"Class: " + save.getString(DB.CLASS_VAR),
				"Level: " + save.getInt(DB.LV_VAR)
			};

			System.out.println("┏" + "━".repeat(INFO_WIDTH + 2) + "┓");
			for (String line : lines) {
				System.out.println(formatLine(line, INFO_WIDTH));
			}
			System.out.println("┗" + "━".repeat(INFO_WIDTH + 2) + "┛");
			System.out.println();
		}

		save.close();
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
