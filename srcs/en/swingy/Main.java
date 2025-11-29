package en.swingy;

import en.swingy.game.Game;

public class Main {
	public static void usage(String args[]) {
		if (args.length != 1) {
			System.out.println("Usage: java -jar swingy.jar [console/gui]");
			System.exit(1);;
		}
	}

	public static void main(String args[]) {
		usage(args);

		if (args[0].equals("gui")) {
			Game.startGame(true);
		} else if (args[0].equals("console")) {
			Game.startGame(false);
		} else {
			usage(args);
		}
	}
}