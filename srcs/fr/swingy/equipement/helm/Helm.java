package fr.swingy.equipement.helm;

public class Helm {
	protected final String name;
	protected final int hit_points;

	public Helm(String p_name, int p_hit_points) {
		this.name = p_name;
		this.hit_points = p_hit_points;
	}

	public String getName() {
		return this.name;
	}

	public int getHitPoints() {
		return this.hit_points;
	}
}