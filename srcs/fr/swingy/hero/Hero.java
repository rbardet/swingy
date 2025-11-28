package fr.swingy.hero;

import fr.swingy.entity.Entity;

public class Hero extends Entity {
	private int level = 1;
	private int xp = 0;
	
	public Hero(String p_name) {
		super(p_name, 1, 1, 1);
	}

	public void levelUp() {
		this.level += 1;
	}
}