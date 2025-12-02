package en.swingy.equipement.helm;

import en.swingy.equipement.Equipement;

public class Helm extends Equipement {
	protected final int hit_points;

	public Helm(String p_name, int p_hit_points) {
		super(p_name);
		this.hit_points = p_hit_points;
	}

	public int getHitPoints() {
		return this.hit_points;
	}
}