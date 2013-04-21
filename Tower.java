import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * 
 * Provides a class that all towers are instances of
 * 
 * @author Alex
 *
 */
public class Tower {
	public int x;
	public int y;
	
	public int towerID;
	
	public int direction;
	
	public int firingCooldown;
	
	public Tower(int x, int y, int towerID) {
		this.x = x;
		this.y = y;
		
		this.towerID = towerID;
		this.direction = 270;
		
		this.firingCooldown = 0;
	}
	
	public void onTick() {
		int previousDistance = -1;
		int previousI = -1;
		
		for (int i = 0; i < Screen.enemyList.size(); i++) {
			if (new Ellipse2D.Double((x * Frame.sizeMultiplier) + (Constants.tileSize / 2) - (Constants.towerDiameters[this.towerID] / 2), (y * Frame.sizeMultiplier) + (Constants.tileSize / 2) - (Constants.towerDiameters[this.towerID] / 2), Constants.towerDiameters[towerID], Constants.towerDiameters[towerID]).intersects(new Rectangle2D.Double((Screen.enemyList.get(i).x * Frame.sizeMultiplier), (Screen.enemyList.get(i).y * Frame.sizeMultiplier), Constants.tileSize, Constants.tileSize))) {
				if (previousDistance < Screen.enemyList.get(i).distanceFromStart) {
					previousDistance = Screen.enemyList.get(i).distanceFromStart;
					previousI = i;
				}
			}
		}
		
		if (previousI >= 0) {
			direction = (int) Math.round(Math.toDegrees(Math.atan2(Screen.enemyList.get(previousI).y - y, Screen.enemyList.get(previousI).x - x)));

			if (firingCooldown <= 0) {
				Screen.projectileList.add(new Projectile(towerID, direction, ((x * Frame.sizeMultiplier) - (Constants.projectileSize / 2) + (Constants.tileSize / 2) + (Math.cos(Math.toRadians(direction)) * (Constants.towerBarrelLengths[towerID] - Constants.projectileSize))) / Frame.sizeMultiplier, ((y * Frame.sizeMultiplier) - (Constants.projectileSize / 2) + (Constants.tileSize / 2) + (Math.sin(Math.toRadians(direction)) * (Constants.towerBarrelLengths[towerID] - Constants.projectileSize))) / Frame.sizeMultiplier));
				firingCooldown = Constants.towerCooldown[towerID];
			}
		}
		
		while (direction < 0 || direction >= 360) {
			if (direction < 0) {
				direction += 360;
			} else {
				direction -= 360;
			}
		}
		
		firingCooldown--;
	}
}
