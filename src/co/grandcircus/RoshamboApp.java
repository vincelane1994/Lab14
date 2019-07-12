package co.grandcircus;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RoshamboApp {
	static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
		Roshambo oppChoice = null;
		Roshambo playerChoice = null;
		String opponent = null;

//		int sallysChoice = 1 + (int)(Math.random() * 3);
//		System.out.println(sallysChoice);

		RockPlayer fred = new RockPlayer("Fred");
		RandPlayer sally = new RandPlayer("Sally");

		System.out.print("Enter player name: ");
		String playerName = scnr.nextLine();

		ConsolePlayer player1 = new ConsolePlayer(playerName);

		int playerScore = 0;
		int oppScore = 0;
		int chosenOpp = chooseOpp(scnr, "Please choose an opponent.\n1. Fred\n2. Sally");

		do {
			if (chosenOpp == 1) {
				oppChoice = fred.generateRoshambo();
				opponent = "Fred";
			} else if (chosenOpp == 2) {
				oppChoice = sally.generateRoshambo();
				opponent = "Sally";
			}
			System.out.print(player1.getName() + " select rock, paper or scissors: ");
			playerChoice = player1.generateRoshambo();
			System.out.println(opponent + "'s choice: " + oppChoice);
			System.out.println(player1.getName() + "'s choice: " + playerChoice);
			// this is a comment

			if (keepScore(playerChoice, oppChoice, opponent, playerName).equalsIgnoreCase(playerName)) {
				playerScore = playerScore + 1;
			} else if (keepScore(playerChoice, oppChoice, opponent, playerName).equalsIgnoreCase(opponent)) {
				oppScore = oppScore + 1;
			} else if (keepScore(playerChoice, oppChoice, opponent, playerName).equalsIgnoreCase("none")) {
				playerScore = playerScore + 0;
				oppScore = oppScore + 0;
			}
			System.out.println("");
			System.out.println("WINNER: " + keepScore(playerChoice, oppChoice, opponent, playerName));

		} while (doAgain(scnr, "Play another round(y/n)?"));

		scnr.close();
		System.out.println(playerName + "'s score: " + playerScore);
		System.out.println(opponent + "'s score: " + oppScore);
		if (oppScore > playerScore + 3) {
			System.out.println(generateRudeDefeatMessage());
		} else if (oppScore > playerScore + 2) {
			System.out.println(opponent + generateOverwhelmingVictoryMessage() + player1.getName() + "!");
		} else if (playerScore > oppScore + 2) {
			System.out.println(player1.getName() + generateOverwhelmingVictoryMessage() + opponent + "!");
		} else if (playerScore > oppScore) {
			System.out.println(player1.getName() + generateCloseVictoryMessage() + opponent);
		} else if (oppScore > playerScore) {
			System.out.println(opponent + generateCloseVictoryMessage() + player1.getName());
		}
		System.out.println("Goodbye!");
	}

	private static boolean doAgain(Scanner scnr, String prompt) {
		boolean isValid = false;
		boolean decision = false;
		do {
			System.out.println(prompt);
			String userInput = scnr.nextLine();
			if (userInput.matches("[yYnN][eEoO]{0,1}[sS]{0,1}")) {
				isValid = true;
				if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
					decision = true;
					isValid = true;
				} else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
					decision = false;
					isValid = true;
				}
			} else {
				isValid = false;
				System.out.println("\"" + userInput + "\""
						+ " is not a valid option. You can type \"Y\" or \"Yes\" to continue or  \"N\" and \"No\" to exit");
			}
			// System.out.println(isValid);
		} while (!isValid);

		return decision;

	}

	private static int chooseOpp(Scanner scnr, String prompt) {
		int userInput = 0;
		boolean doAgain = false;
		do {
			System.out.println(prompt);
			try {
				userInput = scnr.nextInt();
				scnr.nextLine();
			} catch (InputMismatchException ex) {
				System.out.println("That was not a number! Lets try again.");
				scnr.nextLine();
				doAgain = true;
				continue;
			}
			doAgain = !Validator.validateNum(userInput);
		} while (doAgain);
		return userInput;
	}

	private static String keepScore(Roshambo playerChoice, Roshambo oppChoice, String opponent, String playerName) {
		if (playerChoice.equals(Roshambo.ROCK) && oppChoice.equals(Roshambo.PAPER)) {
			return opponent;
		} else if (playerChoice.equals(Roshambo.ROCK) && oppChoice.equals(Roshambo.SCISSORS)) {
			return playerName;
		} else if (playerChoice.equals(Roshambo.ROCK) && oppChoice.equals(Roshambo.ROCK)) {
			return "none";
		} else if (playerChoice.equals(Roshambo.PAPER) && oppChoice.equals(Roshambo.PAPER)) {
			return "none";
		} else if (playerChoice.equals(Roshambo.PAPER) && oppChoice.equals(Roshambo.SCISSORS)) {
			return opponent;
		} else if (playerChoice.equals(Roshambo.PAPER) && oppChoice.equals(Roshambo.ROCK)) {
			return playerName;
		} else if (playerChoice.equals(Roshambo.SCISSORS) && oppChoice.equals(Roshambo.PAPER)) {
			return playerName;
		} else if (playerChoice.equals(Roshambo.SCISSORS) && oppChoice.equals(Roshambo.SCISSORS)) {
			return "none";
		} else if (playerChoice.equals(Roshambo.SCISSORS) && oppChoice.equals(Roshambo.ROCK)) {
			return opponent;
		}
		return opponent;
	}

	private static String generateOverwhelmingVictoryMessage() {
		int randMessage = 1 + (int) (Math.random() * 3);
		if (randMessage == 1) {
			return " mopped the floor with ";
		} else if (randMessage == 2) {
			return " completely obliterated ";
		} else if (randMessage == 3) {
			return " casually stomped ";
		}
		return null;

	}

	private static String generateCloseVictoryMessage() {
		int randMessage = 1 + (int) (Math.random() * 3);
		if (randMessage == 1) {
			return " clenched a win against ";
		} else if (randMessage == 2) {
			return " narrowly defeated ";
		} else if (randMessage == 3) {
			return " barely pulled away with a win against ";
		}
		return null;
	}

	private static String generateRudeDefeatMessage() {
		int randMessage = 1 + (int) (Math.random() * 4);
		if (randMessage == 1) {
			return "As far as losers go, you win!";
		} else if (randMessage == 2) {
			return "I really hope you took notes.";
		} else if (randMessage == 3) {
			return "LMAO, you suck!";
		} else if (randMessage == 4) {
			return "You should probably retire.";
		}
		return null;
	}
}
