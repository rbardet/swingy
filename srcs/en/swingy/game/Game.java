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

	public static Boolean GUI;
	public static int dbSize = 0;

	public Game() {}

	public void openGUI() {
		return ;
	}

	public String askPlayerName() {
		String name;
		do {
			GamePrint.clearTerminal();
			System.out.print(ASK_NAME_PROMPT);
			name = GamePrint.STD_IN.nextLine();
		} while (name.isEmpty() || name.length() > 16);
		return name;
	}

	public int askPlayerClass() {
		GamePrint.clearTerminal();
		System.out.println(ASK_CLASS_PROMPT);
		int idx = GamePrint.askOption(EntityClass.prompt_class);
		return idx;
	}

	public Hero createNewChar() throws SQLException {
		if (dbSize >= 3) {
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

	public void setDbSize() throws SQLException {
		ResultSet rs = DB.fetchSaves();
		int size = 0;
		while (rs.next()) {
			size++;
		}

		dbSize = size;
	}

	public Hero loadChar() throws SQLException {
		String regex = "[1-" + dbSize + "]";
		String choice;

		ResultSet rs = DB.fetchSaves();
		do {
			GamePrint.clearTerminal();
			GamePrint.displaySave(rs);
			System.out.print(LOAD_CHAR_PROMPT);
			choice = GamePrint.STD_IN.nextLine();
		} while(!choice.matches(regex));

		rs = DB.fetchSaves();
		int idx = Integer.parseInt(choice);
		while (rs.getInt(DB.ID_VAR) != idx) {
			rs.next();
		}

		return setHeroStats(rs);
	}

	public void deleteSave() throws SQLException {
		String regex = "[1-" + dbSize + "]";
		String choice;

		ResultSet rs = DB.fetchSaves();
		GamePrint.clearTerminal();
		GamePrint.displaySave(rs);
		do {
			System.out.print(DEL_CHAR_PROMPT);
			choice = GamePrint.STD_IN.nextLine();
		} while(!choice.matches(regex));

		int idx = Integer.parseInt(choice);
		DB.deleteHero(idx);
		return ;
	}

	public static void exitGame() { System.exit(0); }

	public void runGame(Hero player) throws SQLException {
		Map m = new Map();
		m.setMapSize(player.getLevel());
		m.generateMap();
		m.initController();
		do {
			if (!GUI) {
				GamePrint.clearTerminal();
				GamePrint.playerInfo(player);
				m.printMap();
			}
			m.playerAction(player);
		} while (!m.Clear());
	}

	public void startGame(boolean gui) throws SQLException {
		GUI = gui;

		setDbSize();
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
				default:
					exitGame();
			}
		} while (player == null);

		while (true) {
			runGame(player);
		}
	}
}