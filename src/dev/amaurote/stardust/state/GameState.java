package dev.amaurote.stardust.state;

import java.awt.Graphics;

import dev.amaurote.stardust.GameCore;
import dev.amaurote.stardust.entities.creatures.Player;

public class GameState extends State {

	private Player player;

	public GameState(GameCore game) {
		super(game);
		player = new Player(game, 0, 0, 40, 40, 100);
	}

	@Override
	public void tick() {
		player.tick();
	}

	@Override
	public void render(Graphics g) {
		player.render(g);
	}

}
