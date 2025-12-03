package en.swingy.entity;

import en.swingy.entity.entityclass.EntityClass;

public class Entity {
	protected String name;
	protected EntityClass e_class;

	public Entity(String p_name, EntityClass p_e_class) {
		this.name = p_name;
		this.e_class = p_e_class;
	}

	public EntityClass getEClass() {
		return this.e_class;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String p_name) {
		this.name = p_name;
	}
	
	public void takeDamage(float amount) {
		this.e_class.setHP(this.e_class.getHP() - amount);
	}

	public boolean isAlive() {
		if (e_class.getHP() <= 0) {
			return false;
		} else {
			return true;
		}
	}
}