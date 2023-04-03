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
	public static final int ROUNDS_MAX_NUMBER = 100;
	public static final int[] ROUNDS_FOR_REDEEM = { 25, 50, 100 };

	public Game() {
		board = new Board(15, this);
		bird = new Bird(board);
		GameFrame gf = new GameFrame(this);
		gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gf.setVisible(true);
		beginGame();
	}

	public void beginGame() {
		roundsPlayed = 0;
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
		return roundsPlayed;
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
			roundsPlayed++;
			if (roundsPlayed < ROUNDS_MAX_NUMBER) {
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
					"You redeemed a coupon with worth " + coupon.getWorth() + " points.", "Condratulations",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showInternalMessageDialog(null,
					"You can redeem a coupon only after " + Arrays.toString(ROUNDS_FOR_REDEEM) + " rounds.", "Not now jet",
					JOptionPane.WARNING_MESSAGE);
		}
		return coupon;
	}

}
