package en.swingy.equipement;

import java.util.Random;

import en.swingy.equipement.armor.Armor;
import en.swingy.equipement.armor.ArmorEnum;
import en.swingy.equipement.helm.Helm;
import en.swingy.equipement.helm.HelmEnum;
import en.swingy.equipement.weapon.Weapon;
import en.swingy.equipement.weapon.WeaponEnum;

public abstract class Equipement {
	private String name;

	public Equipement(String p_name) {
		this.name = p_name;
	}

	/**
	 * Returns the name of the equipment.
	 *
	 * @return equipment name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the equipment.
	 *
	 * @param p_name new name to set
	 */
	public void setName(String p_name) {
		this.name = p_name;
	}

	/**
	 * Generates a random stat value based on level.
	 *
	 * @param level the level of the hero/equipment
	 * @return a float value rounded to 1 decimal
	 */
	public static float generateStat(int level) {
		float value = (float) (Math.random() * level);
		return Math.round(value * 10f) / 10f;
	}

	/**
	 * Generates a random equipment item (Weapon, Helm, or Armor) with a stat based on level.
	 *
	 * @param level the level used to generate the item's stat
	 * @return a randomly generated Equipement object
	 */
	public static Equipement generateItem(int level) {
		Random r = new Random();
		int value = r.nextInt(3) + 1;
		switch (value) {
			case 1: return new Weapon(WeaponEnum.getRandom(), generateStat(level));
			case 2: return new Helm(HelmEnum.getRandom(), generateStat(level));
			case 3: return new Armor(ArmorEnum.getRandom(), generateStat(level));
			default: return null;
		}
	}

	/**
	 * Returns the attack value of the equipment.
	 * By default, returns 0 for non-weapon items.
	 *
	 * @return attack value (float)
	 */
	public float getAttack() { return 0; }

	/**
	 * Returns the defense value of the equipment.
	 * By default, returns 0 for non-armor items.
	 *
	 * @return defense value (float)
	 */
	public float getDefense() { return 0; }

	/**
	 * Returns the HP value of the equipment.
	 * By default, returns 0 for non-helm items.
	 *
	 * @return HP value (float)
	 */
	public float getHP() { return 0; }

	/**
	 * Returns the type of the equipment as a String ("Weapon", "Helm", or "Armor").
	 *
	 * @param item the equipment object
	 * @return type of the equipment as a string
	 */
	public static String getType(Equipement item) {
		if (item instanceof Weapon) {
			return ("Weapon");
		} else if (item instanceof Helm) {
			return ("Helm");
		} else {
			return ("Armor");
		}
	}
}
