package task.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import task.game.Game;

public class ViewportListener extends MouseAdapter {
	private int dx;
	private int dy;
	private Game game;

	public ViewportListener(Game game) {
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {
		game.getBird().setXY(e.getX(), e.getY());
	}

	public void mouseDragged(MouseEvent e) {
		dx = e.getX() - (int) game.getBird().getX();
		dy = e.getY() - (int) game.getBird().getY();
		game.getBird().setVelocity(dx, dy);
	}
}
