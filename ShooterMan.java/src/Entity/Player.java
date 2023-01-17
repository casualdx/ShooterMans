package Entity;

import TileMap.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;

import GameState.GameStateManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Object {
	
	// player stuff
	private int health;
	private int maxHealth;
	private boolean dead;
	private boolean flinching;
	private long flinchTimer;
	
	// missile
	private boolean firing;
	private int missileDamage;
	private ArrayList<Missile> missiles;
	
	// animations
	private ArrayList<BufferedImage[]> sprites;

	//how many frames of animation are in each in action
	private final int[] numFrames = {
		/*idle*/4, /*walking*/6, /*jumping*/4, /*falling*/1, /*missile*/4,
	};
	
	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int MISSILE = 4;
	
	public Player(TileMap tm, String image) {
		super(tm);

		//width of sprite 
		width = 35;
		height = 35;

		//width of collision box
		cwidth = 20;
		cheight = 20;
		
		//player attributes 
		moveSpeed = 1;
		maxSpeed = 3.0;
		stopSpeed = 0.4;
		fallSpeed = 0.3;
		maxFallSpeed = 6.0;
		jumpStart = -8.8;
		stopJumpSpeed = 0.3; 
		facingRight = true;
		
		health = maxHealth = 100;
		
		dead = false;

		//missile attributes 
		missileDamage = 10;
		missiles = new ArrayList<Missile>();
		
		// loading the sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream(image));
			
			sprites = new ArrayList<BufferedImage[]>();

			//going through the rows of the sprite sheet 
			for(int i = 0; i < 5; i++) {
				
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				
				//going through each row and printing out each 35x35 slice of the spritesheet as each frame
				for(int j = 0; j < numFrames[i]; j++) {
						bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
				}
				sprites.add(bi);
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//to solve a null pointer exception
		//have manually set the default animation to something 
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		
	}
	
	//getters
	public int getHealth() { 
		return health;
	}
	public int getMaxHealth() { 
		return maxHealth; 
	}
	
	//setters 
	public void setFiring() { 
		firing = true;
	}


	//checks if the missile has hit a player
	public void checkAttack(Player target, GameStateManager gsm){
		//check missile
		for(int i = 0; i < missiles.size(); i++){
			if(missiles.get(i).intersects(target)){ 
				target.hit(missileDamage); 
				missiles.get(i).setHit(); 
				if(target.dead){ 
					gsm.setState(GameStateManager.VICTORY); 
				}
				break;
		}
	}
}
	//what happens if the missile hits something 
	public void hit(int damage) {	
		if(flinching) return;  
		health -= damage; 
		if(health < 0) health = 0;
		if(health == 0){ 
			dead = true;
		}
		//makes the player flinch
		flinching = true;
		flinchTimer = System.nanoTime();
	}
	

	//gets the next Position of the player OR ACTION of the player 
	private void getNextPosition() {
		// movement
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) { 
				dx = -maxSpeed;
			}
		}
		else if(right) { 
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		
		// jumping
		if(jumping && !falling) {
			dy = jumpStart;
			falling = true;	
		}
		
		// falling
		if(falling) {
			dy+= fallSpeed;
				if(dy > 0){ 
				jumping = false;
			}
			if(dy < 0 && !jumping){
				dy += stopJumpSpeed;
			}
			if(dy > maxFallSpeed){
				dy = maxFallSpeed;
			} 
			
		}
		
	}
	
	//updates everything about the player
	//checks the current action of the player 
	//updates the animations for the players as well 
	public void update() {
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(currentAction == MISSILE){
			if(animation.hasPlayedOnce()) firing = false;
		}

		//missile attack
		if(firing && currentAction != MISSILE){
			Missile m = new Missile(tileMap, facingRight);
			m.setPosition(x, y);
			missiles.add(m);
		}

		//update missile 
		for(int i = 0; i < missiles.size(); i++){
			missiles.get(i).update();
			if(missiles.get(i).shouldRemove()){
				missiles.remove(i); 
				i--;
			}
		}

		// check done flinching
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed > 500) {
				flinching = false;
			}
		}
	
		else if(firing) {
			if(currentAction != MISSILE) {
				currentAction = MISSILE;
				animation.setFrames(sprites.get(MISSILE));
				animation.setDelay(100);
				width = 30;
			}
		}
		
		else if(dy > 0) {	
			 if(currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING)); 
				animation.setDelay(100);
				width = 30;
			}
		}
		else if(dy < 0) {
			if(currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 30;
			}
		}
		else if(left || right) {
			if(currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
				width = 30;
			}
		}
		else { 
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE)); 
				animation.setDelay(400);
				width = 30;
			}
		}
		
		animation.update();
	
		if(currentAction != MISSILE) {
			if(right) facingRight = true;
			if(left) facingRight = false;
		}
		
	}
	

	//draws the missiles, plays the flinching animation, and plays the animation if you are facing left or right 
	public void draw(Graphics2D g) {
		
		setMapPosition();
		//draw missiles
		for(int i = 0; i < missiles.size(); i++){
			missiles.get(i).draw(g);
		}
		// draw player
		if(flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed / 100 % 2 == 0) {
				return;
			}
		}
		
		if(facingRight) {
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2), (int)(y + ymap - height / 2), null);
		}
		else {
			g.drawImage(animation.getImage(), (int)(x + xmap - width / 2 + width), (int)(y + ymap - height / 2), -width, height, null);
			
		}
		
	}
	
}

















