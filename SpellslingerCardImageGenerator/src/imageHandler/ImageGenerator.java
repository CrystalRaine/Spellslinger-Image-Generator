package imageHandler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import dataReader.Card;
import imageElements.BasicBorder;
import imageElements.CardElement;

public class ImageGenerator {

	String directory;
	List<CardElement> cardElems = new ArrayList<CardElement>();
	BufferedImage bi;
	
	public ImageGenerator(String directory) {
		this.directory = directory;
	}
	
	public void addCardElement(CardElement ce) {
		cardElems.add(ce);
	}
	
	public void clearCardElements() {
		cardElems.clear();
	}
	
	public BufferedImage getBufferedImage() {
		return bi;
	}
	
	public File GenerateImageForCard(Card card, CardElement border, int width, int height, int extendedBorderSize) {
		
		File outputFile = new File(directory + "/" + card.getName().replaceAll(" " , "_") + ".png");
		
		bi = new BufferedImage(width + (extendedBorderSize * 2),height + (extendedBorderSize * 2),BufferedImage.TYPE_INT_RGB);
		Graphics grap = bi.createGraphics();
		Color[][] colors = new Color[width][height];
		
		for(CardElement ce : cardElems) {
			ce.draw(extendedBorderSize, extendedBorderSize, grap, width, height, colors);
		}
		new BasicBorder(extendedBorderSize, Color.black).draw(0, 0, grap, width + 2 * extendedBorderSize, height + 2 * extendedBorderSize, colors);
		
		try {
		ImageIO.write(bi, "PNG", outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return outputFile; 
	}

}
