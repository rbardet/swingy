package fr.swingy.character;

public class Character {
	protected int attack;
	protected int defense;
	protected int hit_points;

	Character(int p_attack, int p_defense, int p_hit_points) {
		this.attack = p_attack;
		this.defense = p_defense;
		this.hit_points = p_hit_points;
	}
	
	public void setAttack(int p_attack) {
		this.attack = p_attack;
	}

	public void setDefense(int p_defense) {
		this.defense = p_defense;
	}

	public void setHitPoints(int p_hit_points) {
		this.hit_points = p_hit_points;
	}

	public int getAttack() {
		return this.attack;
	}

	public int getDefense() {
		return this.defense;
	}

	public int getHitPoints() {
		return this.hit_points;
	}
}