import java.awt.Font;

/**
 * Contains all constants needed by the program. This includes drawing constants, font constants, and all other constants.
 * 
 * @author Alex
 *
 */
public final class Constants {

	public static void resetVariables() {
		// setting size
		Frame.screen.setMaximumSize(Frame.panelSize);
		Frame.screen.setMinimumSize(Frame.panelSize);
		Frame.screen.setPreferredSize(Frame.panelSize);

		tileSize = (int) Math.round(32 * Frame.sizeMultiplier);            
		projectileSize = (int) Math.round(8 * Frame.sizeMultiplier);                                              

		enemyHealthOffset = (int) Math.round(2 * Frame.sizeMultiplier);      
		enemyHealthHeight = (int) Math.round(2 * Frame.sizeMultiplier);      

		shopButtonSize = (int) Math.round(48 * Frame.sizeMultiplier);      
		shopFirstButtonX = (int) Math.round(75 * Frame.sizeMultiplier);    
		shopButtonSpacing = (int) Math.round(7 * Frame.sizeMultiplier);    
		costSpacingX = (int) Math.round(4 * Frame.sizeMultiplier);         
		costSpacingY = (int) Math.round(12 * Frame.sizeMultiplier);        
		moneyAndLivesX = (int) Math.round(2 * Frame.sizeMultiplier);       
		moneyAndLivesSize = (int) Math.round(16 * Frame.sizeMultiplier);   
		moneyY = (int) Math.round(427 * Frame.sizeMultiplier);             
		livesY = (int) Math.round(454 * Frame.sizeMultiplier);             
		moneyLivesStringX = (int) Math.round(20 * Frame.sizeMultiplier);   
		playPauseWidth = (int) Math.round(40 * Frame.sizeMultiplier);      
		playPauseHeight = (int) Math.round(20 * Frame.sizeMultiplier);     
		playPauseX = (int) Math.round(590 * Frame.sizeMultiplier);         
		playY = (int) Math.round(452 * Frame.sizeMultiplier);              
		pauseY = (int) Math.round(424 * Frame.sizeMultiplier);             
		maxHelpLength = (int) Math.round(125 * Frame.sizeMultiplier);      
		helpX = (int) Math.round(460 * Frame.sizeMultiplier);              
		helpY = (int) Math.round(433 * Frame.sizeMultiplier);              
		helpSpacing = (int) Math.round(12 * Frame.sizeMultiplier);         

		initialMenuY = (int) Math.round(30 * Frame.sizeMultiplier);        
		menuButtonWidth = (int) Math.round(200 * Frame.sizeMultiplier);    
		menuButtonHeight = (int) Math.round(30 * Frame.sizeMultiplier);    
		menuStringWidth = (int) Math.round(600 * Frame.sizeMultiplier);    
		menuStringHeight = (int) Math.round(400 * Frame.sizeMultiplier);   
		maxMenuStringLength = (int) Math.round(550 * Frame.sizeMultiplier);
		menuStringOffset = (int) Math.round(25 * Frame.sizeMultiplier);    

		intitalTitleY = (int) Math.round(240 * Frame.sizeMultiplier);
		titleX = (int) Math.round(420 * Frame.sizeMultiplier);

		versionDistanceFromEdge = (int) Math.round(5 * Frame.sizeMultiplier);

		F_moneyLives = new Font("Comic Sans MS", Font.BOLD, (int) Math.round(22 * Frame.sizeMultiplier));
		F_helpText = new Font("Comic Sans MS", Font.PLAIN, (int) Math.round(10 * Frame.sizeMultiplier));
		F_costText = new Font("Comic Sans MS", Font.PLAIN, (int) Math.round(10 * Frame.sizeMultiplier));
		F_menu = new Font("Comic Sans MS", Font.PLAIN, (int) Math.round(20 * Frame.sizeMultiplier));
		F_title = new Font("Comic Sans MS", Font.PLAIN, (int) Math.round(40 * Frame.sizeMultiplier));

		towerDiameters = new int[] {
			(int) Math.round(160 * Frame.sizeMultiplier),
			(int) Math.round(224 * Frame.sizeMultiplier),
			(int) Math.round(192 * Frame.sizeMultiplier),
			(int) Math.round(160 * Frame.sizeMultiplier),
			(int) Math.round(0 * Frame.sizeMultiplier),
			(int) Math.round(0 * Frame.sizeMultiplier),
			(int) Math.round(0 * Frame.sizeMultiplier)
		};
		towerBarrelLengths = new int[] {
			(int) Math.round(14 * Frame.sizeMultiplier),
			(int) Math.round(14 * Frame.sizeMultiplier),
			(int) Math.round(14 * Frame.sizeMultiplier),
			(int) Math.round(14 * Frame.sizeMultiplier),
			(int) Math.round(0 * Frame.sizeMultiplier),
			(int) Math.round(0 * Frame.sizeMultiplier),
			(int) Math.round(0 * Frame.sizeMultiplier)
		};
		enemySize = new int[] {
			0, // place holder: enemyID of 0 is a non-existent enemy
			tileSize,
			tileSize,
			tileSize,
			tileSize,
			tileSize,
			tileSize / 2
		};
	}

