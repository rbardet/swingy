package en.swingy.gui.guigame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import en.swingy.entity.entityclass.EntityClass;
import en.swingy.equipement.armor.ArmorEnum;
import en.swingy.equipement.helm.HelmEnum;
import en.swingy.equipement.weapon.WeaponEnum;
import en.swingy.gui.Assets;
import en.swingy.gui.GUI;
import en.swingy.hero.Hero;

public class InventoryGUI {
	
	public static void displayerPlayerIcon(GUI gui, Hero player, JLabel label) {
		ImageIcon pIcon = EntityClass.getClassAssetsByName(
				player.getEClass().getClass().getSimpleName()
		);
		JLabel PlayerIcon = new JLabel(pIcon);
		PlayerIcon.setBounds(192, 27, pIcon.getIconWidth(), pIcon.getIconHeight());

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
		int nameY = 93;
		PlayerName.setBounds(nameX, nameY, textWidth, 30);
		label.add(PlayerName);
	}
	
	public static void displayPlayerStats(GUI gui, Hero player, JLabel label) {
		JLabel lv = new JLabel("LV " + player.getLevel());
		JLabel xp = new JLabel("XP " + player.getXP());
		float p_att = player.getEClass().getAttack() + player.getWeapon().getAttack();
		JLabel att = new JLabel("ATT " + p_att);
		float p_def = player.getEClass().getDefense() + player.getArmor().getDefense();
		JLabel def = new JLabel("DEF " + p_def);
		float p_hp = player.getEClass().getHP() + player.getHelm().getHP();
		JLabel hp = new JLabel("HP " + p_hp);

		lv.setFont(gui.getCustomFont().deriveFont(Font.BOLD));
		lv.setBounds(110, 280, 400, 30);
		lv.setForeground(Color.white);

		xp.setFont(gui.getCustomFont().deriveFont(Font.BOLD));
		xp.setBounds(110, 320, 400, 30);
		xp.setForeground(Color.white);

		att.setFont(gui.getCustomFont().deriveFont(Font.BOLD));
		att.setBounds(110, 360, 400, 30);
		att.setForeground(Color.white);

		def.setFont(gui.getCustomFont().deriveFont(Font.BOLD));
		def.setBounds(110, 400, 400, 30);
		def.setForeground(Color.white);

		hp.setFont(gui.getCustomFont().deriveFont(Font.BOLD));
		hp.setBounds(110, 440, 400, 30);
		hp.setForeground(Color.white);

		label.add(lv);
		label.add(xp);
		label.add(att);
		label.add(def);
		label.add(hp);
	}

	public static void displayPlayerStuff(GUI gui, Hero player, JLabel label) {
		ImageIcon weapon = new ImageIcon(WeaponEnum.findAssetByName(player.getWeapon().getName()));
		JLabel l1 = new JLabel(weapon);
		l1.setBounds(70, 500, 66, 110);

		ImageIcon armor = new ImageIcon(ArmorEnum.findAssetByName(player.getArmor().getName()));
		JLabel l2 = new JLabel(armor);
		l2.setBounds(185, 500, 66, 110);

		ImageIcon helm = new ImageIcon(HelmEnum.findAssetByName(player.getHelm().getName()));
		JLabel l3 = new JLabel(helm);
		l3.setBounds(295, 500, 66, 110);

		label.add(l1);
		label.add(l2);
		label.add(l3);
	}

	public static void showPlayerInventory(GUI gui, Hero player) {
		ImageIcon i = new ImageIcon(Assets.PLAYER_INV);
		JLabel label = new JLabel(i);
		int x = 100;
		int y = 20;
		label.setBounds(x, y, i.getIconWidth(), i.getIconHeight());

		displayerPlayerIcon(gui, player, label);
		displayPlayerName(gui, player, label, i.getIconWidth());
		displayPlayerStats(gui, player, label);
		displayPlayerStuff(gui, player, label);
		gui.getFrame().add(label);
	}
}