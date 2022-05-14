package com.forestgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Forest {

	List<Animal> animalList = new ArrayList<>();

	Random random = new Random();

	private void addDefaultAnimal() {

		animalList.add(new Animal("Tiger", 3, "Hungry"));
		animalList.add(new Animal("Tiger", 3, "Angry"));
		animalList.add(new Animal("Deer", 1, "Hungry"));
		animalList.add(new Animal("Deer", 1, "Angry"));
		animalList.add(new Animal("Rabbit", 1, "Hungry"));
		animalList.add(new Animal("Rabbit", 1, "Angry"));
		animalList.add(new Animal("Snake", 2, "Hungry"));
		animalList.add(new Animal("Snake", 2, "Angry"));
		animalList.add(new Animal("Cheetha", 3, "Hungry"));
		animalList.add(new Animal("Cheetha", 3, "Angry"));
		animalList.add(new Animal("Lion", 3, "Hungry"));
		animalList.add(new Animal("Lion", 3, "Angry"));
	}

	private void startGame() {
		int currentIndex = random.nextInt(animalList.size());
		Animal currentAnimal = animalList.get(currentIndex);
		int randomNumber = generateRandom(currentIndex);
		Animal randomAnimal = animalList.get(randomNumber);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (currentAnimal.getStrength() == randomAnimal.getStrength()) {
			if (currentAnimal.getEmotion().equals("Hungry") && randomAnimal.getEmotion().equals("Hungry")) {
				System.out.println(currentAnimal.getName() + " Walk Away on meeting " + randomAnimal.getName());
			} else if (currentAnimal.getEmotion().equals("Angry") && randomAnimal.getEmotion().equals("Angry")){
				System.out.println(currentAnimal.getName() + " Starts fight with " + randomAnimal.getName());
			} else if (currentAnimal.getEmotion().equals("Angry") && randomAnimal.getEmotion().equals("Hungry")){
				System.out.println(currentAnimal.getName() + " Starts fight with " + randomAnimal.getName());
				int randomStrength = randomAnimal.setStrength(randomAnimal.getStrength()-1);
				animalList.remove(randomNumber);
				System.out.println(randomAnimal.getName() + " strength reduces to " + randomStrength + " and got eliminated");
			} else {
				System.out.println(randomAnimal.getName() + " Starts fight with " + currentAnimal.getName());
				int randomStrength = currentAnimal.setStrength(currentAnimal.getStrength()-1);
				System.out.println(currentAnimal.getName() + " strength reduces to " + randomStrength);
			}
		} else if (currentAnimal.getStrength() > randomAnimal.getStrength()) {
			if (currentAnimal.getEmotion().equals("Hungry")) {
				System.out.println(currentAnimal.getName() + " Hunt " + randomAnimal.getName() + " and " + randomAnimal.getName() + " got killed");
				animalList.remove(randomNumber);
			} else {
				System.out.println(currentAnimal.getName() + " Starts fight with " + randomAnimal.getName());
				if(randomAnimal.getStrength() == 0) {
					animalList.remove(randomNumber);
					System.out.println(currentAnimal.getName()+" Wins and looks for another animal");
				}else {
					randomAnimal.setStrength(randomAnimal.getStrength()-1);
				}
			}
		} else {
			if (randomAnimal.getEmotion().equals("Hungry")) {
				System.out.println(randomAnimal.getName() + " Hunts " + currentAnimal.getName());
				animalList.remove(currentAnimal);
			} else {
				System.out.println(randomAnimal.getName() + " fights with " + currentAnimal.getName());
				if(currentAnimal.getStrength() == 0) {
					animalList.remove(currentAnimal);
				}else {
					currentAnimal.setStrength(currentAnimal.getStrength()-1);
				}
			}
		}
		
		if(endGame()) {
			System.out.println();
			System.out.println("Game Over");
			System.out.println();
			System.out.println("Animals left in the forest are: ");
			System.out.println(animalList);
		}else {
			startGame();
		}
	}
	
	private boolean endGame() {
		if(animalList.size()<=1) {
			return true;
		}else {
			return animalList.stream().map(Animal::getStrength).distinct().count()==1
					;
		}
	}

	private int generateRandom(int previousIndex) {
		int index = random.nextInt(animalList.size());
		while(previousIndex==index) {
			index = random.nextInt(animalList.size());
		}
		return index;
	}
	
	public static void main(String[] args) {

		Forest forest = new Forest();
		forest.addDefaultAnimal();

		System.out.println("Game Starts");

		forest.initGame();
		

	}

	private void initGame() {
		System.out.println("Animals in the Forest");
		for (int i = 0; i < animalList.size(); i++) {
			System.out.println(animalList.get(i));
		}

		try (Scanner scn = new Scanner(System.in)) {
			System.out.println("Press A to add animal of your choice or Press S to start the game");
			String str = scn.nextLine();
			if ("A".equals(str)) {
				System.out.println(
						"Provide animal name, strength(1 for low, 2 for medium & 3 for high),emotion (1 for angry or 2 for Hungry) as comma seperated");
				String str1 = scn.nextLine();
				String[] animalArray = str1.split(",");
				String animalName = animalArray[0];
				int animalStrength = Integer.valueOf(animalArray[1]);
				String emotion = animalArray[2];

				String animalEmotion = null;
				if (emotion.equals("1")) {
					animalEmotion = "angry";
				} else if (emotion.equals("2")) {
					animalEmotion = "Hungry";
				}

				animalList.add(new Animal(animalName, animalStrength, animalEmotion));
				initGame();
			} else if ("S".equals(str)) {
				startGame();
			} else {
				System.out.println("Fool..Press the key correctly");
				initGame();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
