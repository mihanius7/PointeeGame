package task.model;

import java.awt.geom.Point2D;

import task.view.BirdImage;
import task.view.GameFrame;
import task.view.GameViewport;
import task.view.Paintable;

public class Bird {
	double x;
	double y;
	double velocity;
	double angle;
	boolean isFlying;
	Paintable image;

	public Bird() {
		super();
		this.image = new BirdImage(this);
	}

	public Point2D.Float getLocation() {
		Point2D.Float location = new Point2D.Float();
		location.setLocation(x, y);
		return location;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getVelocity() {
		return velocity;
	}

	public double getAngle() {
		return angle;
	}

	public void fly() {
		if (isFlying) {
			double dt = GameViewport.TIME_STEP / 1000.0;
			x += dt * velocity * Math.cos(angle / 180 * Math.PI);
			y += dt * velocity * Math.sin(angle / 180 * Math.PI);
			if (x > GameFrame.VIEW_WIDTH || x < 0 || y > GameFrame.VIEW_HEIGHT || y < 0) {
				isFlying = false;
			}
		}
	}

	public void launch() {
		x = 0;
		y = Math.random() * GameFrame.VIEW_WIDTH;
		velocity = Math.random() * 25 + 75;
		angle = Math.random() * 90 - 45;
		isFlying = true;
	}

	public Paintable getImage() {
		return image;
	}

}
