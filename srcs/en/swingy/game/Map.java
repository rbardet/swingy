package en.swingy.game;

import java.awt.Point;
import java.util.Random;

import en.swingy.game.Fight.Fight;
import en.swingy.game.Fight.console.FightConsole;
import en.swingy.game.Fight.gui.FightGUI;
import en.swingy.gui.Assets;
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

	public String[][] getMap() {
		return this.map;
	}

	public Controller getController() {
		return this.controller;
	}

	public int getMapSize() {
		return this.size;
	}

	public Point getPlayerPos() {
		return new Point(this.controller.getPlayerX(), this.controller.getPlayerY());
	}

	public String getTileAsset(int x, int y) {
		switch (map[y][x]) {
			case EMPTY_CELL: return Assets.GROUND;
			case PLAYER_CELL: return Assets.PLAYER;
			case ENNEMY_CELL: return Assets.ENNEMY;
			default: return "";
		}
	}

	public String getTile(int x, int y) {
		return this.map[y][x];
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
			case EMPTY_CELL: return GamePrint.GREEN;
			case PLAYER_CELL: return GamePrint.BLUE;
			case ENNEMY_CELL: return GamePrint.RED;
			default: return "";
		}
	}

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

	public void initController() {
		this.controller = new Controller(this.size / 2, this.size / 2);
	}

	public boolean isOnEnnemy() {
		if (this.map[this.controller.getPlayerY()][this.controller.getPlayerX()] == ENNEMY_CELL) {
			return true;
		}
		return false;
	}

	public void movePlayer() {
		this.map[this.controller.getPlayerY()][this.controller.getPlayerX()] = Map.PLAYER_CELL;
	}

	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String BOLD = "\u001B[1m";

	public void playerAction(Hero player, String mov) {
		System.out.println(
			BOLD + CYAN + "[PLAYER ACTION] " + RESET +
			BLUE + "id=" + player.getId() + RESET +
			" | " + GREEN + "level=" + player.getLevel() + RESET +
			" | " + GREEN + "xp=" + player.getXP() + RESET +

			" | " + PURPLE + "class" + RESET +
			"(atk=" + player.getEClass().getAttack() +
			", def=" + player.getEClass().getDefense() +
			", hp=" + player.getEClass().getHP() + ")" +

			" | " + YELLOW + "weapon=" + RESET +
			(player.getWeapon() != null
				? player.getWeapon().getName() +
				"(atk=" + player.getWeapon().getAttack() + ")"
				: RED + "none" + RESET) +

			" | " + YELLOW + "armor=" + RESET +
			(player.getArmor() != null
				? player.getArmor().getName() +
				"(def=" + player.getArmor().getDefense() + ")"
				: RED + "none" + RESET) +

			" | " + YELLOW + "helm=" + RESET +
			(player.getHelm() != null
				? player.getHelm().getName() +
				"(hp=" + player.getHelm().getHP() + ")"
				: RED + "none" + RESET)
		);
		player.getEClass().getAttack();
		player.getEClass().getDefense();
		player.getEClass().getHP();
		mov = this.controller.Movement(this.map, mov);
		if (isOnEnnemy()) {
			if (!Game.GUI_MODE) {
				if (FightConsole.Flee()) {
					this.controller.goBack(mov);
				}
			} else {
				FightGUI.Flee(this.controller, mov);
				movePlayer();
				return ;
			}
			Fight.Simulate(player);
		}
		movePlayer();
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
