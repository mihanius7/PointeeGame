package task.view;

import java.awt.Color;
import java.awt.Graphics;

import task.model.Pointee;

public class PointeeImage implements Paintable {
	public static final int POINTEE_SIZE = 8;
	private final Color color;
	private Pointee pointee;

	public PointeeImage(Pointee pointee) {
		super();
		this.pointee = pointee;
		this.color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
	}

	@Override
	public void paintOn(Graphics g) {
		g.setColor(color);
		g.fillOval((int) Math.round(pointee.getX() - POINTEE_SIZE / 2),
				(int) Math.round(pointee.getY() - POINTEE_SIZE / 2), POINTEE_SIZE, POINTEE_SIZE);
	}

}
