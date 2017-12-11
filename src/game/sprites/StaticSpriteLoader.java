package game.sprites;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class StaticSpriteLoader {
	public static final float GLOBAL_SCALE = 4;
	private static final String SPRITE_FILENAME="data/sprites.png";
	private static final String MISC_FILENAME="data/misc.png";
	
	private static final SpriteSheet SPRITE_SHEET = loadSheet(SPRITE_FILENAME),
									MISC_SHEET = loadSheet(MISC_FILENAME);
	
	public static Image getSprite(int x, int y) {
		return SPRITE_SHEET.getSprite(x, y).getScaledCopy(GLOBAL_SCALE);
	}

	public static Image getMisc(int x, int y) {
		return MISC_SHEET.getSprite(x, y).getScaledCopy(GLOBAL_SCALE);
	}
	
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
