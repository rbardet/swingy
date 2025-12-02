package en.swingy.equipement;

import java.util.Random;

import en.swingy.equipement.armor.Armor;
import en.swingy.equipement.armor.ArmorEnum;
import en.swingy.equipement.helm.Helm;
import en.swingy.equipement.helm.HelmEnum;
import en.swingy.equipement.weapon.Weapon;
import en.swingy.equipement.weapon.WeaponEnum;

public class Equipement {
	private final String name;

	public Equipement(String p_name) {
		this.name = p_name;
	}

	public String getName() {
		return this.name;
	}

	public static Equipement generateItem() {
		Random r = new Random();
		int value = r.nextInt(3) + 1;
		switch (value) {
			case 1: return new Weapon(WeaponEnum.getRandom(), value);
			case 2: return new Helm(HelmEnum.getRandom(), value);
			case 3: return new Armor(ArmorEnum.getRandom(), value);
			default: return null;
		}
	}

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
