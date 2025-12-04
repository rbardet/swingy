package en.swingy.game;

import java.sql.ResultSet;
import java.sql.SQLException;

import en.swingy.db.DB;
import en.swingy.entity.entityclass.EntityClass;
import en.swingy.equipement.armor.Armor;
import en.swingy.equipement.helm.Helm;
import en.swingy.equipement.weapon.Weapon;
import en.swingy.hero.Hero;

public class Game {
	private static final String[] prompt_start = {
		"Create a new character",
		"Load a character",
		"Delete a character",
		"Exit the game"
	};

	private static final String ASK_NAME_PROMPT = GamePrint.BOLD +
	"""
	The user name limit is 16 Charcter
	Enter your hero name : 
	""" + GamePrint.COLOR_RESET;

	private static final String ASK_CLASS_PROMPT = GamePrint.BOLD +
	"""
	Enter your hero class : 
	""" + GamePrint.COLOR_RESET;

	private static final String DB_SIZE_PROMPT =  GamePrint.BOLD +
	"""
	You have reached the maximun amount of save,\n
	do you want to delete a save [y/n]?
	""" + GamePrint.COLOR_RESET;

	private static final String LOAD_CHAR_PROMPT = GamePrint.BOLD +
	"""
	Enter the save id to load: 
	""" + GamePrint.COLOR_RESET;
	
	private static final String DEL_CHAR_PROMPT = GamePrint.BOLD +
	"""
	Enter the save id to delete: 
	""" + GamePrint.COLOR_RESET;

	private static final String NO_SAVE_PROMPT = GamePrint.BOLD +
	"""
	There is currently no save\n
	Would you like to create one [y/n]?
	""" + GamePrint.COLOR_RESET;

	private static final String RETURN_MENU_PROMPT = GamePrint.BOLD +
	"""
	Press q to return to the main menu
	""" + GamePrint.COLOR_RESET;

	private Boolean GUI;
	private int dbSize = 0;
	private GUI screen;
	
	public Game() {
		this.screen = new GUI();
	}

	/**
	 * Placeholder function for opening the GUI.
	 * Currently does nothing.
	 */
	public void openGUI() {
		return ;
	}

	/**
	 * Prompts the player to enter a hero name.
	 * Repeats until a valid name is entered (non-empty, max 16 characters).
	 * 
	 * @return the name entered by the player
	 */
	public String askPlayerName() {
		String name;
		do {
			GamePrint.clearTerminal();
			System.out.print(ASK_NAME_PROMPT);
			name = GamePrint.STD_IN.nextLine();
		} while (name.isEmpty() || name.length() > 16);
		return name;
	}

	/**
	 * Prompts the player to choose a hero class.
	 * Displays available classes and reads the selection.
	 * 
	 * @return the index of the selected class in EntityClass.E_CLASS
	 */
	public int askPlayerClass() {
		GamePrint.clearTerminal();
		System.out.println(ASK_CLASS_PROMPT);
		int idx = GamePrint.askOption(EntityClass.prompt_class);
		return idx;
	}

	/**
	 * Creates a new hero character.
	 * Checks database save limit and prompts to delete old save if limit is reached.
	 * 
	 * @return the created Hero object, or null if creation is cancelled
	 * @throws SQLException if a database operation fails
	 */
	public Hero createNewChar() throws SQLException {
		if (this.dbSize >= 3) {
			String s;
			do {
				System.out.println(DB_SIZE_PROMPT);
				s = GamePrint.STD_IN.nextLine();
			} while (!s.matches("y|n"));

			if (s.equals("y")) {
				deleteSave();
			} else {
				return null;
			}
		}

		String name = askPlayerName();
		int idx = askPlayerClass();
		Hero player = new Hero(name, EntityClass.E_CLASS[idx - 1]);
		long id = DB.createAccount(name, EntityClass.E_CLASS[idx - 1]);
		player.setId(((int)id));
		return player;
	}
	
	/**
	 * Sets hero stats from a database ResultSet.
	 * 
	 * @param rs the ResultSet containing hero data
	 * @return a Hero object populated with data from the ResultSet
	 * @throws SQLException if accessing ResultSet fields fails
	 */
	public Hero setHeroStats(ResultSet rs) throws SQLException {
		Hero player = new Hero(rs.getString(DB.NAME_VAR), EntityClass.getClassByName(rs.getString(DB.CLASS_VAR)));
		player.setId(rs.getInt(DB.ID_VAR));
		player.setLv(rs.getInt(DB.LV_VAR));
		player.setXp(rs.getInt(DB.XP_VAR));
		player.getEClass().setAttack(rs.getFloat(DB.ATT_VAR));
		player.getEClass().setDefense(rs.getFloat(DB.DEF_VAR));
		player.getEClass().setHP(rs.getFloat(DB.HP_VAR));
		Weapon w = new Weapon(rs.getString(DB.WP_NAME_VAR), rs.getFloat(DB.WP_ATT_VAR));
		Armor a = new Armor(rs.getString(DB.AM_NAME_VAR), rs.getFloat(DB.AM_DEF_VAR));
		Helm h = new Helm(rs.getString(DB.HL_NAME_VAR), rs.getFloat(DB.HL_HP_VAR));
		player.setWeapon(w);
		player.setArmor(a);
		player.setHelm(h);
		return player;
	}

