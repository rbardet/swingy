package en.swingy.game.Fight.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import en.swingy.equipement.Equipement;
import en.swingy.gui.Assets;
import en.swingy.gui.GUI;
import en.swingy.gui.button.Button;
import en.swingy.hero.Hero;

public class FightGUI {

	public static void AfterFightEquipEvent(Hero player, Equipement drop) {

	}
	
	public static boolean Flee() {
		GUI gui = GUI.getInstance();

		final boolean[] fleeResult = {false};

		int dialogWidth = 400;
		int dialogHeight = 220;

		// JDialog transparent et modale
		JDialog dialog = new JDialog(gui.getFrame(), "Flee", true);
		dialog.setUndecorated(true);
		dialog.setSize(dialogWidth, dialogHeight);

		// Rend la fenêtre vraiment transparente
		dialog.setBackground(new Color(0, 0, 0, 0)); // <-- TRANSPARENT

		// Centrage
		int centerX = (GUI.WIDHT - dialogWidth) / 2;
		int centerY = (GUI.HEIGHT - dialogHeight) / 2;
		dialog.setLocation(centerX, centerY);

		// Panel transparent pour contenir les éléments
		JPanel panel = new JPanel(null);
		panel.setOpaque(false); // <-- PAS DE FOND
		dialog.setContentPane(panel);

		// Texte
		JLabel text = new JLabel("Do you want to flee?", SwingConstants.CENTER);
		text.setBounds(0, 20, dialogWidth, 60);
		text.setFont(gui.getCustomFont().deriveFont(Font.BOLD, 26f));
		text.setForeground(Color.WHITE);
		panel.add(text);

		// Bouton YES
		JButton yesButton = Button.createMenuButton(
			gui,
			"Yes",
			0,
			120,
			false,
			Assets.MENU_BUTTON,
			Assets.MENU_BUTTON_HOVER,
			e -> {
				fleeResult[0] = true;
				dialog.dispose();
			}
		);
		panel.add(yesButton);

		// Bouton NO
		JButton noButton = Button.createMenuButton(
			gui,
			"No",
			800,
			120,
			false,
			Assets.MENU_BUTTON,
			Assets.MENU_BUTTON_HOVER,
			e -> {
				fleeResult[0] = false;
				dialog.dispose();
			}
		);
		panel.add(noButton);

		dialog.setVisible(true);

		return fleeResult[0];
	}
}
