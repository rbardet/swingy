package en.swingy.hero;

import en.swingy.entity.Entity;
import en.swingy.equipement.armor.Armor;
import en.swingy.equipement.helm.Helm;
import en.swingy.equipement.weapon.Weapon;
import en.swingy.hero.heroclass.Archer;
import en.swingy.hero.heroclass.Tank;
import en.swingy.hero.heroclass.Warrior;


public class Hero extends Entity {
	private int level = 1;
	private int xp = 0;
	private Weapon weapon;
	private Armor armor;
	private Helm helm;

	public Hero(String p_name, String p_class) {
		super(p_name, getAttack(p_class), getDefense(p_class), getHP(p_class));
	}

	private static int getAttack(String p_class) {
		switch (p_class) {
			case "Archer": return Archer.attack;
			case "Warrior": return Warrior.attack;
			case "Tank": return Tank.attack;
			default: return 0;
		}
	}

	private static int getDefense(String p_class) {
		switch (p_class) {
			case "Archer": return Archer.defense;
			case "Warrior": return Warrior.defense;
			case "Tank": return Tank.defense;
			default: return 0;
		}
	}

	private static int getHP(String p_class) {
		switch (p_class) {
			case "Archer": return Archer.HP;
			case "Warrior": return Warrior.HP;
			case "Tank": return Tank.HP;
			default: return 0;
		}
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