package game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

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
		Shape collideShape = null;
		for (Shape s : boundaries) {
			if (s.intersects(c.getCollisionBox())) {
				collide = true;
				collideShape = s;
				break;
			}
		}
		if (collide) {
			System.out.println("Collision detected");
			Vector2f collideShapeCenter = new Vector2f(collideShape.getCenter());
			c.collide(0, c.getX(), c.getY());
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		for (Shape s : boundaries) {
			g.draw(s);
		}
		wallTest.draw(g);
	}
}
