package game.sprites;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import game.ControlScheme;
import game.Map;
import game.physics.CharDir;
import game.physics.Direction;
import game.physics.RigidBody;
import game.slickutils.DefaultKeyListener;

public class Character implements Drawable, DefaultKeyListener {
	// How much to resize sprite before size effects
	private final float SPRITE_RATIO = 1;
	private final Vector2f SPRITE_DISPLACEMENT = new Vector2f(-24, -24);
	private final Vector2f SPRITE_CENTER = new Vector2f(24, 24);
	
	private Sprite sprite;
	private RigidBody rb = new RigidBody();
	private Sword sword;
	private ControlScheme controlScheme = new ControlScheme(false);

	public Character() {
		sword = new Sword();
		sword.setOwner(this);

		Image img = StaticSpriteLoader.getSprite(0, 8);
		img.setFilter(Image.FILTER_NEAREST);
		sprite = new Sprite(img.getScaledCopy((float) (SPRITE_RATIO * rb.getSize())));
		sprite.setCenter(SPRITE_CENTER);
	}
	
	public void setControlScheme(ControlScheme s) {
		this.controlScheme = s;
	}
	
	@Override
	public void draw(Viewport vp) {
		Vector2f imageLocation = getLocation().add(SPRITE_DISPLACEMENT);
		sprite.setHorizontalDirection(rb.getDirection() == CharDir.RIGHT);
		vp.draw(sprite, imageLocation);
		vp.draw(rb.getCollisionBox(), Color.red);
		if (sword != null) {
			sword.drawWithOwner(vp, getSwordPosition(), rb.getDirection() == CharDir.RIGHT);
		}
	}
	
	private Vector2f getSwordPosition() {
		Vector2f pos = getLocation();
		return pos;
	}
	
	public Vector2f getLocation() {
		return rb.getLocation();
	}
	
	public void update(int delta, Map m) {
		rb.updateVelocity(delta);
		m.enforceCollisions(rb);
		rb.update(delta);
	}

	@Override
	public void keyPressed(int key, char c) {
		InputKey inp = controlScheme.parseInput(key);
		switch (inp) {
		case RIGHT:
			rb.move(Direction.RIGHT);
			break;
		case LEFT:
			rb.move(Direction.LEFT);
			break;
		case UP:
			rb.move(Direction.UP);
			break;
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		InputKey inp = controlScheme.parseInput(key);
		if ((InputKey.LEFT == inp && rb.getDirection()== CharDir.LEFT) ||
				(InputKey.RIGHT == inp && rb.getDirection() == CharDir.RIGHT)) {
			rb.stopMoving();
		}
	}
}