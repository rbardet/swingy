package en.swingy.equipement.weapon;

import en.swingy.equipement.Equipement;

public class Weapon extends Equipement {
	protected final int attack;

	public Weapon(String p_name, int p_attack) {
		super(p_name);
		this.attack = p_attack;
	}

	@Override
	public int getAttack() {
		return this.attack;
	}
}