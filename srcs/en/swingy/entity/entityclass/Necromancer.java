package en.swingy.entity.entityclass;

public class Necromancer extends EntityClass {
	public static final float BASE_ATTACK = 8f;
	public static final float BASE_DEFENSE = 4f;
	public static final float BASE_HP = 8f;

	public static final float ATT_COEFF = 1.10f;
	public static final float DEF_COEFF = 1.04f;
	public static final float HP_COEFF  = 1.06f;

	public Necromancer() {
		super(BASE_ATTACK, BASE_DEFENSE, BASE_HP, ATT_COEFF, DEF_COEFF, HP_COEFF);
	}

	public void statsLevelUp() {
		this.setAttack(this.getAttack() * ATT_COEFF);
		this.setDefense(this.getDefense() * DEF_COEFF);
		this.setHP(this.getHP() * HP_COEFF);
	}
}
