package en.swingy.gui.guigame;

import java.awt.Point;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import en.swingy.game.Game;
import en.swingy.game.GamePrint;
import en.swingy.game.Map;
import en.swingy.gui.Assets;
import en.swingy.gui.GUI;
import en.swingy.hero.Hero;

public class GuiGame {
	Hero player;
	Boolean Inventory_seen = false;

	public GuiGame(Hero p_player) {
		this.player = p_player;
	}

	public void placeTile(GUI gui, int x, int y, String sprite) {
		ImageIcon icon = new ImageIcon(sprite);
		JLabel label = new JLabel(icon);
		label.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
		label.setSize(64, 64);
		gui.getFrame().add(label);
	}

	public void drawMap(GUI gui, Game g, Map m) {
		Point playerPos = m.getPlayerPos();
		int tileWidth = 64;
		int tileHeight = 64;

		int screenCenterX = 800;
		int screenCenterY = 300;

		int radius = 4;
		int startX = playerPos.x - radius;
		int startY = playerPos.y - radius;
		int tilesVisible = radius * 2 + 1;

		int offsetX = screenCenterX - radius * tileWidth;
		int offsetY = screenCenterY - radius * tileHeight;

		for (int y = 0; y < tilesVisible; y++) {
			for (int x = 0; x < tilesVisible; x++) {
				int mapX = startX + x;
				int mapY = startY + y;

				if (mapX < 0 || mapX >= m.getMapSize() || mapY < 0 || mapY >= m.getMapSize())
					continue;

				int screenX = offsetX + x * tileWidth;
				int screenY = offsetY + y * tileHeight;

				placeTile(gui, screenX, screenY, m.getTileAsset(mapX, mapY));
			}
		}

		gui.getFrame().repaint();
	}

	public void waitForMovement(GUI gui, Game g, Map m) {

	}

	public void startGame(GUI gui, Game g) {
		Map m = new Map();
		m.setMapSize(player.getLevel());
		m.generateMap();
		m.initController();
		do {
			gui.clearScreen();
			drawMap(gui, g, m);
			InventoryGUI.showPlayerInventory(gui, player);
			waitForMovement(gui, g, m);
		} while (!m.Clear());
	}

	public void setGameMainScene(GUI gui, Game g) throws SQLException {
		gui.clearScreen();
		gui.setFrameBg(Assets.MENU_BG);
		gui.setMenuQuitIcon(gui, g);
		InventoryGUI.showPlayerInventory(gui, player);
		startGame(gui, g);
	}
}
