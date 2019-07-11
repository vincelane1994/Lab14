package co.grandcircus;

import java.util.Scanner;

public class RockPlayer extends Player{
	private String name;
	
	public RockPlayer() {};
	
	public RockPlayer(String name) {
		super(name);
		this.name = name;
	}

	@Override
	public Roshambo generateRoshambo() {
		return Roshambo.ROCK;
	}
	
	

}
