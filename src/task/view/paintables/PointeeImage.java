package task.view.paintables;

import java.awt.Color;
import java.awt.Graphics;

import task.model.Pointee;

public class PointeeImage implements Paintable {
	public static final int POINTEE_SIZE = 8;
	private static Color color = Color.BLUE;
	private Pointee pointee;

	public PointeeImage(Pointee pointee) {
		super();
		this.pointee = pointee;
	}

	@Override
	public void paintOn(Graphics g) {
		g.setColor(color);
		g.fillOval((int) Math.round(pointee.getX() - POINTEE_SIZE / 2),
				(int) Math.round(pointee.getY() - POINTEE_SIZE / 2), POINTEE_SIZE, POINTEE_SIZE);
	}

}
