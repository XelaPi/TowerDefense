import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * 
 * Provides a class that all projectiles are instances of
 * 
 * @author Alex
 *
 */
public class Projectile {
	public double x;
	public double y;
	
	public int projectileID;
	
	public int direction;

    public double damage;
	
	public Projectile(int projectileID, int direction, double x, double y) {
		this.projectileID = projectileID;
		this.direction = direction;
		
		this.x = x;
		this.y = y;
		
		this.damage = Constants.projectileDamage[projectileID];
	}
	
	public boolean onTick() {
		for (int i = 0; i < Screen.enemyList.size(); i++) {
			if (new Rectangle2D.Double((x * Frame.sizeMultiplier), (y * Frame.sizeMultiplier), Constants.projectileSize, Constants.projectileSize).intersects(new Rectangle2D.Double((Screen.enemyList.get(i).x * Frame.sizeMultiplier) + Screen.enemyList.get(i).offsetX, (Screen.enemyList.get(i).y * Frame.sizeMultiplier) + Screen.enemyList.get(i).offsetY,  Constants.enemySize[Screen.enemyList.get(i).enemyID], Constants.enemySize[Screen.enemyList.get(i).enemyID]))) {
				if (Constants.projectileExplosionDiameter[projectileID] != 0) {
					Screen.particles.add(new Particles((int) Math.round((x - (Constants.projectileExplosionDiameter[projectileID] / 2)) * Frame.sizeMultiplier) + (Constants.projectileSize / 2 ), (int) Math.round((y - (Constants.projectileExplosionDiameter[projectileID] / 2)) * Frame.sizeMultiplier) + (Constants.projectileSize / 2 ), 0, Constants.explosionFrames * Constants.particleDelay, (int) Math.round(Constants.projectileExplosionDiameter[projectileID] * Frame.sizeMultiplier)));
					
					for (int j = 0; j < Screen.enemyList.size(); j++) {
						if (new Ellipse2D.Double(((x - (Constants.projectileExplosionDiameter[projectileID] / 2)) * Frame.sizeMultiplier) + (Constants.projectileSize / 2), ((y - (Constants.projectileExplosionDiameter[projectileID] / 2)) * Frame.sizeMultiplier) + (Constants.projectileSize / 2), Constants.projectileExplosionDiameter[projectileID] * Frame.sizeMultiplier, Constants.projectileExplosionDiameter[projectileID] * Frame.sizeMultiplier).intersects(new Rectangle2D.Double((Screen.enemyList.get(j).x * Frame.sizeMultiplier) + Screen.enemyList.get(j).offsetX, (Screen.enemyList.get(j).y * Frame.sizeMultiplier) + Screen.enemyList.get(j).offsetY, Constants.enemySize[Screen.enemyList.get(j).enemyID], Constants.enemySize[Screen.enemyList.get(j).enemyID]))) {
							if (i == j && damage > Constants.enemyDefense[Screen.enemyList.get(j).enemyID]) {
								Screen.enemyList.get(j).health -= damage - Constants.enemyDefense[Screen.enemyList.get(j).enemyID];
							} else if (damage / 2 > Constants.enemyDefense[Screen.enemyList.get(j).enemyID]) {
								Screen.enemyList.get(j).health -= (damage / 2) - Constants.enemyDefense[Screen.enemyList.get(j).enemyID];
							}
							
							if (Screen.enemyList.get(j).health <= 0) {
								Screen.money += Constants.enemyWorth[Screen.enemyList.get(j).enemyID];
								
								Enemy enemy = Screen.enemyList.get(j);
								
								Screen.enemyList.remove(j);		
								j--;
								i--;
								
								if (enemy.enemyID == 5) {
									for (int k = 0; k < 2; k++) {
										for (int l = 0; l < 2; l++) {
											Screen.enemyList.add(new Enemy((int) Math.round(enemy.x), (int) Math.round(enemy.y), 6));
											
											Screen.enemyList.get(Screen.enemyList.size() - 1).distanceFromStart = enemy.distanceFromStart;
											Screen.enemyList.get(Screen.enemyList.size() - 1).health = Constants.enemyHealth[enemy.enemyID + 1];
											Screen.enemyList.get(Screen.enemyList.size() - 1).direction = enemy.direction;
											Screen.enemyList.get(Screen.enemyList.size() - 1).counter = enemy.counter;
											Screen.enemyList.get(Screen.enemyList.size() - 1).isFirst = false;
											
											Screen.enemyList.get(Screen.enemyList.size() - 1).blockX = enemy.blockX;
											Screen.enemyList.get(Screen.enemyList.size() - 1).blockY = enemy.blockY;
											
											Screen.enemyList.get(Screen.enemyList.size() - 1).offsetX = k * (Constants.originalTileSize / 2);
											Screen.enemyList.get(Screen.enemyList.size() - 1).offsetY = l * (Constants.originalTileSize / 2);
										}
									}
								}
							}
						}
					}
				} else {
					if (damage > Constants.enemyDefense[Screen.enemyList.get(i).enemyID]) {
						Screen.enemyList.get(i).health -= damage - Constants.enemyDefense[Screen.enemyList.get(i).enemyID];
					}
					
					if (Screen.enemyList.get(i).health <= 0) {
						Screen.money += Constants.enemyWorth[Screen.enemyList.get(i).enemyID];
						
						Enemy enemy = Screen.enemyList.get(i);
						
						Screen.enemyList.remove(i);		
						
						if (enemy.enemyID == 5) {
							for (int j = 0; j < 2; j++) {
								for (int k = 0; k < 2; k++) {
									Screen.enemyList.add(new Enemy((int) Math.round(enemy.x), (int) Math.round(enemy.y), 6));
									
									Screen.enemyList.get(Screen.enemyList.size() - 1).distanceFromStart = enemy.distanceFromStart;
									Screen.enemyList.get(Screen.enemyList.size() - 1).health = Constants.enemyHealth[enemy.enemyID + 1];
									Screen.enemyList.get(Screen.enemyList.size() - 1).direction = enemy.direction;
									Screen.enemyList.get(Screen.enemyList.size() - 1).counter = enemy.counter;
									
									Screen.enemyList.get(Screen.enemyList.size() - 1).blockX = enemy.blockX;
									Screen.enemyList.get(Screen.enemyList.size() - 1).blockY = enemy.blockY;
									
									Screen.enemyList.get(Screen.enemyList.size() - 1).offsetX = j * (Constants.originalTileSize / 2);
									Screen.enemyList.get(Screen.enemyList.size() - 1).offsetY = k * (Constants.originalTileSize / 2);
								}
							}
						}
					}
				}
				
				return true;
			}
		}
		
		x = x + (Math.cos(Math.toRadians(direction)) * Constants.projectileSpeed[projectileID]);
		y = y + (Math.sin(Math.toRadians(direction)) * Constants.projectileSpeed[projectileID]);
		
		return x < 0 || x > Frame.panelSize.width || y < 0 || y > Constants.levelHeight * Constants.tileSize;
	}
}
