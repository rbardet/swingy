package en.swingy.entity.entityclass;

import java.util.Random;

import en.swingy.game.Game;

public class EntityClass {
	protected int attack;
	protected int defense;
	protected int HP;

	public static final EntityClass[] E_CLASS = {
		new Barbarian(),
		new Crusader(),
		new DemonHunter(),
		new Monk(),
		new Necromancer(),
		new WitchDoctor(),
		new Wizard(),
	};

	public EntityClass(int p_attack, int p_defense, int p_HP) {
		this.attack = p_attack;
		this.defense = p_defense;
		this.HP = p_HP;
	}

	public int getAttack() {
		return this.attack;
	}

	public int getDefense() {
		return this.defense;
	}

	public int getHP() {
		return this.HP;
	}

	public void setAttack(int p_attack) {
		this.attack = p_attack;
	}

	public void setDefense(int p_defense) {
		this.defense = p_defense;
	}

	public void setHP(int p_HP) {
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

	public static int askClass(String[] opt) {
		try {
			String choice;
			do {
				Game.clearTerminal();
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
