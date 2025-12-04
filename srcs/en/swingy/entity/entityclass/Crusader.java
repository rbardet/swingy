package en.swingy.entity.entityclass;

public class Crusader extends EntityClass {
	public static final float BASE_ATTACK = 5f;
	public static final float BASE_DEFENSE = 8f;
	public static final float BASE_HP = 7f;

	private static final float ATT_COEFF = 1.04f;
	private static final float DEF_COEFF = 1.10f;
	private static final float HP_COEFF  = 1.12f;

	/**
	 * Constructor for the Crusader class.
	 * Initializes base attack, defense, and HP values.
	 */
	public Crusader() {
		super(BASE_ATTACK, BASE_DEFENSE, BASE_HP);
	}

	/**
	 * Applies level-up scaling to the Crusader's stats.
	 * Each stat is multiplied by its respective coefficient.
	 */
	public void statsLevelUp() {
		this.setAttack(this.getAttack() * ATT_COEFF);
		this.setDefense(this.getDefense() * DEF_COEFF);
		this.setHP(this.getHP() * HP_COEFF);
	}
}
