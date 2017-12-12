package game.physics;

public enum Direction {
	/* Method of specifying direcition looks really dumb, but according to
	* https://stackoverflow.com/questions/8157755/how-to-convert-enum-value-to-int
	* this is the recommended method...
	*/
	UP(0), RIGHT(1), DOWN(2), LEFT(3);

	private final int dir;

	private Direction(int value) {
		dir = value;
	}
	
	public int toInt() {
		return dir;
	}
	
	public boolean isHorizontal () {
		return dir % 2 == 1;
	}
}
