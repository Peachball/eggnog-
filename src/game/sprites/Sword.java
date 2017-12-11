package game.sprites;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import game.Viewport;

public class Sword implements Drawable {
	
	private static final Image SOURCE_IMAGE = StaticSpriteLoader.MISC_SHEET.getSprite(0, 0);
	
	private Image img;
	private Vector2f pos;
	private Character owner = null;
	private Rectangle hitbox;
	
	public Sword() {
		img = SOURCE_IMAGE.copy();
	}
	
	public void setOwner(Character owner) {
		this.owner = owner;
	}
	
	@Override
	public void draw(Viewport vp) {
		if (this.owner != null) {
			vp.draw(SOURCE_IMAGE, pos);
		}
	}

}
