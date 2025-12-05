package en.swingy.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import en.swingy.db.DB;
import en.swingy.entity.entityclass.EntityClass;
import en.swingy.game.Game;

public class GUI {
	private JFrame frame;
	private final int WIDHT = 1280;
	private final int HEIGHT = 720;
	private final String APP_NAME = "Swingy";
	private final String FONT = "ressources/diablo.ttf";
	private Font CUSTOM_FONT;

	private final int startY = 200;
	private final int padding = 70;

	private final String MONK_DESC = """
	Monk -\n
	A swift, spiritual melee fighter using martial arts,
	holy powers and high mobility to strike fast and dodge attacks —
	a balance of offense and agility.\n
	Attack - 7\n
	Defense - 6\n
	HP - 7\n
	""";

	private final String BARBARIAN_DESC = """
	Barbarian -\n
	A savage melee powerhouse who uses raw strength and fury
	to smash through hordes of enemies with brute force
	and devastating close-range attacks.\n
	Attack - 5\n
	Defense - 5\n
	HP - 10\n
	""";

	private final String NECROMANCER_DESC = """
	Necromancer -\n
	A dark spellcaster who manipulates life and death itself:
	raising undead minions, casting curses and controlling corpses
	to overwhelm and debilitate enemies.\n
	Attack - 8\n
	Defense - 4\n
	HP - 8\n
	""";

	private final String CRUSADER_DESC = """
	Crusader -\n
	A heavy-armored holy warrior wielding shields and flails,
	combining melee might with divine powers and protective auras
	to crush foes and defend allies.\n
	Attack - 5\n
	Defense - 8\n
	HP - 7\n
	""";


	private final String WITCHDOCTOR_DESC = """
	Witch Doctor -\n
	A mystical caster who commands curses, poisons, and summoned creatures
	to overwhelm foes from a distance,
	using dark magic and supernatural minions.\n
	Attack - 10\n
	Defense - 3\n
	HP - 7\n
	""";


	private final String DEMONHUNTER_DESC = """
	Demon Hunter -\n
	A deadly ranged class specializing in crossbows, traps,
	and dual-resource management; nimble and tactical,
	striking from afar while avoiding danger.\n
	Attack - 9\n
	Defense - 4\n
	HP - 7\n
	""";


	private final String WIZARD_DESC = """
	Wizard -\n
	A master of arcane and elemental magic,
	capable of dealing massive damage from afar —
	manipulating fire, ice, lightning (and sometimes even time)
	to obliterate enemies.\n
	Attack - 8\n
	Defense - 3\n
	HP - 9\n
	""";

	private int player_class = -1;

	public GUI() throws Exception {
		this.frame = new JFrame();
		CUSTOM_FONT = Font.createFont(
			Font.TRUETYPE_FONT,
			new File(FONT)
		).deriveFont(18f);
		
		GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
		g.registerFont(CUSTOM_FONT);
		this.frame.setSize(WIDHT, HEIGHT);
		this.frame.setTitle(APP_NAME);
		this.frame.setResizable(false);
		this.frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void showScreen() {
		this.frame.setVisible(true);
	}

	public void hideScreen() {
		this.frame.setVisible(false);
	}

	public JButton createMenuButton(String content, int y, ActionListener event) {
		ImageIcon std = new ImageIcon(Assets.MENU_BUTTON);
		ImageIcon hover = new ImageIcon(Assets.MENU_BUTTON_HOVER);

		JButton button = new JButton(content, std);
		button.setRolloverIcon(hover);
		int centerX = (WIDHT - std.getIconWidth()) / 2;
		button.setBounds(centerX, y, std.getIconWidth(), std.getIconHeight());
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);
		button.setForeground(Color.WHITE);
		button.setFont(CUSTOM_FONT);
		button.addActionListener(event);
		return button;
	}

	public void clearScreen() {
		this.frame.getContentPane().removeAll();
		this.frame.repaint();
	}

	public void setFrameBg(String image) {
		ImageIcon bg = new ImageIcon(image);
		JLabel bgLabel = new JLabel(bg);
		bgLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		this.frame.setContentPane(bgLabel);
	}

