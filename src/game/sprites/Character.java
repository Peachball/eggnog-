package game.sprites;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import game.ControlScheme;
import game.Direction;
import game.Map;
import game.RigidBody;
import game.Viewport;
import game.slickutils.DefaultKeyListener;

public class Character implements Drawable, DefaultKeyListener {
	
	private static SpriteSheet spriteSheet = StaticSpriteLoader.SPRITE_SHEET;
	
	// How much to resize sprite before size effects
	private float SPRITE_RATIO = 2;
	private Vector2f SPRITE_DISPLACEMENT = new Vector2f(-16, -24);
	
	private Vector2f loc = new Vector2f();
	
	private RigidBody rb = new RigidBody(loc);
	private Sword sword = null;
	private ControlScheme controlScheme = new ControlScheme(false);

	public Character() {
	}
	
	public void setControlScheme(ControlScheme s) {
		this.controlScheme = s;
	}
	
	@Override
	public void draw(Viewport vp) {
		Vector2f imageLocation = loc.copy().add(SPRITE_DISPLACEMENT);
		vp.draw(getRenderImage(), imageLocation);
		vp.draw(rb.getCollisionBox(), Color.red);
	}
	
	private Image getRenderImage() {
		Image img = spriteSheet.getSprite(0, 8);
		return img.getScaledCopy((float) (SPRITE_RATIO * rb.getSize()));
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