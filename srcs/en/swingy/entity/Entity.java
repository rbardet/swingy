package en.swingy.entity;

public class Entity {
	protected String name;
	protected int attack;
	protected int defense;
	protected int hit_points;

	public Entity(String p_name, int p_attack, int p_defense, int p_hit_points) {
		this.name = p_name;
		this.attack = p_attack;
		this.defense = p_defense;
		this.hit_points = p_hit_points;
	}

	public String getName() {
		return this.name;
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

	public void setName(String p_name) {
		this.name = p_name;
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
}