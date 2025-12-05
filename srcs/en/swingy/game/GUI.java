package en.swingy.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.MediaSize.JIS;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI {
	private JFrame frame;
	private final int WIDHT = 1280;
	private final int HEIGHT = 720;
	private final String APP_NAME = "Swingy";

	private final int startY = 200;
	private final int padding = 70;

	private final String ASSETS_PATH = "ressources/assets/";
	private final String CLASS_BG_PATH = ASSETS_PATH + "classes-bg/";
	private final String CLASS_ICON_PATH = ASSETS_PATH + "classes-icon/";
	private final String MENU_BUTTON =  ASSETS_PATH + "menu_button.png";
	private final String MENU_BUTTON_HOVER = ASSETS_PATH + "menu_button_hover.png";
	private final String MENU_FONT = ASSETS_PATH + "menu_font.png";
	private final String MENU_QUIT = ASSETS_PATH + "menu_quit.png";
	private final String MENU_QUIT_HOVER = ASSETS_PATH + "menu_quit_hover.png";

	private final String MONK_BG = CLASS_BG_PATH + "md_monk_background.png";
	private final String BARBARIAN_BG = CLASS_BG_PATH + "md_barbarian_background.png";
	private final String NECROMANCER_BG = CLASS_BG_PATH + "md_necromancer_background.png";
	private final String CRUSADER_BG = CLASS_BG_PATH + "md_crusader_background.png";
	private final String WITCHDOCTOR_BG = CLASS_BG_PATH + "md_witch_doctor_background.png";
	private final String DEMONHUNTER_BG = CLASS_BG_PATH + "md_demon_hunter_background.png";
	private final String WIZARD_BG = CLASS_BG_PATH + "md_wizard_background.png";

	private final String BARBARIAN_ICON = CLASS_ICON_PATH + "barbarian_icon_default.png";
	private final String BARBARIAN_HOVER_ICON = CLASS_ICON_PATH + "barbarian_icon_active.png";
	private final String CRUSADER_ICON = CLASS_ICON_PATH + "crusader_icon_default.png";
	private final String CRUSADER_HOVER_ICON = CLASS_ICON_PATH + "crusader_icon_active.png";
	private final String DEMONHUNTER_ICON = CLASS_ICON_PATH + "demonhunter_icon_default.png";
	private final String DEMONHUNTER_HOVER_ICON = CLASS_ICON_PATH + "demonhunter_icon_active.png";
	private final String MONK_ICON = CLASS_ICON_PATH + "monk_icon_default.png";
	private final String MONK_HOVER_ICON = CLASS_ICON_PATH + "monk_icon_active.png";
	private final String NECROMANCER_ICON = CLASS_ICON_PATH + "necromancer_icon_default.png";
	private final String NECROMANCER_HOVER_ICON = CLASS_ICON_PATH + "necromancer_icon_active.png";
	private final String WITCHDOCTOR_ICON = CLASS_ICON_PATH + "witch_doctor_icon_default.png";
	private final String WITCHDOCTOR_HOVER_ICON = CLASS_ICON_PATH + "witch_doctor_icon_active.png";
	private final String WIZARD_ICON = CLASS_ICON_PATH + "wizard_icon_default.png";
	private final String WIZARD_HOVER_ICON = CLASS_ICON_PATH + "wizard_icon_active.png";

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

	public GUI() {
		this.frame = new JFrame();
		this.frame.setSize(WIDHT, HEIGHT);
		this.frame.setTitle(APP_NAME);
		this.frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void showScreen() {
		this.frame.setVisible(true);
	}

	public void hideScreen() {
		this.frame.setVisible(false);
	}

	public JButton createMenuButton(String content, int y, ActionListener event) {
		ImageIcon std = new ImageIcon(MENU_BUTTON);
		ImageIcon hover = new ImageIcon(MENU_BUTTON_HOVER);

		JButton button = new JButton(content, std);
		button.setRolloverIcon(hover);
		int centerX = (WIDHT - std.getIconWidth()) / 2;
		button.setBounds(centerX, y, std.getIconWidth(), std.getIconHeight());
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Serif", Font.BOLD, 18));
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

	public void setMainMenu() {
		this.setFrameBg(MENU_FONT);
		JButton b1 = createMenuButton("Create a character", startY, e->createChacterMenu());
		this.frame.add(b1);

		JButton b2 = createMenuButton("Load a character", startY + padding, e->Game.exitGame());
		this.frame.add(b2);

		JButton b3 = createMenuButton("Delete a character", startY + padding * 2, e->Game.exitGame());
		this.frame.add(b3);

		JButton b4 = createMenuButton("Exit the game", startY + padding * 3, e->Game.exitGame());
		this.frame.add(b4);
	}

	public void setClassDesc(String desc) {
		JTextArea textArea = new JTextArea(desc);
		textArea.setBounds(150, 150, 480, 250);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Serif", Font.BOLD, 16));
		this.frame.add(textArea);
	}

	public void setMenuQuitIcon(ActionListener e) {
		ImageIcon i = new ImageIcon(MENU_QUIT);
		ImageIcon hover = new ImageIcon(MENU_QUIT_HOVER);
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
		int class_idx)
	{
		ImageIcon def = new ImageIcon(base);
		ImageIcon hov = new ImageIcon(hover);

		JButton button = new JButton(def);
		button.setRolloverIcon(hov);
		button.setBounds(x, y, def.getIconWidth(), def.getIconHeight());
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.addActionListener(e -> {
			this.clearScreen();
			setFrameBg(bg);
			setClassDesc(class_desc);
			this.player_class = class_idx;
			createClassesCaroussel();
		});

		return button;
	}

	public void createClassesCaroussel() {
		int startX = 50;
		int startY = 100;
		int gap = 20;

		String[][] classes = {
			{BARBARIAN_ICON, BARBARIAN_HOVER_ICON, BARBARIAN_BG, BARBARIAN_DESC},
			{CRUSADER_ICON, CRUSADER_HOVER_ICON, CRUSADER_BG, CRUSADER_DESC},
			{DEMONHUNTER_ICON, DEMONHUNTER_HOVER_ICON, DEMONHUNTER_BG, DEMONHUNTER_DESC},
			{MONK_ICON, MONK_HOVER_ICON, MONK_BG, MONK_DESC},
			{NECROMANCER_ICON, NECROMANCER_HOVER_ICON, NECROMANCER_BG, NECROMANCER_DESC},
			{WITCHDOCTOR_ICON, WITCHDOCTOR_HOVER_ICON, WITCHDOCTOR_BG, WITCHDOCTOR_DESC},
			{WIZARD_ICON, WIZARD_HOVER_ICON, WIZARD_BG, WIZARD_DESC}
		};

		setMenuQuitIcon(e -> setMainMenu());
		int currentY = startY;
		for (int i = 0; i < classes.length; i++) {
			String base = classes[i][0];
			String hover = classes[i][1];
			String bg = classes[i][2];
			String desc = classes[i][3];

			ImageIcon icon = new ImageIcon(base);
			JButton button = createClassesIcon(base, hover, startX, currentY, bg, desc, i);
			this.frame.add(button);

			currentY += icon.getIconHeight() + gap;
		}
	}

	public void createChacterMenu() {
		this.clearScreen();
		ImageIcon bg = new ImageIcon(MENU_BUTTON_HOVER);
		JLabel background = new JLabel(bg);
		background.setBounds(100, 100, bg.getIconWidth(), bg.getIconHeight());
		
		int centerX = (WIDHT - bg.getIconWidth()) / 2;
		JTextField textField = new JTextField();
		textField.setBounds(centerX - 30, 50, bg.getIconWidth(), bg.getIconHeight());
		textField.setOpaque(false);
		textField.setForeground(Color.WHITE);
		background.add(textField);
		this.frame.add(textField);

		this.createClassesCaroussel();
	}

	public void runGui(Game game) {
		this.setMainMenu();
		this.showScreen();
		while (true) {
			
		}
	}
}
