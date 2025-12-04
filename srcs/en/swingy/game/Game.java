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

	private static final String ASK_NAME = "Enter your hero name : ";
	private static final String ASK_CLASS = "Enter your hero class : ";
	public static Boolean GUI;

	public Game() {}

	public void openGUI() {
		return ;
	}

	public String askPlayerName() {
		GamePrint.clearTerminal();
		System.out.print(ASK_NAME);
		String name;
		do {
			name = GamePrint.STD_IN.nextLine();
		} while (name.isEmpty());
		return name;
	}
	public int askPlayerClass() {
		GamePrint.clearTerminal();
		System.out.println(ASK_CLASS);
		int idx = GamePrint.askOption(EntityClass.prompt_class);
		return idx;
	}

	public Hero createNewChar() throws SQLException {
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

	public Hero loadChar() throws SQLException {
		ResultSet rs = DB.fetchSaves();
		int size = 0;
		while (rs.next()) {
			size++;
		}
		String regex = "[1-" + size + "]";
		String choice;

		rs = DB.fetchSaves();
		GamePrint.clearTerminal();
		GamePrint.displaySave(rs);
		do {
			System.out.print("Enter the save id to load: ");
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
		ResultSet rs = DB.fetchSaves();
		int size = 0;
		while (rs.next()) {
			size++;
		}
		String regex = "[1-" + size + "]";
		String choice;

		rs = DB.fetchSaves();
		GamePrint.clearTerminal();
		GamePrint.displaySave(rs);
		do {
			System.out.print("Enter the save id to delete: ");
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

		GamePrint.clearTerminal();
		GamePrint.printTitle();

		int opt = GamePrint.askOption(prompt_start);

		Hero player = null;
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

		while (true) {
			runGame(player);
		}
	}
}