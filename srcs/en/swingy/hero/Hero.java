package en.swingy.hero;

import java.util.Random;

import en.swingy.entity.Entity;
import en.swingy.entity.entityclass.EntityClass;
import en.swingy.equipement.Equipement;
import en.swingy.equipement.armor.Armor;
import en.swingy.equipement.helm.Helm;
import en.swingy.equipement.weapon.Weapon;

public class Hero extends Entity {
	private int level = 1;
	private int xp = 0;
	private Equipement weapon;
	private Equipement armor;
	private Equipement helm;

	private final String EQUIPED = "You have equiped ";

	public Hero(String p_name, EntityClass p_e_class) {
		super(p_name, p_e_class);
		this.weapon = new Weapon("None", 0);
		this.armor = new Armor("None", 0);
		this.helm = new Helm("None", 0);
	}

	public void levelUp() {
		this.level += 1;
	}

	public void printStat() {
		System.out.println(this.e_class.getAttack());
		System.out.println(this.e_class.getDefense());
		System.out.println(this.e_class.getHP());
	}

	public int getLevel() {
		return this.level;
	}

	public int getXP() {
		return this.xp;
	}
	
	public void dropItem(Equipement item) {
		System.out.println("You have lost " + item.getName());
		item = null;
	}

	public void dropRandomItem() {
		Random r = new Random();
		int value = r.nextInt(3) + 1;
		switch (value) {
			case 1:
				dropItem(this.weapon);
				break ;
			case 2:
				dropItem(this.armor);
				break ;
			case 3:
				dropItem(this.helm);
				break ;
			default:
				break;
		}
	}

	public String getWeapon() {
		return this.weapon.getName();
	}

	public String getArmor() {
		return this.armor.getName();
	}

	public String getHelm() {
		return this.helm.getName();
	}

	public void setWeapon(Equipement weapon) {
		this.weapon = weapon;
	}

	public void setArmor(Equipement armor) {
		this.armor = armor;
	}

	public void setHelm(Equipement helm) {
		this.helm = helm;
	}

	public void equip(Equipement item) {
		if (item instanceof Weapon) {
			setWeapon(item);
		} else if (item instanceof Armor) {
			setArmor(item);
		} else {
			setHelm(item);
		}
		System.out.println(EQUIPED + item.getName());
	}
}