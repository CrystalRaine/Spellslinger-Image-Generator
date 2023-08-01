package imageElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public interface CardElement {
	
	void draw(int extendedBorderSizex, int extendedBorderSizey, Graphics g, int width, int height, Color[][] colors);
	
}
