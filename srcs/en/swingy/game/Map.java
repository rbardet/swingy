package en.swingy.game;

import java.util.Random;

public class Map {
	public static final String EMPTY_CELL = "0";
	public static final String PLAYER_CELL = "P";
	public static final String ENNEMY_CELL = "E";

	public static final String COLOR_RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String BLUE = "\u001B[34m";

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

	public void movePlayer(int player_x, int player_y, String dir) {
		this.map[player_y][player_x] = EMPTY_CELL;
		switch (dir) {
			case "N" -> this.map[player_y -1][player_x] = PLAYER_CELL;
			case "W" -> this.map[player_y][player_x - 1] = PLAYER_CELL;
			case "E" -> this.map[player_y][player_x + 1] = PLAYER_CELL;
			case "S" -> this.map[player_y + 1][player_x] = PLAYER_CELL;
		}
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
	
	public void playerMove() {
		this.controller.Movement(this.map);
	}

	public boolean Clear() {
		return true;
	}
}
