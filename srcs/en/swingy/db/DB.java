package en.swingy.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import en.swingy.entity.entityclass.EntityClass;
import en.swingy.hero.Hero;

public class DB {
	public static final String MAIN_TABLE = "Hero";
	public static final String ID_VAR = "id";
	public static final String NAME_VAR = "name";
	public static final String CLASS_VAR = "class";
	public static final String LV_VAR = "lv";
	public static final String XP_VAR = "xp";
	public static final String ATT_VAR = "attack";
	public static final String DEF_VAR = "defense";
	public static final String HP_VAR = "hp";
	public static final String WP_NAME_VAR = "w_name";
	public static final String WP_ATT_VAR = "w_attack";
	public static final String AM_NAME_VAR = "a_name";
	public static final String AM_DEF_VAR = "a_defense";
	public static final String HL_NAME_VAR = "h_name";
	public static final String HL_HP_VAR = "h_hp";
	private static final String SQL_URL = "jdbc:sqlite:game.db";

	// SQL queries
	private static final String RQ_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + MAIN_TABLE + " (" +
		ID_VAR + " INTEGER PRIMARY KEY, " +
		NAME_VAR + " TEXT, " +
		CLASS_VAR + " TEXT," +
		LV_VAR + " INTEGER, " +
		XP_VAR + " FLOAT, " +
		ATT_VAR + " FLOAT, " +
		DEF_VAR + " FLOAT, " +
		HP_VAR + " FLOAT, " +
		WP_NAME_VAR + " TEXT, " +
		WP_ATT_VAR + " FLOAT, " +
		AM_NAME_VAR + " TEXT, " +
		AM_DEF_VAR + " FLOAT, " +
		HL_NAME_VAR + " TEXT, " +
		HL_HP_VAR + " FLOAT)";

	private static final String RQ_INSERT_USER = "INSERT INTO " + MAIN_TABLE + " (" +
		ID_VAR + ", " +
		NAME_VAR + ", " +
		CLASS_VAR + ", " +
		LV_VAR + ", " +
		XP_VAR + ", " +
		ATT_VAR + ", " +
		DEF_VAR + ", " +
		HP_VAR + ", " +
		WP_NAME_VAR + ", " +
		WP_ATT_VAR + ", " +
		AM_NAME_VAR + ", " +
		AM_DEF_VAR + ", " +
		HL_NAME_VAR + ", " +
		HL_HP_VAR +
		") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String RQ_UPDATE_FIGHT = "UPDATE " + MAIN_TABLE +
		" SET " + XP_VAR + "=?," +
		WP_NAME_VAR + "=?," +
		WP_ATT_VAR + "=?," +
		AM_NAME_VAR + "=?," +
		AM_DEF_VAR + "=?," +
		HL_NAME_VAR + "=?," +
		HL_HP_VAR + "=? " +
		"WHERE " + ID_VAR + "=?;";

	private static final String RQ_UPDATE_LEVEL_UP = "UPDATE " + MAIN_TABLE +
		" SET " + LV_VAR + "=?," +
		ATT_VAR + "=?," +
		DEF_VAR + "=?," +
		HP_VAR + "=? " +
		"WHERE " + ID_VAR + "=?;";

	private static final String RQ_DELETE_REQUEST = "DELETE FROM " + MAIN_TABLE + " WHERE " + ID_VAR + "=?;";

	private static final String RQ_ENTRY_SIZE = "SELECT id FROM " + MAIN_TABLE + " ORDER BY id";

	private static final String RQ_FETCH_USERS = "SELECT * FROM " + MAIN_TABLE;

	public static Connection conn = null;

	public DB() {}

	/**
	 * Returns the current database connection.
	 *
	 * @return Active database connection
	 * @throws SQLException If the connection is not initialized
	 */
	public static Connection getConnection() throws SQLException {
		return conn;
	}

