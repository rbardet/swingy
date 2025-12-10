package en.swingy.game.Fight.console;

import en.swingy.equipement.Equipement;
import en.swingy.game.GamePrint;
import en.swingy.game.Fight.Fight;
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
			if (Fight.calculateFleeOdds())  {
				return true;
			}
		}
		return false;
	}
}
