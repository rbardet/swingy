package en.swingy.game.Fight.console;

import java.util.Random;

import en.swingy.equipement.Equipement;
import en.swingy.game.GamePrint;
import en.swingy.hero.Hero;

public class FightConsole {
	public static final String DROP_PROMPT = GamePrint.BOLD +
	"""
	You have dropped :
	""" + GamePrint.COLOR_RESET;

	public static final String EQUIP_PROMPT = GamePrint.BOLD +
	"""
	Would you like to equipe it ? [y/n]
	""" + GamePrint.COLOR_RESET;

	public static final String FLEE_PROMPT = GamePrint.BOLD +
	"""
	A fight will start would you like to flee ? [y/n]
	""" + GamePrint.COLOR_RESET;

	public static final String FLEE_FAILED_PROMPT = GamePrint.BOLD +
	"""
	You have failed to flee
	""" + GamePrint.COLOR_RESET;

	public static final String FLEE_SUCCESS_PROMPT = GamePrint.BOLD +
	"""
	You have flee
	""" + GamePrint.COLOR_RESET;

	public static void AfterFightEquipEvent(Hero player, Equipement drop) {
			String opt;
			do {
				System.out.println(DROP_PROMPT + drop.getName() + " <"  + Equipement.getType(drop) + ">");
				System.out.println(EQUIP_PROMPT);
				opt = GamePrint.STD_IN.nextLine();
			} while(!opt.matches("y|n"));

			if (opt.equals("y")) {
				player.equip(drop);
			}
	}

	public static boolean Flee() {
		String opt;
		do {
			System.out.println(FLEE_PROMPT);
			opt = GamePrint.STD_IN.nextLine();
		} while (!opt.matches("y|n"));

		if (opt.matches("y")) {
			Random r = new Random();
			int value = r.nextInt(2) + 1;
			if (value == 1) {
				System.out.println(FLEE_SUCCESS_PROMPT);
				return true;
			}
			System.out.println(FLEE_FAILED_PROMPT);
		}
		return false;
	}
}
