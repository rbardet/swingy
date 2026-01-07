package en.swingy;

import en.swingy.game.Game;

public class Main {
	public static void usage(String args[]) {
		System.out.println("Usage: java -jar swingy.jar [console/gui]");
		System.exit(1);;
	}

	public static void main(String args[]) throws Exception {
		Game game = Game.getInstance();
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