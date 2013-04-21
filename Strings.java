import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * 
 * Manages strings and string functions
 * 
 * @author Alex
 *
 */
public class Strings {

	public static final String oldVersionStringPart1 = "You are not running the most up-to-date version of Tower Defense. You are running version ";
	public static final String oldVersionStringPart2 = " of Tower Defense. You can get the most up-to-date version, version ";
	public static final String oldVersionStringPart3 = ", at http://www.piguy.net/towerdefense.";
	
	public static final String betaVersionStringPart = "You are running a beta build. If you are not the author of \"Tower Defense\", then you should probably not be using this version. Please use a stable version at http://www.piguy.net/towerdefense.";
	
	public static final String versionContinue = "Continue";
	public static final String versionTextString = "Tower Defense V ";
	
	public static final String authorString = "by XelaPi";
	
	// strings for events in game
	public static final String firstString = "This is tower defense: Buy towers at the shop with money collected from killing enemy tanks.";
	public static final String defaultStringPart1 = "Level ";
	public static final String defaultStringPart2 = " Passed.";
	
	// tower descriptions
	public static final String[] towerDescriptions = new String[] {
		"Basic Tower: Average range, average speed, but not very expensive.",
		"Fast Tower: Good range, very fast speed, but not a powerful shot.",
		"Heavy Tower: Good range, powerful shot, but a long reload time.",
		"Bomb Tower: Fired bomb explodes on impact and damages surrounding tanks",
		"",
		"",
		""
	};

	// menu button strings
	public static final String[] titleStrings = new String[] {
		"Tower Defense",
		"Play",
		"Exit"
	};
	
	public static final String[] menuStrings = new String[] {
		"Resume",
		"Options",
		"Restart",
		"Exit to Menu"
	};

	public static final String[] optionsStrings = new String[] {
		"Fullscreen: ",
		"Back"
	};
	
	public static final String[] gameOverStrings = new String[] {
		"Game Over",
		"Restart"
	};
		
	public static String getString(int shopHighlighted) {
		if (shopHighlighted >= 0) {
			return towerDescriptions[shopHighlighted];
		} else if (Screen.level == 1) {
			return firstString;
		} else {
			return defaultStringPart1 + (Screen.level - 1) + defaultStringPart2;
		}
	}
	
	public static ArrayList<String> getCutUpStrings(String string, int maxStringLength, FontMetrics fontMetrics) {
		int currentWidth = 0;
		int currentPositionInString = 0;
		ArrayList<String> linesOfHelp = new ArrayList<String>();
		
		while (currentPositionInString < string.length()) {
			currentWidth += fontMetrics.charWidth(string.charAt(currentPositionInString));
			
			if (currentWidth > maxStringLength) {
				linesOfHelp.add(string.substring(0, string.substring(0, currentPositionInString).lastIndexOf(" ")));

				string = string.substring(linesOfHelp.get(linesOfHelp.size() - 1).length() + 1, string.length());

				currentPositionInString = 0;
				currentWidth = 0;
			} else {
				currentPositionInString++;
			}
		}
		linesOfHelp.add(string);
		
		return linesOfHelp;
	}
	
	public static int getStringWidth(String string, Graphics g) {
		int stringWidth = 0;
		
		for (int i = 0; i < string.length(); i++) {
			stringWidth += g.getFontMetrics().charWidth(string.charAt(i));
		}
		
		return stringWidth;
	}
}
