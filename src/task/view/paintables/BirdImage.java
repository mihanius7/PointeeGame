package task.view.paintables;

import java.awt.Color;
import java.awt.Graphics;

import task.model.Bird;

public class BirdImage implements Paintable {
	public static final int BIRD_SIZE = 15;
	public static final int BIRD_VECTOR_LENGTH = 75;
	private Bird bird;

	public BirdImage(Bird b) {
		this.bird = b;
	}

	public void paintOn(Graphics g) {
		drawVelocityVector(g);
		g.setColor(Color.RED);
		g.fillOval((int) Math.round(bird.getX() - BIRD_SIZE / 2), (int) Math.round(bird.getY() - BIRD_SIZE / 2),
				BIRD_SIZE, BIRD_SIZE);

	}
	private void drawVelocityVector(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		drawArrowLine(g, (int) bird.getX(), (int) bird.getY(),
				(int) (bird.getX() + BIRD_VECTOR_LENGTH * Math.cos(bird.getAngle())),
				(int) (bird.getY() + BIRD_VECTOR_LENGTH * Math.sin(bird.getAngle())), 12, 5);
	}

	private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - d, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;

		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;

		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;

		int[] xpoints = { x2, (int) xm, (int) xn };
		int[] ypoints = { y2, (int) ym, (int) yn };

		g.drawLine(x1, y1, x2, y2);
		g.fillPolygon(xpoints, ypoints, 3);
	}
}
