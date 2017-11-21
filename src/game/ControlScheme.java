package game;

import org.newdawn.slick.Input;

public class ControlScheme {
	private boolean wasd = false;
	
	public ControlScheme() {
	}

	public ControlScheme(boolean wasd) {
		this.wasd = wasd;
	}
	
	public InputKey parseInput(int key) {
		if (wasd) {
			switch(key) {
			case Input.KEY_W:
				return InputKey.UP;
			case Input.KEY_S:
				return InputKey.DOWN;
			case Input.KEY_D:
				return InputKey.RIGHT;
			case Input.KEY_A:
				return InputKey.LEFT;
			}
		}
		else {
			switch(key) {
			case Input.KEY_UP:
				return InputKey.UP;
			case Input.KEY_DOWN:
				return InputKey.DOWN;
			case Input.KEY_RIGHT:
				return InputKey.RIGHT;
			case Input.KEY_LEFT:
				return InputKey.LEFT;
			}
		}
		return InputKey.UNKNOWN;
	}

}
