package en.swingy.gui;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import en.swingy.game.Game;

public class CreateSaveMenu {
	public static JButton createClassesIcon(
		String base,
		String hover,
		int x,
		int y,
		String bg,
		String class_desc,
		int class_idx,
		Game g,
		GUI gui) throws SQLException
	{
		ImageIcon def = new ImageIcon(base);
		ImageIcon hov = new ImageIcon(hover);

		JButton button = new JButton(def);
		button.setRolloverIcon(hov);
		button.setBounds(x, y, def.getIconWidth(), def.getIconHeight());
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.addActionListener(e -> {
			try {
				gui.clearScreen();
				gui.setFrameBg(bg);
				gui.setPlayerClass(class_idx);
				createChacterMenu(gui, g);
				gui.setClassDesc(class_desc);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		return button;
	}

	public static void createClassesCaroussel(GUI gui, Game g) throws SQLException {
		int startX = 50;
		int startY = 125;
		int gap = 20;

		String[][] classes = {
			{Assets.BARBARIAN_ICON, Assets.BARBARIAN_HOVER_ICON, Assets.BARBARIAN_BG, GUI.BARBARIAN_DESC},
			{Assets.CRUSADER_ICON, Assets.CRUSADER_HOVER_ICON, Assets.CRUSADER_BG, GUI.CRUSADER_DESC},
			{Assets.DEMONHUNTER_ICON, Assets.DEMONHUNTER_HOVER_ICON, Assets.DEMONHUNTER_BG, GUI.DEMONHUNTER_DESC},
			{Assets.MONK_ICON, Assets.MONK_HOVER_ICON, Assets.MONK_BG, GUI.MONK_DESC},
			{Assets.NECROMANCER_ICON, Assets.NECROMANCER_HOVER_ICON, Assets.NECROMANCER_BG, GUI.NECROMANCER_DESC},
			{Assets.WITCHDOCTOR_ICON, Assets.WITCHDOCTOR_HOVER_ICON, Assets.WITCHDOCTOR_BG, GUI.WITCHDOCTOR_DESC},
			{Assets.WIZARD_ICON, Assets.WIZARD_HOVER_ICON, Assets.WIZARD_BG, GUI.WIZARD_DESC}
		};

		gui.setMenuQuitIcon(e -> {
			try {
				MainMenu.setMainMenu(gui, g);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		int currentY = startY;
		for (int i = 0; i < classes.length; i++) {
			String base = classes[i][0];
			String hover = classes[i][1];
			String bg = classes[i][2];
			String desc = classes[i][3];

			ImageIcon icon = new ImageIcon(base);
			JButton button = createClassesIcon(base, hover, startX, currentY, bg, desc, i, g, gui);
			gui.getFrame().add(button);

			currentY += icon.getIconHeight() + gap;
		}

		JPanel line = new JPanel();
		line.setBackground(Color.WHITE);
		line.setBounds(startX + 50 + 20, startY, 2, currentY - startY); 

		gui.getFrame().add(line);
	}

	public static void setCharacterNameField(GUI gui) throws SQLException {
		ImageIcon i = new ImageIcon(Assets.USERNAME);
		JLabel imageLabel = new JLabel(i);
		imageLabel.setBounds(150, 15, i.getIconWidth(), i.getIconHeight());
		gui.getFrame().add(imageLabel);

		ImageIcon bg = new ImageIcon(Assets.USERNAME_FIELD);
		JLabel background = new JLabel(bg);
		background.setBounds(150, 70, bg.getIconWidth(), bg.getIconHeight());
		gui.getFrame().add(background);

		JTextField textField = new JTextField();
		textField.setBounds(150, 70, bg.getIconWidth(), bg.getIconHeight());
		textField.setOpaque(false);
		textField.setForeground(Color.WHITE);

		gui.getFrame().add(textField);
	}

	public static void createChacterMenu(GUI gui, Game g) throws SQLException {
		gui.clearScreen();
		setCharacterNameField(gui);
		createClassesCaroussel(gui, g);
	}

}
