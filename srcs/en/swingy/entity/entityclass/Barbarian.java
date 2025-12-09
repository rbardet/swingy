package en.swingy.entity.entityclass;

public class Barbarian extends EntityClass {
	public static final float BASE_ATTACK = 5f;
	public static final float BASE_DEFENSE = 5f;
	public static final float BASE_HP = 10f;

	private static final float ATT_COEFF = 1.05f;
	private static final float DEF_COEFF = 1.07f;
	private static final float HP_COEFF  = 1.15f;

	public Barbarian() {
		super(BASE_ATTACK, BASE_DEFENSE, BASE_HP);
	}

	public void statsLevelUp() {
		this.setAttack(this.getAttack() * ATT_COEFF);
		this.setDefense(this.getDefense() * DEF_COEFF);
		this.setHP(this.getHP() * HP_COEFF);
	}
}
