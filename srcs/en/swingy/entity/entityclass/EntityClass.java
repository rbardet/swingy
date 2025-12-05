package en.swingy.entity.entityclass;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import en.swingy.game.GUI;

public abstract class EntityClass {
	protected float attack;
	protected float defense;
	protected float HP;

	public static final EntityClass[] E_CLASS = {
		new Barbarian(),
		new Crusader(),
		new DemonHunter(),
		new Monk(),
		new Necromancer(),
		new WitchDoctor(),
		new Wizard()
	};

	public static final String[] prompt_class = {
		"Barbarian 5/5/10",
		"Crusader 5/8/7",
		"Demon Hunter 9/4/7",
		"Monk 7/6/7",
		"Necromancer 8/4/8",
		"Witch Doctor 10/3/7",
		"Wizard 8/3/9"
	};

	/**
	 * Constructs an EntityClass with specified stats.
	 *
	 * @param p_attack base attack value
	 * @param p_defense base defense value
	 * @param p_HP base HP value
	 */
	public EntityClass(float p_attack, float p_defense, float p_HP) {
		this.attack = p_attack;
		this.defense = p_defense;
		this.HP = p_HP;
	}

	/**
	 * Returns the attack value.
	 *
	 * @return attack as float
	 */
	public float getAttack() {
		return this.attack;
	}

	/**
	 * Returns the defense value.
	 *
	 * @return defense as float
	 */
	public float getDefense() {
		return this.defense;
	}

	/**
	 * Returns the HP value.
	 *
	 * @return HP as float
	 */
	public float getHP() {
		return this.HP;
	}

	/**
	 * Sets the attack value.
	 *
	 * @param p_attack new attack value
	 */
	public void setAttack(float p_attack) {
		this.attack = p_attack;
	}

	/**
	 * Sets the defense value.
	 *
	 * @param p_defense new defense value
	 */
	public void setDefense(float p_defense) {
		this.defense = p_defense;
	}

	/**
	 * Sets the HP value.
	 *
	 * @param p_HP new HP value
	 */
	public void setHP(float p_HP) {
		this.HP = p_HP;
	}

	/**
	 * Returns a random EntityClass from the predefined array.
	 *
	 * @return a randomly selected EntityClass
	 */
	public static EntityClass randomClass() {
		Random r = new Random();
		int index = r.nextInt(E_CLASS.length);
		return E_CLASS[index];
	}

	/**
	 * Returns the type of the EntityClass as a simple class name string.
	 *
	 * @param e the EntityClass instance
	 * @return class name as String
	 */
	public static String getType(EntityClass e) {
		return e.getClass().getSimpleName();
	}

	/**
	 * Returns a new EntityClass instance based on a given class name.
	 *
	 * @param name the class name as a string
	 * @return a new EntityClass instance, or null if the name doesn't match
	 */
	public static EntityClass getClassByName(String name) {
		switch (name) {
			case "Barbarian": return new Barbarian();
			case "Crusader": return new Crusader();
			case "DemonHunter": return new DemonHunter();
			case "Monk": return new Monk();
			case "Necromancer": return new Necromancer();
			case "WitchDoctor": return new WitchDoctor();
			case "Wizard": return new Wizard();
			default: return null;
		}
	}

	public static ImageIcon getClassAssetsByName(String name) {
		switch (name) {
			case "Barbarian": return new ImageIcon(GUI.BARBARIAN_HOVER_ICON);
			case "Crusader": return new ImageIcon(GUI.CRUSADER_HOVER_ICON);
			case "DemonHunter": return new ImageIcon(GUI.DEMONHUNTER_HOVER_ICON);
			case "Monk": return new ImageIcon(GUI.MONK_HOVER_ICON);
			case "Necromancer": return new ImageIcon(GUI.NECROMANCER_HOVER_ICON);
			case "WitchDoctor": return new ImageIcon(GUI.WITCHDOCTOR_HOVER_ICON);
			case "Wizard": return new ImageIcon(GUI.WITCHDOCTOR_HOVER_ICON);
			default: return null;
		}
	}

	/**
	 * Abstract method to handle stat increases when leveling up.
	 * Must be implemented by all subclasses.
	 */
	public abstract void statsLevelUp();
}
