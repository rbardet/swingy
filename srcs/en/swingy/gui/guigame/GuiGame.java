package en.swingy.gui.guigame;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import en.swingy.game.Game;
import en.swingy.game.Map;
import en.swingy.gui.Assets;
import en.swingy.gui.GUI;
import en.swingy.hero.Hero;

public class GuiGame {
	Hero player;
	Boolean Inventory_seen = false;
	static KeyListener movEvent = null;

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

	public void drawMap(GUI gui, Map m) {
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


	public void initMovementEvent(GUI gui, Game g, Map m) {
		movEvent = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_W -> m.getController().Movement(g, m.getMap(), "W");
					case KeyEvent.VK_A -> m.getController().Movement(g, m.getMap(), "A");
					case KeyEvent.VK_S -> m.getController().Movement(g, m.getMap(), "S");
					case KeyEvent.VK_D -> m.getController().Movement(g, m.getMap(), "D");
				}

				gui.clearScreen();
				drawMap(gui, m);
				InventoryGUI.showPlayerInventory(gui, player);
				gui.getFrame().repaint();
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		};
	}

	public static void removeMovementEvent(GUI gui) {
		if (movEvent == null) {
			return ;
		}
		gui.removeEventToFrame(movEvent);
	}

	public static void addMovementEvent(GUI gui) {
		if (movEvent == null) {
			return ;
		}
		gui.AddEventToFrame(movEvent);
	}

	public void startGame(GUI gui, Game g)  {
		Map m = new Map();
		m.setMapSize(player.getLevel());
		m.generateMap();
		m.initController();
		initMovementEvent(gui, g, m);
		addMovementEvent(gui);

		gui.setKeyboardFocus();
		drawMap(gui, m);
		InventoryGUI.showPlayerInventory(gui, player);
	}

	public void setGameMainScene(GUI gui, Game g)  {
		gui.clearScreen();
		gui.setFrameBg(Assets.MENU_BG);
		gui.setMenuQuitIcon(gui, g);
		addMovementEvent(gui);
		startGame(gui, g);
	}
}
