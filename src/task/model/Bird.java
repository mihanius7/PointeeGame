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
	Board board;
	public static final double MAX_VELOCITY = 500;
	public static final double BOUNDARY_MARGIN = 100;

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
			x += dt * velocity * Math.cos(angle);
			y += dt * velocity * Math.sin(angle);
			if (isOutOfBoundary()) {
				status = BirdStatus.FINISHED;
			}
		}
	}

	private boolean isOutOfBoundary() {
		return (x > board.cornerX + board.size + BOUNDARY_MARGIN || x < board.cornerX - BOUNDARY_MARGIN
				|| y > board.cornerY + board.size + BOUNDARY_MARGIN || y < board.cornerY - BOUNDARY_MARGIN);
	}

	public void toStartPostion() {
		x = board.cornerX - BOUNDARY_MARGIN;
		y = GameFrame.VIEW_HEIGHT / 2;
		velocity = 250;
		angle = 0;
		status = BirdStatus.READY_TO_FLY;
	}

	public void launch() {
		status = BirdStatus.FLYING;
	}

	public Paintable getImage() {
		return image;
	}

	public BirdStatus getStatus() {
		return status;
	}

	public void setVelocity(int vx, int vy) {
		if (status != BirdStatus.FLYING) {
			velocity = Math.min(Math.sqrt(vx * vx + vy * vy), MAX_VELOCITY);
			angle = Math.atan2(vy, vx);
		}
	}

	public void setXY(int newX, int newY) {
		if (status != BirdStatus.FLYING && !board.isPointAboveBoard(newX, newY)) {
			x = newX;
			y = newY;
		}
	}

}
