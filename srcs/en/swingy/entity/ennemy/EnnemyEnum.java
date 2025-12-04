package en.swingy.entity.ennemy;

import java.util.Random;

public enum EnnemyEnum {
	DIABLO("Diablo"),
	LEORIC("Leoric"),
	BELIAL("Belial"),
	AZMODAN("Azmodan"),
	MALTHAEL("Malthael"),
	ADRIA("Adria"),
	MAGHDA("Maghda"),
	RAKANOTH("Rakanoth"),
	CYDAEA("Cydaea"),
	THE_BUTCHER("The Butcher");

	private final String label;

	EnnemyEnum(String label) {
		this.label = label;
	}

	/**
	 * Returns the label (name) of the enemy.
	 *
	 * @return enemy label as a string
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Returns the label of a randomly selected enemy from the enum.
	 *
	 * @return a random enemy label as a string
	 */
	public static String getRandom() {
		EnnemyEnum[] values = EnnemyEnum.values();
		int index = new Random().nextInt(values.length);
		return values[index].getLabel();
	}
}
