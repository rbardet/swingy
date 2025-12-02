package en.swingy.equipement.armor;

import en.swingy.equipement.Equipement;

public class Armor extends Equipement {
	protected final int defense;

	public Armor(String p_name, int p_defense) {
		super(p_name);
		this.defense = p_defense;
	}

	public int getDefense() {
		return this.defense;
	}
}