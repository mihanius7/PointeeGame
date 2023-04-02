package task.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import task.model.Game;

public class GameViewport extends JPanel implements ActionListener {
	public static final int TIME_STEP = 25;
	private Timer timer;
	private Game game;

	public GameViewport(Game game) {
		this.game = game;

		timer = new Timer(TIME_STEP, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == timer) {
			game.updateEntities();
			repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		paintBackground(g);
		game.getBoard().getImage().paintOn(g);
		game.getBird().getImage().paintOn(g);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	private void paintBackground(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}
