package en.swingy;

import java.sql.SQLException;

import en.swingy.game.Game;

public class Main {
	public static void usage(String args[]) {
		if (args.length != 1) {
			System.out.println("Usage: java -jar swingy.jar [console/gui]");
			System.exit(1);;
		}
	}

	public static void main(String args[]) throws SQLException {
		Game game = new Game();
		if (args[0].equals("gui")) {
			game.setGui(true);
		} else if (args[0].equals("console")) {
			game.setGui(false);
		} else {
			usage(args);
		}

		game.startGame();
	}
}