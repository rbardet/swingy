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

	public String getLabel() {
		return label;
	}

	public static String getRandom() {
		EnnemyEnum[] values = EnnemyEnum.values();
		int index = new Random().nextInt(values.length);
		return values[index].getLabel();
	}
}
