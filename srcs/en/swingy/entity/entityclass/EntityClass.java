package en.swingy.entity.entityclass;

public class EntityClass {
	protected int attack;
	protected int defense;
	protected int HP;

	public EntityClass(int p_attack, int p_defense, int p_HP) {
		this.attack = p_attack;
		this.defense = p_defense;
		this.HP = p_HP;
	}

	public int getAttack() {
		return this.attack;
	}

	public int getDefense() {
		return this.defense;
	}

	public int getHP() {
		return this.HP;
	}

	public void setAttack(int p_attack) {
		this.attack = p_attack;
	}

	public void setDefense(int p_defense) {
		this.defense = p_defense;
	}

	public void setHP(int p_HP) {
		this.HP = p_HP;
	}
}
