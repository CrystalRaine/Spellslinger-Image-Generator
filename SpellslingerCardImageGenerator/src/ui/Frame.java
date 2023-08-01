package ui;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import dataReader.Card;
import dataReader.CardReader;

public class Frame extends JFrame{

	public static List<String> cards = new ArrayList<String>();
	public static List<Card> data;
	public static PrintImagePanel img;
	
	public Frame() {
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		
		CardReader cr = new CardReader(new File("res/Spellslinger.csv"));
		cr.printAllCards();
		
		data = cr.getCards();
		
		DecklistWindow decklist = new DecklistWindow();
		img = new PrintImagePanel();
		
		this.add(decklist, BorderLayout.EAST);
		this.add(img, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000-688 + (17 * PrintImagePanel.SCALE),500-439 + (22 * PrintImagePanel.SCALE));
		this.setVisible(true);
	}
	
	public static void trigger() {
		img.draw();
	}
}
