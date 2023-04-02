package task.model;

import java.awt.geom.Point2D;

import task.view.GameViewport;
import task.view.paintables.Paintable;
import task.view.paintables.PointeeImage;

public class Pointee {
	private BoardSquare ownSquare;
	private Game game;
	public static double PANIC_DISTANCE = 200;
	public static double MIN_AMPLITUDE = 0.7;
	double x;
	double y;
	double time;
	double oscillationAmplitude = MIN_AMPLITUDE;
	double oscillationFrequency;
	static double jumpMaxDistance;
	private Paintable image;

	public Pointee(BoardSquare square, Game game) {
		super();
		this.game = game;
		this.ownSquare = square;
		centerInSquare();
		image = new PointeeImage(this);
		oscillationFrequency = Math.random() * 2 + 2;
	}

	public void centerInSquare() {
		this.x = ownSquare.x;
		this.y = ownSquare.y;
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
		x = ownSquare.x + oscillationAmplitude * Math.cos(2 * Math.PI * oscillationFrequency * time / 1000);
		y = ownSquare.y + oscillationAmplitude * Math.sin(2 * Math.PI * oscillationFrequency * time / 1000);
		time += GameViewport.TIME_STEP;
		watchForBird();
	}

	private void watchForBird() {
		double birdDistance = Point2D.distance(x, y, game.bird.x, game.bird.y);
		double panicAmplitude;
		jumpMaxDistance = game.getBoard().getSquareSize();
		if (birdDistance <= PANIC_DISTANCE) {
			panicAmplitude = PANIC_DISTANCE / birdDistance;
			oscillationAmplitude = Math.min(panicAmplitude, jumpMaxDistance);
			checkJumpOff();
		} else {
			oscillationAmplitude = MIN_AMPLITUDE;
		}
	}

	private void checkJumpOff() {
		double threshold = game.getBoard().getSquareSize() / 2;
		if (Math.abs(x - ownSquare.x) > threshold || Math.abs(y - ownSquare.y) > threshold) {
			ownSquare.getPointees().remove(this);
			ownSquare = game.getBoard().defineNearestSquare(x, y);
			ownSquare.getPointees().add(this);
			System.out.println("Own square changed!");
		}
	}

}
