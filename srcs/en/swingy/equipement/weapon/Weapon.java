package en.swingy.equipement.weapon;

import en.swingy.equipement.Equipement;

public class Weapon extends Equipement {
	protected float attack;

	public Weapon(String p_name, float p_attack) {
		super(p_name);
		this.attack = p_attack;
	}

	@Override
	public float getAttack() {
		return this.attack;
	}

	public void setAttack(float p_attack) {
		this.attack = p_attack;
	}
}