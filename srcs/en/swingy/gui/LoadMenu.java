package en.swingy.gui;

import java.sql.SQLException;

import en.swingy.game.Game;

public class LoadMenu {
	public static void showLoadSaveButton(Game g) throws SQLException {

	}

	public static void showLoadSaveMenu(GUI gui, Game g) throws SQLException {
		gui.clearScreen();
		SavesDisplay.showAvailableSaves(gui, g);
		showLoadSaveButton(g);
	}

}
