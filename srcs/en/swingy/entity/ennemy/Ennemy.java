package en.swingy.entity.ennemy;

import en.swingy.entity.Entity;
import en.swingy.entity.entityclass.EntityClass;

public class Ennemy extends Entity {

	/**
	 * Constructor for the Ennemy class.
	 * Initializes the enemy with a name and a specific entity class (stats).
	 *
	 * @param p_name Name of the enemy
	 * @param p_e_class EntityClass instance defining the enemy's stats
	 */
	public Ennemy(String p_name, EntityClass p_e_class) {
		super(p_name, p_e_class);
	}
}
