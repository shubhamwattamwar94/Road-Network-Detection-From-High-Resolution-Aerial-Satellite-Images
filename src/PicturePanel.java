import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;
import javax.swing.JPanel;
/**
 *
 * @author Shubham
 */
public class PicturePanel extends JPanel {
BufferedImage image;

	public PicturePanel() {
		setSize(800,600);
		setVisible(true);
		setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		if (image!=null)
			graphics.drawImage(image, 0, 0, null);
	}

	public void drawPicture(BufferedImage image) {
		this.image = image;
		repaint();
	}

	public BufferedImage getImage(){
		return image;
	}
}
