package imageElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import dataReader.ImprovedNoise;

public class MottledBackground implements CardElement {

	Color color;
	boolean inverted;
	boolean exponential;
	private int points;
	
	public MottledBackground(Color color, int points, boolean inverted, boolean exponential) {
		this.color = color;
		this.inverted = inverted;
		this.exponential = exponential;
		this.points = points;
	}
	
	@Override
	public void draw(int x, int y, Graphics g, int width, int height, Color[][] colors) {
		ImprovedNoise in = new ImprovedNoise(width, height);
		
		int[] pointsX = new int[points];
		int[] pointsY = new int[points];
		Random r = new Random();
		
		for(int i = 0; i < points; i++) {
			pointsX[i] = r.nextInt(width);
			pointsY[i] = r.nextInt(height);
		}
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				double minDistance = Double.MAX_VALUE;
				double maxDistance = Math.sqrt(width * width + (height * height));
				for(int k = 0; k < points; k++) {
					double dist = distanceTo(i,j,pointsX[k], pointsY[k]);
					if(dist < minDistance)
						minDistance = dist;
				}
 				
				double color;
				
				if(!exponential) {
					color = ((minDistance / maxDistance)*2);
				} else {
					color = (Math.pow(minDistance / maxDistance, 1/2));
				}
				
				if(inverted) {
					color = 1-color;
				}
				
				
				Color c = new Color((int)(this.color.getRed() * color), 
						(int)(this.color.getGreen() * color),
						(int)(this.color.getBlue() * color));
				g.setColor(c);
				colors[i][j] = c;
				g.drawRect(i + x, j + y, 1, 1);
			}
		}
		
	}
	
	private double distanceTo(int x, int y, int x2, int y2) {
		double difx = Math.pow(Math.abs(x - x2), 2);
		double dify = Math.pow(Math.abs(y - y2), 2);
		return Math.sqrt(difx + dify); 
	}

}
