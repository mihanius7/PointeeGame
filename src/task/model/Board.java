package task.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import task.game.Game;
import task.view.GameFrame;
import task.view.paintables.BoardImage;
import task.view.paintables.Paintable;

public class Board {
	public static final int DEFAULT_SIZE = 600;
	private List<BoardSquare> squares;
	private Paintable image;
	private Game game;
	private int squareSize;
	int cornerX;
	int cornerY;
	int size;

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

	public void aliveSquares() {
		for (BoardSquare bs : squares) {
			bs.movePointees();
		}
	}

	public boolean isPointAboveBoard(double x, double y) {
		BoardSquare bs = defineNearestSquare(x, y);
		return Point2D.distance(bs.x, bs.y, x, y) < squareSize;
	}

	public BoardSquare defineNearestSquare(double x, double y) {
		double minDistanceSq = Double.MAX_VALUE;
		double currentDistanceSq;
		BoardSquare nearestSquare = squares.get(0);
		for (BoardSquare square : squares) {
			currentDistanceSq = Point2D.distanceSq(x, y, square.x, square.y);
			if (currentDistanceSq < minDistanceSq) {
				minDistanceSq = currentDistanceSq;
				nearestSquare = square;
			}
		}
		return nearestSquare;
	}

	public BoardSquare defineBestSquare() {
		int maxPointsSquare = 0;
		int currentSquare;
		BoardSquare bestSquare = squares.get(0);
		for (BoardSquare square : squares) {
			currentSquare = square.getPoints();
			if (currentSquare > maxPointsSquare) {
				maxPointsSquare = currentSquare;
				bestSquare = square;
			}
		}
		return bestSquare;
	}

	public int defineOverallPoints() {
		int points = 0;
		for (int i = 0; i < squares.size(); i++) {
			points += squares.get(i).getPoints();
		}
		return points;
	}

}
