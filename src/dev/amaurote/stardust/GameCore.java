package dev.amaurote.stardust;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.amaurote.stardust.display.Display;
import dev.amaurote.stardust.field.Field;
import dev.amaurote.stardust.gfx.Assets;
import dev.amaurote.stardust.gfx.Camera;
import dev.amaurote.stardust.input.KeyManager;
import dev.amaurote.stardust.state.GameState;
import dev.amaurote.stardust.state.MenuState;
import dev.amaurote.stardust.state.State;

public class GameCore implements Runnable {

	////////////////////////////////////////////////////////////////////////////////
	// OBJECTS
	private Display display;
	private Field field;

	// game cycle
	private Thread thread;
	private boolean running = false;

	// graphics
	private BufferStrategy bs;
	private Graphics g;
	private Camera c;

	// states
	private State gameState;
	private State menuState;

	// input
	private KeyManager keyManager;

	// frame size
	private int width, height;

	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTOR
	public GameCore(int width, int height) {
		// frame size in pixels
		this.width = width;
		this.height = height;

		keyManager = new KeyManager();

		// test
		field = new Field(20, 20,
				"{WWWWWWWWWWWWWWWWWWWWW....W.............WW....W.............WW....W.............WW----W.............WW----W.............WW----W             WWWWWWW             WW.........WWWWWWWWWWW..................WW..................WW..................WW..................WW..................WW..................WW..................WW------------------WW------------------WW------------------WWWWWWWWWWWWWWWWWWWWW}");
		c = new Camera(20, 20);
	}

	private void init() {
		display = new Display("Star Dust", width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();

		gameState = new GameState(this);
		menuState = new MenuState(this);

		State.setState(gameState);
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();

		// Clear
		g.clearRect(0, 0, width, height);

		if (State.getState() != null) {
			State.getState().render(g);
		}

		// Draw Here
		field.render(g, c);

		// End Drawing
		bs.show();
		g.dispose();

	}

	////////////////////////////////////////////////////////////////////////////////
	// TIMING
	private void tick() {
		keyManager.tick();

		if (State.getState() != null) {
			State.getState().tick();
		}
	}

	private void onTime() {

	}

	////////////////////////////////////////////////////////////////////////////////
	// CORE CYCLE
	@Override
	public void run() {

		init();

		int fps = 60;
		double timePerTick = 333333333 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;

		while (running) {
			now = System.nanoTime();
			delta = delta + (now - lastTime) / timePerTick;
			timer = timer + now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				delta--;
			}

			if (timer >= 333333333) {
				timer = 0;
				onTime();
			}

			try {
				Thread.sleep(0, 999999);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		stop();
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public synchronized void start() {
		if (running) {
			return;
		}

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}

		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
