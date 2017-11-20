package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
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
	
	private Vector2f loc = new Vector2f();
	private double speed = .3;
	
	/*
	 * Left is -1, Right is 1, Stationary is 0
	 */
	private byte direction = 0;
	private boolean moving = false;
	private Vector2f velocity = new Vector2f();
	private double gravity = 0.05;
	private double size = 1;
	private Shape charCollisionBox;

	public Character() {
		charCollisionBox = new Rectangle(0, 16, 16, 16);
		loc.x = 50;
		loc.y = 400;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(getRenderImage(), (int) loc.x, (int) loc.y);
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
	
	public Shape getCollisionBox() {
		Transform t = Transform.createScaleTransform(BOUNDING_BOX_RATIO, BOUNDING_BOX_RATIO);
		t.concatenate(Transform.createTranslateTransform((int) loc.x, (int) loc.y));
		return charCollisionBox.transform(t);
	}
	
	public int getX() {
		return (int) loc.x;
	}

	public int getY() {
		return (int) loc.y;
	}
	
	public void collide(int direction, int nx, int ny) {
		if (direction % 2 == 0) {
			velocity.y = Math.min(0, velocity.y);
		}
		else {
			loc.x = nx;
		}
		loc.y = ny;
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
		switch (key) {
		case Input.KEY_RIGHT:
			direction = 1;
			moving = true;
			break;
		case Input.KEY_LEFT:
			direction = -1;
			moving = true;
			break;
		case Input.KEY_UP:
			velocity.y = -10;
			break;
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		if ((Input.KEY_LEFT == key && direction == -1) ||
				(Input.KEY_RIGHT == key && direction == 1)) {
			moving = false;
		}
	}
}