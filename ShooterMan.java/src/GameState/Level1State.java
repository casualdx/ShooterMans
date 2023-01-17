package GameState;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State extends GameState {
	
	private TileMap tileMap;
	
	private Player player;
	private Player player2;

	private HUD hud;

	//constructor 
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		//initializing the tilemap 
		tileMap = new TileMap(30);
		tileMap.loadTiles("/TileSet/TileSet.png");
		tileMap.loadMap("/Maps/Map.map");
		tileMap.setPosition(0, 0);
		
		//initializing the players 
		player = new Player(tileMap,"/Sprites/player1sprites.png");
		player2 = new Player(tileMap,"/Sprites/player2sprites.png");

		//sets player spawns
		player.setPosition(100,100);
		player2.setPosition(500,100);

		//draws the HUD (health)
		hud = new HUD(player,player2);
	}
	
	
	public void update() {
		//players
		player.update();
		player2.update();

		//checks the attacks
		player.checkAttack(player2, gsm);
		player2.checkAttack(player, gsm);
	}

	public void draw(Graphics2D g) {
		
		// clears screen
		g.setColor(Color.black);
		g.fillRect(0, 0, GamePanel.width, GamePanel.height);
		
		// draws tilemap
		tileMap.draw(g);
		
		//draw players
		player.draw(g);
		player2.draw(g);

		//draw hud
		hud.draw(g);
	}
	
	public void keyPressed(int k) {
		//player 1 controls
		if(k == KeyEvent.VK_A) player.setLeft(true);
		if(k == KeyEvent.VK_D) player.setRight(true);
		if(k == KeyEvent.VK_W) player.setJumping(true);
		if(k == KeyEvent.VK_SPACE) player.setFiring();

		//player 2 controls
		if(k == KeyEvent.VK_LEFT) player2.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player2.setRight(true);
		if(k == KeyEvent.VK_UP) player2.setJumping(true);
		if(k == KeyEvent.VK_SHIFT) player2.setFiring();
	}
	
	public void keyReleased(int k) {
		//player 1 controls
		if(k == KeyEvent.VK_A) player.setLeft(false);
		if(k == KeyEvent.VK_D) player.setRight(false);
		if(k == KeyEvent.VK_W) player.setJumping(false);
		//player 2 controls
		if(k == KeyEvent.VK_LEFT) player2.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player2.setRight(false);
		if(k == KeyEvent.VK_UP) player2.setJumping(false);
		
	}
}












