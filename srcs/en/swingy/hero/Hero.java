package en.swingy.hero;

import java.sql.SQLException;

import en.swingy.db.DB;
import en.swingy.entity.Entity;
import en.swingy.entity.entityclass.EntityClass;
import en.swingy.equipement.Equipement;
import en.swingy.equipement.armor.Armor;
import en.swingy.equipement.helm.Helm;
import en.swingy.equipement.weapon.Weapon;
import en.swingy.game.GamePrint;

public class Hero extends Entity {
	private long id = -1;
	private int level = 1;
	private int xp = 0;
	private Equipement weapon;
	private Equipement armor;
	private Equipement helm;

	private final String EQUIPED_PROMPT = GamePrint.BOLD +
	"""
	You have equiped
	""" + GamePrint.COLOR_RESET;

	/**
	 * Constructor for the Hero class.
	 * @param p_name  Name of the hero
	 * @param p_e_class Class of the hero (EntityClass)
	 */
	public Hero(String p_name, EntityClass p_e_class) {
		super(p_name, p_e_class);
		this.weapon = new Weapon("None", 0);
		this.armor = new Armor("None", 0);
		this.helm = new Helm("None", 0);
	}

	/**
	 * Levels up the hero by 1 and updates its stats.
	 */
	private void levelUp() {
		this.level += 1;
		this.e_class.statsLevelUp();
	}

	/**
	 * Returns the current level of the hero.
	 * @return Hero's level
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * Returns the current experience points of the hero.
	 * @return Current XP
	 */
	public int getXP() {
		return this.xp;
	}

	/**
	 * Returns the weapon equipped by the hero.
	 * @return Equipped weapon
	 */
	public Equipement getWeapon() {
		return this.weapon;
	}

	/**
	 * Returns the armor equipped by the hero.
	 * @return Equipped armor
	 */
	public Equipement getArmor() {
		return this.armor;
	}

	/**
	 * Returns the helm equipped by the hero.
	 * @return Equipped helm
	 */
	public Equipement getHelm() {
		return this.helm;
	}

	/**
	 * Returns the ID of the hero.
	 * @return Hero's ID
	 */
	public int getId() {
		return ((int)this.id);
	}

	/**
	 * Adds experience points to the hero.
	 * @param amount Amount of XP to add
	 */
	public void addXp(int amount) {
		this.xp += amount;
	}

	/**
	 * Calculates the experience required to reach a given level.
	 * @param level Target level
	 * @return XP needed to reach the target level
	 */
	public int xpNeeded(int level) {
		return level * 1000 + (level - 1) * (level - 1) * 450;
	}

	/**
	 * Checks if the hero can level up.
	 * If so, increases level, updates stats, and updates the database.
	 * @throws SQLException If an error occurs while updating the database
	 */
	public void checkLevelUp() throws SQLException {
		if (this.xp >= xpNeeded(this.level)) {
			this.levelUp();
			this.e_class.setAttack(this.e_class.getAttack() + 1);
			this.e_class.setDefense(this.e_class.getDefense() + 1);
			this.e_class.setHP(this.e_class.getHP() + 1);
			DB.updateAfterLevel(this);
		}
	}

	/**
	 * Sets the experience points of the hero.
	 * @param xp New XP value
	 */
	public void setXp(int xp) {
		this.xp = xp;
	}

	/**
	 * Sets the level of the hero.
	 * @param level New level
	 */
	public void setLv(int level) {
		this.level = level;
	}

	/**
	 * Sets the hero's weapon.
	 * @param weapon Weapon to equip
	 */
	public void setWeapon(Equipement weapon) {
		this.weapon = weapon;
	}

	/**
	 * Sets the hero's armor.
	 * @param armor Armor to equip
	 */
	public void setArmor(Equipement armor) {
		this.armor = armor;
	}

	/**
	 * Sets the hero's helm.
	 * @param helm Helm to equip
	 */
	public void setHelm(Equipement helm) {
		this.helm = helm;
	}

	/**
	 * Sets the hero's ID.
	 * @param p_id ID to assign
	 */
	public void setId(int p_id) {
		this.id = p_id;
	}

	/**
	 * Equips an item to the hero.
	 * Depending on the item type, it will be equipped as a weapon, armor, or helm.
	 * Prints a message confirming the equipment.
	 * @param item Item to equip
	 */
	public void equip(Equipement item) {
		if (item instanceof Weapon) {
			setWeapon(item);
		} else if (item instanceof Armor) {
			setArmor(item);
		} else {
			setHelm(item);
		}
		System.out.println(EQUIPED_PROMPT + item.getName());
	}
}
