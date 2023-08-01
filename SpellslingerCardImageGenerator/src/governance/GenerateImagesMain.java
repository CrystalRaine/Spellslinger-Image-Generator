package governance;

import java.awt.Color;
import java.io.File;

import dataReader.Card;
import dataReader.CardReader;
import imageElements.BasicBorder;
import imageElements.CostElement;
import imageElements.ImageElement;
import imageElements.LineElement;
import imageElements.MottledBackground;
import imageElements.RulesTextElement;
import imageElements.TitleTextElement;
import imageHandler.ImageGenerator;

public class GenerateImagesMain {

	public static void main(String[] args) {
		CardReader reader = new CardReader(new File("res/Spellslinger.csv"));
		reader.printAllCards();
		
		File f = new File("out");
		for(File k : f.listFiles()) {
			k.delete();
		}
		
		ImageGenerator ig = new ImageGenerator("out");
		
		// 63 x 88mm : 2.48" x 3.46"
		// 300dpi
		// 744px x 1038px
		// 600dpi 
		// 1488px x 2076px

		int scale = 1;
		int borderThickness = 36 * scale;
//		int extendedBorderForPrinting = 70 * scale;
		int extendedBorderForPrinting = 0;	// for creating digital versions
		int height = 1038 * scale;
		int width = 744 * scale;
		int titleThickness = 120 * scale;
		int bodyTextSize = 34 * scale;
		int lineLength = 39;
		int costBarWidth = 170 * scale;
		
		// image from
		// 36x - 708x = 672px wide
		// 120y - 623y = 503px tall
		
		for(Card c : reader.getCards()) {
			ig.clearCardElements();
			ig.addCardElement(new MottledBackground(c.getColor(), 25, true, false));	// start with background
			
			ig.addCardElement(new TitleTextElement(c.getName(), 50 * scale, 100 * scale, 70 * scale, titleThickness, Color.white.darker(), c.getColor()));
			ig.addCardElement(new RulesTextElement(c.getColor(), Color.white.darker(), c.getPlayAffect(), c.getStaticAffect(), c.getCastAffect(), bodyTextSize, lineLength));
			ig.addCardElement(new LineElement(Color.black, 0, 3*height/5, width, 3*height/5));
			ig.addCardElement(new ImageElement(c.getName(), borderThickness, titleThickness));
			ig.addCardElement(new CostElement(Color.white.darker(), c.getColor().darker().darker().darker().darker(), c.getCost(), c.getStaticAffect(), costBarWidth, scale, borderThickness));
			
			ig.addCardElement(new BasicBorder(borderThickness, Color.black));// finish with border
			ig.GenerateImageForCard(c, null, width, height, extendedBorderForPrinting);
//			break;
		}
		
	}

}
