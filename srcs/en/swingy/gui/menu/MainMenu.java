package en.swingy.gui.menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import en.swingy.game.Game;
import en.swingy.gui.Assets;
import en.swingy.gui.GUI;
import en.swingy.gui.button.Button;
import en.swingy.gui.guigame.GUIGame;

public class MainMenu {

	public static final int startY = 200;
	public static final int padding = 70;

	public static void tooManySaves(GUI gui)  {
		gui.clearScreen();

		JLabel l = new JLabel("Maximum number of save reached, Would you like to delete one ?");
		l.setForeground(Color.WHITE);
		l.setFont(gui.getCustomFont().deriveFont(Font.BOLD, 20f));
		int labelWidth = 900;
		int labelX = (GUI.WIDHT - labelWidth) / 2;
		l.setBounds(labelX, 100, labelWidth, 50);
		gui.getFrame().add(l);

		JButton buttonYes = Button.createMenuButton(gui, "Yes", 0, 250, true,
		Assets.MENU_BUTTON, Assets.MENU_BUTTON_HOVER,
		e -> {
			try {
				DeleteMenu.showDeleteSaveMenu(gui);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		gui.getFrame().add(buttonYes);

		JButton buttonNo = Button.createMenuButton(gui, "No", 0, 350, true,
		Assets.MENU_BUTTON, Assets.MENU_BUTTON_HOVER,
		e -> {
			try {
				MainMenu.setMainMenu(gui);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		gui.getFrame().add(buttonNo);

		gui.getFrame().repaint();
	}



	public static void setMainMenu(GUI gui)  {
		GUIGame.removeMovementEvent(gui);
		Game.setDbSize();
		gui.setFrameBg(Assets.MENU_BG);
		JButton b1 = Button.createMenuButton(gui, "Create a character", 0, startY, true,
		Assets.MENU_BUTTON, Assets.MENU_BUTTON_HOVER,
		e->{
			try {
				if (Game.getDBSize() >= 3) {
					tooManySaves(gui);
					return ;
				}
				CreateSaveMenu.createChacterMenu(gui);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		gui.getFrame().add(b1);

		JButton b2 = Button.createMenuButton(gui, "Load a character", 0, startY + padding, true,
		Assets.MENU_BUTTON, Assets.MENU_BUTTON_HOVER,
		e->{
			try {
				LoadMenu.showLoadSaveMenu(gui);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		gui.getFrame().add(b2);

		JButton b3 = Button.createMenuButton(gui, "Delete a character", 0, startY + padding * 2, true,
		Assets.MENU_BUTTON, Assets.MENU_BUTTON_HOVER,
		e->{
			try {
				DeleteMenu.showDeleteSaveMenu(gui);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		gui.getFrame().add(b3);

		JButton b4 = Button.createMenuButton(gui, "Exit the game", 0, startY + padding * 3, true,
		Assets.MENU_BUTTON, Assets.MENU_BUTTON_HOVER,
		e->Game.exitGame());
		gui.getFrame().add(b4);
	}
}
