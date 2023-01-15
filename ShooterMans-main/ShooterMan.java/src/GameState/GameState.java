package GameState;

public abstract class GameState {
	
	protected GameStateManager gsm;
	
	public abstract void init(); //initializes any other classes that appear in the state, like players backgrounds, etc. 
	public abstract void update(); //updates any classes that need updating, mainly for player and missile animations 
	public abstract void draw(java.awt.Graphics2D g); //draws the things that need to be drawn (backgrounds, players etc.)
	public abstract void keyPressed(int k); // reads the key presses and does what they are supposed to do 
	public abstract void keyReleased(int k); // reads when the key is released and stops what happens when the key is pressed. 
	
}
