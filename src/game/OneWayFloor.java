package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class OneWayFloor implements Drawable {
	
	private Line l;
	private boolean isFloor;

	public OneWayFloor(Line l, boolean floor) {
		this.l = l;
		this.isFloor = floor;
	}
	
	public OneWayFloor(Line l) {
		this(l, true);
	}
	
	public Vector2f intersect(Character c) {
		Vector2f path = c.getVelocity();
		Polygon r = (Polygon) c.getCollisionBox();
		Vector2f overallMovement = new Vector2f();
		boolean collided = false;
		for (int i = 0; i < r.getPointCount(); i++) {
			float[] p = r.getPoint(i);
			Vector2f vp = new Vector2f(p);
			if (vp.y == r.getMinY() && isFloor) {
				// Only collide bottom points (if is floor)
				continue;
			}
			if (vp.y == r.getMaxY() && !isFloor) {
				// Only collide top points (if is ceiling)
				continue;
			}

			Line lpath = new Line(vp, vp.copy().add(path));
			if (lpath.intersects(this.l)) {
				collided = true;
				Vector2f intersectPoint = lpath.intersect(this.l);
				// New distance
				Vector2f newMovement = intersectPoint.copy().sub(vp);
				if (newMovement.lengthSquared() > overallMovement.lengthSquared()) {
					overallMovement.set(newMovement);
				}
			}
		}
		if (collided) {
			c.collide(Direction.UP, c.getLocation().add(overallMovement));
		}
		return overallMovement;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.draw(l);
	}
}
