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

	private static final String CREATE_TABLE =
		"CREATE TABLE IF NOT EXISTS " + MAIN_TABLE + " (" +
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

	private static final String INSERT_USER =
		"INSERT INTO " + MAIN_TABLE + " (" +
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
		") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE_FIGHT =
		"UPDATE " + MAIN_TABLE +
		" SET " + XP_VAR + "=" + "?," +
		WP_NAME_VAR + "=" + "?," +
		WP_ATT_VAR + "=" + "?," +
		AM_NAME_VAR + "=" + "?," +
		AM_DEF_VAR + "=" + "?," +
		HL_NAME_VAR + "=" + "?," +
		HL_HP_VAR + "=" + "? " +
		"WHERE " + ID_VAR + "=" + "?;";

	private static final String UPDATE_LEVEL_UP =
		"UPDATE " + MAIN_TABLE +
		" SET " + LV_VAR + "=" + "?," +
		ATT_VAR + "=" + "?," +
		DEF_VAR + "=" + "?," +
		HP_VAR + "=" + "? " +
		"WHERE " + ID_VAR + "=" + "?;";

	private static final String FETCH_USERS = "SELECT * FROM " + MAIN_TABLE;

	public static Connection conn = null;

	public DB() {}

	public static Connection getConnection() throws SQLException {
		return conn;
	}

	public static void initDb() throws SQLException {
		if (conn == null) {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				throw new SQLException("SQLite JDBC driver not found", e);
			}
			conn = DriverManager.getConnection(SQL_URL);
			getConnection().prepareStatement(CREATE_TABLE).execute();
		}
	}

	public static long createAccount(String name, EntityClass c) throws SQLException {
		initDb();
		PreparedStatement s = getConnection().prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
		s.setString(1, name);
		s.setString(2, c.getClass().getSimpleName());
		s.setInt(3, 1);
		s.setInt(4, 0);
		s.setFloat(5, c.getAttack());
		s.setFloat(6, c.getDefense());
		s.setFloat(7, c.getHP());

		s.setString(8, "None");
		s.setFloat(9, 0);
		s.setString(10, "None");
		s.setFloat(11, 0);
		s.setString(12, "None");
		s.setFloat(13, 0);

		s.executeUpdate();

		long key = -1L;
		ResultSet rs = s.getGeneratedKeys();
		if (rs.next()) {
			key = rs.getLong(1);
		}

		return key;
	}

	public static void updateAfterFight(Hero player) throws SQLException {
		PreparedStatement s = getConnection().prepareStatement(UPDATE_FIGHT);
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

	public static void updateAfterLevel(Hero player) throws SQLException {
		PreparedStatement s = getConnection().prepareStatement(UPDATE_LEVEL_UP);
		s.setInt(1, player.getLevel());
		s.setFloat(2, player.getEClass().getAttack());
		s.setFloat(3, player.getEClass().getDefense());
		s.setFloat(4, player.getEClass().getHP());
		s.setInt(5, player.getId());

		s.executeUpdate();
	}

	public static ResultSet fetchSaves() throws SQLException {
		initDb();
		PreparedStatement s = getConnection().prepareStatement(FETCH_USERS);
		ResultSet rs = s.executeQuery();
		
		return rs;
	}
}
