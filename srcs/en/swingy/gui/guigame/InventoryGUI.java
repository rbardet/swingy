package en.swingy.gui.guigame;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import en.swingy.entity.entityclass.EntityClass;
import en.swingy.gui.Assets;
import en.swingy.gui.GUI;
import en.swingy.hero.Hero;

public class InventoryGUI {

	public static void placePlayerStuff(GUI gui, Hero player) {
		ImageIcon i = new ImageIcon(player.getWeapon().getName());
	}

	public static void showPlayerStats(GUI gui, Hero player) {

	}

	public static void showPlayerInventory(GUI gui, Hero player) {
		ImageIcon i = new ImageIcon(Assets.PLAYER_INV);
		JLabel label = new JLabel(i);
		int x = 100;
		int y = 30;
		label.setBounds(x, y, i.getIconWidth(), i.getIconHeight());

		JLabel PlayerName = new JLabel(player.getName());
		PlayerName.setFont(gui.getCustomFont());
		PlayerName.setForeground(Color.WHITE);
		PlayerName.setBounds(200, 90, 200, 30);
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