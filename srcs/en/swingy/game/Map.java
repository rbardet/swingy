package en.swingy.game;

import java.sql.SQLException;
import java.util.Random;

import en.swingy.hero.Hero;

public class Map {
	public static final String EMPTY_CELL = "0";
	public static final String PLAYER_CELL = "P";
	public static final String ENNEMY_CELL = "E";

	private String map[][];
	private int size;
	private Controller controller;

	public Map() {
		this.map = null;
		this.size = 0;
	}

	/**
	 * Sets the size of the map based on the hero's level.
	 * @param level Hero's level
	 */
	public void setMapSize(int level) {
		this.size = (level - 1) * 5 + 10 -(level % 2);
	}

	/**
	 * Generates a single cell for the map.
	 * 90% chance to be empty, 10% chance to contain an enemy.
	 * @return String representing the cell type (EMPTY_CELL or ENNEMY_CELL)
	 */
	public String generateCell() {
		Random r = new Random();
		int rInt = r.nextInt(100) + 1;
		if (rInt <= 90) {
			return EMPTY_CELL;
		} else {
			return ENNEMY_CELL;
		}
	}

	/**
	 * Generates the entire map with random cells.
	 * The player is placed at the center of the map.
	 */
	public void generateMap() {
		this.map = new String[this.size][this.size];
		for(int i = 0; i < this.size; i++) {
			for(int j = 0; j < this.size; j++) {
				this.map[i][j] = generateCell();
			}
		}
		map[this.size / 2][this.size / 2] = PLAYER_CELL;
	}

	/**
	 * Returns the color code for a given cell.
	 * @param cell The cell type
	 * @return ANSI color code corresponding to the cell
	 */
	public String getCellColor(String cell) {
		switch (cell) {
			case EMPTY_CELL: return GamePrint.GREEN;
			case PLAYER_CELL: return GamePrint.BLUE;
			case ENNEMY_CELL: return GamePrint.RED;
			default: return "";
		}
	}

	/**
	 * Prints the map to the console with colors.
	 */
	public void printMap() {
		String color;
		for(int i = 0; i < this.size; i++) {
			for(int j = 0; j < this.size ; j++) {
				color = getCellColor(this.map[i][j]);
				System.out.print(color + this.map[i][j] + GamePrint.COLOR_RESET);
			}
			System.out.println();
		}
	}

	/**
	 * Initializes the controller with the player's starting position at the center.
	 */
	public void initController() {
		this.controller = new Controller(this.size / 2, this.size / 2);
	}

	/**
	 * Handles the player's action including movement and encounters.
	 * If an enemy is encountered, the player can flee or fight.
	 * @param g The current game instance
	 * @param player The hero object
	 * @throws SQLException If database operations fail during fights
	 */
	public void playerAction(Game g, Hero player) throws SQLException {
		String mov = this.controller.Movement(g, this.map);
		if (this.map[this.controller.getPlayerY()][this.controller.getPlayerX()] == ENNEMY_CELL) {
			if (Fight.flee()) {
				this.controller.goBack(mov, map);
			} else {
				Fight.Simulate(player);
			}
		}
		this.map[this.controller.getPlayerY()][this.controller.getPlayerX()] = Map.PLAYER_CELL;
	}

	/**
	 * Checks if the player has reached the border of the map.
	 * @return true if the player is at or beyond the map boundary, false otherwise
	 */
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
