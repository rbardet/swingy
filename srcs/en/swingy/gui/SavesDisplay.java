package en.swingy.gui;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import en.swingy.db.DB;
import en.swingy.entity.entityclass.EntityClass;
import en.swingy.game.Game;

public class SavesDisplay {

	public static JLabel setBanner(GUI gui, int x, int y) {
		ImageIcon bannerIcon = new ImageIcon(Assets.BANNER);
		JLabel bannerLabel = new JLabel(bannerIcon);
		bannerLabel.setBounds(x, y, 320, bannerIcon.getIconHeight());
		gui.getFrame().add(bannerLabel);
		return bannerLabel;
	}

	public static void showAvailableSaves(GUI gui, Game g) throws SQLException {
		gui.clearScreen();
		ResultSet rs = DB.fetchSaves();

		int panelWidth = 350;
		int panelHeight = 400;
		int yPosition = 150;
		int index = 0;
		int spacing = 50;

		while (rs.next()) {
			int xPosition = 50 + index * (panelWidth + spacing);

			JLabel saveBanner = setBanner(gui, xPosition, yPosition);
			saveBanner.setSize(panelWidth, panelHeight);

			ImageIcon charIcon = EntityClass.getClassAssetsByName(rs.getString(DB.CLASS_VAR));
			JLabel iconLabel = new JLabel(charIcon);
			iconLabel.setBounds((panelWidth - charIcon.getIconWidth()) / 2, 10,
			charIcon.getIconWidth(), charIcon.getIconHeight());
			saveBanner.add(iconLabel);

			String[] lines = {
				"Username: " + rs.getString(DB.NAME_VAR),
				"Class: " + rs.getString(DB.CLASS_VAR),
				"Level: " + rs.getInt(DB.LV_VAR),
				"XP: " + rs.getInt(DB.XP_VAR),
				"Attack: " + rs.getInt(DB.ATT_VAR) + " + " + rs.getInt(DB.WP_ATT_VAR),
				"Defense: " + rs.getInt(DB.DEF_VAR) + " + " + rs.getInt(DB.AM_DEF_VAR),
				"HP: " + rs.getInt(DB.HP_VAR) + " + " + rs.getInt(DB.HL_HP_VAR),
				"Weapon: " + rs.getString(DB.WP_NAME_VAR),
				"Armor: " + rs.getString(DB.AM_NAME_VAR),
				"Helm: " + rs.getString(DB.HL_NAME_VAR)
			};

			int lineHeight = 25;
			int yText = iconLabel.getY() + charIcon.getIconHeight() + 10;
			for (String line : lines) {
				JLabel label = new JLabel(line);
				label.setForeground(Color.WHITE);
				label.setBounds(10, yText, panelWidth - 20, lineHeight);
				saveBanner.add(label);
				yText += lineHeight;
			}

			index++;
		}

		rs.close();

		gui.setMenuQuitIcon(e -> {
			try {
				MainMenu.setMainMenu(gui, g);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		gui.getFrame().repaint();
	}
}
