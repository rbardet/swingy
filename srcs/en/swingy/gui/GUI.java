package en.swingy.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import en.swingy.game.Game;

public class GUI {
	private JFrame frame;
	public static final int WIDHT = 1280;
	public static final int HEIGHT = 720;
	private final String APP_NAME = "Swingy";
	public final String FONT = "ressources/diablo.ttf";
	private Font CUSTOM_FONT;

	public static final String MONK_DESC = """
	Monk -\n
	A swift, spiritual melee fighter using martial arts,
	holy powers and high mobility to strike fast and dodge attacks —
	a balance of offense and agility.\n
	Attack - 7\n
	Defense - 6\n
	HP - 7\n
	""";

	public static final String BARBARIAN_DESC = """
	Barbarian -\n
	A savage melee powerhouse who uses raw strength and fury
	to smash through hordes of enemies with brute force
	and devastating close-range attacks.\n
	Attack - 5\n
	Defense - 5\n
	HP - 10\n
	""";

	public static final String NECROMANCER_DESC = """
	Necromancer -\n
	A dark spellcaster who manipulates life and death itself:
	raising undead minions, casting curses and controlling corpses
	to overwhelm and debilitate enemies.\n
	Attack - 8\n
	Defense - 4\n
	HP - 8\n
	""";

	public static final String CRUSADER_DESC = """
	Crusader -\n
	A heavy-armored holy warrior wielding shields and flails,
	combining melee might with divine powers and protective auras
	to crush foes and defend allies.\n
	Attack - 5\n
	Defense - 8\n
	HP - 7\n
	""";


	public static final String WITCHDOCTOR_DESC = """
	Witch Doctor -\n
	A mystical caster who commands curses, poisons, and summoned creatures
	to overwhelm foes from a distance,
	using dark magic and supernatural minions.\n
	Attack - 10\n
	Defense - 3\n
	HP - 7\n
	""";


	public static final String DEMONHUNTER_DESC = """
	Demon Hunter -\n
	A deadly ranged class specializing in crossbows, traps,
	and dual-resource management; nimble and tactical,
	striking from afar while avoiding danger.\n
	Attack - 9\n
	Defense - 4\n
	HP - 7\n
	""";


	public static final String WIZARD_DESC = """
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

	public void showLoadSaveButton(Game g) throws SQLException {

	}

	public void showLoadSaveMenu(Game g) throws SQLException {
		clearScreen();
		SavesDisplay.showAvailableSaves(this, g);
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

	public void setPlayerClass(int choice) {
		this.player_class = choice;
	}

	public Font getCustomFont() {
		return this.CUSTOM_FONT;
	}

	public Frame getFrame() {
		return this.frame;
	}

	public void runGui(Game game) throws SQLException {
		MainMenu.setMainMenu(this , game);
		this.showScreen();
		while (true) {
			
		}
	}
}
