package en.swingy.gui.guigame;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import en.swingy.entity.entityclass.EntityClass;
import en.swingy.equipement.weapon.WeaponAssets;
import en.swingy.gui.Assets;
import en.swingy.gui.GUI;
import en.swingy.hero.Hero;

public class InventoryGUI {

	public static void setPlayerInventoryToggle(GUI gui, Hero player) {
		KeyListener k = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					showPlayerInventory(gui, player);
				}
			}
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
		};
		gui.AddEventToFrame(k);
		gui.setKeyboardFocus();
	}

	public static void placePlayerStuff(GUI gui, Hero player) {
		ImageIcon i = new ImageIcon(player.getWeapon().getName());
	}

	public static void showPlayerInventory(GUI gui, Hero player) {
		// if (Inventory_seen == true) {
		// 	return ;
		// }
		ImageIcon i = new ImageIcon(Assets.PLAYER_INV);
		JLabel label = new JLabel(i);
		int x = (GUI.WIDHT - i.getIconWidth()) / 2;
		int y = (GUI.HEIGHT - i.getIconHeight()) / 2;
		label.setBounds(x, y, i.getIconWidth(), i.getIconHeight());

		JLabel PlayerName = new JLabel(player.getName());
		PlayerName.setFont(gui.getCustomFont());
		PlayerName.setForeground(Color.WHITE);
		PlayerName.setBounds(80, 90, 200, 30);
		PlayerName.setSize(400, 30);

		ImageIcon pIcon = EntityClass.getClassAssetsByName(
				player.getEClass().getClass().getSimpleName()
		);
		JLabel PlayerIcon = new JLabel(pIcon);
		PlayerIcon.setBounds(182, 30, pIcon.getIconWidth(), pIcon.getIconHeight());

		label.add(PlayerName);
		label.add(PlayerIcon);
		gui.getFrame().add(label);
		placePlayerStuff(gui, player);
	}
}