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
	private Wall floorTest;
	private Wall vertWallTest;
	private Wall uniWall;

	public Map() {
		boundaries.add(new Rectangle(0, 500, 500, 800));
		floorTest = new Wall(new Line(0, 400, 500, 400), Direction.UP);
		vertWallTest = new Wall(new Line(200, 0, 200, 800), Direction.RIGHT);
		uniWall = new Wall(new Line(500, 400, 800, 400), Direction.UP);
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
			c.collide(Direction.UP, c.getX(), c.getY());
		}
		floorTest.intersect(c);
		vertWallTest.intersect(c);
		uniWall.intersect(c);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		for (Shape s : boundaries) {
			g.draw(s);
		}
		floorTest.draw(g);
		vertWallTest.draw(g);
		uniWall.draw(g);
	}
}
