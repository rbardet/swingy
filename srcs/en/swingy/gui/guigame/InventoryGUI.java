package en.swingy.gui.guigame;

import java.awt.Color;
import java.awt.FontMetrics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import en.swingy.entity.entityclass.EntityClass;
import en.swingy.gui.Assets;
import en.swingy.gui.GUI;
import en.swingy.hero.Hero;

public class InventoryGUI {
	
	public static void displayerPlayerIcon(GUI gui, Hero player, JLabel label) {
		ImageIcon pIcon = EntityClass.getClassAssetsByName(
				player.getEClass().getClass().getSimpleName()
		);
		JLabel PlayerIcon = new JLabel(pIcon);
		PlayerIcon.setBounds(182, 30, pIcon.getIconWidth(), pIcon.getIconHeight());

		label.add(PlayerIcon);
	}

	public static void displayPlayerName(GUI gui, Hero player, JLabel label, int IconWidth) {
		JLabel PlayerName = new JLabel(player.getName());
		PlayerName.setFont(gui.getCustomFont());
		PlayerName.setForeground(Color.WHITE);

		FontMetrics fm = PlayerName.getFontMetrics(PlayerName.getFont());
		int textWidth = fm.stringWidth(player.getName());
		int bannerWidth = IconWidth;

		int nameX = (bannerWidth - textWidth) / 2;
		int nameY = 90;
		PlayerName.setBounds(nameX, nameY, textWidth, 30);
		label.add(PlayerName);
	}
	
	public static void displayPlayerStats(GUI gui, Hero player) {

	}

	public static void placePlayerStuff(GUI gui, Hero player) {
		ImageIcon weapon = new ImageIcon(player.getWeapon().getName());
	}

	public static void showPlayerInventory(GUI gui, Hero player) {
		ImageIcon i = new ImageIcon(Assets.PLAYER_INV);
		JLabel label = new JLabel(i);
		int x = 100;
		int y = 30;
		label.setBounds(x, y, i.getIconWidth(), i.getIconHeight());

		displayPlayerName(gui, player, label, i.getIconWidth());
		displayerPlayerIcon(gui, player, label);

		gui.getFrame().add(label);

		placePlayerStuff(gui, player);
	}
}