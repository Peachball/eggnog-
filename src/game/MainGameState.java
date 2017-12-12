package game;

import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import game.slickutils.DefaultGameState;
import game.sprites.Character;
import game.sprites.Viewport;

public class MainGameState extends DefaultGameState {

	private Character player1 = new Character(),
					  player2 = new Character();
	private List<Character> players = Arrays.asList(player1, player2);
	private Map m = new Map();

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		player1.setControlScheme(new ControlScheme(false));
		player2.setControlScheme(new ControlScheme(true));
		for (Character c : players) {
			container.getInput().addKeyListener(c);
		}
		m.loadWall("testData/wallTest.txt");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		Viewport vp = new Viewport(g);
		vp.extractViewportInformation(container);
		vp.setCenter(player1.getLocation());
		for (Character c : players) {
			c.draw(vp);
		}
		m.draw(vp);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		for (Character c: players) {
			c.update(delta, m);
		}
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		
	}
	
	@Override
	public int getID() {
		return 0;
	}
}
