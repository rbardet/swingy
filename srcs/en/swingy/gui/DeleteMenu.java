package en.swingy.gui;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import en.swingy.db.DB;
import en.swingy.game.Game;

public class DeleteMenu {
	public static JButton createDeleteSaveButton(int x, int y, ActionListener e)  {
		ImageIcon std = new ImageIcon(Assets.MENU_BUTTON);
		ImageIcon hover = new ImageIcon(Assets.MENU_BUTTON_HOVER);

		JButton button = new JButton("Delete Save", std);
		button.setRolloverIcon(hover);

		button.setBounds(x, y, std.getIconWidth(), std.getIconHeight());

		button.setFocusPainted(false);
		button.setForeground(Color.WHITE);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);
		button.addActionListener(e);
		return button;
	}

	public static void showDeleteSaveButton(GUI gui, Game g)  {
		try {
			ResultSet rs = DB.fetchSaves();

			int idx = 0;

			while (rs.next()) {
				final int heroId = rs.getInt(DB.ID_VAR);

				int xPosition = 60  + idx * (SavesDisplay.bannerWidth + SavesDisplay.bannerSpacing);
				int yPosition = SavesDisplay.bannerYPos + SavesDisplay.bannerHeight + 20;

				JButton button = createDeleteSaveButton(xPosition, yPosition, e -> {
					try {
						DB.deleteHero(heroId);
						MainMenu.setMainMenu(gui, g);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});

				button.setFocusPainted(false);
				button.setBorderPainted(false);
				button.setContentAreaFilled(false);
				button.setForeground(Color.WHITE);

				gui.getFrame().add(button);
				idx++;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void showDeleteSaveMenu(GUI gui, Game g)  {
		gui.clearScreen();
		SavesDisplay.showAvailableSaves(gui, g);
		showDeleteSaveButton(gui, g);

	}
}
