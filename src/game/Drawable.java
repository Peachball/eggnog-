package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public interface Drawable {
	default void drawInto(Graphics g) {
		drawDisplaced(g, new Vector2f(0, 0));
	};
	void drawDisplaced(Graphics g, Vector2f displacement);
}
