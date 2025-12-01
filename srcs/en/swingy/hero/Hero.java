package en.swingy.hero;

import en.swingy.entity.Entity;
import en.swingy.entity.entityclass.EntityClass;
import en.swingy.equipement.armor.Armor;
import en.swingy.equipement.helm.Helm;
import en.swingy.equipement.weapon.Weapon;


public class Hero extends Entity {
	private int level = 1;
	private int xp = 0;
	private Weapon weapon;
	private Armor armor;
	private Helm helm;

	public Hero(String p_name, EntityClass p_e_class) {
		super(p_name, p_e_class);
	}

	public void levelUp() {
		this.level += 1;
	}

	public void printStat() {
		System.out.println(this.e_class.getAttack());
		System.out.println(this.e_class.getDefense());
		System.out.println(this.e_class.getHP());
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public void setHelm(Helm helm) {
		this.helm = helm;
	}
}