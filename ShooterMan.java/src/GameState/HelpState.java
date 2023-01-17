package GameState;

import Main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HelpState extends GameState{
    
    private Color textColor; 
    private Font textFont; 

    public HelpState(GameStateManager gsm) {
		
		this.gsm = gsm;

        try{
            //defining variables
            textColor = new Color(128,0,200);
            textFont = new Font("Century Gothic", Font.PLAIN,28);
        }
        catch(Exception e) {
			e.printStackTrace();
		}

    }

	public void init() {}
    public void update() {}
    public void draw(Graphics2D g) {
        //erase previous state 
        g.setColor(Color.black);
		g.fillRect(0, 0, GamePanel.width, GamePanel.height);
		
        //draw the controls 
        g.setColor(textColor);
        g.setFont(textFont);
        g.drawString("How to Play: ", 50, 70);
        g.setColor(Color.cyan);
        g.drawString("Player 1: ", 80, 110);
        g.drawString("W - Jump", 80, 140);
        g.drawString("A - Move Left ", 80, 170);
        g.drawString("D - Move Right", 80, 200);
        g.drawString("Space - Shoot",80, 230); 

        g.setColor(new Color(168, 50, 139));
        g.drawString("Player 2: ", 320, 110);
        g.drawString("↑ - Jump", 320, 140);
        g.drawString("← - Move Left ", 320, 170);
        g.drawString("→ - Move Right", 320, 200);
        g.drawString("Shift - Shoot", 320, 230); 

        //draw the game 
        g.setColor(textColor);
        g.drawString("The Objective of the game is to kill the ", 50, 280);
        g.drawString("other player.", 50, 315);
        g.drawString("Do this with the missiles provided.",50, 370);
        g.drawString("Good Luck.", 50, 430);

        //draw the return to menu prompt
        g.drawString("← ESC", 525,70 );
        
    }
    
    public void keyPressed(int k ){
        if(k == KeyEvent.VK_ESCAPE){
            gsm.setState(GameStateManager.MENUSTATE); //returns back to menustate if esc is pressed
        }
    }
    public void keyReleased(int k){}



}








