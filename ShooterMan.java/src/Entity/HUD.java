package Entity;

import java.awt.*;

public class HUD {
    
    private Player player;
    private Player player2;
    private Font font;

    //constructor 
    //defines the instance variables.
    public HUD(Player p, Player p2){
        player = p;
        player2 = p2;
        try{
            font = new Font("Arial",Font.PLAIN,20);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //draws the hud at the positions provided 
    public void draw(Graphics2D g){
        g.setFont(font);
        g.setColor(Color.cyan);
        g.drawString("P1 Health: " + player.getHealth(), 35, 55);
        g.setColor(new Color(168, 50, 139));
        g.drawString("P2 Health: " + player2.getHealth(), 430, 55);
    }
}
