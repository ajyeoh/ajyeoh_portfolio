import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * ImageComponent.java
 * 
 * Using similar code from Lab 9
 * 
 * @author adelyn.yeoh
 * 
 *         CS 201 - Final Project
 */
public class ImageComponent extends JComponent {

	private BufferedImage img;
	private int scale;

	/**
	 * Constructor reads image file
	 * 
	 * @param imagePath
	 *            String
	 */
	public ImageComponent(String imagePath) {
		// Set scaled
		scale = 1;
		try {
			// Try to read file
			img = ImageIO.read(new File(imagePath));

		} // catch error if thrown
		catch (IOException e) {
			System.out.println("file IO exception");
			e.printStackTrace();

		}
	}

	/**
	 * Override paint method
	 * 
	 * @param g
	 *            Graphics
	 */
	public void paint(Graphics g) {

		// draws the image: gets the image and draws onto component scaled
		g.drawImage(img, 10, 10, scale * img.getWidth(),
				scale * img.getHeight(), 0, 0, img.getWidth(), img.getHeight(),
				null);
	}

	/**
	 * Get preferred size
	 * 
	 * @return Dimension
	 */
	public Dimension getPreferredSize() {

		// return Dimension according to image size
		if (img == null) {
			// if no image return this dimension
			return new Dimension(100, 100);
		} else {
			return new Dimension(scale * img.getWidth(null), scale
					* img.getHeight(null));
		}
	}

}
