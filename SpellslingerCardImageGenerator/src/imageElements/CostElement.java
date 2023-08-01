package imageElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Scanner;

public class CostElement implements CardElement {

	private Color textColor;
	private Color cardColor;
	private static final int TEXT_SIZE = 70;
	private String cost;
	private String passive;
	private int barWidth;
	private int scale;
	private int borderThickness;
	
	public CostElement(Color textColor, Color cardColor, String cost, String passive, int barWidth, int scale, int borderThickness) {
		this.textColor = textColor;
		this.cardColor = cardColor;
		this.cost = cost;
		this.passive = passive;
		this.barWidth = barWidth;
		this.scale = scale;
		this.borderThickness = borderThickness;
	}
	
	@Override
	public void draw(int x, int y, Graphics g, int width, int height, Color[][] colors) {
		String qp = null;
		if(passive != null && passive.toLowerCase().contains("quickplay")) {
			qp = passive.substring(passive.toLowerCase().indexOf("quickplay") + 10);
			qp = qp.substring(0,1);
		}
		
		int circleSize = barWidth;	
		int costInt = 0;
		int quickplay = 0;
		try {
			costInt = Integer.parseInt(cost);
			if(qp != null) {
				quickplay = Integer.parseInt(qp);
			}
		} catch (NumberFormatException e){
			
			
		}
		
		// create bookmark banner with stars representing the cost
		
		g.setColor(cardColor);
		if(costInt>0) {
			g.fillRoundRect(x + width - (circleSize/2 + borderThickness), -(circleSize/2) + borderThickness + y, circleSize/2+1, circleSize + (60 * scale * costInt) - 60 * scale, circleSize/2, circleSize/2);
		} else {
			g.fillRoundRect(x + width - (circleSize/2 + borderThickness), -(circleSize/2) + borderThickness + y, circleSize/2+1, circleSize + (60 * scale * 2) - 60 * scale, circleSize/2, circleSize/2);
		}
		
		// if card has a quickplay value, add a second bookmark to represent that value
		
		if(quickplay > 0) {
			g.setColor(cardColor.brighter().brighter());
			g.fillRoundRect(x + width - (5*circleSize/12 + borderThickness), -(circleSize/2) + borderThickness + y, circleSize/3+2, circleSize + (60 * scale * quickplay) - 75 * scale, circleSize/3, circleSize/3);
		}
		
		// add stars onto the bookmark
		
		g.setFont(new Font(g.getFont().getFontName(), g.getFont().getStyle(), TEXT_SIZE * scale));
		
		for(int i = 0; i < costInt; i++) {
			if(i < quickplay) {
				g.setColor(this.textColor.brighter());
				g.drawString("*", x + width - (borderThickness + 55 * scale), 80 * scale + (60 * scale * i) + borderThickness + y);
			} else {
				g.setColor(this.textColor.brighter());
				g.drawString("*", x + width - (borderThickness + 55 * scale), 80 * scale + (60 * scale * i) + borderThickness + y);
			}
		}
		if(costInt == 0) {
			g.setColor(this.textColor.brighter());
			g.drawString("X", x + width - (borderThickness + 65 * scale), (int) (80 * scale + (60 * scale * 0.5d) + borderThickness + y));
		}
		
		
		// create bottom-right corner numerical cost 
		
		g.setColor(cardColor);
		g.fillRoundRect(x + width - (circleSize/2 + borderThickness), (int) (height - ((TEXT_SIZE * scale * 1.8) + borderThickness) + borderThickness) + y, circleSize/2+1, circleSize + (TEXT_SIZE * scale), circleSize/2, circleSize/2);
		g.setColor(this.textColor.brighter());
		if(costInt > 0) {
			g.drawString(cost, x + width - (borderThickness + 61 * scale), y + height - 48 * scale);
		} else {
			g.drawString(cost, x + width - (borderThickness + 65 * scale), y + height - 48 * scale);
		}
	}
	
}
