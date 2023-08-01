package dataReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardReader {

	enum State {	// states used for iterating when reading in cards
		NONE, NAME, TYPE, PLAY, PASSIVE, CAST, COST
	}
	
	State state = State.NONE;
	Scanner sc;
	String type;
	
	List<Card> cards = new ArrayList<Card>();
	
	public CardReader(File f) {
	
		try {
			
			int index = 0;
			int total = 0;
			
			Scanner sc1 = new Scanner(f);
			while(sc1.hasNextLine()) {		// look at each line
				state = State.NONE;	
				String line = sc1.nextLine();	// for each line, setup to look at each cell entry
				sc = new Scanner(line);
				sc.useDelimiter(",");
				
				Scanner sc2 = new Scanner(line);	// grab the supertype if it is a type line
				sc2.useDelimiter(",");
				String cell1 = sc2.next();
				if(!cell1.equals("") && !cell1.contains(" ")) {		
					type = cell1;
				}
				
				while(sc.hasNext()) {	// for each cell in the line
					
					String cell = sc.next();	
					cell = cell.strip();
					
					if(cell.equals("Name")) {	// check what line of the card it is on, and set the state, then go to the next cell
						state = State.NAME;
						continue;
					}
					if(cell.equals("On Play")) {
						state = State.PLAY;
						continue;
					}
					if(cell.equals("Passive")) {
						state = State.PASSIVE;
						continue;
					}
					if(cell.equals("On Cast")) {
						state = State.CAST;
						continue;
					}
					if(cell.equals("Cost")) {
						state = State.COST;
						continue;
					}
					if(cell.equals("")) {
						state = State.NONE;
						index = 0;
						continue;
					}
					
					switch(state) {	// for each cell after, depending on the state, set the properties of the card
					case NAME:
						Card c = new Card();	// create the card object
						c.setName(cell);
						cards.add(c);
						break;
					case COST:
						if(total + index < cards.size())
						cards.get(total + index).setCost(cell);
						break;
					case PLAY:
						if(total + index < cards.size())
						cards.get(total + index).setPlayAffect(cell.equals("-") ? null : cell);
						break;
					case PASSIVE:
						if(total + index < cards.size())
						cards.get(total + index).setStaticAffect(cell.equals("-") ? null : cell);
						break;
					case CAST:
						if(total < cards.size())
						cards.get(total).setCastAffect(cell.equals("-") ? null : cell);

						setSchool(cards.get(total), type);
						if(cards.get(total).getPlayAffect() != null) {						// set the type-line once everything is done for the card
							cards.get(total).setTypeLine(type + " - " + "Immediate");
						}
						if(cards.get(total).getStaticAffect() != null) {
							cards.get(total).setTypeLine(((cards.get(total).getTypeLine().equals("")) ? type + " - ": cards.get(total).getTypeLine() + " ") + "Passive");
						}
						if(cards.get(total).getCastAffect() != null) {
							cards.get(total).setTypeLine(((cards.get(total).getTypeLine().equals("")) ? type + " - ": cards.get(total).getTypeLine() + " ") + "Cast");
						}
						total++;	// increment the total number of cards
						break;
					default:
						continue;
					}
					
					index++;		// increment to the next card index in the current row
					
				}
				sc.close();
				sc2.close();
			}
			sc1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private void setSchool(Card c, String s) {
		if(s.equals("Arcane"))
			c.setSchool(Card.School.ARCANE);
		if(s.equals("Thermal"))
			c.setSchool(Card.School.THERMAL);
		if(s.equals("Kinetic"))
			c.setSchool(Card.School.KINETIC);
		if(s.equals("Electric"))
			c.setSchool(Card.School.ELECTRIC);
		if(s.equals("Light"))
			c.setSchool(Card.School.LIGHT);
		if(s.equals("Spatial"))
			c.setSchool(Card.School.SPACE);
		if(s.equals("Time"))
			c.setSchool(Card.School.TIME);
		
	}
	
	public List<Card> getCards(){
		return cards;
	}
	
	public void printAllCards() {
		for(Card c : cards) {
			System.out.println(c);
		}
	}
	
	
}
