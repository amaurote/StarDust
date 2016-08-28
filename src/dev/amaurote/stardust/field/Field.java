package dev.amaurote.stardust.field;

import java.awt.Graphics;

import dev.amaurote.stardust.Main;
import dev.amaurote.stardust.field.Tile.TileType;
import dev.amaurote.stardust.gfx.Assets;
import dev.amaurote.stardust.gfx.Camera;

public class Field {

	////////////////////////////////////////////////////////////////////////////////
	// OBJECTS
	private final int fieldSizeX;
	private final int fieldSizeY;
	
	private final Tile[][] tiles;

	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTOR
	public Field(int fieldSizeX, int fieldSizeY, String map) {
		
		// field size in Tiles
		this.fieldSizeX = fieldSizeX;
		this.fieldSizeY = fieldSizeY;

		tiles = new Tile[fieldSizeX][fieldSizeY];

		setMap(map);
	}

	// TODO konstruktor iba s parametrom String map

	////////////////////////////////////////////////////////////////////////////////
	// PRIVATE METHODS

	// SET MAP
	private void setMap(String map) {
		// Clear Tile Field
		for (int y = 0; y < fieldSizeY; y++) {
			for (int x = 0; x < fieldSizeX; x++) {
				tiles[x][y] = new Tile();
			}
		}

		// Set Map
		boolean readingMapField = false;
		int x = 0;
		int y = 0;

		for (int a = 0; a < map.length(); a++) {

			// field of map data ended, or too many data
			if (map.charAt(a) == '}' || y >= fieldSizeY) {
				return;
			}

			// field of map data
			if (readingMapField) {
				switch (map.charAt(a)) {

				case ' ': // empty
					x++;
					break;

				case '.': // dust
					tiles[x][y].setTileType(TileType.DUST);
					x++;
					break;

				case 'W': // wall
					tiles[x][y].setTileType(TileType.WALL);
					x++;
					break;

				case '-': // water
					tiles[x][y].setTileType(TileType.WATER);
					x++;
					break;
				}
			}

			// overflow
			if (x >= fieldSizeX) {
				x = 0;
				y++;
			}

			// field of map data started
			if (map.charAt(a) == '{') {
				readingMapField = true;
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////
	// PUBLIC METHODS

	// RENDER
	public void render(Graphics g, Camera c) {
		for (int y = c.getCY(); y < c.getCHeight(); y++) {
			for (int x = c.getCX(); x < c.getCWidth(); x++) {
				g.drawImage(tiles[x][y].getTexture(), (x - c.getCX()) * Main.tileWidth, (y - c.getCY()) * Main.tileHeight, null);
			}		
		}
	}
}
