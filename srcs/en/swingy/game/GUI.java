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
	private int WIDHT = 1280;
	private int HEIGHT = 720;
	private String GAME_NAME = "Swingy";

	private int startY = 200;
	private int padding = 70;

	private String ASSETS_PATH = "ressources/assets/";
	private String MENU_BUTTON =  ASSETS_PATH + "menu_button.png";
	private String MENU_BUTTON_HOVER = ASSETS_PATH + "menu_button_hover.png";
	private String MENU_FONT = ASSETS_PATH + "menu_font.png";

	public GUI() {
		this.frame = new JFrame();
		this.frame.setSize(WIDHT, HEIGHT);
		this.frame.setTitle(GAME_NAME);
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

	public void setMainMenuBG() {
		ImageIcon bg = new ImageIcon(MENU_FONT);
		JLabel bgLabel = new JLabel(bg);
		bgLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		this.frame.setContentPane(bgLabel);
	}

	public void setMainMenu() {
		this.setMainMenuBG();
		JButton b1 = createMenuButton("Create a character", startY, e->createChacterMenu());
		this.frame.add(b1);

		JButton b2 = createMenuButton("Load a character", startY + padding, e->Game.exitGame());
		this.frame.add(b2);

		JButton b3 = createMenuButton("Delete a character", startY + padding * 2, e->Game.exitGame());
		this.frame.add(b3);

		JButton b4 = createMenuButton("Exit the game", startY + padding * 3, e->Game.exitGame());
		this.frame.add(b4);
	}

	public void createChacterMenu() {
		this.clearScreen();
		ImageIcon bg = new ImageIcon(MENU_BUTTON);
		JLabel background = new JLabel(bg);
		background.setBounds(100, 100, bg.getIconWidth(), bg.getIconHeight());
		
		int centerX = (WIDHT - bg.getIconWidth()) / 2;
		JTextField textField = new JTextField();
		textField.setBounds(centerX, 0, bg.getIconWidth(), bg.getIconHeight());
		textField.setOpaque(false);
		textField.setForeground(Color.WHITE);

		background.add(textField);
		this.frame.add(textField);
	}

	public void runGui(Game game) {
		this.setMainMenu();
		this.showScreen();
		while (true) {
			
		}
	}
}
