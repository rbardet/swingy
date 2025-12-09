package en.swingy.entity.entityclass;

public class WitchDoctor extends EntityClass {
	public static final float BASE_ATTACK = 10f;
	public static final float BASE_DEFENSE = 3f;
	public static final float BASE_HP = 7f;

	private static final float ATT_COEFF = 1.10f;
	private static final float DEF_COEFF = 1.03f;
	private static final float HP_COEFF  = 1.07f;

	public WitchDoctor() {
		super(BASE_ATTACK, BASE_DEFENSE, BASE_HP);
	}

	public void statsLevelUp() {
		this.setAttack(this.getAttack() * ATT_COEFF);
		this.setDefense(this.getDefense() * DEF_COEFF);
		this.setHP(this.getHP() * HP_COEFF);
	}
}
