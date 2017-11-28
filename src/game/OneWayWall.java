package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class OneWayWall implements Drawable {
	
	private boolean isLeft = false;
	private Line pos;
	
	/*
	 * @param pos Line representing the position of the wall
	 * @param direction Whether or not the wall faces left
	 */
	public OneWayWall(Line pos, boolean direction) {
		assert (pos.getDX() == 0) : "Line not vertical! Collision detection will not work";
		this.pos = pos;
		this.isLeft = direction;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.pink);
		g.draw(pos);
	}
	
	public void collide(Character c) {
		Shape hitbox = c.getCollisionBox();
		if (hitbox.intersects(pos) && ((c.getVelocity().x > 0) ^ (!isLeft))) {
			Vector2f disp = new Vector2f();
			if (isLeft) {
				disp.x = hitbox.getMaxX() - pos.getMaxX();
			}
			else {
				disp.x = pos.getMinX() - hitbox.getMinX();
			}
			c.collide(isLeft ? Direction.LEFT : Direction.RIGHT, c.getLocation().add(disp));
		}
	}
}
