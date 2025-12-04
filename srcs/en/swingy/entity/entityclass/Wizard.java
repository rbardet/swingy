package en.swingy.entity.entityclass;

public class Wizard extends EntityClass {
	private static final float BASE_ATTACK = 8f;
	private static final float BASE_DEFENSE = 3f;
	private static final float BASE_HP = 9f;

	private static final float ATT_COEFF = 1.13f;
	private static final float DEF_COEFF = 1.02f;
	private static final float HP_COEFF  = 1.05f;

	/**
	 * Constructor for the Wizard class.
	 * Initializes base attack, defense, and HP values.
	 */
	public Wizard() {
		super(BASE_ATTACK, BASE_DEFENSE, BASE_HP);
	}

	/**
	 * Applies level-up scaling to the Wizard's stats.
	 * Each stat is multiplied by its respective coefficient.
	 */
	public void statsLevelUp() {
		this.setAttack(this.getAttack() * ATT_COEFF);
		this.setDefense(this.getDefense() * DEF_COEFF);
		this.setHP(this.getHP() * HP_COEFF);
	}
}
