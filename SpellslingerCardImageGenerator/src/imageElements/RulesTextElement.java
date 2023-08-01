package imageElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RulesTextElement implements CardElement {

	private Color cardColor;
	private Color textColor;
	private int textSize;
	private int textOffsetX;
	private int lineSpacing;
	private int lineLength;
	private String play, passive, cast;
	private int lineCount = 0;
	
	public RulesTextElement(Color cardColor, Color textColor, String play, String passive, String cast, int textSize, int lineLength) {
		this.cardColor = cardColor;
		this.textColor = textColor;
		this.play = play;
		this.passive = passive;
		this.cast = cast;
		this.textSize = textSize;
		textOffsetX = (int) (textSize * 1.6f);
		lineSpacing = (int) (textSize * 1.2f);
		this.lineLength = lineLength;
	}
	
	@Override
	public void draw(int x, int y, Graphics g, int width, int height, Color[][] colors) {
		
		int boxStartVertical = 3*height/5;
		int boxSizeVertical = 2*height/5;
		
		TransparentBoxElement tbe = new TransparentBoxElement(0, boxStartVertical, width, boxSizeVertical, cardColor, 1f);
		tbe.draw(x, y, g, width, height, colors);
		
		g.setFont(new Font(g.getFont().getFontName(), g.getFont().getStyle(), textSize));
		g.setColor(this.textColor);
		
		writeRule(x, y, play, "Play", boxStartVertical, g);
		writeRule(x, y, passive, "Passive", boxStartVertical, g);
		writeRule(x, y, cast, "Cast", boxStartVertical, g);
		
		System.out.println(cast);
		
	}
	
	private void writeRule(int x, int y, String s, String label, int boxOffset, Graphics g) {
		if(s != null) {
			
			Scanner sc = new Scanner(s);
			String line = label + ": ";
			int offsetX = 0;
			List<String> words = new ArrayList<String>();
			
			while(sc.hasNext()) {
				words.add(sc.next());
			}
			
			int i = 0;
			while(i < words.size()) {	// go until scanner runs out
				while(i < words.size() && (line.length() + words.get(i).length()) < lineLength) {
					line += words.get(i) + " ";
					i++;
				}
				
				g.drawString(line, textOffsetX + offsetX + x, boxOffset + (lineSpacing * lineCount) + lineSpacing + y);

				line = "";
				offsetX = (int)((2/3f) * textSize);
				lineCount++;
			}
		}
	}

}
