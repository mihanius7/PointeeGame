package task.model;

import java.util.ArrayList;
import java.util.List;

import task.view.BoardImage;
import task.view.GameFrame;
import task.view.Paintable;

public class Board {
	private static final int DEFAULT_SIZE = 600;
	private List<BoardSquare> squares;
	private Paintable image;
	private Game game;
	private int x;
	private int y;
	private int size;
	private int squareSize;

	public Board(int squaresOnSide, Game game) {
		super();
		this.size = (int) Math.min(DEFAULT_SIZE, GameFrame.VIEW_HEIGHT);
		centerInViewport();
		this.squares = new ArrayList<>();
		this.game = game;
		generateBoard(squaresOnSide);
		this.image = new BoardImage(this);
	}

	private void centerInViewport() {
		this.x = GameFrame.VIEW_WIDTH / 2 - size / 2;
		this.y = GameFrame.VIEW_HEIGHT / 2 - size / 2;
	}

	private void generateBoard(int squaresOnSide) {
		squareSize = this.size / squaresOnSide;
		System.out.println("board x " + x);
		System.out.println("board y " + y);
		System.out.println("board size " + size);
		System.out.println("square size " + squareSize);
		for (int j = 0; j < squaresOnSide; j++) {
			for (int i = 0; i < squaresOnSide; i++) {
				int sqX = this.x + squareSize * i + squareSize / 2;
				int sqY = this.y + squareSize * j + squareSize / 2;
				BoardSquare sq = new BoardSquare(sqX, sqY);
				sq.addOnePointee(game.getBird());
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

}
