package task.model;

import javax.swing.JFrame;

import task.view.GameControls;
import task.view.GameFrame;

public class Game {
	Board board;
	Bird bird;
	GameControls controls;
	private int roundsNumber;
	private boolean isPlaying;
	public static final int ROUNDS_MAX_NUMBER = 100;
	public static final int[] ROUNDS_FOR_REDEEM = {25, 50, 100};

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
		bird.toStartPostion();
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

	public void setControls(GameControls controls) {
		this.controls = controls;
	}

	public void checkBirdStatus() {
		if (bird.getStatus() == BirdStatus.FINISHED) {
			roundsNumber++;
			if (roundsNumber < ROUNDS_MAX_NUMBER) {
				bird.toStartPostion();
			} else {
				System.out.println("Game finished!");
				isPlaying = false;
			}
		}
		if (controls != null)
			controls.updateControls();
	}

	public boolean isPlaying() {
		return isPlaying;
	}	

}
