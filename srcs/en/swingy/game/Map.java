package en.swingy.game;

import java.util.Random;

public class Map {
	public static final String EMPTY_CELL = "0";
	public static final String PLAYER_CELL = "P";
	public static final String ENNEMY_CELL = "E";
	public static String map[][];

	public static int getMapSize(int level) {
		return (level - 1) * 5 + 10 -(level % 2);
	}

	public static String generateCell() {
		Random r = new Random();
		int rInt = r.nextInt(100) + 1;
		if (rInt <= 90) {
			return EMPTY_CELL;
		} else {
			return ENNEMY_CELL;
		}
	}

	public static String[][] generateMap(int size, int player_x, int player_y) {
		map = new String[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				map[i][j] = generateCell();
			}
		}

		map[player_y][player_y] = PLAYER_CELL;
		return map;
	}

	public static void movePlayer(int player_x, int player_y, String dir) {
		map[player_y][player_x] = EMPTY_CELL;
		switch (dir) {
			case "N" -> map[player_y -1][player_x] = PLAYER_CELL;
			case "W" -> map[player_y][player_x - 1] = PLAYER_CELL;
			case "E" -> map[player_y][player_x + 1] = PLAYER_CELL;
			case "S" -> map[player_y + 1][player_x] = PLAYER_CELL;
		}
	}

	public static boolean Clear(int player_x, int player_y) {
		return true;
	}
}
