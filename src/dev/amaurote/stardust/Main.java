package dev.amaurote.stardust;

public class Main {
	
	// Form size in pixels
	public static final int formWidth = 680, formHeight = 440;
	// Tile size in pixels
	public static final int tileWidth = 40, tileHeight = 40;
	
	public static void main(String[] args) {
		GameCore game = new GameCore(formWidth, formHeight);
		game.start();
	}

}
