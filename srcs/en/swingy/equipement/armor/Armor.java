package en.swingy.equipement.armor;

import en.swingy.equipement.Equipement;

public class Armor extends Equipement {
	protected float defense;

	public Armor(String p_name, float p_defense) {
		super(p_name);
		this.defense = p_defense;
	}

	@Override
	public float getDefense() {
		return this.defense;
	}

	public void setDefense(float p_defense) {
		this.defense = p_defense;
	}
}
