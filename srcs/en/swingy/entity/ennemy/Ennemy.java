package en.swingy.entity.ennemy;

import en.swingy.entity.Entity;
import en.swingy.entity.entityclass.EntityClass;

public class Ennemy extends Entity {

	public Ennemy(String p_name, EntityClass p_e_class) {
		super(p_name, p_e_class);
	}

	public Ennemy(String p_name, EntityClass p_e_class, int p_level) {
		super(p_name, p_e_class);

		float att = this.getEClass().getAttack() + (this.getEClass().ATT_COEFF * p_level);
		float def = this.getEClass().getDefense() + (this.getEClass().DEF_COEFF * p_level);
		float hp = this.getEClass().getHP() + (this.getEClass().HP_COEFF * p_level); 

		this.getEClass().setAttack(att);
		this.getEClass().setDefense(def);
		this.getEClass().setHP(hp);
	}
}
