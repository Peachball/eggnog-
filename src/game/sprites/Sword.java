package game.sprites;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class Sword implements Drawable {
	private static final Image SOURCE_IMAGE = StaticSpriteLoader.getMisc(0, 0);
	private static final float IMAGE_SCALE = 1;
	private static final Vector2f SPRITE_CENTER = new Vector2f(0, 24);
	
	private Sprite sprite;
	private Vector2f pos = new Vector2f();
	private Character owner = null;
	private Shape hitbox = new Rectangle(5, 5, 64, 15);
	private boolean isRight = true;
	private float scale = 1f;
	
	public Sword() {
		Image img = SOURCE_IMAGE.getScaledCopy(IMAGE_SCALE);
		img.setFilter(Image.FILTER_NEAREST);
		sprite = new Sprite(img);
		sprite.setCenter(SPRITE_CENTER);
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
	
	public void setDirection(boolean isRight) {
		this.isRight = isRight;
	}
	
	public void drawWithOwner(Viewport vp, Vector2f ownerPosition) {
		sprite.setHorizontalDirection(isRight);
		vp.draw(sprite, ownerPosition);
		
		vp.draw(getHitbox());
	}
	
	private Shape getHitbox() {
		if (owner != null) {
			Vector2f ownerPosition = owner.getLocation();
			Transform boxDisplacement =
					Transform.createTranslateTransform(
							ownerPosition.x - (isRight ? 0 : (hitbox.getWidth() + 2 * hitbox.getMinX())),
							ownerPosition.y);
			boxDisplacement.concatenate(Transform.createScaleTransform(scale, scale));
			return hitbox.transform(boxDisplacement);
		}
		else {
			return null;
		}
	}
}