package en.swingy.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

	private int player_class = -1;

	public GUI() {
		this.frame = new JFrame();
		this.frame.setSize(WIDHT, HEIGHT);
		this.frame.setTitle(APP_NAME);
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

	public JButton createClassesIcon(
		String base,
		String hover,
		int x,
		int y,
		String bg,
		String class_data,
		int class_idx)
	{
		ImageIcon def = new ImageIcon(base);
		ImageIcon hov = new ImageIcon(hover);

		JButton button = new JButton(hov);
		button.setRolloverIcon(hov);
		button.setBounds(x, y, def.getIconWidth(), def.getIconHeight());
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.addActionListener(e -> {
			setFrameBg(bg);
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
			{BARBARIAN_ICON, BARBARIAN_HOVER_ICON, BARBARIAN_BG},
			{CRUSADER_ICON, CRUSADER_HOVER_ICON, CRUSADER_BG},
			{DEMONHUNTER_ICON, DEMONHUNTER_HOVER_ICON, DEMONHUNTER_BG},
			{MONK_ICON, MONK_HOVER_ICON, MONK_BG},
			{NECROMANCER_ICON, NECROMANCER_HOVER_ICON, NECROMANCER_BG},
			{WITCHDOCTOR_ICON, WITCHDOCTOR_HOVER_ICON, WITCHDOCTOR_BG},
			{WIZARD_ICON, WIZARD_HOVER_ICON, WIZARD_BG}
		};

		int currentY = startY;
		for (int i = 0; i < classes.length; i++) {
			String base = classes[i][0];
			String hover = classes[i][1];
			String bg = classes[i][2];

			ImageIcon icon = new ImageIcon(base);
			JButton button = createClassesIcon(base, hover, startX, currentY, bg, "TEST", i);
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
