package imageElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BasicBorder implements CardElement {
	
	int borderThickness;
	Color color;

	public BasicBorder(int thickness, Color c) {
		borderThickness = thickness;
		this.color = c;
	}

	@Override
	public void draw(int x, int y, Graphics g, int width, int height, Color[][] ig) {
		g.setColor(color);
		for(int i = 0; i < borderThickness; i++)
		g.drawRect(i + x, i + y, width - 2*i, height - 2*i);
	}
}
