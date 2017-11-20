package game;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Map implements Drawable {
	private ArrayList<Shape> boundaries = new ArrayList<Shape>();
	private OneWayWall wallTest;

	public Map() {
		boundaries.add(new Rectangle(0, 500, 500, 800));
		wallTest = new OneWayWall(new Line(0, 400, 500, 400), true);
	}
	
	// Boundaries
	public void enforceCollisions(Character c) {
		boolean collide = false;
		for (Shape s : boundaries) {
			if (s.intersects(c.getCollisionBox())) {
				collide = true;
				break;
			}
		}
		if (collide) {
			System.out.println("Collision detected");
			c.collide(0, c.getX(), c.getY());
		}
	}
	
	public void draw(Graphics g) {
		for (Shape s : boundaries) {
			g.draw(s);
		}
	}
}
