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

	public static final String COLOR_RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String BLUE = "\u001B[34m";
	public static final String BOLD = "\u001B[1m";

	public static void clearTerminal() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void printTitle() {
		String title = BOLD +
			"██████╗ ██╗ █████╗ ██████╗ ██╗      ██████╗     ██████╗ \n" +
			"██╔══██╗██║██╔══██╗██╔══██╗██║     ██╔═══██╗    ╚════██╗\n" +
			"██║  ██║██║███████║██████╔╝██║     ██║   ██║     █████╔╝\n" +
			"██║  ██║██║██╔══██║██╔══██╗██║     ██║   ██║     ╚═══██╗\n" +
			"██████╔╝██║██║  ██║██████╔╝███████╗╚██████╔╝    ██████╔╝\n" +
			"╚═════╝ ╚═╝╚═╝  ╚═╝╚═════╝ ╚══════╝ ╚═════╝     ╚═════╝ \n" +
			COLOR_RESET + "\t\t(Terminal edition)";
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

	public static void displaySave()  {
		try {
			ResultSet rs = DB.fetchSaves();
			while (rs.next()) {
				String[] lines = {
					"Id: " + rs.getString(DB.ID_VAR),
					"Username: " + rs.getString(DB.NAME_VAR),
					"Class: " + rs.getString(DB.CLASS_VAR),
					"Level: " + rs.getInt(DB.LV_VAR),
					"XP: " + rs.getInt(DB.XP_VAR),
					"Attack: " + rs.getInt(DB.ATT_VAR) + " + " + rs.getInt(DB.WP_ATT_VAR),
					"Defense: " + rs.getInt(DB.DEF_VAR) + " + " + rs.getInt(DB.AM_DEF_VAR),
					"HP: " + rs.getInt(DB.HP_VAR) + " + " + rs.getInt(DB.HL_HP_VAR),
					"Weapon: " + rs.getString(DB.WP_NAME_VAR),
					"Armor: " + rs.getString(DB.AM_NAME_VAR),
					"Helm: " + rs.getString(DB.HL_NAME_VAR)
				};
				System.out.println("┏" + "━".repeat(INFO_WIDTH + 2) + "┓");
				for (String line : lines) {
					System.out.println(formatLine(line, INFO_WIDTH));
				}
				System.out.println("┗" + "━".repeat(INFO_WIDTH + 2) + "┛");
				System.out.println();
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

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

	public static void println(String s) {
		if (!Game.GUI) {
			System.out.println(s);
		}
	}

}
