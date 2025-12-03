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

	public String getName() {
		return this.name;
	}

	public void setName(String p_name) {
		this.name = p_name;
	}

	public static int generateStat(int level) {
		Random r = new Random();
		return r.nextInt(level) + 1;
	}

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
	
	public float getAttack() { return 0; }

	public float getDefense() { return 0; }

	public float getHP() { return 0; }

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
