package imageElements;

import java.awt.Color;
import java.awt.Graphics;

public class LineElement implements CardElement {

	int x1, y1, x2, y2;
	Color c;
	public LineElement(Color c, int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.c = c;
	}
	
	
	@Override
	public void draw(int x, int y, Graphics g, int width, int height, Color[][] colors) {
		g.setColor(c);
		for(int i = 0; i < 10; i++) {
			g.drawLine(x + x1, y + y1 - i, x + x2, y + y2 - i);
		}

	}

}
