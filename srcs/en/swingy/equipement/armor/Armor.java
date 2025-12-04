package en.swingy.equipement.armor;

import en.swingy.equipement.Equipement;

public class Armor extends Equipement {
	protected float defense;

	/**
	 * Constructor for Armor.
	 *
	 * @param p_name Name of the armor
	 * @param p_defense Defense value of the armor
	 */
	public Armor(String p_name, float p_defense) {
		super(p_name);
		this.defense = p_defense;
	}

	/**
	 * Returns the defense value of the armor.
	 *
	 * @return Armor's defense
	 */
	@Override
	public float getDefense() {
		return this.defense;
	}

	/**
	 * Sets the defense value of the armor.
	 *
	 * @param p_defense New defense value
	 */
	public void setDefense(float p_defense) {
		this.defense = p_defense;
	}
}
