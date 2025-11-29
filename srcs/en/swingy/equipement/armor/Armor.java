package en.swingy.equipement.armor;

public class Armor {
	protected final String name;
	protected final int defense;

	public Armor(String p_name, int p_defense) {
		this.name = p_name;
		this.defense = p_defense;
	}

	public String getName() {
		return this.name;
	}

	public int getDefense() {
		return this.defense;
	}
}