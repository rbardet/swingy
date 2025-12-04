package en.swingy.equipement.weapon;

import en.swingy.equipement.Equipement;

public class Weapon extends Equipement {
	protected float attack;

	/**
	 * Constructs a Weapon with a name and attack value.
	 *
	 * @param p_name the name of the weapon
	 * @param p_attack the attack value of the weapon
	 */
	public Weapon(String p_name, float p_attack) {
		super(p_name);
		this.attack = p_attack;
	}

	/**
	 * Returns the attack value of the weapon.
	 *
	 * @return attack value as a float
	 */
	@Override
	public float getAttack() {
		return this.attack;
	}

	/**
	 * Sets the attack value of the weapon.
	 *
	 * @param p_attack new attack value
	 */
	public void setAttack(float p_attack) {
		this.attack = p_attack;
	}
}