	public static int imageSize = 128;
	public static int projectileImageSize = 32;

	public static int particleDelay = 3;
	public static int explosionFrames = 10;

	public static int originalTileSize = 32;
	public static int tileSize = (int) Math.round(originalTileSize * Frame.sizeMultiplier);
	public static int projectileSize = (int) Math.round(8 * Frame.sizeMultiplier);
	public static int levelWidth = 20;
	public static int levelHeight = 13;

	public static int enemyHealthOffset = (int) Math.round(2 * Frame.sizeMultiplier);
	public static int enemyHealthHeight = (int) Math.round(2 * Frame.sizeMultiplier);

	public static int shopButtonSize = (int) Math.round(48 * Frame.sizeMultiplier);
	public static int shopFirstButtonX = (int) Math.round(75 * Frame.sizeMultiplier);
	public static int shopButtonSpacing = (int) Math.round(7 * Frame.sizeMultiplier);
	public static int costSpacingX = (int) Math.round(4 * Frame.sizeMultiplier);
	public static int costSpacingY = (int) Math.round(12 * Frame.sizeMultiplier);
	public static int moneyAndLivesX = (int) Math.round(2 * Frame.sizeMultiplier);
	public static int moneyAndLivesSize = (int) Math.round(16 * Frame.sizeMultiplier);
	public static int moneyY = (int) Math.round(427 * Frame.sizeMultiplier);
	public static int livesY = (int) Math.round(454 * Frame.sizeMultiplier);
	public static int moneyLivesStringX = (int) Math.round(20 * Frame.sizeMultiplier);
	public static int playPauseWidth = (int) Math.round(40 * Frame.sizeMultiplier);
	public static int playPauseHeight = (int) Math.round(20 * Frame.sizeMultiplier);
	public static int playPauseX = (int) Math.round(590 * Frame.sizeMultiplier);
	public static int playY = (int) Math.round(452 * Frame.sizeMultiplier);
	public static int pauseY = (int) Math.round(424 * Frame.sizeMultiplier);
	public static int maxHelpLength = (int) Math.round(125 * Frame.sizeMultiplier);
	public static int helpX = (int) Math.round(460 * Frame.sizeMultiplier);
	public static int helpY = (int) Math.round(433 * Frame.sizeMultiplier);
	public static int helpSpacing = (int) Math.round(12 * Frame.sizeMultiplier);

	public static int initialMenuY = (int) Math.round(30 * Frame.sizeMultiplier);
	public static int menuButtonWidth = (int) Math.round(200 * Frame.sizeMultiplier);
	public static int menuButtonHeight = (int) Math.round(30 * Frame.sizeMultiplier);
	public static int menuStringWidth = (int) Math.round(600 * Frame.sizeMultiplier);
	public static int menuStringHeight = (int) Math.round(400 * Frame.sizeMultiplier);
	public static int maxMenuStringLength = (int) Math.round(550 * Frame.sizeMultiplier);
	public static int menuStringOffset = (int) Math.round(25 * Frame.sizeMultiplier);

	public static int intitalTitleY = (int) Math.round(220 * Frame.sizeMultiplier);
	public static int titleX = (int) Math.round(420 * Frame.sizeMultiplier);

	public static int versionDistanceFromEdge = (int) Math.round(5 * Frame.sizeMultiplier);

