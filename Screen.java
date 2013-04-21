import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * Main drawing class of the game, as well as the game loop (Found in method run)
 * 
 * @author Alex
 * 
 */
public class Screen extends JPanel implements Runnable {
	
	public static Grid[][] world;
	public static Enemy[][] enemies;
	public static ShopButton[] shopButtons;
	public static int[][][] worldInt;
	public static int[][] enemiesInt;
	
	public static volatile ArrayList<Tower> towers;
	public static volatile ArrayList<Enemy> enemyList;
	public static volatile ArrayList<Projectile> projectileList;
	public static volatile ArrayList<Particles> particles;
	
	public static Image[] tileset_ground;
	public static Image[] tileset_air;
	public static Image[] tileset_towers;
	public static Image[] tileset_enemies;
	public static Image[] tileset_projectiles;
	
	public static Image[] tileset_icons;
	public static ArrayList<ArrayList<Image>> tileset_particles;
	
	public static Thread thread;
	
	public static Point mouse;
	
	public static int lives;
	public static int money;
	
	public static int waitTime;
	
	public static boolean inLevel;
	public static boolean isPaused;
	public static int pausedID;
	public static boolean gameOver;
	
	public static boolean drawMouse;
	public static int mouseImage;
	
	public static boolean ableToBePlaced;
	
	public static int enemyStartY;

    public static int timeUntilNextMob;
	public static boolean allEnemiesSpawned;
	public static int enemyCount;
	public static int level;
	
