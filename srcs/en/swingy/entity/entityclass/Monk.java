package en.swingy.entity.entityclass;

public class Monk extends EntityClass {
	public static final int BASE_ATTACK = 4;
	public static final int BASE_DEFENSE = 1;
	public static final int BASE_HP = 3;

	public Monk() {
		super(BASE_ATTACK, BASE_DEFENSE, BASE_HP);
	}
}
