package fr.swingy.equipement.weapon;

public class Weapon {
	protected final String name;
	protected final int attack;

	public Weapon(String p_name, int p_attack) {
		this.name = p_name;
		this.attack = p_attack;
	}

	public String getName() {
		return this.name;
	}

	public int getAttack() {
		return this.attack;
	}
}