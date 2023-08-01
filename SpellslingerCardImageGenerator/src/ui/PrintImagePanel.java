package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dataReader.Card;

public class PrintImagePanel extends JPanel {

	public static final int SCALE = 28;

	public static final int PAGE_WIDTH = 17 * SCALE; // 8.5x11
	public static final int PAGE_HEIGHT = 22 * SCALE;
	
	public static final int X_COUNT = 3;
	public static final int Y_COUNT = 3;

	private int page = 0;
	private static List<JPanel> pages = new ArrayList<JPanel>();

	public PrintImagePanel() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);

		JButton print = new JButton(" Print ");	// set up buttons to view the generated pages
		JButton left = new JButton(" < ");
		JButton right = new JButton(" > ");
		JPanel buttons = new JPanel();
		right.setEnabled(false);
		left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (page > 0) {
					page--;
					render(page);
				}
				if (page == 0) {
					left.setEnabled(false);
				}
				if (page < pages.size() - 1) {
					right.setEnabled(true);
				}
			}
		});
		right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (page < pages.size() - 1) {
					page++;
					render(page);
				}
				if (page == pages.size() - 1) {
					right.setEnabled(false);
				}
				if (page > 0) {
					left.setEnabled(true);
				}
			}
		});

		buttons.setLayout(new BorderLayout());
		buttons.add(print, BorderLayout.CENTER);
		buttons.add(left, BorderLayout.WEST);
		buttons.add(right, BorderLayout.EAST);

		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				print();
			}
		});

		this.add(buttons, BorderLayout.SOUTH);	// add all buttons into panel
		
	}

	public void draw() {
		if (!check())
			return;
		pages.clear();
		this.add(new JLabel(), BorderLayout.CENTER); // added so the render method has something to remove.
		createPage();

		int x = 0;	// iterates for currently printing location
		int y = 0;

		int xOffset = 5;
		int yOffset = 5;

		int lineLength = 26;

		countLoop: for (int i = 0; i < 3; i++) { // make 2 copies of each card
			int count = 0;
			outer: for (String s : Frame.cards) {
				if (i > 0 && count == 0) { // only put the class card in once
					count++;
					continue;
				}
				if(i > 1 && count > 1) {	//put in signature spell 3x
					break countLoop;
				}

				for (Card c : Frame.data) {	// find the matching card
					if (s.toUpperCase().strip().equals(c.getName().toUpperCase().strip())) {	
						// title
						JLabel l = new JLabel(s);
						l.setOpaque(true);
						l.setBackground(Color.white);
						pages.get(page).add(l);
						l.setLocation(xOffset + (x * (PAGE_WIDTH / X_COUNT)), yOffset + (y * (PAGE_HEIGHT / Y_COUNT)));
						l.setSize(new Dimension((PAGE_WIDTH / X_COUNT) - (3* xOffset) - 6, l.getPreferredSize().height));

						// cost
						JLabel n = new JLabel(c.getCost());
						n.setOpaque(true);
						n.setBackground(Color.white);
						if(!c.getCost().equals("-"))
						pages.get(page).add(n);
						n.setLocation((-3* xOffset) + ((x+1) * (PAGE_WIDTH / X_COUNT)), yOffset + (y * (PAGE_HEIGHT / Y_COUNT)));
						n.setSize(new Dimension((3* xOffset) - 6, n.getPreferredSize().height));

						
						// play
						JLabel j = new JLabel(
								"<html>" + (c.getPlayAffect() != null ? "Play: " + c.getPlayAffect() : "") + "</html>");
						j.setOpaque(true);
						j.setBackground(Color.white);
						pages.get(page).add(j);
						j.setLocation(xOffset + (x * (PAGE_WIDTH / X_COUNT)),
								l.getHeight() + l.getHeight() + (y * (PAGE_HEIGHT / Y_COUNT)));
						j.setSize(new Dimension((PAGE_WIDTH / X_COUNT) - 6,
								(c.getPlayAffect() != null
										? j.getPreferredSize().height * ((j.getText().length() / lineLength) + 1)
										: 0)));

						// static

						JLabel k = new JLabel("<html>"
								+ (c.getStaticAffect() != null ? "Static: " + c.getStaticAffect() : "") + "</html>");
						k.setOpaque(true);
						k.setBackground(Color.white);
						pages.get(page).add(k);
						k.setLocation(xOffset + (x * (PAGE_WIDTH / X_COUNT)),
								l.getHeight() + j.getHeight() + l.getHeight() + (y * (PAGE_HEIGHT / Y_COUNT)));
						k.setSize(new Dimension((PAGE_WIDTH / X_COUNT) - 6,
								(c.getStaticAffect() != null
										? k.getPreferredSize().height * ((k.getText().length() / lineLength) + 1)
										: 0)));

						// cast

						JLabel m = new JLabel(
								"<html>" + (c.getCastAffect() != null ? "Cast: " + c.getCastAffect() : "") + "</html>");
						m.setOpaque(true);
						m.setBackground(Color.white);
						pages.get(page).add(m);
						m.setLocation(xOffset + (x * (PAGE_WIDTH / X_COUNT)), l.getHeight() + j.getHeight()
								+ l.getHeight() + k.getHeight() + (y * (PAGE_HEIGHT / Y_COUNT)));
						m.setSize(new Dimension((PAGE_WIDTH / X_COUNT) - 6,
								(c.getCastAffect() != null
										? m.getPreferredSize().height * ((m.getText().length() / lineLength) + 1)
										: 0)));

						x++;
						if (x >= X_COUNT) {
							x = 0;
							y++;
						}
						if (y >= Y_COUNT) {
							y = 0;
							page++;
							createPage();
							
							continue outer;
						}
						break;
					}
				}
				count++;
			}
		}
		render(page);
	}

	public boolean check() {
		for (String s : Frame.cards) {
			boolean check = false;
			for (Card c : Frame.data) {
				if (s.toUpperCase().strip().equals(c.getName().toUpperCase().strip())) {
					check = true;
				}
			}
			if (!check) {
				System.out.println("invalid card name: " + s);
				return false;
			}
		}
		return true;
	}

	public void render(int page) {
		BorderLayout layout = (BorderLayout) getLayout();
		this.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		this.add(pages.get(page), BorderLayout.CENTER);

		this.revalidate();
		this.repaint();
	}

	public void createPage() {
		JPanel a = new JPanel() {
			private static final long serialVersionUID = 2704001322562290484L;

			@Override
			public void paintComponent(Graphics g) {
				int x = 0;
				int y = 0;

				int yOffset = 5;
				for (y = 0; y < Y_COUNT; y++) {
					for (x = 0; x < X_COUNT; x++)
						g.drawRect(x * (PAGE_WIDTH / X_COUNT), y * (PAGE_HEIGHT / Y_COUNT), (PAGE_WIDTH / X_COUNT),
								(PAGE_HEIGHT / Y_COUNT));
					g.drawLine(0, (y * (PAGE_HEIGHT / Y_COUNT)) + yOffset + 20, PAGE_WIDTH,
							(y * (PAGE_HEIGHT / Y_COUNT)) + yOffset + 20);
				}
			}
		};
		a.setLayout(null);
		pages.add(a);
	}

	public void print() {

		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setJobName("Print Spellslinger Cards");
		PageFormat pf = pj.getPageFormat(null);
		Paper p = new Paper();
		p.setImageableArea(20, 20, pf.getWidth(), pf.getHeight());	// set margin bounds
		pf.setPaper(p);
		Book bk = new Book();
		for(int i = 0; i < pages.size(); i++) {
			bk.append(new Page(i), pf);
		}
		
		pj.setPageable(bk);

		if (pj.printDialog() == false)
			return;

		try {
			pj.print();
		} catch (PrinterException ex) {
		}

	}
	
	
	public static class Page implements Printable {

        @SuppressWarnings("unused")
		private int page;

        public Page(int page) {

            this.page = page;

        }

        @Override
        public int print(Graphics graphics, PageFormat pf, int pageIndex) throws PrinterException {
			Graphics2D g2 = (Graphics2D) graphics;
			g2.translate(pf.getImageableX(), pf.getImageableY());
			pages.get(pageIndex).paint(g2);
			return Printable.PAGE_EXISTS;
        }

    }

}
