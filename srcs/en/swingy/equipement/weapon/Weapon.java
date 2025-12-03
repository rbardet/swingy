package en.swingy.equipement.weapon;

import en.swingy.equipement.Equipement;

public class Weapon extends Equipement {
	protected final float attack;

	public Weapon(String p_name, int p_attack) {
		super(p_name);
		this.attack = p_attack;
	}

	@Override
	public float getAttack() {
		return this.attack;
	}
}