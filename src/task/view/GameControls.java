package task.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import task.game.Game;
import task.model.BirdStatus;

public class GameControls extends JPanel {
	JLabel l1;
	JButton b1;
	JButton b2;
	JButton b3;
	Game game;

	public GameControls(Game game) {

		this.game = game;
		game.setControls(this);

		l1 = new JLabel("Rounds played: 0");
		l1.setPreferredSize(new Dimension(140, 32));
		add(l1);

		b1 = new JButton("Send bird");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.getBird().launch();
			}
		});
		b1.setPreferredSize(new Dimension(140, 48));
		b1.setBackground(Color.LIGHT_GRAY);
		add(b1);

		b2 = new JButton("Get best coupon");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.redeemBestCoupon();
			}
		});
		b2.setPreferredSize(new Dimension(140, 24));
		b2.setBackground(Color.LIGHT_GRAY);
		add(b2);
		
		b3 = new JButton("New game");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.newGame();
			}
		});
		b3.setPreferredSize(new Dimension(140, 24));
		b3.setBackground(Color.LIGHT_GRAY);
		add(b3);
	}

	public void updateControls() {
		l1.setText("Rounds played: " + game.getRoundsNumber());
		b1.setEnabled(game.getBird().getStatus() != BirdStatus.FLYING && game.isPlaying());
		b2.setEnabled(game.getBird().getStatus() != BirdStatus.FLYING);
		if (game.isRedeemPermitted() && game.getBird().getStatus() != BirdStatus.FLYING)
			b2.setBackground(Color.GREEN);
		else
			b2.setBackground(Color.LIGHT_GRAY);
	}

}
