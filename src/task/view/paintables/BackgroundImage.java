package task.view.paintables;

import java.awt.Color;
import java.awt.Graphics;

import task.view.GameViewport;

public class BackgroundImage implements Paintable {
	GameViewport viewport;
	
	public BackgroundImage(GameViewport viewport) {
		this.viewport = viewport;
	}

	@Override
	public void paintOn(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, viewport.getWidth(), viewport.getHeight());
	}

}
