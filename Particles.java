
public class Particles {
	public int x;
	public int y;
	
	public int particleID;
	
	public int time;
	public int maxTime;
	
	public int size;
	
	public Particles(int x, int y, int particleID, int maxTime, int size) {
		this.x = x;
		this.y = y;
		
		this.particleID = particleID;
		
		this.time = 0;
		this.maxTime = maxTime;
		
		this.size = size;
	}
	
	public boolean onTick() {
		time++;
		
		return time >= maxTime;
	}
}
