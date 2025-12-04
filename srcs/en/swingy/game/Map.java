package en.swingy.game;

import java.sql.SQLException;
import java.util.Random;

import en.swingy.hero.Hero;

public class Map {
	public static final String EMPTY_CELL = "0";
	public static final String PLAYER_CELL = "P";
	public static final String ENNEMY_CELL = "E";

	private final String COLOR_RESET = "\u001B[0m";
	private final String RED = "\u001B[31m";
	private final String GREEN = "\u001B[32m";
	private final String BLUE = "\u001B[34m";

	private String map[][];
	private int size;
	private Controller controller;

	public Map() {
		this.map = null;
		this.size = 0;
	}

	public void setMapSize(int level) {
		this.size = (level - 1) * 5 + 10 -(level % 2);
	}

	public String generateCell() {
		Random r = new Random();
		int rInt = r.nextInt(100) + 1;
		if (rInt <= 90) {
			return EMPTY_CELL;
		} else {
			return ENNEMY_CELL;
		}
	}

	public void generateMap() {
		this.map = new String[this.size][this.size];
		for(int i = 0; i < this.size; i++) {
			for(int j = 0; j < this.size; j++) {
				this.map[i][j] = generateCell();
			}
		}
		map[this.size / 2][this.size / 2] = PLAYER_CELL;
	}

	public String getCellColor(String cell) {
		switch (cell) {
			case EMPTY_CELL: return GREEN;
			case PLAYER_CELL: return BLUE;
			case ENNEMY_CELL: return RED;
			default: return "";
		}
	}

	public void printMap() {
		String color;
		for(int i = 0; i < this.size; i++) {
			for(int j = 0; j < this.size ; j++) {
				color = getCellColor(this.map[i][j]);
				System.out.print(color + this.map[i][j] + COLOR_RESET);
			}
			System.out.println();
		}
	}

	public void initController() {
		this.controller = new Controller(this.size / 2, this.size / 2);
	}

	public void playerAction(Hero player) throws SQLException {
		String mov = this.controller.Movement(this.map);
		
		if (this.map[this.controller.getPlayerY()][this.controller.getPlayerX()] == ENNEMY_CELL) {
			if (Fight.flee()) {
				this.controller.goBack(mov, map);
			} else {
				Fight.Simulate(player);
			}
		}

		this.map[this.controller.getPlayerY()][this.controller.getPlayerX()] = Map.PLAYER_CELL;
	}

	public boolean Clear() {
		if (this.controller.getPlayerX() <= 0
		|| this.controller.getPlayerX() >= size - 1
		|| this.controller.getPlayerY() <= 0
		|| this.controller.getPlayerY() >= size - 1) {
			return true;
		}
		return false;
	}
}
