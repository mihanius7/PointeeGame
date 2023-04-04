package task.view.paintables;

import java.awt.Color;
import java.awt.Graphics;

import task.model.Board;
import task.model.BoardSquare;
import task.model.Pointee;

public class BoardImage implements Paintable {
	private Board board;

	public BoardImage(Board b) {
		this.board = b;
	}

	public void paintOn(Graphics g) {
		for (int i = 0; i < board.getSquares().size(); i++) {
			BoardSquare bs = board.getSquares().get(i);
			int x = bs.getX();
			int y = bs.getY();
			g.setColor(Color.GRAY);
			g.drawRect(x - board.getSquareSize() / 2, y - board.getSquareSize() / 2, board.getSquareSize(), board.getSquareSize());
			g.drawString(String.valueOf(bs.getPointees().size()), x - board.getSquareSize() / 2 + 3, y + board.getSquareSize() / 2 - 2);
			for (int n = 0; n < bs.getPointees().size(); n++) {
				Pointee p = bs.getPointees().get(n);
				p.getImage().paintOn(g);
			}
		}
	}

}
