package en.swingy.entity.entityclass;

import java.util.Random;

import javax.swing.ImageIcon;

import en.swingy.gui.Assets;

public abstract class EntityClass {
	protected float attack;
	protected float defense;
	protected float HP;

	public final float ATT_COEFF;
	public final float DEF_COEFF;
	public final float HP_COEFF ;

	public static final EntityClass[] E_CLASS = {
		new Barbarian(),
		new Crusader(),
		new DemonHunter(),
		new Monk(),
		new Necromancer(),
		new WitchDoctor(),
		new Wizard()
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

	public EntityClass(float p_attack, float p_defense, float p_HP,
		float ATT_COEFF, float DEF_COEFF, float HP_COEFF) {
		this.attack = p_attack;
		this.defense = p_defense;
		this.HP = p_HP;

		this.ATT_COEFF = ATT_COEFF;
		this.DEF_COEFF = DEF_COEFF;
		this.HP_COEFF = HP_COEFF;
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

	public static EntityClass getClassByName(String name) {
		switch (name) {
			case "Barbarian": return new Barbarian();
			case "Crusader": return new Crusader();
			case "DemonHunter": return new DemonHunter();
			case "Monk": return new Monk();
			case "Necromancer": return new Necromancer();
			case "WitchDoctor": return new WitchDoctor();
			case "Wizard": return new Wizard();
			default: return null;
		}
	}

	public static ImageIcon getClassAssetsByName(String name) {
		switch (name) {
			case "Barbarian": return new ImageIcon(Assets.BARBARIAN_HOVER_ICON);
			case "Crusader": return new ImageIcon(Assets.CRUSADER_HOVER_ICON);
			case "DemonHunter": return new ImageIcon(Assets.DEMONHUNTER_HOVER_ICON);
			case "Monk": return new ImageIcon(Assets.MONK_HOVER_ICON);
			case "Necromancer": return new ImageIcon(Assets.NECROMANCER_HOVER_ICON);
			case "WitchDoctor": return new ImageIcon(Assets.WITCHDOCTOR_HOVER_ICON);
			case "Wizard": return new ImageIcon(Assets.WITCHDOCTOR_HOVER_ICON);
			default: return null;
		}
	}

	public abstract void statsLevelUp();
}
