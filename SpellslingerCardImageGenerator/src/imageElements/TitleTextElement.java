package imageElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TitleTextElement implements CardElement {

	private String text;
	private Color color;
	private Color cardColor;
	private int x;
	private int y;
	private int size;
	private final int barHeight;
	
	
	public TitleTextElement(String text, int x, int y, int size, int barHeight, Color color, Color cardColor) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
		this.cardColor = cardColor;
		this.barHeight = barHeight;
	}
	
	@Override
	public void draw(int x1, int y1, Graphics g, int width, int height, Color[][] ig) {
		TransparentBoxElement tbe = new TransparentBoxElement(0, 0, width, barHeight, cardColor, 1);
		
		tbe.draw(x1, y1, g, width, height, ig);
		
		g.setFont(new Font(g.getFont().getFontName(), g.getFont().getStyle(), size));
		g.setColor(this.color);
		g.drawString(text, x + x1, y + y1);

	}

}
