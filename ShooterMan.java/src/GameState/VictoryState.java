package GameState;

import Main.GamePanel;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.KeyEvent;

public class VictoryState extends GameState{

    private Color textColor;
    private Font font;

    public VictoryState(GameStateManager gsm){
        this.gsm = gsm;
        try{
            //defining the variables
            textColor = new Color(128, 0, 200);
            font = new Font("Century Gothic", Font.PLAIN, 28);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void init() {

    }

    public void update() {

    }

   
    public void draw(Graphics2D g) {
        //Erase
        g.setColor(Color.black);
		g.fillRect(0, 0, GamePanel.width, GamePanel.height);

        g.setColor(textColor);
        g.setFont(font);

        //Victory sign 
        g.drawString("VICTORY.", 250, 100); //STILL HAVENT FIGURED OUT HOW TO PRINT WHICH PLAYER DIED

        //Options
        g.setColor(Color.cyan);
        g.drawString("Play Again: ENTER", 50, 300);
        g.setColor(new Color(168, 50, 139));
        g.drawString("Exit to Menu: ESC", 350, 300);
        
    }

    public void keyPressed(int k) {
        //play again
        if(k == KeyEvent.VK_ENTER){
            gsm.setState(GameStateManager.LEVEL1STATE);
        }
        //return to menu 
        if(k == KeyEvent.VK_ESCAPE){
            gsm.setState(GameStateManager.MENUSTATE);
        }
        
    }

    
    public void keyReleased(int k) {
        
        
    }
    
}
