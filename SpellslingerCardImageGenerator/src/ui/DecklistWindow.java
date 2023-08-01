package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class DecklistWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DecklistWindow() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(300,0));
		this.setBorder(BorderFactory.createMatteBorder(0,1,0,0,Color.black));
		
		JTextArea jtp = new JTextArea(
				  "Spatial Mage\r\n"
				+ "Change Priorities"
				+ "\r\nLightning Bolt\r\n"
				+ "Lightbeam\r\n"
				+ "Ignite Spell\r\n"
				+ "Casting Blitz\r\n"
				+ "Arcane Bolt\r\n"
				+ "Desperate Protection\r\n"
				+ "Rapid Cast\r\n"
				+ "Stasis\r\n"
				+ "Spatial Tear\r\n"
				+ "Robust Cast\r\n"
				+ "Fireblast\r\n"
				+ "Stagger \r\n"
				+ "Counterspell\r\n"
				+ "Reactive Barrier"
				);
		JButton done = new JButton("Done");
		
		jtp.setLineWrap(true);
		jtp.setMargin(new Insets(0,5,5,5));
		done.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Frame.cards.clear();
				
				String input = jtp.getText();
				Scanner sc = new Scanner(input);
				
				while(sc.hasNextLine()) {
					Frame.cards.add(sc.nextLine());
				}
				
				Frame.trigger();
			}
		});
		
		this.add(jtp, BorderLayout.CENTER);
		this.add(done, BorderLayout.SOUTH);
		
		
	}
	
}
