package dev.amaurote.stardust.state;

import java.awt.Graphics;

import dev.amaurote.stardust.GameCore;

public abstract class State {
	
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	// CLASS
	protected GameCore game;
	
	public State(GameCore game) {
		this.game = game;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
