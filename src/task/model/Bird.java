package task.model;

import task.view.GameFrame;
import task.view.GameViewport;
import task.view.paintables.BirdImage;
import task.view.paintables.Paintable;

public class Bird {
	double x;
	double y;
	double velocity;
	double angle;
	BirdStatus status;
	Paintable image;
	private Board board;

	public Bird(Board board) {
		super();
		this.image = new BirdImage(this);
		this.board = board;
		status = BirdStatus.CREATED;
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
		if (status == BirdStatus.FLYING) {
			double dt = GameViewport.TIME_STEP / 1000.0;
			x += dt * velocity * Math.cos(angle / 180 * Math.PI);
			y += dt * velocity * Math.sin(angle / 180 * Math.PI);
			if (isOut()) {
				status = BirdStatus.FINISHED;
			}
		}
	}
	
	private boolean isOut() {
		return (x > board.cornerX + board.size + board.squareSize || x < board.cornerX - board.squareSize || y > board.cornerY + board.size + board.squareSize || y < board.cornerY - board.squareSize);
	}
	
	private void toStartPostion() {
		x = board.cornerX - board.squareSize;
		y = Math.random() * GameFrame.VIEW_HEIGHT;
		velocity = 200;
		angle = Math.random() * 90 - 45;
		status = BirdStatus.READY_TO_FLY;
	}

	public void launch() {
		toStartPostion();
		status = BirdStatus.FLYING;
	}

	public Paintable getImage() {
		return image;
	}

	public BirdStatus getStatus() {
		return status;
	}

}
