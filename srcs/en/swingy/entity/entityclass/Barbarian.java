package en.swingy.entity.entityclass;

public class Barbarian extends EntityClass {
	public static final float BASE_ATTACK = 5f;
	public static final float BASE_DEFENSE = 5f;
	public static final float BASE_HP = 10f;

	public static final float ATT_COEFF = 1.05f;
	public static final float DEF_COEFF = 1.07f;
	public static final float HP_COEFF  = 1.15f;

	public Barbarian() {
		super(BASE_ATTACK, BASE_DEFENSE, BASE_HP, ATT_COEFF, DEF_COEFF, HP_COEFF);
	}

	public void statsLevelUp() {
		this.setAttack(this.getAttack() * ATT_COEFF);
		this.setDefense(this.getDefense() * DEF_COEFF);
		this.setHP(this.getHP() * HP_COEFF);
	}
}
