package dev.amaurote.stardust.gfx;

public class Camera {
	private final int cWidth, cHeight;
	private int fieldWidth, fieldHeight;
	private int cX, cY;
	
	public Camera(int fieldWidth, int fieldHeight) {
		// size of camera in Tiles
		cWidth = 17;
		cHeight = 11;
		
		// size of field in Tiles
		this.fieldWidth = fieldWidth;
		this.fieldHeight = fieldHeight;
		
		// camera start coordinates
		cX = 0;
		cY = 0;
	}
	
	public void setCamera(int cX, int cY) {
		if (cX < 0) {
			cX = 0;
		}
		
		if (cX > fieldWidth - cWidth) {
			cX = fieldWidth - cWidth;
		}
		
		if (cY < 0) {
			cY = 0;
		}
		
		if (cY > fieldHeight - cHeight) {
			cY = fieldHeight - cHeight;
		}
		
		this.cX = cX;
		this.cY = cY;
	}

	public int getCWidth() {
		return cWidth;
	}

	public int getCHeight() {
		return cHeight;
	}

	public int getCX() {
		return cX;
	}

	public int getCY() {
		return cY;
	}
	
	
}
