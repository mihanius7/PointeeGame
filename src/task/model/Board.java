package task.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import task.view.GameFrame;
import task.view.paintables.BoardImage;
import task.view.paintables.Paintable;

public class Board {
	private static final int DEFAULT_SIZE = 600;
	private List<BoardSquare> squares;
	private Paintable image;
	private Game game;
	int cornerX;
	int cornerY;
	int size;
	int squareSize;

	public Board(int squaresOnSide, Game game) {
		super();
		this.size = (int) Math.min(DEFAULT_SIZE, GameFrame.VIEW_HEIGHT - 40);
		centerInViewport();
		this.squares = new ArrayList<>();
		this.game = game;
		generateBoard(squaresOnSide);
		this.image = new BoardImage(this);
	}

	private void centerInViewport() {
		this.cornerX = GameFrame.VIEW_WIDTH / 2 - size / 2 - 75;
		this.cornerY = GameFrame.VIEW_HEIGHT / 2 - size / 2;
	}

	private void generateBoard(int squaresOnSide) {
		squareSize = this.size / squaresOnSide;
		System.out.println("board x " + cornerX);
		System.out.println("board y " + cornerY);
		System.out.println("board size " + size);
		System.out.println("square size " + squareSize);
		for (int j = 0; j < squaresOnSide; j++) {
			for (int i = 0; i < squaresOnSide; i++) {
				int sqX = this.cornerX + squareSize * i + squareSize / 2;
				int sqY = this.cornerY + squareSize * j + squareSize / 2;
				BoardSquare sq = new BoardSquare(sqX, sqY);
				sq.addOnePointee(this.game);
				squares.add(sq);
			}
		}
	}

	public List<BoardSquare> getSquares() {
		return squares;
	}

	public Paintable getImage() {
		return image;
	}

	public int getSquareSize() {
		return squareSize;
	}

	public void alive() {
		for (BoardSquare bs : squares) {
			bs.movePointees();
		}
	}

	public BoardSquare defineNearestSquare(double x, double y) {
		double minDistanceSq = Double.MAX_VALUE;
		double distanceSq;
		BoardSquare nearest = squares.get(0);
		for (BoardSquare bs : squares) {
			distanceSq = Point2D.distanceSq(x, y, bs.x, bs.y);
			if (distanceSq < minDistanceSq) {
				minDistanceSq = distanceSq;
				nearest = bs;
			}
		}
		return nearest;
	}

}
