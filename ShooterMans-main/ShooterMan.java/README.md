David Xie 
dxie3@u.rochester.edu

GAME DESCRIPTION: 
    There are 2 players. 
    They have Missile Launchers. 
    Shoot the other player and bring their health to zero. 

HOW TO RUN: 
    - Unzip the project folder
    - Open ShooterMan.java in IDE 
    - In Package Main, run Game.java
    - Enjoy! 

SOMETHING I INVENTED CRITERIA 

Animation - Player animations, missile animations 

Interactive - Use the keyboard to control 

SCORING MECHANISM - The health of each player 

ENDING MECHANISM - Health = 0, you lose 

PHYSICAL MECHANISM - jumping around, gravity, and player motion 

Collision detection - walls and missile collision 

Flourish - customized sprites and is a 2 player game. 



PACKAGES AND CLASSES 

ENTITY:
    Animation:
        - Sets up the frames and the delays between each frame 
    HUD: 
        - The hud displaying each players health value. 
    
    Missile: 
        - Is the missiles that are being shot out by the players 
        - Manages their speed and damage 
        - Loads their sprites 
        - sets how the missile knows when it hits something and what to do from there 

    Object: 
        - Players and missiles are objects within the game 
        - most importantly, sets up their collision boxes and collision detection with other objects and the tiles 
        - sets their attributes 
    Player: 
        - Sets everything about the player
        - Sets Animations
        - Collision with missiles 
        - Player actions 

MAIN:
    Game: 
        -Sets the JFrame in which everything is displayed upon
    GamePanel: 
        - JPanel in which everything happens
        - Creates a Game Loop and the thread

GAMESTATE:
    GameState: 
        - Parent class for other State Classes 
        - defines the methods that have to be included 
    GameState Manager: 
        - where the states are held 
        - allows traversal through the different states 
        - Contains methods that are useful for manipulating states later on 

        Level1State: 
            - Loads the tiles and prints them out in the map 
            - Creates the Constructor for the players and the HUD.
            - Draws the players, stage map, and HUD 
            - Maps the keys to each of the players controls. 

        MenuState: 
            - Is the main menu of the game
            - Draws the title and each of the options.
            - Cycles through and allows the selection of the Start, Instructions, and Exit options through methods  

        HelpState: 
            - Provides instructions for the game
            - lots of drawString methods 

        VictoryState:
            - Ends the game when someone has died (didnt have time to figure out which player died)
            - Gives the option to return to Main menu or Restart. 

        GameStateManager: 
                - Manages all the GameStates by storing them in an ArrayList
                - Provides methods that allow me to manipulate Game States (i.e set the current state, draw the current state etc.)



 TILEMAP:
    This was probably the biggest challenge of the whole project. 

    I needed a way to tell if a tile is collidable or not and where they needed to be.

    I accomplished this using a self-created TileMap. You can find it in /ShooterMan/bin/Maps/GamePlay.Map/. It is a text file that shows which tile is a collidable block (the 1s in the block of text) and the tiles that are simply air (the 0s). The numbers on the top are the dimensions.
    
    The program reads through and places the correct tile where it needs to go, creating the game map. 

    I also have the tiles that it draws from the map at /ShooterMan/bin/TileSets/GamePlay.Map/ (blocks 1 are the cyan, blocks 2 are the transparent one (textures that are transparent are pink or black depending which software looks at it.))

    TileMap
        - loads the tiles from the tileset and the map from the text file
        - creates methods that manage the tile map (setpositions, what type a tile at a certain position is etc. )
    Tile
        - creates tile object 
        - what image its from 
        - what type of tile it is 
        
    BackGround
        - simply the background image 

