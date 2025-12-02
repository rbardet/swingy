package en.swingy.entity.entityclass;

public class Barbarian extends EntityClass {
	public static final int BASE_ATTACK = 3;
	public static final int BASE_DEFENSE = 3;
	public static final int BASE_HP = 3;

	public Barbarian() {
		super(BASE_ATTACK, BASE_DEFENSE, BASE_HP);
	}
}
