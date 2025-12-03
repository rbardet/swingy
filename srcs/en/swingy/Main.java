package en.swingy;

import en.swingy.game.Game;

public class Main {
	private static Game game = new Game();

	public static void usage(String args[]) {
		if (args.length != 1) {
			System.out.println("Usage: java -jar swingy.jar [console/gui]");
			System.exit(1);;
		}
	}

	public static void main(String args[]) {
		usage(args);

		if (args[0].equals("gui")) {
			game.startGame(true);
		} else if (args[0].equals("console")) {
			game.startGame(false);
		} else {
			usage(args);
		}
	}
}