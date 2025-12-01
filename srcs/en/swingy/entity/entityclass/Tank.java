package en.swingy.entity.entityclass;

public class Tank extends EntityClass {
	public static final int BASE_ATTACK = 2;
	public static final int BASE_DEFENSE = 4;
	public static final int BASE_HP = 4;

	public Tank() {
		super(BASE_ATTACK, BASE_DEFENSE, BASE_HP);
	}	
}
