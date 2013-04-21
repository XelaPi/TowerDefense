import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.Scanner;

/**
 * 
 * Handles file loading and saving
 * 
 * @author Alex
 *
 */
public class Load {
	
	public int[][][] loadLevel(String levelName) {
		int[][][] level = new int[2][Constants.levelHeight][Constants.levelWidth];
		
		try {		
			Scanner scanner = new Scanner(this.getClass().getResourceAsStream("resources/levels/" + levelName + ".lvl"));
			
			while (scanner.hasNext()) {
				for (int i = 0; i < level[0].length; i++) {
					for (int j = 0; j < level[0][i].length; j++) {
						level[0][i][j] = scanner.nextInt();
					}
				}

				for (int i = 0; i < level[1].length; i++) {
					for (int j = 0; j < level[1][i].length; j++) {
						level[1][i][j] = scanner.nextInt();
					}
				}
			}
			
			scanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return level;
	}
	
	public int[][] loadMobs(String mobSaveName) {
		int[][] level = new int[Constants.maxRounds][Constants.maxMobsPerRound];
		
		try {
			Scanner scanner = new Scanner(this.getClass().getResourceAsStream("resources/levels/" + mobSaveName + ".lvl"));
			
			int levelIterator = 0;
			int monsterIterator = 0;
			
			while (scanner.hasNext()) {
				String mobs = scanner.nextLine();
			
				while (mobs.length() > 0) {
					
					if (mobs.contains(" ")) {
						level[levelIterator][monsterIterator] = Integer.valueOf(mobs.substring(0, mobs.indexOf(" ")));
						mobs = mobs.substring(mobs.indexOf(" ") + 1, mobs.length());
						
					} else {
						level[levelIterator][monsterIterator] = Integer.valueOf(mobs);
						mobs = "";
					}
					
					monsterIterator++;
				}
				
				levelIterator++;
				monsterIterator = 0;
			}
			
			scanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return level;
	}
	
	public static boolean loadOptions(String optionName) {
		String path = javax.swing.filechooser.FileSystemView.getFileSystemView().getDefaultDirectory().toURI().toString().replace("%20%", " ") + "My Games/Tower Defense/" + optionName + ".txt";
		path = path.substring(6, path.length());
		
		File file = new File(path);
		
		boolean fullscreen = false;
		
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.getParentFile().createNewFile();
				
				FileWriter writer = new FileWriter(file);
				
				writer.write("false");
				
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				Scanner scanner = new Scanner(file);
				
				fullscreen = scanner.nextBoolean();
				
				scanner.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return fullscreen;
	}
	
	public static void writeOptions(String optionName, boolean fullscreen) {
		String path = javax.swing.filechooser.FileSystemView.getFileSystemView().getDefaultDirectory().toURI().toString().replace("%20%", " ") + "My Games/Tower Defense/" + optionName + ".txt";
		path = path.substring(6, path.length());
		
		File file = new File(path);
		
		try {
			FileWriter writer = new FileWriter(file);
			
			writer.write(String.valueOf(fullscreen));
			
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String oldVersion() {
		String isOld = "";
		
		try {
			URL file = new URL("http://www.piguy.net/towerdefense/version.txt");
			
			Scanner scanner = new Scanner(file.openStream());

			isOld = scanner.nextLine();
			
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isOld;
	}
}
