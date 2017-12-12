package game.sprites;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class Sword implements Drawable {
	private static final Image SOURCE_IMAGE = StaticSpriteLoader.getMisc(0, 0);
	private static final float IMAGE_SCALE = 1;
	private static final Vector2f SPRITE_CENTER = new Vector2f(0, 0);
	
	private Sprite sprite;
	private Vector2f pos = new Vector2f();
	private Character owner = null;
	
	private Shape hitbox;
	
	public Sword() {
		Image img = SOURCE_IMAGE.getScaledCopy(IMAGE_SCALE);
		img.setFilter(Image.FILTER_NEAREST);
		sprite = new Sprite(img);
		sprite.setCenter(SPRITE_CENTER);
		hitbox = new Rectangle(0, 0, 10, 10);
	}
	
	public void setOwner(Character owner) {
		this.owner = owner;
	}
	
	@Override
	public void draw(Viewport vp) {
		if (this.owner != null) {
			vp.draw(sprite, pos);
		}
		vp.draw(hitbox);
	}
	
	public void drawWithOwner(Viewport vp, Vector2f ownerPosition, boolean isRight) {
		sprite.setHorizontalDirection(isRight);
		vp.draw(sprite, ownerPosition);
		
		Transform boxDisplacement =
				Transform.createTranslateTransform(
						ownerPosition.x - (isRight ? 0 : hitbox.getWidth()),
						ownerPosition.y);
		vp.draw(hitbox.transform(boxDisplacement));
	}
}