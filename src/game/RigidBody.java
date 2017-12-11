package game;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import game.sprites.CharDir;

public class RigidBody {

	private final float BOUNDING_BOX_RATIO = 1;
	private final float JUMP_FORCE = 15;
	
	private float size = 2f;
	private Shape charCollisionBox;
	private double speed = .3;
	private Vector2f loc;
	private CharDir direction = CharDir.STATIONARY;
	private boolean horizontalMovement = false;
	private Vector2f velocity = new Vector2f();
	private double gravity = 0.05;
	
	public RigidBody(Vector2f loc) {
		charCollisionBox = new Rectangle(-8, -16, 16, 32);
		this.loc = loc;
	}

	public Vector2f getLocation() {
		return loc.copy();
	}
	
	public void updateVelocity(int delta) {
		if (horizontalMovement) {
			velocity.x = (float) (speed * delta * direction.getValue());
		}
		else {
			velocity.x = 0;
		}
		velocity.y += gravity * delta;
	}
	
	public void update(int delta) {
		loc.add(velocity);
	}

	public Vector2f getVelocity () {
		return velocity.copy();
	}
	
	public Line getPath(float s) {
		return new Line(getLocation(), getLocation().add(getVelocity().scale(s)));
	}
	
	public Line getPath() {
		return getPath(1);
	}
	
	public Shape getCollisionBox() {
		Transform t = new Transform();
		t.concatenate(Transform.createTranslateTransform(loc.x, loc.y));
		t.concatenate(Transform.createScaleTransform(BOUNDING_BOX_RATIO * size, BOUNDING_BOX_RATIO * size));
		return charCollisionBox.transform(t);
	}
	
	public float getX() {
		return loc.x;
	}

	public float getY() {
		return loc.y;
	}
	
	/*
	 * Implement a collision
	 */
	public void collide(Direction direction, float nx, float ny) {
		if (!direction.isHorizontal()) {
			velocity.y = Math.min(0, velocity.y);
		}
		else {
			switch (direction) {
			case LEFT:
				velocity.x = Math.min(0, velocity.x);
				break;
			case RIGHT:
				velocity.x = Math.max(0, velocity.x);
				break;
			}
		}
		loc.x = nx;
		loc.y = ny;
	}
	
	public void collide(Direction direction, Vector2f pos) {
		collide(direction, (int) pos.x, (int) pos.y);
	}
	
	public void collide(Direction direction, float disp) {
		Vector2f res = loc.copy();
		if (direction.isHorizontal()) {
			res.add(new Vector2f(disp, 0));
		}
		else {
			res.add(new Vector2f(0, disp));
		}
		collide(direction, disp);
	}
	
	public void stopMoving() {
		horizontalMovement = false;
	}
	
	public void move(Direction d) {
		switch (d) {
		case RIGHT:
		case LEFT:
			direction = CharDir.fromDirection(d);
			horizontalMovement = true;
			break;
		case UP:
			this.velocity.y = -JUMP_FORCE;
		}
	}
	
	public float getSize() {
		return size;
	}
	
	public CharDir getDirection() {
		return direction;
	}
}