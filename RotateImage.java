import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class RotateImage {
	public static Image rotateImage(Image image, int angle, int size) {
		BufferedImage rotatedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = rotatedImage.createGraphics();
		
		g.rotate(Math.toRadians(angle), size / 2, size / 2);
		g.drawImage(image, 0, 0, null);
		g.dispose();
		
		return rotatedImage;
	}
}
