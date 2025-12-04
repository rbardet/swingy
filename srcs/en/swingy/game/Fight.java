package en.swingy.game;

import java.sql.SQLException;
import java.util.Random;

import en.swingy.db.DB;
import en.swingy.entity.ennemy.Ennemy;
import en.swingy.entity.ennemy.EnnemyEnum;
import en.swingy.entity.entityclass.EntityClass;
import en.swingy.equipement.Equipement;
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

	public static void AfterFightSim(Hero player, float player_hp) throws SQLException {
		if (player_hp <= 0) {
			System.out.println(LOSE_PROMPT);
		} else {
			System.out.println(WIN_PROMPT);
			player.addXp(250);
			player.checkLevelUp();
			Equipement drop = Equipement.generateItem(player.getLevel());
			String opt;
			do {
				System.out.println(DROP_PROMPT + drop.getName() + " <"  + Equipement.getType(drop) + ">");
				System.out.println(EQUIP_PROMPT);
				opt = GamePrint.STD_IN.nextLine();
			} while(!opt.matches("y|n"));

			if (opt.equals("y")) {
				player.equip(drop);
			}

			DB.updateAfterFight(player);
		}

		System.out.print("Press enter to continue...");
		GamePrint.STD_IN.nextLine();
	}

	public static float computeDamage(float atk, float def) {
		float variation = (float)(1 + (Math.random() * 0.3 - 0.15));
		float raw = atk * variation;

		float reduction = 100f / (100f + def);
		float dmg = raw * reduction;

		return Math.max(dmg, 1);
	}

	public static void Simulate(Hero player) throws SQLException {
		float baseHp = player.getEClass().getHP();
		float player_hp = player.getEClass().getHP() + player.getHelm().getHP();

		Ennemy bot = new Ennemy(EnnemyEnum.getRandom(), EntityClass.randomClass());
		float player_dmg;
		float bot_dmg;
		System.out.println(FIGHT_PROMPT + bot.getName());

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

	public static boolean flee() {
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

