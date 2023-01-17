package GameState;

import java.util.ArrayList;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int HELPSTATE = 2; 
	public static final int VICTORY = 3;
	
	public GameStateManager() {
		//adding the states to an array list 
		gameStates = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new Level1State(this));
		gameStates.add(new HelpState(this));
		gameStates.add(new VictoryState(this));
		
	}
	
	//methods that are very useful 
	//sets the state to whatever you want 
	public void setState(int state) { 
		currentState = state; 
		gameStates.get(currentState).init();
	}
	
	//updates the current state 
	public void update() {
		gameStates.get(currentState).update();
	}
	
	//draws the current state 
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	//measures the keystrokes of the current state 
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
	
}









