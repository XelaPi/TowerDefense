/**
 * 
 * Provides a class that all enemies are instances of
 * 
 * @author Alex
 *
 */
public class Enemy {

	public double x;
	public double y;

	public int previousDirection;
	public int direction;

	public int blockX;
	public int blockY;
	
	public int offsetX;
	public int offsetY;
	
	public int enemyID;
	
	public boolean isFirst;
	
	public int counter;
	
	public double health;
	
	public int distanceFromStart;
	
	public Enemy(int x, int y, int enemyID) {
		this.enemyID = enemyID;
		
		if (enemyID > 0) {
			this.x = x;
			this.y = y;
			
			this.blockX = (int) Math.floor(this.x / Constants.originalTileSize);
			this.blockY = (int) Math.floor(this.y / Constants.originalTileSize);
			
			this.offsetX = 0;
			this.offsetY = 0;
			
			this.direction = 0;
			
			this.isFirst = true;	
			
			this.counter = 0;
			this.distanceFromStart = 0;
			
			this.health = Constants.enemyHealth[enemyID];
		}
	}

	public boolean onTick() {
		
		if (counter >= Constants.enemySpeed[enemyID]) {
			if (!isFirst && x % Constants.originalTileSize == 0 && y % Constants.originalTileSize == 0) {
				previousDirection = direction;

				if (direction == right && x % Constants.originalTileSize == 0) {
					blockX++;
				} else if (direction == up && y % Constants.originalTileSize == 0) {
					blockY--; 
				} else if (direction == left && x % Constants.originalTileSize == 0) {
					blockX--;
				} else if (direction == down && y % Constants.originalTileSize == 0) {
					blockY++;
				}

				if (previousDirection != left) {
					try {
						if (world[blockY][blockX + 1] == 1) {
							direction = right;
						}
					} catch (Exception e) {
                        System.err.print(e);
                    }
				}
				if (previousDirection != down) {
					try {
						if (world[blockY - 1][blockX] == 1) {
							direction = up;
						}
					} catch (Exception e) {
                        System.err.print(e);
                    }
				}
				if (previousDirection != right) {
					try {
						if (world[blockY][blockX - 1] == 1) {
							direction = left;
						}
					} catch (Exception e) {
                        System.err.print(e);
                    }
				}
				if (previousDirection != up) {
					try {
						if (world[blockY + 1][blockX] == 1) {
							direction = down;
						}
					} catch (Exception e) {
                        System.err.print(e);
                    }
				}
			} else {
				isFirst = false;
			}

			x = x + (Math.cos(Math.toRadians(direction)));
			y = y + (Math.sin(Math.toRadians(direction)));
			
			distanceFromStart++;
			
			if ((int) Math.round(x * Frame.sizeMultiplier) > Frame.panelSize.width) {
				return true;
			}
			
			counter = 0;
		} else {
			counter++;
		}
		
		return false;
	}
	
	public static int right = 0;
	public static int up = 270;
	public static int left = 180;
	public static int down = 90;
	
	public static int[][] world;
}
