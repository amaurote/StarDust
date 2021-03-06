package dev.amaurote.stardust.entities;

import java.awt.Graphics;

public abstract class Entity {

	protected int x, y;
	protected int width, height;

	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
