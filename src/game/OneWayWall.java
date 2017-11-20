package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;

public class OneWayWall implements Drawable {
	
	private Line l;
	private boolean wallDirection;

	public OneWayWall(Line l, boolean out) {
		this.l = l;
		this.wallDirection = out;
	}
	
	public boolean collide(Character c) {
		Line velLine = new Line(c.getLocation(), c.getLocation().add(c.getVelocity()));
		if (l.intersects(velLine)) {
		}
		return false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.draw(l);
	}
}
