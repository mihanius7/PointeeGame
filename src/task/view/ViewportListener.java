package task.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import task.model.Game;

public class ViewportListener extends MouseAdapter {
	int x1;
	int y1;
	int dx;
	int dy;
	Game game;

	public ViewportListener(Game game) {
		this.game = game;
	}

	public void mouseReleased(MouseEvent e) {
		dx = e.getX() - (int) game.getBird().getX();
		dy = e.getY() - (int) game.getBird().getY();
		game.getBird().setVelocity(dx, dy);
		System.out.println("dx = " + dx + ", dy = " + dy);
	}
}
