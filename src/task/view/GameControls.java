package task.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import task.model.BirdStatus;
import task.model.Game;

public class GameControls extends JPanel {
	JLabel l1;
	JButton b1;
	Game game;
	
	public GameControls(Game game) {
		
		this.game = game;
		game.setControls(this);
		
		l1 = new JLabel("Rounds played: 0");
		l1.setPreferredSize(new Dimension(140, 24));
		add(l1);
		
		b1 = new JButton("Launch");
		b1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	game.getBird().launch();
		    }});
		b1.setPreferredSize(new Dimension(140, 24));
		add(b1);
	}
	
	public void updateControls() {
		l1.setText("Rounds played: " + game.getRoundsNumber());
		b1.setEnabled(game.getBird().getStatus() != BirdStatus.FLYING);
	}

}
