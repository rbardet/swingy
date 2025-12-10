package en.swingy.game.Fight.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import en.swingy.equipement.Equipement;
import en.swingy.game.Controller;
import en.swingy.game.Game;
import en.swingy.game.Fight.Fight;
import en.swingy.gui.Assets;
import en.swingy.gui.GUI;
import en.swingy.gui.button.Button;
import en.swingy.hero.Hero;

public class FightGUI { 	
	public static boolean isInMenu = false;
	
	public static void placeYesNoButton(
		ActionListener yesEvent,
		ActionListener noEvent
	)
	{
		GUI gui = GUI.getInstance();
		JButton yesButton = Button.createMenuButton(gui, "Yes", 0, 400, true,
		Assets.MENU_BUTTON, Assets.MENU_BUTTON_HOVER, yesEvent);
		gui.getFrame().add(yesButton);

		JButton noButton = Button.createMenuButton(gui, "No", 0, 500, true,
		Assets.MENU_BUTTON, Assets.MENU_BUTTON_HOVER, noEvent);
		gui.getFrame().add(noButton);
	}

	public static void quitMenu(boolean clearScreen) {
		GUI gui = GUI.getInstance();
		if (clearScreen) {
			gui.clearScreen();
		}
		JLabel label = new JLabel("Press any key to continue");
		label.setFont(gui.getCustomFont().deriveFont(Font.BOLD, 30f));
		label.setForeground(Color.WHITE);
		label.setBounds(400, 200, 800, 100);

		gui.getFrame().add(label);
		gui.getFrame().repaint();

		isInMenu = false;
		gui.setKeyboardFocus();
	}

	public static void AfterFightEquipEvent(Hero player, Equipement drop) {
		GUI gui = GUI.getInstance();
		gui.clearScreen();

		JLabel label = new JLabel("You Won");
		label.setFont(gui.getCustomFont().deriveFont(Font.BOLD, 30f));
		label.setForeground(Color.WHITE);
		label.setBounds(300, 200, 500, 100);
		gui.getFrame().add(label);

		JLabel label2 = new JLabel("You have dropped : " + drop.getName());
		label2.setFont(gui.getCustomFont().deriveFont(Font.BOLD, 30f));
		label2.setForeground(Color.WHITE);
		label2.setBounds(300, 300, 500, 100);
		gui.getFrame().add(label2);

		ActionListener yesEvent = e -> {
			player.equip(drop);
			quitMenu(true);
		};

		ActionListener noEvent = e -> {
			quitMenu(true);
		};

		placeYesNoButton(yesEvent, noEvent);
		gui.getFrame().repaint();
	}

	public static void Lose() {
		GUI gui = GUI.getInstance();
		gui.clearScreen();
		JLabel label = new JLabel("You Lost");
		label.setFont(gui.getCustomFont().deriveFont(Font.BOLD, 30f));
		label.setForeground(Color.WHITE);
		label.setBounds(300, 200, 500, 100);
		gui.getFrame().add(label);
		gui.getFrame().repaint();
	}

	public static void FleeSuccess(GUI gui) {
		gui.clearScreen();
		JLabel label = new JLabel("You fleed");
		label.setFont(gui.getCustomFont().deriveFont(Font.BOLD, 30f));
		label.setForeground(Color.WHITE);
		label.setBounds(300, 200, 500, 100);
		gui.getFrame().add(label);
		gui.getFrame().repaint();
	}

	public static void Flee(Controller ctl, String mov) {
		GUI gui = GUI.getInstance();
		gui.clearScreen();

		JLabel label = new JLabel("A fight will start, Would you like to flee");
		label.setFont(gui.getCustomFont().deriveFont(Font.BOLD, 30f));
		label.setForeground(Color.WHITE);
		label.setBounds(250, 200, 1000, 100);
		gui.getFrame().add(label);

		ActionListener yesEvent = e -> {
			if (!Fight.calculateFleeOdds()) {
				Fight.Simulate(Game.getPlayer());
			}
			ctl.goBack(mov);
			FleeSuccess(gui);
			quitMenu(true);
		};

		ActionListener noEvent = e -> {
			Fight.Simulate(Game.getPlayer());
			quitMenu(false);
		};

		placeYesNoButton(yesEvent, noEvent);
		gui.getFrame().repaint();
		isInMenu = true;
	}
}
