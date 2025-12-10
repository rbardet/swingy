package en.swingy.game.Fight.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import en.swingy.db.DB;
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

	private static JLabel centeredLabel(String text, int y, float size) {
		GUI gui = GUI.getInstance();
	
		JLabel label = new JLabel(text);
		label.setFont(gui.getCustomFont().deriveFont(Font.BOLD, size));
		label.setForeground(Color.WHITE);
	
		FontMetrics fm = label.getFontMetrics(label.getFont());
		int textWidth = fm.stringWidth(text);
	
		int x = (GUI.WIDHT / 2) - (textWidth / 2);
	
		label.setBounds(x, y, textWidth, (int)(size + 20));
		return label;
	}

	public static void placeYesNoButton(ActionListener yesEvent, ActionListener noEvent) {
		GUI gui = GUI.getInstance();

		JButton yesButton = Button.createMenuButton(
			gui, "Yes", 0, 400, true,
			Assets.MENU_BUTTON, Assets.MENU_BUTTON_HOVER, yesEvent
		);
		gui.getFrame().add(yesButton);

		JButton noButton = Button.createMenuButton(
			gui, "No", 0, 490, true,
			Assets.MENU_BUTTON, Assets.MENU_BUTTON_HOVER, noEvent
		);
		gui.getFrame().add(noButton);
	}

	public static void quitMenu(boolean clearScreen) {
		GUI gui = GUI.getInstance();
		if (clearScreen) {
			gui.clearScreen();
		}
		GUI.setMenuQuitIcon();

		JLabel label = centeredLabel("Press any key to continue", 300, 30f);
		gui.getFrame().add(label);

		gui.getFrame().repaint();

		isInMenu = false;
		gui.setKeyboardFocus();
	}

	public static void AfterFightEquipEvent(Hero player, Equipement drop) {
		GUI gui = GUI.getInstance();

		JLabel title = centeredLabel("You Won", 200, 42f);
		gui.getFrame().add(title);

		JLabel dropped = centeredLabel("You have dropped : " + drop.getName(), 280, 32f);
		gui.getFrame().add(dropped);
		
		JLabel question = centeredLabel("Would you like to equip", 320, 32f);
		gui.getFrame().add(question);

		ActionListener yesEvent = e -> {
			player.equip(drop);
			DB.updateAfterFight(player);
			quitMenu(true);
		};

		ActionListener noEvent = e -> quitMenu(true);

		placeYesNoButton(yesEvent, noEvent);
		gui.getFrame().repaint();
	}

	public static void Lose() {
		GUI gui = GUI.getInstance();
		gui.clearScreen();
		GUI.setMenuQuitIcon();
		JLabel label = centeredLabel("You Lost", 300, 40f);
		gui.getFrame().add(label);

		gui.getFrame().repaint();
	}

	public static void FleeSuccess(GUI gui) {
		gui.clearScreen();
		GUI.setMenuQuitIcon();
		JLabel label = centeredLabel("You fled successfully", 100, 40f);
		gui.getFrame().add(label);
		gui.getFrame().repaint();
	}

	public static void FleeFailed(GUI gui) {
		gui.clearScreen();
		GUI.setMenuQuitIcon();
		JLabel label = centeredLabel("You failed to flee", 100, 40f);
		gui.getFrame().add(label);
		gui.getFrame().repaint();
	}

	public static void Flee(Controller ctl, String mov) {
		GUI gui = GUI.getInstance();
		gui.clearScreen();
		GUI.setMenuQuitIcon();

		JLabel label = centeredLabel(
			"A fight will start, would you like to flee ?", 
			200, 30f
		);
		gui.getFrame().add(label);

		ActionListener yesEvent = e -> {
			if (!Fight.calculateFleeOdds()) {
				FleeFailed(gui);
				Fight.Simulate(Game.getPlayer());
				return;
			}
			ctl.goBack(mov);
			FleeSuccess(gui);
			quitMenu(false);
		};

		ActionListener noEvent = e -> {
			gui.clearScreen();
			GUI.setMenuQuitIcon();
			Fight.Simulate(Game.getPlayer());
			quitMenu(true);
		};

		placeYesNoButton(yesEvent, noEvent);
		gui.getFrame().repaint();

		isInMenu = true;
	}
}