	/**
	 * Counts the current number of saved heroes in the database
	 * and updates the dbSize field.
	 * 
	 * @throws SQLException if fetching saves from the database fails
	 */
	public void setDbSize() throws SQLException {
		ResultSet rs = DB.fetchSaves();
		int size = 0;
		while (rs.next()) {
			size++;
		}

		this.dbSize = size;
	}

	/**
	 * Prompts the player to select a save from the database.
	 * Can also return to the main menu if 'q' is entered.
	 * 
	 * @param msg the message to display when asking for selection
	 * @return the integer ID of the selected save
	 * @throws SQLException if database operations fail during return to main menu
	 */
	public int selectSave(String msg) throws SQLException {
		String regex = "[1-" + this.dbSize + "q]";
		String choice;
		do {
			GamePrint.clearTerminal();
			GamePrint.displaySave();
			System.out.println(RETURN_MENU_PROMPT);
			System.out.print(msg);
			choice = GamePrint.STD_IN.nextLine();
		} while(!choice.matches(regex));

		if (choice.equals("q")) {
			startGame();
		}

		return Integer.parseInt(choice);
	}

	/**
	 * Loads a hero from the database.
	 * Prompts to create a new character if no saves exist.
	 * 
	 * @return the loaded Hero object, or a new Hero if created
	 * @throws SQLException if database operations fail
	 */
	public Hero loadChar() throws SQLException {
		if (this.dbSize <= 0) {
			System.out.println(NO_SAVE_PROMPT);
			String s;
			do {
				s = GamePrint.STD_IN.nextLine();
			} while (!s.matches("y|n"));

			if (s.equals("y")) {
				return createNewChar();
			} else {
				return null;
			}
		}

		ResultSet rs = DB.fetchSaves();
		int idx = selectSave(LOAD_CHAR_PROMPT);
		while (rs.getInt(DB.ID_VAR) != idx) {
			rs.next();
		}

		return setHeroStats(rs);
	}

	/**
	 * Deletes a hero save from the database.
	 * Prompts the player to select which save to delete.
	 * 
	 * @throws SQLException if database operations fail
	 */
	public void deleteSave() throws SQLException {
		if (this.dbSize <= 0) {
			System.out.println(NO_SAVE_PROMPT);
			return ;
		}

		int idx = selectSave(DEL_CHAR_PROMPT);
		DB.deleteHero(idx);
		return ;
	}

	/**
	 * Exits the game immediately.
	 */
	public static void exitGame() { System.exit(0); }

	/**
	 * Runs the main game loop for a given hero.
	 * Initializes the map and handles player actions until completion.
	 * 
	 * @param player the Hero object to play with
	 * @throws SQLException if database operations fail during the game
	 */
	public void runGame(Hero player) throws SQLException {
		Map m = new Map();
		m.setMapSize(player.getLevel());
		m.generateMap();
		m.initController();
		do {
			if (!GUI) {
				GamePrint.clearTerminal();
				GamePrint.playerInfo(player);
				System.out.println(RETURN_MENU_PROMPT);
				m.printMap();
			}
			m.playerAction(this, player);
		} while (!m.Clear());
	}

	/**
	 * Starts the game menu loop.
	 * Allows creating, loading, deleting characters, or exiting the game.
	 * After a hero is selected, runs the main game loop indefinitely.
	 * 
	 * @throws SQLException if database operations fail
	 */
	public void startGame() throws SQLException {
		setDbSize();
		if (this.GUI) {
			this.screen.runGui(this);
		}

		Hero player = null;

		do {
			GamePrint.clearTerminal();
			GamePrint.printTitle();
			int opt = GamePrint.askOption(prompt_start);

			switch (opt) {
				case 1:
					player = createNewChar();
					break;
				case 2:
					player = loadChar();
					break;
				case 3:
					deleteSave();
					break;
				default:
					exitGame();
			}
		} while (player == null);

		while (true) {
			runGame(player);
		}
	}

	/**
	 * Sets the GUI mode of the game.
	 * 
	 * @param mode true to enable GUI, false for terminal mode
	 */
	public void setGui(boolean mode) {
		GUI = mode;
	}
}