	public void setMainMenu(Game g) throws SQLException {
		this.setFrameBg(Assets.MENU_FONT);
		JButton b1 = createMenuButton("Create a character", startY, e->{
			try {
				createChacterMenu(g);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		this.frame.add(b1);

		JButton b2 = createMenuButton("Load a character", startY + padding, e->{
			try {
				showLoadSaveMenu(g);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		this.frame.add(b2);

		JButton b3 = createMenuButton("Delete a character", startY + padding * 2, e->{
			try {
				showDeleteSaveMenu(g);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		this.frame.add(b3);

		JButton b4 = createMenuButton("Exit the game", startY + padding * 3, e->Game.exitGame());
		this.frame.add(b4);
	}
	
	public JLabel setBanner(int x, int y) {
		ImageIcon bannerIcon = new ImageIcon(Assets.BANNER);
		JLabel bannerLabel = new JLabel(bannerIcon);
		bannerLabel.setBounds(x, y, 320, bannerIcon.getIconHeight());
		this.frame.add(bannerLabel);
		return bannerLabel;
	}

	public void showAvailableSaves(Game g) throws SQLException {
		this.clearScreen();
		ResultSet rs = DB.fetchSaves();

		int panelWidth = 350;
		int panelHeight = 400;
		int yPosition = 150;
		int index = 0;
		int spacing = 50;

		while (rs.next()) {
			int xPosition = 50 + index * (panelWidth + spacing);

			JLabel saveBanner = setBanner(xPosition, yPosition);
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

		this.setMenuQuitIcon(e -> {
			try {
				setMainMenu(g);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		this.frame.repaint();
	}


	public JButton createDeleteSaveButton(int x, int y, ActionListener e) throws SQLException {
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

	public void showDeleteSaveButton(Game g) throws SQLException {
		ResultSet rs = DB.fetchSaves();

		int panelWidth = 350;
		int panelHeight = 400;
		int yPosition = 160;
		int idx = 0;

		while (rs.next()) {
			int xPosition = 50 + idx * (panelWidth + 50);
			final int heroId = rs.getInt(DB.ID_VAR);

			JButton button = createDeleteSaveButton(xPosition, yPosition + panelHeight + 30, e -> {
				try {
					DB.deleteHero(heroId);
					setMainMenu(g);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});

			button.setFocusPainted(false);
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.setForeground(Color.WHITE);

			this.frame.add(button);
			idx++;
		}
		rs.close();
	}

	public void showDeleteSaveMenu(Game g) throws SQLException {
		clearScreen();
		showAvailableSaves(g);
		showDeleteSaveButton(g);
	}

	public void showLoadSaveButton(Game g) throws SQLException {

	}

	public void showLoadSaveMenu(Game g) throws SQLException {
		clearScreen();
		showAvailableSaves(g);
		showLoadSaveButton(g);
	}

	public void setClassDesc(String desc) {
		JTextArea textArea = new JTextArea(desc);
		textArea.setBounds(150, 150, 480, 320);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(CUSTOM_FONT);
		this.frame.add(textArea);
	}

	public void setMenuQuitIcon(ActionListener e) throws SQLException {
		ImageIcon i = new ImageIcon(Assets.MENU_QUIT);
		ImageIcon hover = new ImageIcon(Assets.MENU_QUIT_HOVER);
		JButton button = new JButton(i);
		button.setRolloverIcon(hover);
		button.setBounds(20, 20, 50, 50);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.addActionListener(e);
		this.frame.add(button);
	}

	public JButton createClassesIcon(
		String base,
		String hover,
		int x,
		int y,
		String bg,
		String class_desc,
		int class_idx,
		Game g) throws SQLException
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
				this.clearScreen();
				setFrameBg(bg);
				this.player_class = class_idx;
				createChacterMenu(g);
				setClassDesc(class_desc);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		});

		return button;
	}

	public void createClassesCaroussel(Game g) throws SQLException {
		int startX = 50;
		int startY = 125;
		int gap = 20;

		String[][] classes = {
			{Assets.BARBARIAN_ICON, Assets.BARBARIAN_HOVER_ICON, Assets.BARBARIAN_BG, BARBARIAN_DESC},
			{Assets.CRUSADER_ICON, Assets.CRUSADER_HOVER_ICON, Assets.CRUSADER_BG, CRUSADER_DESC},
			{Assets.DEMONHUNTER_ICON, Assets.DEMONHUNTER_HOVER_ICON, Assets.DEMONHUNTER_BG, DEMONHUNTER_DESC},
			{Assets.MONK_ICON, Assets.MONK_HOVER_ICON, Assets.MONK_BG, MONK_DESC},
			{Assets.NECROMANCER_ICON, Assets.NECROMANCER_HOVER_ICON, Assets.NECROMANCER_BG, NECROMANCER_DESC},
			{Assets.WITCHDOCTOR_ICON, Assets.WITCHDOCTOR_HOVER_ICON, Assets.WITCHDOCTOR_BG, WITCHDOCTOR_DESC},
			{Assets.WIZARD_ICON, Assets.WIZARD_HOVER_ICON, Assets.WIZARD_BG, WIZARD_DESC}
		};

		setMenuQuitIcon(e -> {
			try {
				setMainMenu(g);
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
			JButton button = createClassesIcon(base, hover, startX, currentY, bg, desc, i, g);
			this.frame.add(button);

			currentY += icon.getIconHeight() + gap;
		}

		JPanel line = new JPanel();
		line.setBackground(Color.WHITE);
		line.setBounds(startX + 50 + 20, startY, 2, currentY - startY); 

		this.frame.add(line);
	}

	public void setCharacterNameField() throws SQLException {
		ImageIcon i = new ImageIcon(Assets.USERNAME);
		JLabel imageLabel = new JLabel(i);
		imageLabel.setBounds(150, 15, i.getIconWidth(), i.getIconHeight());
		this.frame.add(imageLabel);

		ImageIcon bg = new ImageIcon(Assets.USERNAME_FIELD);
		JLabel background = new JLabel(bg);
		background.setBounds(150, 70, bg.getIconWidth(), bg.getIconHeight());
		this.frame.add(background);

		JTextField textField = new JTextField();
		textField.setBounds(150, 70, bg.getIconWidth(), bg.getIconHeight());
		textField.setOpaque(false);
		textField.setForeground(Color.WHITE);

		this.frame.add(textField);
	}

	public void createChacterMenu(Game g) throws SQLException {
		this.clearScreen();
		this.setCharacterNameField();
		this.createClassesCaroussel(g);
	}

	public void runGui(Game game) throws SQLException {
		this.setMainMenu(game);
		this.showScreen();
		while (true) {
			
		}
	}
}
