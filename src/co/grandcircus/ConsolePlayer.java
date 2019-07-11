package co.grandcircus;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsolePlayer extends Player{
	String userInput = null;
	boolean doAgain = true;
	Roshambo playerChoice = Roshambo.PAPER;
	Scanner scnr = new Scanner(System.in);
	
	public ConsolePlayer(String name) {
		super(name);
	}
	@Override
	public Roshambo generateRoshambo() {
		do {
			try {
				userInput = scnr.nextLine();
			} catch (InputMismatchException ex) {
				System.out.println("Oops looks like those werent even words. Lets try that again.");
				doAgain = false;
				continue;
			}
			if (userInput.equalsIgnoreCase("paper")) {
				playerChoice = Roshambo.PAPER;
			} else if (userInput.equalsIgnoreCase("rock")) {
				playerChoice = Roshambo.ROCK;
			} else if (userInput.equalsIgnoreCase("scissors")) {
				playerChoice = Roshambo.SCISSORS;
			} else {
				System.out.println("That was not a valid choice. Lets try that again.");
				doAgain = false;
				continue;
			}
		} while (!doAgain);
		
		return playerChoice;
	}
	

}
