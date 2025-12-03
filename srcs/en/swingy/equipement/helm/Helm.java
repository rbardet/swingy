package en.swingy.equipement.helm;

import en.swingy.equipement.Equipement;

public class Helm extends Equipement {
	protected final float hit_points;

	public Helm(String p_name, float p_hit_points) {
		super(p_name);
		this.hit_points = p_hit_points;
	}

	@Override
	public float getHP() {
		return this.hit_points;
	}
}