package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainGameState implements GameState {

	private Character mainCharacter = new Character();
	private Map m = new Map();

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		container.getInput().addKeyListener(mainCharacter);
		m.loadWall("testData/wallTest.txt");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		mainCharacter.drawInto(g);
		m.drawInto(g);
		drawDebugInformation(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		mainCharacter.updateVelocity(delta);
		m.enforceCollisions(mainCharacter);
		mainCharacter.update(delta);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		
	}
	
	public void drawDebugInformation(Graphics g) {
		Vector2f loc = mainCharacter.getLocation();
		g.setColor(Color.white);
		g.drawString(String.format("Character position: %f %f", loc.x, loc.y), 100, 100);
		g.setColor(Color.lightGray);
		g.draw(mainCharacter.getPath(5));
	}

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void mouseWheelMoved(int change) {
		
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		
		
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		
		
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		
		
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		
		
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		
		
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
	}

	@Override
	public void keyReleased(int key, char c) {
		
		
	}

	@Override
	public void controllerLeftPressed(int controller) {
		
		
	}

	@Override
	public void controllerLeftReleased(int controller) {
		
		
	}

	@Override
	public void controllerRightPressed(int controller) {
		
		
	}

	@Override
	public void controllerRightReleased(int controller) {
		
		
	}

	@Override
	public void controllerUpPressed(int controller) {
		
		
	}

	@Override
	public void controllerUpReleased(int controller) {
		
		
	}

	@Override
	public void controllerDownPressed(int controller) {
		
		
	}

	@Override
	public void controllerDownReleased(int controller) {
		
		
	}

	@Override
	public void controllerButtonPressed(int controller, int button) {
		
		
	}

	@Override
	public void controllerButtonReleased(int controller, int button) {
		
		
	}


}
