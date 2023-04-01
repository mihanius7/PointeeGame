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
	private Board board;

	public Bird(Board board) {
		super();
		this.image = new BirdImage(this);
		this.board = board;
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
			if (x > board.cornerX + board.size + board.squareSize || x < board.cornerX - board.squareSize || y > board.cornerY + board.size + board.squareSize || y < board.cornerY - board.squareSize) {
				isFlying = false;
			}
		}
	}

	public void launch() {
		x = board.cornerX - board.squareSize;
		y = Math.random() * GameFrame.VIEW_WIDTH;
		velocity = 200;
		angle = Math.random() * 90 - 45;
		isFlying = true;
	}

	public Paintable getImage() {
		return image;
	}

}
