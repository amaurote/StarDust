package dev.amaurote.stardust.field;

import java.awt.image.BufferedImage;

import dev.amaurote.stardust.gfx.Assets;

public class Tile {

	public enum TileType {
		EMPTY, DUST, WALL, WATER
	}

	private TileType tileType;

	private BufferedImage texture;

	private boolean solid;

	private boolean playerStepIn;
	private boolean playerStepFrom;

	private boolean monsterStepIn;
	private boolean monsterStepFrom;

	private boolean itemStepIn;
	private boolean itemStepFrom;

	private boolean consumable;
	private boolean consumer;

	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	public Tile() {
		tileType = TileType.EMPTY;
		fitParameters();
	}

	public Tile(TileType tileType) {
		this.tileType = tileType;
		fitParameters();
	}

	////////////////////////////////////////////////////////////////////////////////
	// TILE EVENTS
	public void eventPlayerStepIn() {
		if (consumable) {
			tileType = TileType.EMPTY;
			fitParameters();
		}

		if (consumer) {
			// TODO zomri
		}
	}

	public void eventItemStepIn() {
		if (consumer) {
			// TODO zhor
		}
	}

	////////////////////////////////////////////////////////////////////////////////
	// FIT PARAMETERS
	private void fitParameters() {
		if (tileType == TileType.EMPTY) {
			texture = Assets.empty;

			solid = false;
			playerStepIn = true;
			playerStepFrom = true;
			monsterStepIn = true;
			monsterStepFrom = true;
			itemStepIn = true;
			itemStepFrom = true;
			consumable = false;
			consumer = false;
		}

		if (tileType == TileType.DUST) {
			texture = Assets.dust;

			solid = false;
			playerStepIn = true;
			playerStepFrom = true;
			monsterStepIn = false;
			monsterStepFrom = false;
			itemStepIn = false;
			itemStepFrom = false;
			consumable = true;
			consumer = false;
		}

		if (tileType == TileType.WALL) {
			texture = Assets.wall;

			solid = true;
			playerStepIn = false;
			playerStepFrom = false;
			monsterStepIn = false;
			monsterStepFrom = false;
			itemStepIn = false;
			itemStepFrom = false;
			consumable = false;
			consumer = false;
		}

		if (tileType == TileType.WATER) {
			texture = Assets.water;

			solid = false;
			playerStepIn = true;
			playerStepFrom = false;
			monsterStepIn = false;
			monsterStepFrom = false;
			itemStepIn = true;
			itemStepFrom = false;
			consumable = false;
			consumer = true;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	// GETTERS SETTERS
	public void setTileType(TileType tileType) {
		this.tileType = tileType;
		fitParameters();
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
	
	public boolean isSolid() {
		return solid;
	}

	public boolean canPlayerStepIn() {
		return playerStepIn;
	}

	public boolean canPlayerStepFrom() {
		return playerStepFrom;
	}

	public boolean canMonsterStepIn() {
		return monsterStepIn;
	}

	public boolean canMonsterStepFrom() {
		return monsterStepFrom;
	}

	public boolean canItemStepIn() {
		return itemStepIn;
	}

	public boolean canItemStepFrom() {
		return itemStepFrom;
	}

}
