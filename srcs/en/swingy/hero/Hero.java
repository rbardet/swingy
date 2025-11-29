package en.swingy.hero;

import en.swingy.entity.Entity;
import en.swingy.equipement.armor.Armor;
import en.swingy.equipement.helm.Helm;
import en.swingy.equipement.weapon.Weapon;


public class Hero extends Entity {
	private int level = 1;
	private int xp = 0;
	private Weapon weapon;
	private Armor armor;
	private Helm helm;

	public Hero(String p_name) {
		super(p_name, 1, 1, 1);
	}

	public void levelUp() {
		this.level += 1;
	}

	void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	void setArmor(Armor armor) {
		this.armor = armor;
	}

	void setHelm(Helm helm) {
		this.helm = helm;
	}
}