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

public class Character implements Drawable, KeyListener {
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
	
	private double xloc, yloc;
	private double speed = .1;
	
	/*
	 * Left is -1, Right is 1, Stationary is 0
	 */
	private byte direction = 0;
	private boolean moving = false;
	private double yvel = 0;
	private double gravity = 0.01;
	private double size = 5;
	private Shape charCollisionBox;

	public Character() {
		charCollisionBox = new Rectangle(0, 16, 16, 16);
		xloc = 50;
		yloc = 400;
	}
	
	@Override
	public void draw(Graphics g) {
		Image curImage = spriteSheet.getSprite(0, 8);
		g.drawImage(curImage.getScaledCopy((float) 2), (int) xloc, (int) yloc);
		g.draw(getCollisionBox());
	}
	
	public void update(int delta) {
		if (moving) {
			xloc += speed * delta * direction;
		}
		yloc += gravity * delta * yvel;
		yvel += gravity * delta;
	}
	
	public Shape getCollisionBox() {
		Transform t = Transform.createTranslateTransform((int) xloc, (int) yloc);
		return charCollisionBox.transform(t);
	}
	
	public int getX() {
		return (int) xloc;
	}

	public int getY() {
		return (int) yloc;
	}
	
	public void collide(int direction, int nx, int ny) {
		if (direction % 2 == 0) {
			yvel = 0;
		}
		xloc = nx;
		yloc = ny;
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
			break;
		case Input.KEY_LEFT:
			direction = -1;
			break;
		}
		moving = true;
	}

	@Override
	public void keyReleased(int key, char c) {
		moving = false;
	}
}