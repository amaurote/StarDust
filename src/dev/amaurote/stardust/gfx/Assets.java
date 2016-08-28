package dev.amaurote.stardust.gfx;

import java.awt.image.BufferedImage;

import dev.amaurote.stardust.Main;

public class Assets {

	private static int width = Main.tileWidth;
	private static int height = Main.tileHeight;

	public static BufferedImage empty, dust, wall, water, stone;

	public static void init() {
		SpriteSheet sprites = new SpriteSheet(ImageLoader.loadImage("/textures/sprites.png"));

		empty = sprites.crop(0, 0, width, height);
		dust = sprites.crop(width, 0, width, height);
		wall = sprites.crop(width * 2, 0, width, height);
		water = sprites.crop(width * 3, 0, width, height);

		stone = sprites.crop(0, height, width, height);
	}

}
