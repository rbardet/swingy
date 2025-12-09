package en.swingy.gui.guigame;

import java.sql.SQLException;


import en.swingy.game.Game;
import en.swingy.gui.Assets;
import en.swingy.gui.GUI;
import en.swingy.hero.Hero;

public class GuiGame {
	Hero player;
	Boolean Inventory_seen = false;

	public GuiGame(Hero p_player) {
		this.player = p_player;
	}

	public void setGameMainScene(GUI gui, Game g) throws SQLException {
		gui.clearScreen();
		gui.setFrameBg(Assets.MENU_BG);
		gui.setMenuQuitIcon(gui, g);
		InventoryGUI.showPlayerInventory(gui, player);
		InventoryGUI.setPlayerInventoryToggle(gui, player);
	}
}
