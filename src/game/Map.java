package game;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Map implements Drawable {
	private ArrayList<Shape> boundaries = new ArrayList<Shape>();
	private Wall floorTest;
	private Wall vertWallTest;
	private Wall uniWall;
	private ArrayList<Wall> walls = new ArrayList<Wall>();

	public Map() {
		boundaries.add(new Rectangle(0, 500, 500, 800));
		floorTest = new Wall(new Line(0, 400, 500, 400), Direction.UP);
		vertWallTest = new Wall(new Line(200, 0, 200, 800), Direction.RIGHT);
		uniWall = new Wall(new Line(500, 400, 800, 400), Direction.UP);
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
		floorTest.intersect(c);
		vertWallTest.intersect(c);
		uniWall.intersect(c);
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
				int p1 = s.nextInt(),
					p2 = s.nextInt(),
					p3 = s.nextInt();
				switch (lr.next()) {
				case "f":
					walls.add(new Wall(new Line(), Direction.UP));
					break;
				case "c":
					break;
				case "l":
					break;
				case "r":
					break;
				case "\"":
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
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		for (Shape s : boundaries) {
			g.draw(s);
		}
		floorTest.draw(g);
		vertWallTest.draw(g);
		uniWall.draw(g);
		for (Wall w : walls) {
			w.draw(g);
		}
	}
}
