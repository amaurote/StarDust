package dev.amaurote.stardust.entities.creatures;

import java.awt.Graphics;

import dev.amaurote.stardust.GameCore;
import dev.amaurote.stardust.gfx.Assets;

public class Player extends Creature {

	private GameCore game;
	public int keyDelay;

	public Player(GameCore game, int x, int y, int width, int height, int health) {
		super(x, y, width, height, health);
		this.game = game;
		
		keyDelay = 0;
	}

	@Override
	public void tick() {
		if (keyDelay < 100) {
			keyDelay++;
		}

		if (keyDelay > 30) {
			if (game.getKeyManager().up) {
				move("up");
				keyDelay = 0;
				return;
			}
			if (game.getKeyManager().down) {
				move("down");
				keyDelay = 0;
				return;
			}
			if (game.getKeyManager().left) {
				move("left");
				keyDelay = 0;
				return;
			}
			if (game.getKeyManager().right) {
				move("right");
				keyDelay = 0;
				return;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO temp test, todo player asset and camera
		// g.drawImage(Assets.dust, x, y, width, height, null);
	}
	
	private void move(String dir) {
		
	}

}
