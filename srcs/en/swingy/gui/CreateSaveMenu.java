package en.swingy.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

import en.swingy.game.Game;

public class CreateSaveMenu {

	public static void setClassDesc(GUI gui, String descHeader, String descBody, String descStat) {
		JLabel header = new JLabel(descHeader);
		header.setBounds(150, 150, 480, 30);
		header.setForeground(Color.WHITE);
		header.setFont(gui.getCustomFont().deriveFont(Font.BOLD, 20f));
		gui.getFrame().add(header);

		JTextArea body = new JTextArea(descBody);
		body.setBounds(150, 190, 480, 120);
		body.setLineWrap(true);
		body.setWrapStyleWord(true);
		body.setEditable(false);
		body.setOpaque(false);
		body.setForeground(new Color(0xDC, 0xB7, 0x95));
		body.setFont(gui.getCustomFont().deriveFont(Font.PLAIN, 14f));
		gui.getFrame().add(body);

		String[] stats = descStat.split("; ");
		int statY = 320;
		for (String stat : stats) {
			JLabel statLabel = new JLabel(stat);
			statLabel.setBounds(150, statY, 480, 25);
			statLabel.setForeground(Color.WHITE);
			statLabel.setFont(gui.getCustomFont().deriveFont(Font.BOLD, 14f));
			gui.getFrame().add(statLabel);
			statY += 25;
		}
	}

	public static JButton createClassesIcon(
		String base,
		String hover,
		int x,
		int y,
		String bg,
		String class_header,
		String class_body,
		String class_stats,
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
				setClassDesc(gui, class_header, class_body, class_stats);
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
			{Assets.BARBARIAN_ICON, Assets.BARBARIAN_HOVER_ICON, Assets.BARBARIAN_BG, GUI.BARBARIAN_DESC_HEADER, GUI.BARBARIAN_DESC_BODY, GUI.BARBARIAN_DESC_STAT},
			{Assets.CRUSADER_ICON, Assets.CRUSADER_HOVER_ICON, Assets.CRUSADER_BG, GUI.CRUSADER_DESC_HEADER, GUI.CRUSADER_DESC_BODY, GUI.CRUSADER_DESC_STAT},
			{Assets.DEMONHUNTER_ICON, Assets.DEMONHUNTER_HOVER_ICON, Assets.DEMONHUNTER_BG, GUI.DEMONHUNTER_DESC_HEADER, GUI.DEMONHUNTER_DESC_BODY, GUI.DEMONHUNTER_DESC_STAT},
			{Assets.MONK_ICON, Assets.MONK_HOVER_ICON, Assets.MONK_BG, GUI.MONK_DESC_HEADER, GUI.MONK_DESC_BODY, GUI.MONK_DESC_STAT},
			{Assets.NECROMANCER_ICON, Assets.NECROMANCER_HOVER_ICON, Assets.NECROMANCER_BG, GUI.NECROMANCER_DESC_HEADER, GUI.NECROMANCER_DESC_BODY, GUI.NECROMANCER_DESC_STAT},
			{Assets.WITCHDOCTOR_ICON, Assets.WITCHDOCTOR_HOVER_ICON, Assets.WITCHDOCTOR_BG, GUI.WITCHDOCTOR_DESC_HEADER, GUI.WITCHDOCTOR_DESC_BODY, GUI.WITCHDOCTOR_DESC_STAT},
			{Assets.WIZARD_ICON, Assets.WIZARD_HOVER_ICON, Assets.WIZARD_BG, GUI.WIZARD_DESC_HEADER, GUI.WIZARD_DESC_BODY, GUI.WIZARD_DESC_STAT}
		};

		gui.setMenuQuitIcon(gui, g);
		int currentY = startY;
		for (int i = 0; i < classes.length; i++) {
			String base = classes[i][0];
			String hover = classes[i][1];
			String bg = classes[i][2];
			String desc_header = classes[i][3];
			String desc_body = classes[i][4];
			String desc_stat = classes[i][5];

			ImageIcon icon = new ImageIcon(base);
			JButton button = createClassesIcon(base, hover, startX, currentY, bg, desc_header, desc_body, desc_stat, i, g, gui);
			gui.getFrame().add(button);

			currentY += icon.getIconHeight() + gap;
		}

		JPanel line = new JPanel();
		line.setBackground(new Color(0xDB, 0xB9, 0x97, 150));
		line.setBounds(startX + 50 + 20, startY, 2, currentY - startY); 

		gui.getFrame().add(line);
	}

	public static void setCharacterNameField(GUI gui) {
		ImageIcon i = new ImageIcon(Assets.USERNAME);
		JLabel l = new JLabel(i);
		l.setBounds(150, 15, i.getIconWidth(), i.getIconHeight());
		gui.getFrame().add(l);

		JTextField textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setFont(gui.getCustomFont().deriveFont(16f));
		textField.setOpaque(false);
		textField.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(Color.WHITE, 2),
			BorderFactory.createEmptyBorder(5, 5, 5, 5)
		));
		textField.setCaretColor(Color.WHITE);
		textField.setBounds(150, 70, 250, 35);

		textField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(DocumentEvent e) { updateUsername(); }
			public void removeUpdate(DocumentEvent e) { updateUsername(); }
			public void changedUpdate(DocumentEvent e) { updateUsername(); }

			private void updateUsername() {
				gui.setUsername(textField.getText());
			}
		});

		gui.getFrame().add(textField);
		gui.getFrame().repaint();
	}

	public static void setCreateAccountButton(GUI gui) {

	}

	public static void createChacterMenu(GUI gui, Game g) throws SQLException {
		gui.clearScreen();
		setCharacterNameField(gui);
		createClassesCaroussel(gui, g);
		setCharacterNameField(gui);
	}

}
