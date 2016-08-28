package dev.amaurote.stardust.entities.creatures;

import dev.amaurote.stardust.entities.Entity;

public abstract class Creature extends Entity {

	protected int health;
	
	public Creature(int x, int y, int width, int height, int health) {
		super(x, y, width, height);
		this.health = health;
	}

	
	
}
