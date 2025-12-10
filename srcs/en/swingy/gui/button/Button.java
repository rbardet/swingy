package en.swingy.gui.button;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import en.swingy.gui.GUI;

public class Button {
	public static JButton createMenuButton(
		GUI gui,
		String content,
		int x,
		int y,
		boolean center,
		String basic_asset,
		String hover_asset,
		ActionListener event
	) {
		ImageIcon std = new ImageIcon(basic_asset);
		ImageIcon hover = new ImageIcon(hover_asset);

		JButton button = new JButton(content, std);
		button.setRolloverIcon(hover);
		if (center) {
			x = (GUI.WIDHT - std.getIconWidth()) / 2;
		}
		button.setBounds(x, y, std.getIconWidth(), std.getIconHeight());
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setVerticalTextPosition(SwingConstants.CENTER);
		button.setForeground(Color.WHITE);
		button.setFont(gui.getCustomFont());
		button.addActionListener(event);
		return button;
	}
}
