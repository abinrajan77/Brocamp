package com.forestgame;

public class Animal {

	private String name;
	private int strength;
	private String emotion;
	
	public Animal(String name, int strength, String emotion) {
		super();
		this.name = name;
		this.strength = strength;
		this.emotion = emotion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStrength() {
		return strength;
	}

	public int setStrength(int strength) {
		return this.strength = strength;
	}

	public String getEmotion() {
		return emotion;
	}

	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", strength=" + strength + ", emotion=" + emotion + "]";
	}
	
}
