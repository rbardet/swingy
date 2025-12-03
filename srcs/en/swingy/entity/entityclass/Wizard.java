package en.swingy.entity.entityclass;

public class Wizard extends EntityClass {
	private static final float BASE_ATTACK = 8f;
	private static final float BASE_DEFENSE = 3f;
	private static final float BASE_HP = 9f;

	private static final float ATT_COEFF = 1.13f;
	private static final float DEF_COEFF = 1.02f;
	private static final float HP_COEFF  = 1.05f;

	public Wizard() {
		super(BASE_ATTACK, BASE_DEFENSE, BASE_HP);
	}

	public void statsLevelUp() {
		this.setAttack(this.getAttack() * ATT_COEFF);
		this.setDefense(this.getDefense() * DEF_COEFF);
		this.setHP(this.getHP() * HP_COEFF);
	}
}
