package imageElements;

import java.awt.Color;
import java.awt.Graphics;

public class TransparentBoxElement implements CardElement {

	private Color color;
	private int width;
	private int height;
	private int x;
	private int y;
	private float bgWeight;
	
	public TransparentBoxElement(int x, int y, int width, int height, Color color, float bgWeight) {
		this.x = x; 
		this.y = y;
		this.height = height;
		this.width = width;
		this.color = color;
		this.bgWeight = bgWeight;
	}
	
	@Override
	public void draw(int x1, int y1, Graphics g, int width, int height, Color[][] colors) {
		for(int i = x; i < x + this.width; i++) {
			for(int j = y; j < y + this.height; j++) {
				
				color = color.darker();
				
				Color c = new Color((int)((color.getRed() + (bgWeight * colors[i][j].getRed())) / (1+bgWeight)), 
						(int)((color.getGreen() + (bgWeight * colors[i][j].getGreen()))/(1+bgWeight)),
						(int)((color.getBlue() + (bgWeight * colors[i][j].getBlue()))/(1+bgWeight)));
				
				g.setColor(c);
				colors[i][j] = c;
				g.drawRect(i + x1, j + y1, 1,	1);
			}
		}
	}

}
