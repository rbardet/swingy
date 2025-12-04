package en.swingy.equipement.helm;

import en.swingy.equipement.Equipement;

public class Helm extends Equipement {
	protected float hit_points;

	/**
	 * Constructs a Helm with a name and HP value.
	 *
	 * @param p_name the name of the helm
	 * @param p_hit_points the HP value provided by the helm
	 */
	public Helm(String p_name, float p_hit_points) {
		super(p_name);
		this.hit_points = p_hit_points;
	}

	/**
	 * Returns the HP value provided by the helm.
	 *
	 * @return HP value as a float
	 */
	@Override
	public float getHP() {
		return this.hit_points;
	}

	/**
	 * Sets the HP value of the helm.
	 *
	 * @param p_hp new HP value
	 */
	public void setHP(float p_hp) {
		this.hit_points = p_hp;
	}
}
