package game;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Map implements Drawable {
	private ArrayList<Shape> boundaries = new ArrayList<Shape>();
	private Wall floorTest;
	private Wall vertWallTest;
	private Wall uniWall;
	private ArrayList<Wall> walls = new ArrayList<Wall>();

	public Map() {
	}
	
	// Boundaries
	public void enforceCollisions(Character c) {
		boolean collide = false;
		for (Shape s : boundaries) {
			if (s.intersects(c.getCollisionBox())) {
				collide = true;
				break;
			}
		}
		if (collide) {
			c.collide(Direction.UP, c.getLocation());
		}
		for (Wall w : walls) {
			w.intersect(c);
		}
	}
	
	/* 
	 * file format:
	 *    floors:
	 *    	f [height] [start] [end]
	 *    ceilings:
	 *      c [height] [start] [end]
	 *    walls
	 *    	[l|r] [x-coord] [bottom] [top]
	 *    Comments
	 *    	" [comment text]
	 *    Anything after the specified lines are comments
	 */
	public void loadWall(String filename) {
		try {
			Scanner s = new Scanner(new FileReader(filename));
			while (s.hasNextLine()) {
				Scanner lr = new Scanner(s.nextLine());
				String command = lr.next();
				int p1 = lr.nextInt(),
					p2 = lr.nextInt(),
					p3 = lr.nextInt();
				switch (command) {
				case "f":
					addHorizontalWall(p1, p2, p3, Direction.UP);
					break;
				case "c":
					addHorizontalWall(p1, p2, p3, Direction.DOWN);
					break;
				case "l":
					addVerticalWall(p1, p2, p3, Direction.LEFT);
					break;
				case "r":
					addVerticalWall(p1, p2, p3, Direction.RIGHT);
					break;
				case "\"":
					// Ignore comment lines
					break;
				default:
					throw new IOException(String.format("File %s incorrectly formated", filename));
				}
			}
		}
		catch (IOException e) {
			System.err.printf("ERROR: Unable to read file %s\n", filename);
		}
	}
	
	private void addHorizontalWall(float height, float left, float right, Direction dir) {
		Line wallLocation = new Line(left, height, right, height);
		walls.add(new Wall(wallLocation, dir));
	}
	
	private void addVerticalWall(float position, float bottom, float top, Direction dir) {
		Line wallLocation = new Line(position, bottom, position, top);
		walls.add(new Wall(wallLocation, dir));
	}
	
	@Override
	public void drawDisplaced(Graphics g, Vector2f disp) {
		g.setColor(Color.red);
		for (Shape s : boundaries) {
			g.draw(s);
		}
		for (Wall w : walls) {
			w.drawDisplaced(g, disp);
		}
	}
}