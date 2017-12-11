package game.sprites;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class StaticSpriteLoader {
	
	private static final String SPRITE_FILENAME="data/sprites.png";
	private static final String MISC_FILENAME="data/misc.png";
	
	public static final SpriteSheet SPRITE_SHEET = loadSheet(SPRITE_FILENAME),
									MISC_SHEET = loadSheet(MISC_FILENAME);

	private static SpriteSheet loadSheet(String filename) {
		return loadSheet(filename, 16, 16);
	}
	
	
	private static SpriteSheet loadSheet(String filename, int w, int h) {
		try {
			Image img = new Image(filename);
			SpriteSheet s = new SpriteSheet(img, w, h);
			return s;
		}
		catch (SlickException e) {
			assert false: "Unable to load file: " + filename;
		}
		return null;
	}

}
