package task.model;

import javax.swing.JFrame;

import task.view.GameFrame;

public class Game {
	Board board;
	Bird bird;
	private int roundsNumber;
	private boolean isPlaying;
	public static final int ROUNDS_MAX_NUMBER = 25;

	public Game() {
		board = new Board(15, this);
		bird = new Bird(board);
		GameFrame gf = new GameFrame(this);
		gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gf.setVisible(true);
		beginGame();
	}

	public void beginGame() {
		roundsNumber = 0;
		bird.launch();
		isPlaying = true;
	}

	public Bird getBird() {
		return bird;
	}

	public Board getBoard() {
		return board;
	}

	public int getRoundsNumber() {
		return roundsNumber;
	}

	public void updateEntities() {
		if (isPlaying) {
			bird.fly();
			board.alive();
			checkBirdStatus();
		}
	}

	public void checkBirdStatus() {
		if (!bird.isFlying) {
			if (roundsNumber < ROUNDS_MAX_NUMBER) {
				System.out.println("Round " + (roundsNumber + 1) + " finished.");
				roundsNumber++;
				bird.launch();
			} else {
				System.out.println("Game finished!");
				isPlaying = false;
			}
		}
	}

}
