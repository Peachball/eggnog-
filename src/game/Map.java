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
	private OneWayFloor floorTest;
	private OneWayWall wallTest;

	public Map() {
		boundaries.add(new Rectangle(0, 500, 500, 800));
		floorTest = new OneWayFloor(new Line(0, 400, 500, 400));
		wallTest = new OneWayWall(new Line(200, 0, 200, 800), true);
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
			Vector2f collideShapeCenter = new Vector2f(collideShape.getCenter());
			c.collide(0, c.getX(), c.getY());
		}
		floorTest.intersect(c);
		wallTest.collide(c);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		for (Shape s : boundaries) {
			g.draw(s);
		}
		floorTest.draw(g);
		wallTest.draw(g);
	}
}
