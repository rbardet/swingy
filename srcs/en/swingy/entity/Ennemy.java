package en.swingy.entity;

import java.util.Random;

import en.swingy.entity.entityclass.EntityClass;

public class Ennemy extends Entity {
	public enum NAME {
		Macron,
		Macron2,
		Macron3,
		Macron4
	};

	public Ennemy(String p_name, EntityClass p_e_class) {
		super(p_name, p_e_class);
	}

	public static String randomName() {
		NAME[] values = NAME.values();
		int index = new Random().nextInt(values.length);
		return values[index].toString(); 
	}
}
