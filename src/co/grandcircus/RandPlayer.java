package co.grandcircus;

public class RandPlayer extends Player{
	private String name;
	public RandPlayer(String name) {
		super(name);
		this.name = name;
	}
	@Override
	public Roshambo generateRoshambo() {
		int sallysChoice = 1 + (int)(Math.random() * 3);
		if(sallysChoice == 1 ) {
			return Roshambo.PAPER;
		}else if (sallysChoice == 2 ) {
			return Roshambo.ROCK;
		}else if (sallysChoice == 3 ) {
			return Roshambo.SCISSORS;
		}else {
			return null;
		}
		
	}
}
