package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class Wall implements Drawable {
	private Direction direction;
	private Line loc;
	
	public Wall (Line loc, Direction direction) {
		if (loc.getDY() != 0 && loc.getDX() != 0) {
			throw new IllegalArgumentException("Wall position can only be horizontal or vertical");
		}
		if ((direction.isHorizontal()) ^ (loc.getDX() == 0)) {
			throw new IllegalArgumentException("Line position and direction of wall don't match");
		}
		this.loc = loc;
		this.direction = direction;
	}

	public Vector2f intersect(Character c) {
		Vector2f path = c.getVelocity();
		Polygon r = (Polygon) c.getCollisionBox();
		Vector2f overallMovement = new Vector2f();
		boolean collided = false;
		for (int i = 0; i < r.getPointCount(); i++) {
			float[] p = r.getPoint(i);
			Vector2f vp = new Vector2f(p);
			switch (direction) {
			case UP:
				if (vp.y == r.getMinY()) {
					continue;
				}
				break;
			case RIGHT:
				if (vp.x == r.getMaxX()) {
					continue;
				}
				break;
			case LEFT:
				if (vp.x == r.getMinX()) {
					continue;
				}
				break;
			case DOWN:
				if (vp.y == r.getMaxY()) {
					continue;
				}
				break;
			}

			Line lpath = new Line(vp, vp.copy().add(path));
			if (lpath.intersects(this.loc)) {
				collided = true;
				Vector2f intersectPoint = lpath.intersect(this.loc);
				// New distance
				Vector2f newMovement = intersectPoint.copy().sub(vp);
				if (newMovement.lengthSquared() > overallMovement.lengthSquared()) {
					overallMovement.set(newMovement);
				}
			}
		}
		if (collided) {
			c.collide(this.direction, c.getLocation().add(overallMovement));
		}
		return overallMovement;
	}


	@Override
	public void draw(Viewport vp) {
		vp.draw(loc, Color.magenta);
	}
}
