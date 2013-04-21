import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 
 * Handles mouse input from the user
 * 
 * @author Alex
 *
 */
public class Mouse implements MouseListener, MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent m) {
		
	}

	@Override
	public void mouseMoved(MouseEvent m) {
		Screen.mouse = m.getPoint();
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		
	}

	@Override
	public void mouseEntered(MouseEvent m) {
		
	}

	@Override
	public void mouseExited(MouseEvent m) {
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		if (m.getButton() == MouseEvent.BUTTON1) {
			if (Frame.isBetaVersion || Frame.isOldVersion) {
				if (new Rectangle((Frame.panelSize.width - Constants.menuButtonWidth) / 2, ((Frame.panelSize.width - Constants.menuStringWidth) / 2) + Constants.menuStringHeight + (Constants.initialMenuY / 2), Constants.menuButtonWidth, Constants.menuButtonHeight).contains(Screen.mouse)) {
					Frame.isBetaVersion = false;
					Frame.isOldVersion = false;
				}
			} else if (Screen.gameOver) {
				if (new Rectangle((Frame.panelSize.width - Constants.menuButtonWidth) / 2, Constants.initialMenuY + (Constants.initialMenuY * 2), Constants.menuButtonWidth, Constants.menuButtonHeight).contains(Screen.mouse)) {
					System.out.println("");
					System.out.println("--------------------------------------------------------------------------------");
					Screen.initializeVariables(System.nanoTime());
				}
			} else if (Screen.isPaused) {
				switch (Screen.pausedID) {
					case 0:
						// TITLE MENU
						// detects title menu buttons
						for (int i = 0; i < Constants.titleButtonAmount; i++) {
							if (new Rectangle(Constants.titleX, Constants.intitalTitleY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight).contains(Screen.mouse)) {
								switch (i) {
									case 0:
										Screen.isPaused = false;
										break;
									
									case 1:
										Frame.close();
										break;
								}
							}
						}
						
						break;
					
					case 10:
						// MAIN MENU
						// detects pause menu buttons
						for (int i = 0; i < Constants.menuButtonAmount; i++) {
							if (new Rectangle((Frame.panelSize.width - Constants.menuButtonWidth) / 2, Constants.initialMenuY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight).contains(Screen.mouse)) {
								switch (i) {
									case 0:
										Screen.isPaused = false;
										break;
									
									case 1:
										Screen.isPaused = true;
										Screen.pausedID = 11;
										break;
									
									case 2:
										System.out.println("");
										System.out.println("--------------------------------------------------------------------------------");
										Screen.initializeVariables(System.nanoTime());
										break;
										
									case 3:
										Screen.pausedID = 0;
										break;
								}
							} 
						}
						
						break;
						
					case 11:
						// MAIN OPTIONS MENU
						// detects pause menu buttons
						for (int i = 0; i < Constants.menuButtonAmount; i++) {
							if (new Rectangle((Frame.panelSize.width - Constants.menuButtonWidth) / 2, Constants.initialMenuY + (Constants.initialMenuY * i * 2), Constants.menuButtonWidth, Constants.menuButtonHeight).contains(Screen.mouse)) {
								switch (i) {
									case 0:
										Load.writeOptions(Frame.optionName, !Frame.fullscreen);
										
										boolean isOldVersion = Frame.isOldVersion;
										
										Frame.frame.removeAll();
										Frame.frame.dispose();
										Frame.frame = new Frame();
										
										Frame.isOldVersion = isOldVersion;
										
										Frame.frame.add(Frame.screen);
										Frame.frame.pack();
										if (!Frame.fullscreen) {
											Frame.frame.setSize(Frame.frame.getWidth() - (Frame.frame.getContentPane().getWidth() - Frame.panelSize.width), Frame.frame.getHeight() - (Frame.frame.getContentPane().getHeight() - Frame.panelSize.height));
										}
										Constants.resetVariables();
										
										Frame.frame.setLocationRelativeTo(Frame.frame.getRootPane());
										Frame.frame.setVisible(true);
										
										break;
									
									case 1:
										Screen.pausedID = 10;
										break;
								}
							} 
						}
						break;
				}
			} else {
				// detects shop buttons being pressed
				for (int i = 0; i < Screen.shopButtons.length; i++) {
					if (Screen.shopButtons[i].activated) {
						if (new Rectangle(Constants.shopFirstButtonX + (i * (Constants.shopButtonSize + Constants.shopButtonSpacing)), ((Frame.panelSize.height - (Constants.tileSize * Constants.levelHeight) - Constants.shopButtonSize) / 2) + (Constants.tileSize * Constants.levelHeight), Constants.shopButtonSize, Constants.shopButtonSize).contains(Screen.mouse)) {
							Screen.drawMouse = true;
							Screen.mouseImage = Screen.shopButtons[i].shopID;
	
							break;
						} else {
							if (Screen.drawMouse && Screen.money >= Constants.towerPrices[Screen.mouseImage] && Screen.ableToBePlaced) {
								Screen.towers.add(new Tower((int) Math.round((Math.round((m.getPoint().x + Constants.tileSize) / Constants.tileSize) * Constants.tileSize - Constants.tileSize) / Frame.sizeMultiplier), (int) Math.round((Math.round((m.getPoint().y + Constants.tileSize) / Constants.tileSize) * Constants.tileSize - Constants.tileSize) / Frame.sizeMultiplier), Screen.mouseImage));
								Screen.worldInt[1][(Screen.mouse.y + Constants.tileSize) / Constants.tileSize - 1][(Screen.mouse.x + Constants.tileSize) / Constants.tileSize - 1] = 1;
								Screen.money -= Constants.towerPrices[Screen.mouseImage];
							}
							Screen.drawMouse = false;
						}
					}
				}
				
				// detects pause button being pressed
				if (new Rectangle(Constants.playPauseX, Constants.pauseY, Constants.playPauseWidth, Constants.playPauseHeight).contains(Screen.mouse)) {
					Screen.isPaused = true;
					Screen.pausedID = 10;
				}
				
				// detects play/fast forward button being pressed
				if (new Rectangle(Constants.playPauseX, Constants.playY, Constants.playPauseWidth, Constants.playPauseHeight).contains(Screen.mouse)) {
					if (Screen.inLevel) {
						if (Screen.waitTime == Constants.originalWaitTime) {
							Screen.waitTime = Constants.spedUpWaitTime;
						} else {
							Screen.waitTime = Constants.originalWaitTime;
						}
					} else {
						Screen.inLevel = true;
					}
				}
			}
		} else if (m.getButton() == MouseEvent.BUTTON3) {
			Screen.drawMouse = false;
		}
	}

	@Override
	public void mouseReleased(MouseEvent m) {
		
	}

}
