package GameState;

import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

import Main.GamePanel;

public class MenuState extends GameState {
	
	private Background bg;
	
	private int currentChoice = 0;
	private String[] options = {"Start", "How To Play", "Quit"}; 
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		try {
			//creates new background and file path
			bg = new Background("/Background/Background.gif");

			//font settings 
			titleColor = new Color(168, 50, 139);
			titleFont = new Font("Century Gothic", Font.PLAIN, 50);
			font = new Font("Arial", Font.PLAIN, 28);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void init() {}
	
	public void update() {
	
	}
	
	public void draw(Graphics2D g) {
		//clear background 
		g.setColor(Color.black);
		g.fillRect(0, 0, GamePanel.width, GamePanel.height);

		// draw bg
		bg.draw(g);
		
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Shooter Mans", 40, 100);
		
		// draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.CYAN);
			}
			else {
				g.setColor(new Color(128,0,200));
			}
			g.drawString(options[i], 80, 140 + i * 35);
		}
		
	}
	
	//controls what happens if an option is selected
	private void select() {
		if(currentChoice == 0) {
			//start option
			gsm.setState(GameStateManager.LEVEL1STATE); //sets current state to the level1state
		}
		if(currentChoice == 1) {
			// help
			gsm.setState(GameStateManager.HELPSTATE); // sets current state to the How To Play state
		}
		if(currentChoice == 2) {
			//exit
			System.exit(0); // closes out of the game 
		}
	}
	
	//menu controls
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select(); // enter to select the option (uses method above)
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;	//up arrow to select the uppper option
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++; //donw arrow to select the lower option
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {}
	
}










