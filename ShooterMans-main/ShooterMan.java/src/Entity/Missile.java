package Entity;

import java.awt.*;
import javax.imageio.ImageIO;
import TileMap.TileMap;
import java.awt.image.BufferedImage;

public class Missile extends Object{
    
    private boolean hit;
    private boolean remove; 
    private BufferedImage[] sprites; 
    private BufferedImage[] hitSprites;

    //constructor / defining instance variables 
    public Missile(TileMap tm, boolean right){
        super(tm);

        moveSpeed = 8;
        if(right){
            dx = moveSpeed;
        } else{
            dx = -moveSpeed;
        }

        //width of the frame that we need to parse from the sprite sheet
        width = 30;
        height = 30;

        //collision box dimensions 
        cwidth =14;
        cheight = 14;

        //loading sprites 
        try{
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/missile.png"));
                sprites = new BufferedImage[4];
                for(int i = 0; i<sprites.length; i++){
                    sprites[i] = spritesheet.getSubimage(i*width,0,width,height);
                }
                hitSprites = new BufferedImage[3];
                for(int i = 0; i <hitSprites.length; i++){
                    hitSprites[i] = spritesheet.getSubimage(i*width, height, width, height);
                }
                animation = new Animation();
                animation.setFrames(sprites);
                animation.setDelay(70);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //when the missile hits something 
    public void setHit(){
        if(hit) return;
        hit = true;
        animation.setFrames(hitSprites); 
        animation.setDelay(70); 
        dx = 0; 
    }

    //should the missile be removed? 
    public boolean shouldRemove() {
        return remove;
    }


    //updating the missile
    public void update(){
        checkTileMapCollision();
        setPosition(xtemp, ytemp);
        animation.update();

        if(dx == 0 && !hit){
            setHit();
        }

        if(hit&&animation.hasPlayedOnce()){
            remove = true;
        }
    }

    //drawing the missile 
    public void draw(Graphics2D g) {
        setMapPosition();

        if(facingRight) {
			g.drawImage(animation.getImage(),(int)(x + xmap - width / 2),(int)(y + ymap - height / 2),null);
		}
		else {
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2 + width), (int)(y + ymap - height / 2), -width, height,null);
		}

    }
}
