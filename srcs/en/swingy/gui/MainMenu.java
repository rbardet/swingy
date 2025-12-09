package en.swingy.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import en.swingy.game.Game;

public class MainMenu {

	public static final int startY = 200;
	public static final int padding = 70;

	public static JButton createMenuButton(GUI gui, String content, int y, ActionListener event) {
		ImageIcon std = new ImageIcon(Assets.MENU_BUTTON);
		ImageIcon hover = new ImageIcon(Assets.MENU_BUTTON_HOVER);

		JButton button = new JButton(content, std);
		button.setRolloverIcon(hover);
		int centerX = (GUI.WIDHT - std.getIconWidth()) / 2;
		button.setBounds(centerX, y, std.getIconWidth(), std.getIconHeight());
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);
		button.setForeground(Color.WHITE);
		button.setFont(gui.getCustomFont());
		button.addActionListener(event);
		return button;
	}

	public static void tooManySaves(GUI gui, Game g) throws SQLException {
		gui.clearScreen();

		JLabel l = new JLabel("Maximum number of save reached, Would you like to delete one ?");
		l.setForeground(Color.WHITE);
		l.setFont(gui.getCustomFont().deriveFont(Font.BOLD, 20f));
		int labelWidth = 900;
		int labelX = (GUI.WIDHT - labelWidth) / 2;
		l.setBounds(labelX, 100, labelWidth, 50);
		gui.getFrame().add(l);

		JButton buttonYes = createMenuButton(gui, "Yes", 250, e -> {
			try {
				DeleteMenu.showDeleteSaveMenu(gui, g);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		gui.getFrame().add(buttonYes);

		JButton buttonNo = createMenuButton(gui, "No", 350, e -> {
			try {
				MainMenu.setMainMenu(gui, g);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		gui.getFrame().add(buttonNo);

		gui.getFrame().repaint();
	}



	public static void setMainMenu(GUI gui, Game g) throws SQLException {
		g.setDbSize();
		gui.setFrameBg(Assets.MENU_BG);
		JButton b1 = createMenuButton(gui, "Create a character", startY, e->{
			try {
				if (g.getDBSize() >= 3) {
					tooManySaves(gui, g);
					return ;
				}
				CreateSaveMenu.createChacterMenu(gui, g);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		gui.getFrame().add(b1);

		JButton b2 = createMenuButton(gui, "Load a character", startY + padding, e->{
			try {
				LoadMenu.showLoadSaveMenu(gui, g);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		gui.getFrame().add(b2);

		JButton b3 = createMenuButton(gui, "Delete a character", startY + padding * 2, e->{
			try {
				DeleteMenu.showDeleteSaveMenu(gui, g);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		gui.getFrame().add(b3);

		JButton b4 = createMenuButton(gui, "Exit the game", startY + padding * 3, e->Game.exitGame());
		gui.getFrame().add(b4);
	}
}
