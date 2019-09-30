package StructuralMetrics;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class GameMasterMultiTool {

	private static final int BODY = 0;
	private static final int QUICK = 1;
	private static final int STR = 2;
	private static final int CHARISMA = 3;
	private static final int INTELLIGENCE = 4;
	private static final int WILL = 5;
	private static final int NUM_ATTRIBUTES =6;
	private static final int SIDES = 6;
	private static final int ATTRIBUTE_TOTAL = 30;
	private static final int STARTING_SKILLPOINTS = 30;
	private static final int ATTRIBUTE_CAP = 6;
	private static final int STARTING_SKILLCAP = 6;
	private HashMap<String, CharacterSheet> characters;
	
	public GameMasterMultiTool(){
		this.characters = new HashMap<String, CharacterSheet>();
	}
	
	///creates a new character
	public void characterBuilder(String name, int[] attributes, HashMap<String, Integer>skills) {
		CharacterSheet character = new CharacterSheet();
		character.setName(name);
		character.setAttributes(attributes);
		character.startingSkills(skills);
		characters.put(name, character);
		
	}
	
	
	///conducts a simple success test against a targetNumber
	public int simpleTest(int numDice, int targetNumber) {
		Random random = new Random();
		int totalSuccesses = 0;
		for(int i=0;i<numDice;i++) {
			int roll = (random.nextInt(SIDES)+1);
			if(roll>=targetNumber) {
				totalSuccesses++;
			}
		}
		return totalSuccesses;
	}
	
	///returns the name of the successful character in an opposed test
	public String opposedTest(CharacterSheet character1, CharacterSheet character2, String check, int targetNumber) {
		int character1successes = 0;
		int character2successes = 0;
		while(character1successes==character2successes) {
			character1successes = simpleTest(character1.getSkillLevel(check), targetNumber);
			character2successes = simpleTest(character2.getSkillLevel(check), targetNumber);
		}
		if (character1successes>character2successes) {
			return character1.getName();
		}
		else return character2.getName();
	}
	
	///get a character's reaction score
	public int getReactionScore(String name) {
		CharacterSheet character  = characters.get(name);
		int quickness = character.getAttribute(QUICK);
		int intelligence = character.getAttribute(INTELLIGENCE);
		return ((quickness+intelligence)/2);
	}
	
	public void spendAttributePoints() {
		boolean done = false;
		int attributePoints = 0;
		int[] attributes = {0,0,0,0,0,0};
		Scanner input = new Scanner(System.in);
		while(!done) {
			displayAttributes(attributes);
			int currentAttribute = -1;
			while(currentAttribute<0) {
				System.out.println("Please select the attribute you would like to assign points to: ");
				System.out.println("Valid selections: Body, Quick, Strength, Charisma, Intelligence, Will");
				String currentSelection = input.nextLine();
				currentSelection = currentSelection.toUpperCase().trim();
				if(currentSelection=="BODY") {
					currentAttribute = this.BODY;
					System.out.println("You have selected Body");
				}
				else if(currentSelection=="QUICK") {
					currentAttribute = this.QUICK;
					System.out.println("You have selected Quickness");
				}
				else if(currentSelection=="STRENGTH") {
					currentAttribute = this.STR;
					System.out.println("You have selected Strength");
				}
				else if(currentSelection=="CHARISMA") {
					currentAttribute = this.CHARISMA;
					System.out.println("You have selected Charisma");
				}
				else if (currentSelection=="INTELLIGENCE") {
					currentAttribute = this.INTELLIGENCE;
					System.out.println("You have selected Intelligence");
				}
				else if(currentSelection=="WILL") {
					currentAttribute = this.WILL;
					System.out.println("You have selected Will");
				}
				else System.out.println("I'm sorry I didn't understand that.");
			}
			boolean valid = false;
			while(!valid) {
				System.out.println("Please note: the attribute cap is " + ATTRIBUTE_CAP);
				System.out.println("You have "+ (ATTRIBUTE_TOTAL-attributePoints) + "remaining");
				System.out.println("What value would you like to assign to this attribute?");
				int value=input.nextInt();
				if(value>=0&&value<ATTRIBUTE_CAP) {
					int priorValue = attributes[currentAttribute];
					attributes[currentAttribute]=value;
					int newAttributePoints=0;
					for(int attribute : attributes) {
						newAttributePoints += attribute;
					}
					if(newAttributePoints<=ATTRIBUTE_TOTAL) {
						attributePoints = newAttributePoints;
						valid = true;
					}
					else{
						System.out.println("That does not seem to have been a valid selection");
						attributes[currentAttribute]=priorValue;
					}
				}
				
			}
			displayAttributes(attributes);
			if(attributePoints==ATTRIBUTE_TOTAL) {
				System.out.println("Are you satisfied with your attribute distribution?  y/n");
				if(input.next().toUpperCase().trim()=="Y") {
					done=true;
				}
			}
			
		}

	}
	
	public void displayAttributes(int[] attributes) {
		if(attributes.length<NUM_ATTRIBUTES) {
			System.out.println("Invalid attribute selection");
			return;
		}
		System.out.println("Your current attribute values are: ");
		System.out.println("Body: "+ attributes[BODY]);
		System.out.println("Quickness: " + attributes[QUICK]);
		System.out.println("Strength: "+ attributes[STR]);
		System.out.println("Charisma: "+ attributes[CHARISMA]);
		System.out.println("Intelligence: "+attributes[INTELLIGENCE]);
		System.out.println("Willpower: "+ attributes[WILL]);
	}
	
}