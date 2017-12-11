package game.slickutils;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public interface DefaultKeyListener extends KeyListener {
	@Override
	default public void setInput(Input input) {
	}

	@Override
	default public boolean isAcceptingInput() {
		return true;
	}

	@Override
	default public void inputEnded() {
	}

	@Override
	default public void inputStarted() {
	}

	@Override
	default public void keyPressed(int key, char c) {
	}

	@Override
	default public void keyReleased(int key, char c) {
	}
}
