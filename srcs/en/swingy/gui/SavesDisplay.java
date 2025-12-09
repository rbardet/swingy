package en.swingy.gui;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import en.swingy.db.DB;
import en.swingy.entity.entityclass.EntityClass;
import en.swingy.game.Game;

public class SavesDisplay {
	static final int bannerWidth = 320;
	static final int bannerHeight = 608;
	static final int bannerSpacing = 80;
	static final int bannerYPos = 0;
	static final int bannerXPos = 80;

	public static JLabel setBanner(GUI gui, int x, int y) {
		ImageIcon bannerIcon = new ImageIcon(Assets.BANNER);
		JLabel bannerLabel = new JLabel(bannerIcon);
		bannerLabel.setBounds(x, y, 320, bannerIcon.getIconHeight());
		gui.getFrame().add(bannerLabel);
		return bannerLabel;
	}

	public static void showAvailableSaves(GUI gui, Game g)  {
		try {
			gui.clearScreen();
			ResultSet rs = DB.fetchSaves();

			int idx = 0;

			while (rs.next()) {
				int xPosition = bannerXPos + idx * (bannerWidth + bannerSpacing);

				JLabel banner = setBanner(gui, xPosition, bannerYPos);
				banner.setSize(bannerWidth, bannerHeight);

				ImageIcon charIcon = EntityClass.getClassAssetsByName(rs.getString(DB.CLASS_VAR));
				JLabel iconLabel = new JLabel(charIcon);
				iconLabel.setBounds((bannerWidth / 2) - 22, 15, charIcon.getIconWidth(), charIcon.getIconHeight());
				banner.add(iconLabel);

				String[] lines = {
					"Level: " + rs.getInt(DB.LV_VAR),
					"XP: " + rs.getInt(DB.XP_VAR),
					"Attack: " + rs.getInt(DB.ATT_VAR) + " + " + rs.getInt(DB.WP_ATT_VAR),
					"Defense: " + rs.getInt(DB.DEF_VAR) + " + " + rs.getInt(DB.AM_DEF_VAR),
					"HP: " + rs.getInt(DB.HP_VAR) + " + " + rs.getInt(DB.HL_HP_VAR),
					"Weapon: " + rs.getString(DB.WP_NAME_VAR),
					"Armor: " + rs.getString(DB.AM_NAME_VAR),
					"Helm: " + rs.getString(DB.HL_NAME_VAR)
				};

				JLabel name = new JLabel(rs.getString(DB.NAME_VAR), SwingConstants.CENTER);
				name.setForeground(Color.WHITE);
				name.setFont(gui.getCustomFont().deriveFont(Font.BOLD, 18f));
				name.setBounds(0, iconLabel.getY() + charIcon.getIconHeight() + 20, bannerWidth, 30);
				banner.add(name);

				JLabel separator = new JLabel();
				separator.setOpaque(true);
				separator.setBackground(Color.WHITE);
				separator.setBounds(50, name.getY() + name.getHeight(), bannerWidth - 100, 2);
				banner.add(separator);

				int lineHeight = 25;
				int yText = separator.getY() + separator.getHeight() + 10;
				for (String line : lines) {
					JLabel label = new JLabel(line, SwingConstants.CENTER);
					label.setForeground(Color.WHITE);
					label.setBounds(0, yText, bannerWidth, lineHeight);
					banner.add(label);
					yText += lineHeight;
				}

				idx++;
			}

			rs.close();

			gui.setMenuQuitIcon(gui, g);

			gui.getFrame().repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