	// warning suppressor due to an array of arraylists
	//@SuppressWarnings("unchecked")
	public Screen() {
		
		long origTime = System.nanoTime();
		
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Time start at " + (float) Math.round((System.nanoTime() - origTime) / 1000000) / 1000 + " seconds");
		
		// setting size
		this.setMaximumSize(Frame.panelSize);
		this.setMinimumSize(Frame.panelSize);
		this.setPreferredSize(Frame.panelSize);
		
		// creating tilesets and images
		tileset_ground = new Image[Constants.tilesetLength];
		tileset_air = new Image[Constants.tilesetLength];
		tileset_towers = new Image[Constants.tilesetLength];
		tileset_enemies = new Image[Constants.tilesetLength];
		tileset_projectiles = new Image[Constants.tilesetLength];
		
		for (int i = 0; i < tileset_ground.length; i++) {
			tileset_ground[i] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + "tileset_ground.png")).getImage();
			tileset_ground[i] = createImage(new FilteredImageSource(tileset_ground[i].getSource(), new CropImageFilter(Constants.imageSize * i, 0, Constants.imageSize, Constants.imageSize)));
		}
		for (int i = 0; i < tileset_air.length; i++) {
			tileset_air[i] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + "tileset_air.png")).getImage();
			tileset_air[i] = createImage(new FilteredImageSource(tileset_air[i].getSource(), new CropImageFilter(Constants.imageSize * i, 0, Constants.imageSize, Constants.imageSize)));
		}
		for (int i = 0; i < tileset_towers.length; i++) {
			tileset_towers[i] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + "tileset_towers.png")).getImage();
			tileset_towers[i] = createImage(new FilteredImageSource(tileset_towers[i].getSource(), new CropImageFilter(Constants.imageSize * i, 0, Constants.imageSize, Constants.imageSize)));
		}
		for (int i = 0; i < tileset_enemies.length; i++) {
			tileset_enemies[i] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + "tileset_enemies.png")).getImage();
			tileset_enemies[i] = createImage(new FilteredImageSource(tileset_enemies[i].getSource(), new CropImageFilter(Constants.imageSize * i, 0, Constants.imageSize, Constants.imageSize)));
		}
		for (int i = 0; i < tileset_projectiles.length; i++) {
			tileset_projectiles[i] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + "tileset_projectiles.png")).getImage();
			tileset_projectiles[i] = createImage(new FilteredImageSource(tileset_projectiles[i].getSource(), new CropImageFilter(Constants.projectileImageSize * i, 0, Constants.projectileImageSize, Constants.projectileImageSize)));
		}
		
		tileset_icons = new Image[Constants.tilesetLength];
		tileset_particles = new ArrayList<ArrayList<Image>>();
		
		// index 0 - 19: shop
		tileset_icons[0] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.shopDirectory + "money.png")).getImage();
		tileset_icons[1] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.shopDirectory + "lives.png")).getImage();
		tileset_icons[2] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.shopDirectory + "shop_button.png")).getImage();
		tileset_icons[3] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.shopDirectory + "shop_button_selected.png")).getImage();
		tileset_icons[4] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.shopDirectory + "pause_button.png")).getImage();
		tileset_icons[5] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.shopDirectory + "pause_button_selected.png")).getImage();
		tileset_icons[6] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.shopDirectory + "play_button.png")).getImage();
		tileset_icons[7] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.shopDirectory + "play_button_selected.png")).getImage();
		tileset_icons[8] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.shopDirectory + "fast_forward_button.png")).getImage();
		tileset_icons[9] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.shopDirectory + "fast_forward_button_selected.png")).getImage();
		tileset_icons[10] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.shopDirectory + "fast_forward_button_pressed.png")).getImage();
		
		// index 20 - 39: menus
		tileset_icons[20] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.menuDirectory + "menu_button.png")).getImage();
		tileset_icons[21] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.menuDirectory + "menu_button_selected.png")).getImage();
		tileset_icons[22] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.menuDirectory + "string_menu.png")).getImage();
		tileset_icons[23] = new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.menuDirectory + "title_menu.png")).getImage();
		
		tileset_particles.add(new ArrayList<Image>());
		int indexTilesetParticles = 0;
		for (int i = 0; i < Constants.explosionFrames; i++) {
			tileset_particles.get(indexTilesetParticles).add(new ImageIcon(this.getClass().getResource(Constants.imageDirectory + Constants.particlesDirectory + "explosion.png")).getImage());
			tileset_particles.get(indexTilesetParticles).set(i, createImage(new FilteredImageSource(tileset_particles.get(indexTilesetParticles).get(i).getSource(), new CropImageFilter(Constants.imageSize * i, 0, Constants.imageSize, Constants.imageSize)))); 
		}
		
		System.out.println("Tilesets created in " + (float) Math.round((System.nanoTime() - origTime) / 1000000) / 1000 + " seconds");
		
		initializeVariables(origTime);
		
		// initializing mouse listeners
		mouse = new Point(0, 0);
		Mouse mouseListeners = new Mouse();
		this.addMouseListener(mouseListeners);
		this.addMouseMotionListener(mouseListeners);
		
		System.out.println("Mouse listeners created in " + (float) Math.round((System.nanoTime() - origTime) / 1000000) / 1000 + " seconds");
		
		//starting game refresh thread
		thread = new Thread(this);
		thread.start();
		
		System.out.println("Thread created in " + (float) Math.round((System.nanoTime() - origTime) / 1000000) / 1000 + " seconds");
		System.out.println("--------------------------------------------------------------------------------");
		
		Thread repaintThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					repaint();
				}
			}
		});
		repaintThread.start();
	}
	
	public static void initializeVariables(long origTime) {

		//setting original variables
		lives = 20;
		money = 10;

		inLevel = false;
		isPaused = true;
		pausedID = 0;
		gameOver = false;
		
		waitTime = Constants.originalWaitTime;
		allEnemiesSpawned = false;
		enemyCount = 0;
		level = 1;
		timeUntilNextMob = 0;

		// loading and creating world
		world = new Grid[Constants.levelHeight][Constants.levelWidth];

		Load loadLevel = new Load();
		worldInt = loadLevel.loadLevel(Constants.levelName[Constants.levelNumber]);

		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				world[i][j] = new Grid(worldInt[0][i][j], worldInt[1][i][j]);

				// notes the enemy starting and ending position
				if (j == 0 && worldInt[1][i][j] == 1) {
					enemyStartY = i * Constants.originalTileSize;
				} else if (j == Constants.levelWidth && worldInt[1][i][j] == 1) {
                }
			}
		}

		System.out.println("World created in " + (float) Math.round((System.nanoTime() - origTime) / 1000000) / 1000 + " seconds");

		// loading enemies
		enemies = new Enemy[Constants.maxRounds][Constants.maxMobsPerRound];

		Enemy.world = loadLevel.loadLevel(Constants.levelName[Constants.levelNumber])[1];

		enemiesInt = loadLevel.loadMobs(Constants.levelName[Constants.levelNumber] + "enemies");

		for (int i = 0; i < enemiesInt.length; i++) {
			for (int j = 0; j < enemiesInt[i].length; j++) {
				enemies[i][j] = new Enemy(0, enemyStartY, enemiesInt[i][j]);
				
				if (enemies[i][j].enemyID == 6) {
					enemies[i][j].offsetX += Constants.originalTileSize / 4;
					enemies[i][j].offsetY += Constants.originalTileSize / 4;
				}
			}
		}

		System.out.println("Enemies created in " + (float) Math.round((System.nanoTime() - origTime) / 1000000) / 1000 + " seconds");

		// intializing tower lists
		towers = new ArrayList<Tower>();
		enemyList = new ArrayList<Enemy>();
		projectileList = new ArrayList<Projectile>();
		particles = new ArrayList<Particles>();

		System.out.println("Lists created in " + (float) Math.round((System.nanoTime() - origTime) / 1000000) / 1000 + " seconds");
		
		// creating shop
		shopButtons = new ShopButton[7];
		for (int i = 0; i < shopButtons.length; i++) {
			shopButtons[i] = new ShopButton(i, Constants.shopButtonsActivated[i]);
		}
		
		System.out.println("Shop created in " + (float) Math.round((System.nanoTime() - origTime) / 1000000) / 1000 + " seconds");
	}
	
	@Override
	public void paintComponent(Graphics originalG) {
		Graphics2D g = (Graphics2D) originalG;

		// clear screen
		g.setColor(Color.black);
		g.fillRect(0, 0, Frame.panelSize.width, Frame.panelSize.height);
		
		if (Frame.isBetaVersion || Frame.isOldVersion) {
			g.drawImage(tileset_icons[22], (Frame.panelSize.width - Constants.menuStringWidth) / 2, (Frame.panelSize.width - Constants.menuStringWidth) / 2, Constants.menuStringWidth, Constants.menuStringHeight, null);

			g.setColor(Color.white);
			g.setFont(Constants.F_menu);
			
			ArrayList<String> stringList = Frame.isBetaVersion ? Strings.getCutUpStrings(Strings.betaVersionStringPart, Constants.maxMenuStringLength, g.getFontMetrics()) : Strings.getCutUpStrings(Strings.oldVersionStringPart1 + Frame.VERSION + Strings.oldVersionStringPart2 + Frame.newVersion + Strings.oldVersionStringPart3, Constants.maxMenuStringLength, g.getFontMetrics());

			for (int i = 0; i < stringList.size(); i++) {
				g.drawString(stringList.get(i), ((Frame.panelSize.width - Constants.menuStringWidth) / 2) + Constants.menuStringOffset, ((Frame.panelSize.width - Constants.menuStringWidth) / 2) + (Constants.menuStringOffset * (i + 1)));
			}
			
			if (new Rectangle((Frame.panelSize.width - Constants.menuButtonWidth) / 2, ((Frame.panelSize.width - Constants.menuStringWidth) / 2) + Constants.menuStringHeight + (Constants.initialMenuY / 2), Constants.menuButtonWidth, Constants.menuButtonHeight).contains(mouse)) {
				g.drawImage(tileset_icons[21], (Frame.panelSize.width - Constants.menuButtonWidth) / 2, ((Frame.panelSize.width - Constants.menuStringWidth) / 2) + Constants.menuStringHeight + (Constants.initialMenuY / 2), Constants.menuButtonWidth, Constants.menuButtonHeight, null);
			} else {
				g.drawImage(tileset_icons[20], (Frame.panelSize.width - Constants.menuButtonWidth) / 2, ((Frame.panelSize.width - Constants.menuStringWidth) / 2) + Constants.menuStringHeight + (Constants.initialMenuY / 2), Constants.menuButtonWidth, Constants.menuButtonHeight, null);
			}

			String text = Strings.versionContinue;
			int textWidth = 0;

			for (int j = 0; j < text.length(); j++) {
				textWidth += g.getFontMetrics().charWidth(text.charAt(j));
			}

			int textHeight = g.getFontMetrics().getHeight() - (Constants.menuButtonHeight / 5);

			g.drawString(text, ((Frame.panelSize.width - Constants.menuButtonWidth) / 2) + ((Constants.menuButtonWidth - textWidth) / 2), ((Frame.panelSize.width - Constants.menuStringWidth) / 2) + Constants.menuStringHeight + (Constants.initialMenuY / 2) + textHeight);
		} else {
			// draw world tiles
			for (int i = 0; i < world.length; i++) {
				for (int j = 0; j < world[i].length; j++) {
					g.drawImage(Screen.tileset_ground[world[i][j].groundID], j * Constants.tileSize, i * Constants.tileSize, Constants.tileSize, Constants.tileSize, null);
					g.drawImage(Screen.tileset_air[world[i][j].airID], j * Constants.tileSize, i * Constants.tileSize, Constants.tileSize, Constants.tileSize, null);
				}
			}

			// drawing enemies
			for (Enemy enemyI : enemyList) {
				g.drawImage(RotateImage.rotateImage(tileset_enemies[enemyI.enemyID - 1], enemyI.direction, Constants.imageSize), (int) Math.round((enemyI.x * Frame.sizeMultiplier) + (enemyI.offsetX * Frame.sizeMultiplier)), (int) Math.round((enemyI.y * Frame.sizeMultiplier) + (enemyI.offsetY * Frame.sizeMultiplier)), Constants.enemySize[enemyI.enemyID], Constants.enemySize[enemyI.enemyID], null);

                g.setColor(Color.red);
				g.fillRect((int) Math.round((enemyI.x * Frame.sizeMultiplier) + (enemyI.offsetX * Frame.sizeMultiplier)), (int) Math.round((enemyI.y * Frame.sizeMultiplier) + (enemyI.offsetY * Frame.sizeMultiplier)) - Constants.enemyHealthOffset, Constants.enemySize[enemyI.enemyID], Constants.enemyHealthHeight);
				g.setColor(Color.green);
				g.fillRect((int) Math.round((enemyI.x * Frame.sizeMultiplier) + (enemyI.offsetX * Frame.sizeMultiplier)), (int) Math.round((enemyI.y * Frame.sizeMultiplier) + (enemyI.offsetY * Frame.sizeMultiplier)) - Constants.enemyHealthOffset, (int) Math.round(Constants.enemySize[enemyI.enemyID] * (enemyI.health / Constants.enemyHealth[enemyI.enemyID])), Constants.enemyHealthHeight);
			}

			// drawing towers
			for (Tower towerI : towers) {
				g.drawImage(RotateImage.rotateImage(tileset_towers[towerI.towerID], towerI.direction, Constants.imageSize), (int) Math.round(towerI.x * Frame.sizeMultiplier), (int) Math.round(towerI.y * Frame.sizeMultiplier), Constants.tileSize, Constants.tileSize, null);
			}

			// drawing projectiles
			for (Projectile projectileI : projectileList) {
				g.drawImage(RotateImage.rotateImage(tileset_projectiles[projectileI.projectileID], projectileI.direction, Constants.projectileImageSize), (int) Math.round(projectileI.x * Frame.sizeMultiplier), (int) Math.round(projectileI.y * Frame.sizeMultiplier), Constants.projectileSize, Constants.projectileSize, null);
			}
			
			// drawing particles
			for (Particles particlesI : particles) {
				g.drawImage(tileset_particles.get(particlesI.particleID).get(particlesI.time / Constants.particleDelay), particlesI.x, particlesI.y, particlesI.size, particlesI.size, null);
			}

			// drawing the tower being built
			if (drawMouse && mouse.y < Constants.levelHeight * Constants.tileSize) {
				if (worldInt[1][(mouse.y + Constants.tileSize) / Constants.tileSize - 1][(mouse.x + Constants.tileSize) / Constants.tileSize - 1] != 0 || Constants.towerPrices[mouseImage] > money) {
					g.setColor(new Color(255, 0, 0, 100));
					g.fillOval(Math.round((mouse.x + Constants.tileSize) / Constants.tileSize) * Constants.tileSize - ((Constants.towerDiameters[mouseImage] / 2) + (Constants.tileSize / 2)), Math.round((mouse.y + Constants.tileSize) / Constants.tileSize) * Constants.tileSize - ((Constants.towerDiameters[mouseImage] / 2) + (Constants.tileSize / 2)), Constants.towerDiameters[mouseImage], Constants.towerDiameters[mouseImage]);
					g.setColor(Color.red);
					g.drawOval(Math.round((mouse.x + Constants.tileSize) / Constants.tileSize) * Constants.tileSize - ((Constants.towerDiameters[mouseImage] / 2) + (Constants.tileSize / 2)), Math.round((mouse.y + Constants.tileSize) / Constants.tileSize) * Constants.tileSize - ((Constants.towerDiameters[mouseImage] / 2) + (Constants.tileSize / 2)), Constants.towerDiameters[mouseImage], Constants.towerDiameters[mouseImage]);

					ableToBePlaced = false;	
				} else {
					g.setColor(new Color(0, 255, 0, 100));
					g.fillOval(Math.round((mouse.x + Constants.tileSize) / Constants.tileSize) * Constants.tileSize - ((Constants.towerDiameters[mouseImage] / 2) + (Constants.tileSize / 2)), Math.round((mouse.y + Constants.tileSize) / Constants.tileSize) * Constants.tileSize - ((Constants.towerDiameters[mouseImage] / 2) + (Constants.tileSize / 2)), Constants.towerDiameters[mouseImage], Constants.towerDiameters[mouseImage]);
					g.setColor(Color.green);
					g.drawOval(Math.round((mouse.x + Constants.tileSize) / Constants.tileSize) * Constants.tileSize - ((Constants.towerDiameters[mouseImage] / 2) + (Constants.tileSize / 2)), Math.round((mouse.y + Constants.tileSize) / Constants.tileSize) * Constants.tileSize - ((Constants.towerDiameters[mouseImage] / 2) + (Constants.tileSize / 2)), Constants.towerDiameters[mouseImage], Constants.towerDiameters[mouseImage]);
					g.drawImage(tileset_towers[mouseImage], Math.round((mouse.x + Constants.tileSize) / Constants.tileSize) * Constants.tileSize - Constants.tileSize, Math.round((mouse.y + Constants.tileSize) / Constants.tileSize) * Constants.tileSize - Constants.tileSize, Constants.tileSize, Constants.tileSize, null);

					ableToBePlaced = true;
				}

				g.drawImage(RotateImage.rotateImage(tileset_towers[mouseImage], 270, Constants.imageSize), Math.round((mouse.x + Constants.tileSize) / Constants.tileSize) * Constants.tileSize - Constants.tileSize, Math.round((mouse.y + Constants.tileSize) / Constants.tileSize) * Constants.tileSize - Constants.tileSize, Constants.tileSize, Constants.tileSize, null);
			} else {
				ableToBePlaced = false;
			}

			//setting background
			g.setColor(new Color(68, 68, 68));
			g.fillRect(0, Constants.levelHeight * Constants.tileSize, Frame.panelSize.width, (Frame.panelSize.height - Constants.levelHeight) * Constants.tileSize);

			// drawing money and lives
			g.drawImage(tileset_icons[0], Constants.moneyAndLivesX, Constants.moneyY, Constants.moneyAndLivesSize, Constants.moneyAndLivesSize, null);
			g.drawImage(tileset_icons[1], Constants.moneyAndLivesX, Constants.livesY, Constants.moneyAndLivesSize, Constants.moneyAndLivesSize, null);

			g.setColor(Color.white);
			g.setFont(Constants.F_moneyLives);
			g.drawString("" + money, Constants.moneyLivesStringX, Constants.moneyY + Constants.moneyAndLivesSize);
			g.drawString("" + lives, Constants.moneyLivesStringX, Constants.livesY + Constants.moneyAndLivesSize);

			// drawing store buttons
			int shopHighlighted = -1;
			g.setFont(Constants.F_costText);

			for (int i = 0; i < shopButtons.length; i++) {
				if (!isPaused && new Rectangle(Constants.shopFirstButtonX + (i * (Constants.shopButtonSize + Constants.shopButtonSpacing)), ((Frame.panelSize.height - (Constants.tileSize * Constants.levelHeight) - Constants.shopButtonSize) / 2) + (Constants.tileSize * Constants.levelHeight), Constants.shopButtonSize, Constants.shopButtonSize).contains(mouse)) {
					g.drawImage(tileset_icons[3], Constants.shopFirstButtonX + (i * (Constants.shopButtonSize + Constants.shopButtonSpacing)), ((Frame.panelSize.height - (Constants.tileSize * Constants.levelHeight) - Constants.shopButtonSize) / 2) + (Constants.tileSize * Constants.levelHeight), Constants.shopButtonSize, Constants.shopButtonSize, null);
					shopHighlighted = i;
				} else {
					g.drawImage(tileset_icons[2], Constants.shopFirstButtonX + (i * (Constants.shopButtonSize + Constants.shopButtonSpacing)), ((Frame.panelSize.height - (Constants.tileSize * Constants.levelHeight) - Constants.shopButtonSize) / 2) + (Constants.tileSize * Constants.levelHeight), Constants.shopButtonSize, Constants.shopButtonSize, null);
				}
				if (Constants.shopButtonsActivated[i]) {
					g.drawImage(RotateImage.rotateImage(tileset_towers[i], 270, Constants.imageSize), Constants.shopFirstButtonX + (i * (Constants.shopButtonSize + Constants.shopButtonSpacing)) + ((Constants.shopButtonSize - Constants.tileSize) / 2), ((Frame.panelSize.height - (Constants.tileSize * Constants.levelHeight) - Constants.shopButtonSize) / 2) + (Constants.tileSize * Constants.levelHeight) + ((Constants.shopButtonSize - Constants.tileSize) / 2), Constants.tileSize, Constants.tileSize, null);
					g.drawString("" + Constants.towerPrices[i], Constants.shopFirstButtonX + (i * (Constants.shopButtonSize + Constants.shopButtonSpacing)) + Constants.costSpacingX, ((Frame.panelSize.height - (Constants.tileSize * Constants.levelHeight) - Constants.shopButtonSize) / 2) + (Constants.tileSize * Constants.levelHeight) + Constants.costSpacingY);
				}
			}

			// drawing play, fast-forward and pause buttons
			if (!isPaused && new Rectangle(Constants.playPauseX, Constants.pauseY, Constants.playPauseWidth, Constants.playPauseHeight).contains(mouse)) {
				g.drawImage(tileset_icons[5], Constants.playPauseX, Constants.pauseY, Constants.playPauseWidth, Constants.playPauseHeight, null);
			} else {
				g.drawImage(tileset_icons[4], Constants.playPauseX, Constants.pauseY, Constants.playPauseWidth, Constants.playPauseHeight, null);
			}
			if (inLevel) {
				if (!isPaused && new Rectangle(Constants.playPauseX, Constants.playY, Constants.playPauseWidth, Constants.playPauseHeight).contains(mouse)) {
					g.drawImage(tileset_icons[9], Constants.playPauseX, Constants.playY, Constants.playPauseWidth, Constants.playPauseHeight, null);
				} else {
					if (waitTime == Constants.originalWaitTime) {
						g.drawImage(tileset_icons[8], Constants.playPauseX, Constants.playY, Constants.playPauseWidth, Constants.playPauseHeight, null);
					} else if (waitTime == Constants.spedUpWaitTime) {
						g.drawImage(tileset_icons[10], Constants.playPauseX, Constants.playY, Constants.playPauseWidth, Constants.playPauseHeight, null);
					}
				}
			} else {
				if (!isPaused && new Rectangle(Constants.playPauseX, Constants.playY, Constants.playPauseWidth, Constants.playPauseHeight).contains(mouse)) {
					g.drawImage(tileset_icons[7], Constants.playPauseX, Constants.playY, Constants.playPauseWidth, Constants.playPauseHeight, null);
				} else {
					g.drawImage(tileset_icons[6], Constants.playPauseX, Constants.playY, Constants.playPauseWidth, Constants.playPauseHeight, null);
				}		
			}

			// drawing help text
			g.setFont(Constants.F_helpText);
			g.setColor(Color.white);
			
			ArrayList<String> linesOfHelp = Strings.getCutUpStrings(Strings.getString(shopHighlighted), Constants.maxHelpLength, g.getFontMetrics());

			if (linesOfHelp.size() > 4) {
				System.err.println("Too many lines of help text");
			}

			for (int i = 0; i < linesOfHelp.size(); i++) {
				g.drawString(linesOfHelp.get(i), Constants.helpX, Constants.helpY + (Constants.helpSpacing * i));
			}

			// draw paused menus
			if (gameOver) {
				// black out normal game
				g.setColor(new Color(0, 0, 0, 220));
				g.fillRect(0, 0, Frame.panelSize.width, Frame.panelSize.height);

				// draw buttons
				g.setColor(Color.white);
				g.setFont(Constants.F_menu);

				for (int i = 0; i < Constants.gameOverButtonAmount; i++) {
					if (new Rectangle((Frame.panelSize.width - Constants.menuButtonWidth) / 2, Constants.initialMenuY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight).contains(mouse) && i > 0) {
						g.drawImage(tileset_icons[21], (Frame.panelSize.width - Constants.menuButtonWidth) / 2, Constants.initialMenuY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight, null);
					} else {
						g.drawImage(tileset_icons[20], (Frame.panelSize.width - Constants.menuButtonWidth) / 2, Constants.initialMenuY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight, null);
					}

					String text = Strings.gameOverStrings[i];

					int textWidth = 0;

					for (int j = 0; j < text.length(); j++) {
						textWidth += g.getFontMetrics().charWidth(text.charAt(j));
					}

					int textHeight = g.getFontMetrics().getHeight() - (Constants.menuButtonHeight / 5);

					g.drawString(text, ((Frame.panelSize.width - Constants.menuButtonWidth) / 2) + ((Constants.menuButtonWidth - textWidth) / 2), Constants.initialMenuY + (Constants.initialMenuY * i * 2) + textHeight);
				}
			} else if (isPaused) {
				switch (pausedID) {
				
					case 0:
						// TITLE MENU
						// draw background image
						g.drawImage(tileset_icons[23], 0, 0, Frame.panelSize.width, Frame.panelSize.height, null);
						
						// draw version number
						g.setColor(Color.white);
						g.setFont(Constants.F_helpText);
						
						g.drawString(Strings.versionTextString + Frame.VERSION, Constants.versionDistanceFromEdge, Frame.panelSize.height - Constants.versionDistanceFromEdge);
						g.drawString(Strings.authorString, Frame.panelSize.width - Constants.versionDistanceFromEdge - Strings.getStringWidth(Strings.authorString, g), Frame.panelSize.height - Constants.versionDistanceFromEdge);
						
						// draw title block
						g.setFont(Constants.F_title);
						
						g.drawImage(tileset_icons[20], Constants.titleX - Constants.menuButtonWidth, Constants.intitalTitleY - (Constants.menuButtonHeight * 3), Constants.menuButtonWidth * 2, Constants.menuButtonHeight * 2, null);
						
						int titleTextWidth = Strings.getStringWidth(Strings.titleStrings[0], g);
						int titleTextHeight = g.getFontMetrics().getHeight() - ((Constants.menuButtonHeight * 2) / 5);
						g.drawString(Strings.titleStrings[0], (Constants.titleX - Constants.menuButtonWidth) + (((Constants.menuButtonWidth * 2) - titleTextWidth) / 2), (Constants.intitalTitleY - (Constants.menuButtonHeight * 3)) + titleTextHeight);
						
						// draw buttons
						g.setFont(Constants.F_menu);
						
						for (int i = 0; i < Constants.titleButtonAmount; i++) {
							if (new Rectangle(Constants.titleX, Constants.intitalTitleY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight).contains(mouse)) {
								g.drawImage(tileset_icons[21], Constants.titleX, Constants.intitalTitleY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight, null);
							} else {
								g.drawImage(tileset_icons[20], Constants.titleX, Constants.intitalTitleY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight, null);
							}
		
							String text = Strings.titleStrings[i + 1];
							int textWidth = 0;
		
							for (int j = 0; j < text.length(); j++) {
								textWidth += g.getFontMetrics().charWidth(text.charAt(j));
							}
		
							int textHeight = g.getFontMetrics().getHeight() - (Constants.menuButtonHeight / 5);
		
							g.drawString(text, Constants.titleX + ((Constants.menuButtonWidth - textWidth) / 2), Constants.intitalTitleY + (Constants.initialMenuY * i * 2) + textHeight);
						}
						
						break;
					
					case 10:
						// MAIN PAUSED MENU
						// black out normal game
						g.setColor(new Color(0, 0, 0, 220));
						g.fillRect(0, 0, Frame.panelSize.width, Frame.panelSize.height);
		
						// draw buttons
						g.setColor(Color.white);
						g.setFont(Constants.F_menu);
		
						for (int i = 0; i < Constants.menuButtonAmount; i++) {
							if (new Rectangle((Frame.panelSize.width - Constants.menuButtonWidth) / 2, Constants.initialMenuY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight).contains(mouse)) {
								g.drawImage(tileset_icons[21], (Frame.panelSize.width - Constants.menuButtonWidth) / 2, Constants.initialMenuY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight, null);
							} else {
								g.drawImage(tileset_icons[20], (Frame.panelSize.width - Constants.menuButtonWidth) / 2, Constants.initialMenuY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight, null);
							}
		
							String text = Strings.menuStrings[i];
							int textWidth = 0;
		
							for (int j = 0; j < text.length(); j++) {
								textWidth += g.getFontMetrics().charWidth(text.charAt(j));
							}
		
							int textHeight = g.getFontMetrics().getHeight() - (Constants.menuButtonHeight / 5);
		
							g.drawString(text, ((Frame.panelSize.width - Constants.menuButtonWidth) / 2) + ((Constants.menuButtonWidth - textWidth) / 2), Constants.initialMenuY + (Constants.initialMenuY * i * 2) + textHeight);
						}
						
						break;
						
					case 11:
						// MAIN OPTIONS MENU
						// black out normal game
						g.setColor(new Color(0, 0, 0, 220));
						g.fillRect(0, 0, Frame.panelSize.width, Frame.panelSize.height);
		
						// draw buttons
						g.setColor(Color.white);
						g.setFont(Constants.F_menu);
		
						for (int i = 0; i < Constants.optionButtonAmount; i++) {
							if (new Rectangle((Frame.panelSize.width - Constants.menuButtonWidth) / 2, Constants.initialMenuY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight).contains(mouse)) {
								g.drawImage(tileset_icons[21], (Frame.panelSize.width - Constants.menuButtonWidth) / 2, Constants.initialMenuY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight, null);
							} else {
								g.drawImage(tileset_icons[20], (Frame.panelSize.width - Constants.menuButtonWidth) / 2, Constants.initialMenuY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight, null);
							}
		
							String text = Strings.optionsStrings[i];
		
							if (i == 0) {
								text = text + ((Frame.fullscreen) ? "Enabled" : "Disabled");
							}
		
							int textWidth = 0;
		
							for (int j = 0; j < text.length(); j++) {
								textWidth += g.getFontMetrics().charWidth(text.charAt(j));
							}
		
							int textHeight = g.getFontMetrics().getHeight() - (Constants.menuButtonHeight / 5);
		
							g.drawString(text, ((Frame.panelSize.width - Constants.menuButtonWidth) / 2) + ((Constants.menuButtonWidth - textWidth) / 2), Constants.initialMenuY + (Constants.initialMenuY * i * 2) + textHeight);
						}
						
						break;
				}
			}
		}
	}
	
	public void onTick() {
		for (int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).onTick()) {
				lives -= Constants.enemyDamage[enemyList.get(i).enemyID];
				enemyList.remove(i);
				i--;
			}
		}
		
		for (Tower towerI : towers) {
            towerI.onTick();
		}
		
		for (int i = 0; i < projectileList.size(); i++) {
			if (projectileList.get(i).onTick()) {
				Screen.projectileList.remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).onTick()) {
				Screen.particles.remove(i);
				i--;
			}
		}
	}
	
	@Override
	public void run() {
		int counter = 0;
		
		while (true) {
			try {
				Thread.sleep(1);
				
				if (lives <= 0) {
					gameOver = true;
				}
				
				if (!isPaused && !gameOver && counter >= waitTime) {
					onTick();
					counter = 0;
					
					if (inLevel) {
						if (timeUntilNextMob <= 0 && !allEnemiesSpawned) {
							if (enemies[level - 1][enemyCount].enemyID != 0) {
								enemyList.add(enemies[level - 1][enemyCount]);
							} else {
								allEnemiesSpawned = true;
							}
							
							enemyCount++;
							timeUntilNextMob = Constants.timeBetweenMobs;
						} else if (allEnemiesSpawned && enemyList.size() == 0) {
							level++;
							projectileList = new ArrayList<Projectile>();
							enemyCount = 0;
							allEnemiesSpawned = false;
							timeUntilNextMob = 0;
							inLevel = false;
						} else {
							timeUntilNextMob--;
						}
						
						
					}
				} else if (!isPaused) {
					counter++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
