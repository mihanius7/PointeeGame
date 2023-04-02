package task.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import task.model.Game;

public class GameFrame extends JFrame {

	public static final int VIEW_WIDTH = 900;
	public static final int VIEW_HEIGHT = 512;

	public GameFrame(Game game) {
		setTitle("Pointee game");

		GameControls gc = new GameControls(game);
		gc.setPreferredSize(new Dimension(150, 0));
		add(gc, BorderLayout.LINE_START);

		GameViewport gv = new GameViewport(game);
		add(gv, BorderLayout.CENTER);

		setBounds(0, 0, VIEW_WIDTH, VIEW_HEIGHT + 40);
	}

}
