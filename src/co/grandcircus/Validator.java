package co.grandcircus;

public class Validator{
	public static boolean validateNum(int userInput) {
		if (userInput == 1 || userInput == 2) {
			return true;
		}else {
			System.out.println("Please only enter 1 or 2");
			return false;
		}
	}
	
	

}
