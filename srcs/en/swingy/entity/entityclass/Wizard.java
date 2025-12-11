package en.swingy.entity.entityclass;

public class Wizard extends EntityClass {
	private static final float BASE_ATTACK = 8f;
	private static final float BASE_DEFENSE = 3f;
	private static final float BASE_HP = 9f;

	public static final float ATT_COEFF = 1.13f;
	public static final float DEF_COEFF = 1.02f;
	public static final float HP_COEFF  = 1.05f;

	public Wizard() {
		super(BASE_ATTACK, BASE_DEFENSE, BASE_HP, ATT_COEFF, DEF_COEFF, HP_COEFF);
	}

	public void statsLevelUp() {
		this.setAttack(this.getAttack() * ATT_COEFF);
		this.setDefense(this.getDefense() * DEF_COEFF);
		this.setHP(this.getHP() * HP_COEFF);
	}
}
