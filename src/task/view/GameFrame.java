package task.view;

import javax.swing.JFrame;
import task.model.Game;

public class GameFrame extends JFrame {
	
	public static final int VIEW_WIDTH = 960;
	public static final int VIEW_HEIGHT = 512;

	public GameFrame(Game game) {
		setTitle("Pointee game");
		add(new GameViewport(game));
		setBounds(0, 0, VIEW_WIDTH, VIEW_HEIGHT + 40);
	}

}
