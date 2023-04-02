package task.view.paintables;

import java.awt.Color;
import java.awt.Graphics;

import task.model.Bird;

public class BirdImage implements Paintable {
	public static final int BIRD_SIZE = 15;
	private Bird bird;

	public BirdImage(Bird b) {
		this.bird = b;
	}

	public void paintOn(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval((int) Math.round(bird.getX() - BIRD_SIZE / 2), (int) Math.round(bird.getY() - BIRD_SIZE / 2),
				BIRD_SIZE, BIRD_SIZE);
	}
}
