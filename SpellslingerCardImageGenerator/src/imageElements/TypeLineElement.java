package imageElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Scanner;

public class TypeLineElement implements CardElement {

	private Color cardColor;
	private Color textColor;
	private static final int TEXT_SIZE = 38;
	private static final int TEXT_X_OFFSET_START = 50;
	private String typeLine;
	
	public TypeLineElement(Color cardColor, Color textColor, String typeLine) {
		this.cardColor = cardColor;
		this.textColor = textColor;
		this.typeLine = typeLine;
	}
	
	@Override
	public void draw(int x, int y, Graphics g, int width, int height, Color[][] colors) {
		
		int boxStartVertical = 3*height/5 - 65;
		int boxSizeVertical = 65;
		
		TransparentBoxElement tbe = new TransparentBoxElement(0, boxStartVertical, width, boxSizeVertical, Color.black, 0.5f);
		tbe.draw(x, y, g, width, height, colors);
		
		g.setFont(new Font(g.getFont().getFontName(), g.getFont().getStyle(), TEXT_SIZE));
		g.setColor(this.textColor);
		g.drawString(typeLine, x + TEXT_X_OFFSET_START, y + boxStartVertical + 45);
		
	}
	
}
