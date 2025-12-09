package en.swingy.gui;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import en.swingy.game.Game;

public class GUI {
	private JFrame frame;
	public static final int WIDHT = 1280;
	public static final int HEIGHT = 720;
	private final String APP_NAME = "Swingy";
	public final String FONT = "ressources/diablo.ttf";
	private Font CUSTOM_FONT;
	private String username;

	public static final String MONK_DESC_HEADER = "Monk";
	public static final String MONK_DESC_BODY = "A swift, spiritual melee fighter using martial arts, holy powers and high mobility to strike fast and dodge attacks — a balance of offense and agility.";
	public static final String MONK_DESC_STAT = "Attack - 7; Defense - 6; HP - 7";
	
	public static final String BARBARIAN_DESC_HEADER = "Barbarian";
	public static final String BARBARIAN_DESC_BODY = "A savage melee powerhouse who uses raw strength and fury to smash through hordes of enemies with brute force and devastating close-range attacks.";
	public static final String BARBARIAN_DESC_STAT = "Attack - 5; Defense - 5; HP - 10";
	
	public static final String NECROMANCER_DESC_HEADER = "Necromancer";
	public static final String NECROMANCER_DESC_BODY = "A dark spellcaster who manipulates life and death itself: raising undead minions, casting curses and controlling corpses to overwhelm and debilitate enemies.";
	public static final String NECROMANCER_DESC_STAT = "Attack - 8; Defense - 4; HP - 8";
	
	public static final String CRUSADER_DESC_HEADER = "Crusader";
	public static final String CRUSADER_DESC_BODY = "A heavy-armored holy warrior wielding shields and flails, combining melee might with divine powers and protective auras to crush foes and defend allies.";
	public static final String CRUSADER_DESC_STAT = "Attack - 5; Defense - 8; HP - 7";
	
	public static final String WITCHDOCTOR_DESC_HEADER = "Witch Doctor";
	public static final String WITCHDOCTOR_DESC_BODY = "A mystical caster who commands curses, poisons, and summoned creatures to overwhelm foes from a distance, using dark magic and supernatural minions.";
	public static final String WITCHDOCTOR_DESC_STAT = "Attack - 10; Defense - 3; HP - 7";
	
	public static final String DEMONHUNTER_DESC_HEADER = "Demon Hunter";
	public static final String DEMONHUNTER_DESC_BODY = "A deadly ranged class specializing in crossbows, traps, and dual-resource management; nimble and tactical, striking from afar while avoiding danger.";
	public static final String DEMONHUNTER_DESC_STAT = "Attack - 9; Defense - 4; HP - 7";
	
	public static final String WIZARD_DESC_HEADER = "Wizard";
	public static final String WIZARD_DESC_BODY = "A master of arcane and elemental magic, capable of dealing massive damage from afar — manipulating fire, ice, lightning (and sometimes even time) to obliterate enemies.";
	public static final String WIZARD_DESC_STAT = "Attack - 8; Defense - 3; HP - 9";

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
		this.frame.setFocusable(true);
		KeyListener k = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 27) {
					Game.exitGame();
				}
			}
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
		};
		this.frame.addKeyListener(k);
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

	public void setMenuQuitIcon(GUI gui, Game g) throws SQLException {
		ImageIcon i = new ImageIcon(Assets.MENU_QUIT);
		ImageIcon hover = new ImageIcon(Assets.MENU_QUIT_HOVER);
		JButton button = new JButton(i);
		button.setRolloverIcon(hover);
		button.setBounds(20, 20, 50, 50);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.addActionListener(e -> {
			try {
				MainMenu.setMainMenu(gui, g);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
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

	public String getUsername() {
		return this.username;
	}

	public int getPlayerClass() {
		return this.player_class;
	}

	public void setUsername(String s) {
		this.username = s;
	}

	public void runGui(Game game) throws SQLException {
		MainMenu.setMainMenu(this , game);
		this.showScreen();
		while (true);
	}
}
