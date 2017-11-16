import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import game.Game;

public class Main {
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game("eggnog-"));
		app.setDisplayMode(800, 600, false);
		app.setTargetFrameRate(60);
		app.start();
	}
}
