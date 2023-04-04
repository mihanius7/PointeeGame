package task.model;

import java.awt.geom.Point2D;

import task.view.GameViewport;
import task.view.paintables.Paintable;
import task.view.paintables.PointeeImage;

public class Pointee {
	private BoardSquare ownSquare;
	private Game game;
	public static double PANIC_DISTANCE = 200;
	public static double MIN_AMPLITUDE = 0;
	public static double MIN_FREQUENCY = 1;
	public static double MAX_FREQUENCY = 7;
	double x;
	double y;
	static double time;
	double oscillationAmplitude;
	double oscillationFrequency;
	short oscillationSign;
	static double jumpMaxDistance;
	private Paintable image;

	public Pointee(BoardSquare square, Game game) {
		super();
		this.game = game;
		this.ownSquare = square;
		image = new PointeeImage(this);
		oscillationSign = (short) ((Math.random() > 0.5) ? 1 : -1);
		centerInSquare();
	}

	public void centerInSquare() {
		this.x = ownSquare.x;
		this.y = ownSquare.y;
		calm();
	}

	private void calm() {
		oscillationFrequency = Math.random() * (MAX_FREQUENCY - MIN_FREQUENCY) + MIN_FREQUENCY;
		oscillationAmplitude = MIN_AMPLITUDE;
	}

	public BoardSquare getSquare() {
		return ownSquare;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Paintable getImage() {
		return image;
	}

	public void move() {
		watchForBird();
		time += GameViewport.TIME_STEP;
		x = ownSquare.x
				+ oscillationAmplitude * Math.cos(Math.toRadians(oscillationFrequency) * time / 1000 * oscillationSign);
		y = ownSquare.y
				+ oscillationAmplitude * Math.sin(Math.toRadians(oscillationFrequency) * time / 1000 * oscillationSign);
	}

	private void watchForBird() {
		double birdDistance = Point2D.distance(x, y, game.bird.x, game.bird.y);
		double panicAmplitude;
		jumpMaxDistance = game.getBoard().getSquareSize();
		if (birdDistance <= PANIC_DISTANCE) {
			panicAmplitude = PANIC_DISTANCE / birdDistance + MIN_AMPLITUDE;
			oscillationAmplitude = Math.min(panicAmplitude, jumpMaxDistance);
			checkJumpOff();
		} else {
			calm();
		}
	}

	private void checkJumpOff() {
		double threshold = game.getBoard().getSquareSize() / 2;
		if (Math.abs(x - ownSquare.x) > threshold || Math.abs(y - ownSquare.y) > threshold) {
			ownSquare.getPointees().remove(this);
			ownSquare = game.getBoard().defineNearestSquare(x, y);
			ownSquare.getPointees().add(this);
			centerInSquare();
		}
	}

}
