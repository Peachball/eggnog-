package game.physics;

public enum CharDir {
	LEFT(-1), RIGHT(1), STATIONARY(0);
	private int v;
	CharDir(int v) {
		this.v = v;
	}

	public int getValue() {
		return v;
	}

	public static CharDir fromDirection(Direction d) {
		switch (d) {
		case RIGHT:
			return RIGHT;
		case LEFT:
			return LEFT;
		}
		throw new IllegalArgumentException("Direction must be left or right to be convertable");
	}
}
