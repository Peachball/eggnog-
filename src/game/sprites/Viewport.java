package game.sprites;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class Viewport {
	private final Color DEFAULT_STRING_COLOR = Color.white;
	private Graphics dest = null;
	private Vector2f renderPosition = new Vector2f();
	private int windowX = 0;
	private int windowY = 0;
	
	public Viewport(Graphics g) {
		dest = g;
	}
	
	public void setPosition(Vector2f pos) {
		renderPosition.set(pos);
	}
	
	public void setCenter(Vector2f pos) {
		renderPosition.set(pos.copy().sub(new Vector2f(windowX/2, windowY/2)));
	}
	
	public void draw(Image img, Vector2f pos) {
		Vector2f drawPosition = pos.copy().sub(renderPosition);
		dest.drawImage(img, drawPosition.x, drawPosition.y);
	}
	
	public void draw(Shape s) {
		dest.setColor(Color.white);
		dest.draw(s.transform(generateTransform()));
	}
	
	public void draw(Shape s, Color c) {
		dest.setColor(c);
		dest.draw(s.transform(generateTransform()));
	}
	
	private Transform generateTransform() {
		Vector2f neg = renderPosition.negate();
		return Transform.createTranslateTransform(neg.x, neg.y);
	}
	
	public void extractViewportInformation(GameContainer gc) {
		windowX = gc.getWidth();
		windowY = gc.getHeight();
	}
	
	public void draw(Sprite s, Vector2f pos) {
		s.draw(this, pos);
	}
	
	public void drawString(String s, float x, float y, Color c) {
		dest.setColor(c);
		dest.drawString(s, x, y);
	}

	public void drawString(String s, float x, float y) {
		drawString(s, x, y, DEFAULT_STRING_COLOR);
	}
}
