package game.sprites;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Sword implements Drawable {
	private static final Image SOURCE_IMAGE = StaticSpriteLoader.getMisc(0, 0);
	private static final float IMAGE_SCALE = 1;
	
	private Image img;
	private Vector2f pos = new Vector2f();
	private Character owner = null;
	
	public Sword() {
		img = SOURCE_IMAGE.getScaledCopy(IMAGE_SCALE);
		img.setFilter(Image.FILTER_NEAREST);
	}
	
	public void setOwner(Character owner) {
		this.owner = owner;
	}
	
	@Override
	public void draw(Viewport vp) {
		if (this.owner != null) {
			vp.draw(img, pos);
		}
	}
	
	public void drawWithOwner(Viewport vp, Vector2f ownerPosition, boolean isRight) {
		Image renderImage = img.getFlippedCopy(!isRight, false);
		Vector2f flipDisplacement = new Vector2f();
		if (!isRight) {
			flipDisplacement.x = -renderImage.getWidth();
		}
		vp.draw(renderImage, ownerPosition.add(flipDisplacement));
	}
}
