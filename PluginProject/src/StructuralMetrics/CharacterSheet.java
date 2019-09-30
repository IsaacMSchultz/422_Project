package StructuralMetrics;

import java.util.HashMap;

public class CharacterSheet {

	private static final int BODY = 0;
	private static final int QUICK = 1;
	private static final int STR = 2;
	private static final int CHARISMA = 3;
	private static final int INTELLIGENCE = 4;
	private static final int WILL = 5;
	private String name;
	private int[]attributes;
	private HashMap<String, Integer> skills;
	
	public int getSkillLevel(String check) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getName() {
		if(this.name==null) {
			return "unnamed";
		}
		else return this.name;
	}

	public void setName(String name) {
		this.name=name;
		
	}

	public void setAttributes(int[] attributes) {
		this.attributes = attributes;	
	}

	public void startingSkills(HashMap<String, Integer> skills) {
		this.skills = skills;		
	}

	public int getAttribute(int attribute) {
		if(attribute>=0||attribute<6) {
			return this.attributes[attribute];
		}
		else return 0;
	}


}
