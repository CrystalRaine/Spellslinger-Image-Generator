package imageElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageElement implements CardElement {

	Image img = null;
	int x;
	int y;
	
	public ImageElement(String image, int x, int y) {
		this.x = x; 
		this.y = y;
		File resFolder = new File("res");
		List<String> files = Arrays.asList(resFolder.list());
		if(files.contains(image.replaceAll(" ", "_") + ".png")) {
			try {
				img = ImageIO.read(new File("res/" + image.replaceAll(" ", "_") + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public void draw(int x2, int y2, Graphics g, int width, int height, Color[][] colors) {
		if(img != null) {
			ImageObserver obs = null;
			g.drawImage(img, x + x2, y + y2, x2 + img.getWidth(obs) + x, y2 + img.getHeight(obs) + y, 0, 0, img.getWidth(obs), img.getHeight(obs), obs);
		}
	}

}