	/**
	 * Initializes the SQLite database and creates the Hero table if it does not exist.
	 *
	 * @throws SQLException If database connection or table creation fails
	 */
	public static void initDb() throws SQLException {
		if (conn == null) {
			conn = DriverManager.getConnection(SQL_URL);
			getConnection().prepareStatement(RQ_CREATE_TABLE).execute();
		}
	}

	/**
	 * Returns the next available user ID in the Hero table.
	 *
	 * @return Next available ID
	 * @throws SQLException If database query fails
	 */
	public static int getUserId() throws SQLException {
		PreparedStatement s = getConnection().prepareStatement(RQ_ENTRY_SIZE);
		ResultSet rs = s.executeQuery();

		int exId = 1;
		while (rs.next()) {
			int currId = rs.getInt(ID_VAR);
			if (currId != exId) {
				return exId;
			}
			exId++;
		}
		return exId;
	}

	/**
	 * Creates a new hero account in the database.
	 *
	 * @param name Hero's name
	 * @param c Hero's entity class (stats)
	 * @return Generated key (ID) of the new hero
	 * @throws SQLException If insertion fails
	 */
	public static long createAccount(String name, EntityClass c) throws SQLException {
		initDb();
		PreparedStatement s = getConnection().prepareStatement(RQ_INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
		s.setInt(1, getUserId());
		s.setString(2, name);
		s.setString(3, c.getClass().getSimpleName());
		s.setInt(4, 1);
		s.setInt(5, 0);
		s.setFloat(6, c.getAttack());
		s.setFloat(7, c.getDefense());
		s.setFloat(8, c.getHP());
		s.setString(9, "None");
		s.setFloat(10, 0);
		s.setString(11, "None");
		s.setFloat(12, 0);
		s.setString(13, "None");
		s.setFloat(14, 0);

		s.executeUpdate();

		long key = -1L;
		ResultSet rs = s.getGeneratedKeys();
		if (rs.next()) {
			key = rs.getLong(1);
		}

		return key;
	}

	/**
	 * Updates a hero's data after a fight (XP, weapon, armor, helm).
	 *
	 * @param player Hero object to update
	 * @throws SQLException If update fails
	 */
	public static void updateAfterFight(Hero player) throws SQLException {
		PreparedStatement s = getConnection().prepareStatement(RQ_UPDATE_FIGHT);
		s.setInt(1, player.getXP());
		s.setString(2, player.getWeapon().getName());
		s.setFloat(3, player.getWeapon().getAttack());
		s.setString(4, player.getArmor().getName());
		s.setFloat(5, player.getArmor().getDefense());
		s.setString(6, player.getHelm().getName());
		s.setFloat(7, player.getHelm().getHP());
		s.setInt(8, player.getId());

		s.executeUpdate();
	}

	/**
	 * Updates a hero's level and base stats after leveling up.
	 *
	 * @param player Hero object to update
	 * @throws SQLException If update fails
	 */
	public static void updateAfterLevel(Hero player) throws SQLException {
		PreparedStatement s = getConnection().prepareStatement(RQ_UPDATE_LEVEL_UP);
		s.setInt(1, player.getLevel());
		s.setFloat(2, player.getEClass().getAttack());
		s.setFloat(3, player.getEClass().getDefense());
		s.setFloat(4, player.getEClass().getHP());
		s.setInt(5, player.getId());

		s.executeUpdate();
	}

	/**
	 * Deletes a hero from the database.
	 *
	 * @param idx ID of the hero to delete
	 * @throws SQLException If deletion fails
	 */
	public static void deleteHero(int idx) throws SQLException {
		PreparedStatement s = getConnection().prepareStatement(RQ_DELETE_REQUEST);
		s.setInt(1, idx);
		s.executeUpdate();
	}

	/**
	 * Fetches all saved heroes from the database.
	 *
	 * @return ResultSet containing all heroes
	 * @throws SQLException If query fails
	 */
	public static ResultSet fetchSaves() throws SQLException {
		initDb();
		PreparedStatement s = getConnection().prepareStatement(RQ_FETCH_USERS);
		return s.executeQuery();
	}
}
