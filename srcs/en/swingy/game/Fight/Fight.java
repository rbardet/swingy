package en.swingy.game.Fight;

import java.util.Random;

import en.swingy.db.DB;
import en.swingy.entity.ennemy.Ennemy;
import en.swingy.entity.ennemy.EnnemyEnum;
import en.swingy.entity.entityclass.EntityClass;
import en.swingy.equipement.Equipement;
import en.swingy.game.Game;
import en.swingy.game.GamePrint;
import en.swingy.game.Fight.console.FightConsole;
import en.swingy.game.Fight.gui.FightGUI;
import en.swingy.hero.Hero;

public class Fight {
	public static final String FIGHT_PROMPT = GamePrint.BOLD +
	"""
	A fight started agaisnt
	""" + GamePrint.COLOR_RESET;

	public static final String LOSE_PROMPT = GamePrint.BOLD +
	"""
	You have lost the fight
	""" + GamePrint.COLOR_RESET;

	public static final String WIN_PROMPT = GamePrint.BOLD +
	"""
	You have won the fight
	""" + GamePrint.COLOR_RESET;

	public static final String FLEE_FAILED_PROMPT = GamePrint.BOLD +
	"""
	You have failed to flee
	""" + GamePrint.COLOR_RESET;

	public static final String FLEE_SUCCESS_PROMPT = GamePrint.BOLD +
	"""
	You have flee
	""" + GamePrint.COLOR_RESET;

	public static void AfterFightSim(Hero player, float player_hp)  {
		if (player_hp <= 0) {
			if (!Game.GUI_MODE) {
				GamePrint.println(LOSE_PROMPT);
			} else {
				FightGUI.Lose();
			}
		} else {
			GamePrint.println(WIN_PROMPT);
			player.addXp(250);
			player.checkLevelUp();
			Equipement drop = Equipement.generateItem(player.getLevel());

			if (!Game.GUI_MODE) {
				FightConsole.AfterFightEquipEvent(player, drop);
			} else {
				FightGUI.AfterFightEquipEvent(player, drop);
			}
			DB.updateAfterFight(player);
		}


		if (!Game.GUI_MODE) {
			GamePrint.print("Press enter to continue...");
			GamePrint.STD_IN.nextLine();
		}
	}

	public static boolean calculateFleeOdds() {
		Random r = new Random();
		int value = r.nextInt(2) + 1;
		if (value == 1) {
			GamePrint.println(FLEE_SUCCESS_PROMPT);
			return true;
		}
		GamePrint.println(FLEE_FAILED_PROMPT);
		return false;
	}

	public static float computeDamage(float atk, float def) {
		float variation = (float)(1 + (Math.random() * 0.3 - 0.15));
		float raw = atk * variation;
		float reduction = 100f / (100f + def);
		float dmg = raw * reduction;
		return Math.max(dmg, 1);
	}

	public static void Simulate(Hero player)  {
		float baseHp = player.getEClass().getHP();
		float player_hp = player.getEClass().getHP() + player.getHelm().getHP();

		Ennemy bot = new Ennemy(EnnemyEnum.getRandom(), EntityClass.randomClass());
		float player_dmg;
		float bot_dmg;
		
		GamePrint.println(FIGHT_PROMPT + bot.getName());

		do {
			player_dmg = computeDamage(player.getEClass().getAttack() + player.getWeapon().getAttack(), bot.getEClass().getDefense());
			bot.takeDamage(player_dmg);
			if (!bot.isAlive()) {
				break ;
			}
			bot_dmg = computeDamage(bot.getEClass().getAttack(), player.getEClass().getDefense() + player.getArmor().getDefense());
			player_hp -= bot_dmg;
		} while(player_hp > 0);

		AfterFightSim(player, player_hp);
		player.getEClass().setHP(baseHp);
	}
}
