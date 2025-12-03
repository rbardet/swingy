package en.swingy.entity.entityclass;

public class DemonHunter extends EntityClass {
	public static final float BASE_ATTACK = 9f;
	public static final float BASE_DEFENSE = 4f;
	public static final float BASE_HP = 7f;

	private static final float ATT_COEFF = 1.12f;
	private static final float DEF_COEFF = 1.03f;
	private static final float HP_COEFF  = 1.05f;

	public DemonHunter() {
		super(BASE_ATTACK, BASE_DEFENSE, BASE_HP);
	}

	public void statsLevelUp() {
		this.setAttack(this.getAttack() * ATT_COEFF);
		this.setDefense(this.getDefense() * DEF_COEFF);
		this.setHP(this.getHP() * HP_COEFF);
	}
}
