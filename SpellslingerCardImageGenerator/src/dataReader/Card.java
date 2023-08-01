package dataReader;

import java.awt.Color;

public class Card {

	public enum School {
		ARCANE 		(new Color(100,150,200)), 
		THERMAL		(new Color(255,50,50)), 
		KINETIC 	(new Color(100,100,100)), 
		ELECTRIC 	(new Color(200,200,50)),
		LIGHT 		(new Color(255,255,255)),
		SPACE 		(new Color(200,50,200)),
		TIME		(new Color(75,200,100));
		
		Color assoc;
		
		private School(Color c) {
			assoc = c;
		}
	}
	
	
	String name;
	String cost;
	String typeLine;
	School school;
	String playAffect;
	String staticAffect;
	String castAffect;
	
	public Card() {
		typeLine = "";
	}
	
	public Card(String name, String cost, String typeLine, Color color, String playAffect, String staticAffect, String castAffect) {
		this.name = name;
		this.cost = cost;
		this.typeLine = typeLine;
		this.playAffect = playAffect;
		this.staticAffect = staticAffect;
		this.castAffect = castAffect;
		
	}
	
	public void setSchool(School school) {
		this.school = school;
	}
	
	public School getSchool() {
		return school;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public void setTypeLine(String typeLine) {
		this.typeLine = typeLine;
	}

	public void setPlayAffect(String playAffect) {
		this.playAffect = playAffect;
	}

	public void setStaticAffect(String staticAffect) {
		this.staticAffect = staticAffect;
	}

	public void setCastAffect(String castAffect) {
		this.castAffect = castAffect;
	}
	
	public String getName() {
		return name;
	}

	public String getCost() {
		return cost;
	}

	public String getTypeLine() {
		return typeLine;
	}

	public Color getColor() {
		return school.assoc;
	}

	public String getPlayAffect() {
		return playAffect;
	}

	public String getStaticAffect() {
		return staticAffect;
	}

	public String getCastAffect() {
		return castAffect;
	}
	
	@Override 
	public String toString() {
		return name + ", " + cost + ", " + typeLine + ", " + playAffect + ", " + staticAffect + ", " + castAffect;
	}

}
