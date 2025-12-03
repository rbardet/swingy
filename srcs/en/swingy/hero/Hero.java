package en.swingy.hero;

import en.swingy.entity.Entity;
import en.swingy.entity.entityclass.EntityClass;
import en.swingy.equipement.Equipement;
import en.swingy.equipement.armor.Armor;
import en.swingy.equipement.helm.Helm;
import en.swingy.equipement.weapon.Weapon;

public class Hero extends Entity {
	private int level = 1;
	private int xp = 0;
	public Equipement weapon;
	public Equipement armor;
	public Equipement helm;

	private final String EQUIPED = "You have equiped ";

	public Hero(String p_name, EntityClass p_e_class) {
		super(p_name, p_e_class);
		this.weapon = new Weapon("None", 0);
		this.armor = new Armor("None", 0);
		this.helm = new Helm("None", 0);
	}

	private void levelUp() {
		this.level += 1;
		this.e_class.statsLevelUp();
	}

	public int getLevel() {
		return this.level;
	}

	public int getXP() {
		return this.xp;
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

	public void addXp(int amount) {
		this.xp += amount;
	}

	public int xpNeeded(int level) {
		return level * 1000 + (level - 1) * (level - 1) * 450;
	}

	public void checkLevelUp() {
		if (this.xp >= xpNeeded(this.level)) {
			this.levelUp();
			this.e_class.setAttack(this.e_class.getAttack() + 1);
			this.e_class.setDefense(this.e_class.getDefense() + 1);
			this.e_class.setHP(this.e_class.getHP() + 1);
		}
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