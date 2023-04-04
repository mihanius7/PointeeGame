package task.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
	RenderingHints rh;

	public GameViewport(Game game) {
		this.game = game;

		rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		timer = new Timer(TIME_STEP, this);
		timer.start();
		
		addMouseListener(new ViewportListener(game));
		addMouseMotionListener(new ViewportListener(game));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == timer) {
			game.process();
			repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHints(rh);
		paintBackground(g2d);
		game.getBoard().getImage().paintOn(g2d);
		game.getBird().getImage().paintOn(g2d);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	private void paintBackground(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}
