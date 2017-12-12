package game.sprites;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

public class Sprite {
	private Image img;
	private Vector2f center;
	private boolean horizontalFlip = false;
	
	public Sprite(Image img) {
		this.img = img;
		center = new Vector2f();
	}
	
	public void setCenter(Vector2f center) {
		this.center.set(center);
	}
	
	public void draw(Viewport vp, Vector2f loc) {
		Vector2f drawLocation = loc.copy().add(center.negate());
		if (horizontalFlip) {
			drawLocation.x -= img.getWidth() - 2 * center.x;
		}
		vp.draw(img.getFlippedCopy(horizontalFlip, false), drawLocation);
	}
	
	public void flipHorizontal() {
		horizontalFlip = !horizontalFlip;
	}
	
	public void setHorizontalDirection(boolean right) {
		horizontalFlip = !right;
	}
	
	public Vector2f getCenter() {
		return center.copy();
	}
}
