package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class Character implements Drawable, KeyListener {
	
	// Loads sprites on program load (for "speed")
	private static SpriteSheet spriteSheet;
	static {
		try {
			Image spriteImage = new Image("data/sprites.png");
			spriteSheet = new SpriteSheet(spriteImage,
					spriteImage.getWidth() / 8,
					16);
		}
		catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	// How much to resize sprite before size effects
	private float SPRITE_RATIO = 2;
	private float BOUNDING_BOX_RATIO = 1;
	private Vector2f SPRITE_DISPLACEMENT = new Vector2f(-16, -24);
	
	private Vector2f loc = new Vector2f();
	private double speed = .3;
	
	/*
	 * Left is -1, Right is 1, Stationary is 0
	 */
	private byte direction = 0;
	private boolean moving = false;
	private Vector2f velocity = new Vector2f();
	private double gravity = 0.05;
	private float size = 2f;
	private Shape charCollisionBox;
	private boolean hasSword = true;
	private ControlScheme controlScheme = new ControlScheme(false);

	public Character() {
		charCollisionBox = new Rectangle(-8, -16, 16, 32);
		loc.x = 50;
		loc.y = 400;
	}
	
	@Override
	public void draw(Graphics g) {
		Vector2f imageLocation = loc.copy().add(SPRITE_DISPLACEMENT);
		g.drawImage(getRenderImage(), (int) imageLocation.x, (int) imageLocation.y);
		g.setColor(Color.red);
		g.draw(getCollisionBox());
	}
	
	private Image getRenderImage() {
		Image img = spriteSheet.getSprite(0, 8);
		return img.getScaledCopy((float) (SPRITE_RATIO * size));
	}
	
	public Vector2f getLocation() {
		return loc.copy();
	}
	
	public void updateVelocity(int delta) {
		if (moving) {
			velocity.x = (float) (speed * delta * direction);
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
	 * 
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
			}
		}
		loc.x = nx;
		loc.y = ny;
	}
	
	public void collide(Direction direction, Vector2f pos) {
		collide(direction, (int) pos.x, (int) pos.y);
	}

	@Override
	public void setInput(Input input) {
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void inputEnded() {
	}

	@Override
	public void inputStarted() {
	}

	@Override
	public void keyPressed(int key, char c) {
		InputKey inp = controlScheme.parseInput(key);
		switch (inp) {
		case RIGHT:
			direction = 1;
			moving = true;
			break;
		case LEFT:
			direction = -1;
			moving = true;
			break;
		case UP:
			velocity.y = -10;
			break;
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		InputKey inp = controlScheme.parseInput(key);
		if ((InputKey.LEFT == inp && direction == -1) ||
				(InputKey.RIGHT == inp && direction == 1)) {
			moving = false;
		}
	}
}