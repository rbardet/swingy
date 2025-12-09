package en.swingy.gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import en.swingy.entity.entityclass.EntityClass;
import en.swingy.game.Game;
import en.swingy.hero.Hero;

public class GUI_Game {
	Hero player;
	Boolean Inventory_seen = false;

	public GUI_Game(Hero p_player) {
		this.player = p_player;
	}

	public void showPlayerInventory(GUI gui) {
		// if (Inventory_seen == true) {
		// 	return ;
		// }
		ImageIcon i = new ImageIcon(Assets.PLAYER_INV);
		JLabel label = new JLabel(i);
		int x = (GUI.WIDHT - i.getIconWidth()) / 2;
		int y = (GUI.HEIGHT - i.getIconHeight()) / 2;
		label.setBounds(x, y, i.getIconWidth(), i.getIconHeight());
		JLabel PlayerName = new JLabel(this.player.getName());
		PlayerName.setFont(gui.getCustomFont());
		PlayerName.setForeground(Color.WHITE);
		PlayerName.setBounds(x, y, i.getIconWidth(), i.getIconHeight());
		System.out.println(this.player.getEClass().toString());
		ImageIcon pIcon = EntityClass.getClassAssetsByName(this.player.getEClass().getClass().getName());
		JLabel PlayerIcon = new JLabel(pIcon);
		PlayerIcon.setBounds(x, y, i.getIconWidth(), i.getIconHeight());
		label.add(PlayerName);
		label.add(PlayerIcon);
		gui.getFrame().add(PlayerIcon);
	}

	public void setPlayerInventoryToggle(GUI gui) {
		KeyListener k = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					showPlayerInventory(gui);
				}
			}
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
		};
		gui.AddEventToFrame(k);
		gui.setKeyboardFocus();
	}

	public void setGameMainScene(GUI gui, Game g) throws SQLException {
		gui.clearScreen();
		gui.setFrameBg(Assets.MENU_BG);
		gui.setMenuQuitIcon(gui, g);
		showPlayerInventory(gui);
		this.setPlayerInventoryToggle(gui);
	}
}
