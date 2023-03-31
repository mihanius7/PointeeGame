package task.model;

import java.awt.geom.Point2D;

import task.view.GameViewport;
import task.view.Paintable;
import task.view.PointeeImage;

public class Pointee {
	private BoardSquare square;
	private Bird bird;
	double x;
	double y;
	double time;
	double oscillationAmplitude;
	double oscillationFrequency;
	double panicDistance = 75;
	private Paintable image;

	public Pointee(BoardSquare square, Bird bird) {
		super();
		this.square = square;
		this.bird = bird;
		centerInSquare();
		image = new PointeeImage(this);
		oscillationFrequency = Math.random() * 5 + 2;
	}

	public void centerInSquare() {
		this.x = square.x;
		this.y = square.y;
	}

	public BoardSquare getSquare() {
		return square;
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
		x += oscillationAmplitude * Math.cos(2 * Math.PI * oscillationFrequency * time / 1000);
		y += oscillationAmplitude * Math.sin(2 * Math.PI * oscillationFrequency * time / 1000);
		time += GameViewport.TIME_STEP;
		watchForBird();
	}

	private void watchForBird() {
		double birdDistance = Point2D.distance(x, y, bird.x, bird.y);
		if (birdDistance <= panicDistance) {
			oscillationAmplitude = panicDistance / birdDistance;
		} else {
			oscillationAmplitude = 0;
			changeSquare();
			centerInSquare();
		}
	}

	private void changeSquare() {
		
	}

}
