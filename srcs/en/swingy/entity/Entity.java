package en.swingy.entity;

import en.swingy.entity.entityclass.EntityClass;

public class Entity {
	protected String name;
	protected EntityClass e_class;

	/**
	 * Constructs an Entity with a name and an EntityClass.
	 *
	 * @param p_name the name of the entity
	 * @param p_e_class the entity's class containing stats like HP, attack, defense
	 */
	public Entity(String p_name, EntityClass p_e_class) {
		this.name = p_name;
		this.e_class = p_e_class;
	}

	/**
	 * Returns the EntityClass of the entity.
	 *
	 * @return the EntityClass object
	 */
	public EntityClass getEClass() {
		return this.e_class;
	}

	/**
	 * Returns the name of the entity.
	 *
	 * @return the entity's name as a String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the entity.
	 *
	 * @param p_name the new name to assign
	 */
	public void setName(String p_name) {
		this.name = p_name;
	}

	/**
	 * Reduces the entity's HP by a specified amount.
	 *
	 * @param amount the damage amount to apply
	 */
	public void takeDamage(float amount) {
		this.e_class.setHP(this.e_class.getHP() - amount);
	}

	/**
	 * Checks if the entity is still alive.
	 *
	 * @return true if HP > 0, false otherwise
	 */
	public boolean isAlive() {
		return e_class.getHP() > 0;
	}
}
