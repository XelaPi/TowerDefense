import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * 
 * Starter class for game. Contains method main, and serves as the wrapper for the game.
 * 
 * @author Alex
 *
 */
public class Frame extends JFrame {
	
	public static double sizeMultiplier = 1.0;
	
	public static Dimension panelSize = new Dimension((int) Math.round(640 * sizeMultiplier), (int) Math.round(480 * sizeMultiplier));
	
	public static String iconDirectory = "resources/images/other/";
	public static Image image = new ImageIcon(Frame.class.getResource(iconDirectory + "icon.png")).getImage();
	
	public static String title = "Tower Defense";
	public static String optionName = "options";
	
	public static boolean fullscreen = false;
	
	public static Frame frame;
	public static Screen screen;
	
	public static final String VERSION = "Beta 1.12";
	public static String newVersion;
	public static boolean isOldVersion = false;
	public static boolean isBetaVersion = false;

    public Frame() {
		
		fullscreen = Load.loadOptions(optionName);
		newVersion = Load.oldVersion();
		
		isBetaVersion = VERSION.contains("Beta");
		isOldVersion = !newVersion.equals(VERSION);
		
		System.out.println("Is Beta Version: " + isBetaVersion);
		System.out.println("Is Old Version: " + isOldVersion);
		
		if (fullscreen || java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() < (int) Math.round(480 * sizeMultiplier)) {
			sizeMultiplier = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 480;
			panelSize = new Dimension((int) Math.round(640 * sizeMultiplier), (int) Math.round(480 * sizeMultiplier));
			
			fullscreen = true;
		} else {
			sizeMultiplier = 1.0;
			panelSize = new Dimension((int) Math.round(640 * sizeMultiplier), (int) Math.round(480 * sizeMultiplier));
			
			fullscreen = false;
		}
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle(title);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.getContentPane().setBackground(Color.black);
		this.setBackground(Color.black);
		this.setIconImage(image);
		
		if (fullscreen) {
			this.setUndecorated(true);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
	}
	
	public static void close() {
		WindowEvent wEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
		java.awt.Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wEvent);
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				frame = new Frame();
				
				screen = new Screen();
				screen.setAlignmentX(Component.CENTER_ALIGNMENT);
				screen.setBorder(new EmptyBorder(0, 0, 0, 0));
				
				frame.add(screen);
				frame.pack();
				if (!fullscreen) {
					frame.setSize(frame.getWidth() - (frame.getContentPane().getWidth() - panelSize.width), frame.getHeight() - (frame.getContentPane().getHeight() - panelSize.height));
				}
				
				frame.setLocationRelativeTo(frame.getRootPane());
				frame.setVisible(true);
			}
		});
	}
}
