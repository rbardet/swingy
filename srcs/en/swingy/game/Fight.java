package en.swingy.game;

import java.util.Random;

import en.swingy.entity.ennemy.Ennemy;
import en.swingy.entity.ennemy.EnnemyEnum;
import en.swingy.entity.entityclass.EntityClass;
import en.swingy.equipement.Equipement;
import en.swingy.hero.Hero;

public class Fight {
	public static final String START_FIGHT = "A fight started agaisnt ";
	public static final String YOU_LOST = "You have lost the fight";
	public static final String YOU_WON = "You have won the fight";
	public static final String NEW_DROP = "You have dropped : ";
	public static final String EQUIP = "Would you like to equipe it ? [y/n]";
	public static final String FLEE = "A fight will start would you like to flee ? [y/n]";
	public static final String FLEE_FAILED = "You have failed to flee";
	public static final String FLEE_SUCCESS = "You have flee";

	public static void Simulate(Hero player) {
		int hp = player.e_class.getHP() + player.helm.getHP();
		Ennemy bot = new Ennemy(EnnemyEnum.getRandom(), EntityClass.randomClass());
		System.out.println(START_FIGHT + bot.getName());
		do {
			bot.takeDamage(player.e_class.getAttack() + player.weapon.getAttack() - bot.e_class.getDefense());
			if (!bot.isAlive()) {
				break ;
			}
			hp -= bot.e_class.getAttack() - player.e_class.getDefense() + player.armor.getDefense();
		} while(hp != 0);

		if (!player.isAlive()) {
			System.out.println(YOU_LOST);
		} else {
			System.out.println(YOU_WON);
			player.addXp(250);
			player.checkLevelUp();
			Equipement drop = Equipement.generateItem(player.getLevel());
			String opt;
			do {
				System.out.println(NEW_DROP + drop.getName() + " <"  + Equipement.getType(drop) + ">");
				System.out.println(EQUIP);
				opt = Game.STD_IN.nextLine();
			} while(!opt.matches("y|n"));

			if (opt.equals("y")) {
				player.equip(drop);
			}
		}

		System.out.print("Press enter to continue...");
		Game.STD_IN.nextLine();
	}

	public static boolean flee() {
		String opt;
		do {
			System.out.println(FLEE);
			opt = Game.STD_IN.nextLine();
		} while (!opt.matches("y|n"));

		if (opt.matches("y")) {
			Random r = new Random();
			int value = r.nextInt(2) + 1;
			if (value == 1) {
				System.out.println(FLEE_SUCCESS);
				return true;
			}
			System.out.println(FLEE_FAILED);
		}
		return false;
	}
}

