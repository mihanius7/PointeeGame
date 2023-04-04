package task.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import task.game.Game;
import task.view.paintables.BackgroundImage;

public class GameViewport extends JPanel implements ActionListener {
	public static final int TIME_STEP = 25;
	private Timer timer;
	private Game game;
	private ViewportListener listener;
	private BackgroundImage image;
	private RenderingHints rh;

	public GameViewport(Game game) {
		this.game = game;

		rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		timer = new Timer(TIME_STEP, this);
		timer.start();
		
		image = new BackgroundImage(this);
		
		listener = new ViewportListener(game);
		addMouseListener(listener);
		addMouseMotionListener(listener);
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
		image.paintOn(g2d);
		game.getBoard().getImage().paintOn(g2d);
		game.getBird().getImage().paintOn(g2d);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

}
