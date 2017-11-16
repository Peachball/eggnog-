package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

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

	public Character() {
		
	}
	
	@Override
	public void draw(Graphics g) {
		Image curImage = spriteSheet.getSprite(0, 8);
		g.drawImage(curImage.getScaledCopy((float) 2), (int) xloc, (int) yloc);
	}
	
	public void update(int delta) {
		if (moving) {
			xloc += speed * delta * direction;
		}
		yloc += gravity * delta * yvel;
		yvel += gravity * delta;
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