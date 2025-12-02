package en.swingy.game;

import en.swingy.entity.Ennemy;
import en.swingy.entity.entityclass.EntityClass;
import en.swingy.equipement.Equipement;
import en.swingy.hero.Hero;

public class Fight {
	public static final String START_FIGHT = "A fight started agaisnt an ennemy";
	public static final String YOU_LOST = "You have lost the fight";
	public static final String YOU_WON = "You have won the fight";
	public static final String NEW_DROP = "You have dropped : ";
	public static final String EQUIP = "Would you like to equipe it ? [y/n]";

	public static void Simulate(Hero player) {
		int save_hp = player.e_class.getHP();
		Ennemy bot = new Ennemy(Ennemy.randomName(), EntityClass.randomClass());
		System.out.println(START_FIGHT);
		do {
			bot.takeDamage(player.e_class.getAttack() - bot.e_class.getDefense());
			if (!bot.isAlive()) {
				break ;
			}
			player.takeDamage(bot.e_class.getAttack() - player.e_class.getDefense());
		} while(!player.isAlive());

		if (!player.isAlive()) {
			System.out.println(YOU_LOST);
			player.dropRandomItem();
		} else {
			System.out.println(YOU_WON);
			Equipement drop = Equipement.generateItem();
			String opt;
			do {
				System.out.println(NEW_DROP + drop.getName() + " <"  + Equipement.getType(drop) + ">");
				System.out.println(EQUIP);
				opt = Game.STD_IN.nextLine();
			} while(!opt.matches("y|n"));

			if (opt == "y") {
				player.equip(drop);
			}
		}

		player.e_class.setHP(save_hp);
	}
}