	public static int titleButtonAmount = 2;
	public static int menuButtonAmount = 4;
	public static int optionButtonAmount = 2;
	public static int gameOverButtonAmount = 2;
	public static int tilesetLength = 100;
	public static int maxRounds = 100;
	public static int maxMobsPerRound = 100;
	public static int timeBetweenMobs = 150;
	public static int levelNumber = 0;
	public static int originalWaitTime = 10;
	public static int spedUpWaitTime = 2;
	public static int waitTime = originalWaitTime;

	public static Font F_moneyLives = new Font("Comic Sans MS", Font.BOLD, (int) Math.round(22 * Frame.sizeMultiplier));
	public static Font F_helpText = new Font("Comic Sans MS", Font.PLAIN, (int) Math.round(10 * Frame.sizeMultiplier));
	public static Font F_costText = new Font("Comic Sans MS", Font.PLAIN, (int) Math.round(10 * Frame.sizeMultiplier));
	public static Font F_menu = new Font("Comic Sans MS", Font.PLAIN, (int) Math.round(20 * Frame.sizeMultiplier));
	public static Font F_title = new Font("Comic Sans MS", Font.PLAIN, (int) Math.round(40 * Frame.sizeMultiplier));

	public static String imageDirectory = "resources/images/";
	public static String shopDirectory = "shop/";
	public static String menuDirectory = "menu/";
	public static String particlesDirectory = "particles/";

	public static int[] towerDiameters = new int[] {
		(int) Math.round(160 * Frame.sizeMultiplier),
		(int) Math.round(224 * Frame.sizeMultiplier),
		(int) Math.round(192 * Frame.sizeMultiplier),
		(int) Math.round(160 * Frame.sizeMultiplier),
		(int) Math.round(0 * Frame.sizeMultiplier),
		(int) Math.round(0 * Frame.sizeMultiplier),
		(int) Math.round(0 * Frame.sizeMultiplier)
	};
	public static int[] towerPrices = new int[] {
		5,
		15,
		20,
		50,
		0,
		0,
		0
	};
	public static int[] towerBarrelLengths = new int[] {
		(int) Math.round(14 * Frame.sizeMultiplier),
		(int) Math.round(14 * Frame.sizeMultiplier),
		(int) Math.round(14 * Frame.sizeMultiplier),
		(int) Math.round(14 * Frame.sizeMultiplier),
		(int) Math.round(0 * Frame.sizeMultiplier),
		(int) Math.round(0 * Frame.sizeMultiplier),
		(int) Math.round(0 * Frame.sizeMultiplier)
	};
	public static int[] towerCooldown = new int[] {
		150,
		50,
		225,
		150
	};
	public static double[] projectileDamage = new double[] {
		1,
		0.5,
		2,
		3
	};
	public static double[] projectileSpeed = new double[] {
		0.5,
		1,
		0.4,
		0.6
	};
	public static int[] projectileExplosionDiameter = new int[] {
		0,
		0,
		0,
		48
	};
	public static int[] enemySpeed = new int[] {
		0, // place holder: enemyID of 0 is a non-existent enemy
		2,
		0,
		3,
		2,
		6,
		1
	};
	public static int[] enemyHealth = new int[] {
		0, // place holder: enemyID of 0 is a non-existent enemy
		5,
		3,
		12,
		10,
		20,
		5
	};
	public static int[] enemyWorth = new int[] {
		0, // place holder: enemyID of 0 is a non-existent enemy
		1,
		2,
		3,
		5,
		0,
		2
	};
	public static int[] enemyDefense = new int[] {
		0, // place holder: enemyID of 0 is a non-existent enemy
		0,
		0,
		0,
		1,
		1,
		0
	};
	public static int[] enemySize = new int[] {
		0, // place holder: enemyID of 0 is a non-existent enemy
		tileSize,
		tileSize,
		tileSize,
		tileSize,
		tileSize,
		tileSize / 2
	};
	public static int[] enemyDamage = new int[] {
		0, // place holder: enemyID of 0 is a non-existent enemy
		1,
		1,
		1,
		1,
		4,
		1
	};
	public static boolean[] shopButtonsActivated = new boolean[] {
		true,
		true,
		true,
		true,
		false,
		false,
		false
	};
	public static String[] levelName = new String[] {
		"level0"
	};
}
