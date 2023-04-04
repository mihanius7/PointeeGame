package task.model;

import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import task.view.GameControls;
import task.view.GameFrame;

public class Game {
	Board board;
	Bird bird;
	GameControls controls;
	private int roundsPlayed;
	private boolean isPlaying;
	public static final int BOARD_SIZE = 15;
	public static final int ROUNDS_MAX_NUMBER = 100;
	public static final int[] ROUNDS_FOR_REDEEM = { 25, 50, 100 };

	public Game() {
		newGame();
		bird = new Bird(board);
		bird.toStartPostion();
		GameFrame gf = new GameFrame(this);
		gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gf.setVisible(true);
		newGameMessage();
	}

	public void newGame() {
		board = new Board(BOARD_SIZE, this);
		roundsPlayed = 0;
		isPlaying = true;
		bird = new Bird(board);
		bird.toStartPostion();
	}

	public Bird getBird() {
		return bird;
	}

	public Board getBoard() {
		return board;
	}

	public int getRoundsNumber() {
		return roundsPlayed;
	}

	public void updateEntities() {
		if (isPlaying) {
			bird.fly();
			board.alive();
			checkBirdStatus();
			if (controls != null)
				controls.updateControls();
		}
	}

	public void setControls(GameControls controls) {
		this.controls = controls;
	}

	public void checkBirdStatus() {
		if (bird.getStatus() == BirdStatus.FINISHED) {
			roundsPlayed++;
			if (roundsPlayed < ROUNDS_MAX_NUMBER) {
				bird.toStartPostion();
			} else {
				System.out.println("Game finished!");
				isPlaying = false;
			}
		}
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public boolean isRedeemPermitted() {
		boolean permitted = false;
		for (int i = 0; i < ROUNDS_FOR_REDEEM.length; i++) {
			if (roundsPlayed == ROUNDS_FOR_REDEEM[i]) {
				permitted = true;
				break;
			}
		}
		return permitted;
	}

	public Coupon redeemBestCoupon() {
		Coupon coupon = null;
		if (isRedeemPermitted()) {
			coupon = board.defineBestSquare().getCoupon();
			JOptionPane.showInternalMessageDialog(null,
					"Get your a coupon with worth " + coupon.getWorth() + " points!", "Condratulations",
					JOptionPane.INFORMATION_MESSAGE);
			newGame();
			newGameMessage();
		} else {
			JOptionPane.showInternalMessageDialog(null,
					"You can redeem a coupon only after " + Arrays.toString(ROUNDS_FOR_REDEEM) + " rounds.",
					"Not now jet", JOptionPane.WARNING_MESSAGE);
		}
		return coupon;
	}

	private void newGameMessage() {
		JOptionPane.showInternalMessageDialog(null, "Send a bird in any direction and velocity by mouse clicking and dragging",
				"New game", JOptionPane.INFORMATION_MESSAGE);
	}

}
