package en.swingy.entity.entityclass;

import java.util.Random;

import en.swingy.game.Game;

public abstract class EntityClass {
	protected float attack;
	protected float defense;
	protected float HP;

	public static final EntityClass[] E_CLASS = {
		new Barbarian(),
		new Crusader(),
		new DemonHunter(),
		new Monk(),
		new Necromancer(),
		new WitchDoctor(),
		new Wizard(),
	};

	public static final String[] prompt_class = {
		"Barbarian 5/5/10",
		"Crusader 5/8/7",
		"Demon Hunter 9/4/7",
		"Monk 7/6/7",
		"Necromancer 8/4/8",
		"Witch Doctor 10/3/7",
		"Wizard 8/3/9"
	};


	public EntityClass(float p_attack, float p_defense, float p_HP) {
		this.attack = p_attack;
		this.defense = p_defense;
		this.HP = p_HP;
	}

	public float getAttack() {
		return this.attack;
	}

	public float getDefense() {
		return this.defense;
	}

	public float getHP() {
		return this.HP;
	}

	public void setAttack(float p_attack) {
		this.attack = p_attack;
	}

	public void setDefense(float p_defense) {
		this.defense = p_defense;
	}

	public void setHP(float p_HP) {
		this.HP = p_HP;
	}

	public static EntityClass randomClass() {
		Random r = new Random();
		int index = r.nextInt(E_CLASS.length);
		return E_CLASS[index];
	}

	public static String getType(EntityClass e) {
		return e.getClass().getSimpleName();
	}
	
	public abstract void statsLevelUp();

	public static int askClass(String[] opt) {
		try {
			String choice;
			do {
				Game.clearTerminal();
				System.out.println("Id  Name     Stats(att/def/hp)");
				for(int i = 0; i < opt.length; i++) {
					System.out.println(i + 1 + " : " + opt[i]);
				}
				choice = Game.STD_IN.nextLine();
			} while (!choice.matches("[1-7]"));

			int ret = Integer.parseInt(choice);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}

		Game.exitGame();
		return -1;
	}
}
