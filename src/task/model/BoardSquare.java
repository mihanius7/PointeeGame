package task.model;

import java.util.ArrayList;
import java.util.List;

import task.game.Game;

public class BoardSquare {
	int x;
	int y;
	private List<Pointee> pointees;
	
	public BoardSquare(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		pointees = new ArrayList<Pointee>();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean addOnePointee(Game game) {
		return pointees.add(new Pointee(this, game));
	}

	public List<Pointee> getPointees() {
		return pointees;
	}
	
	public int getPoints() {
		return pointees.size();
	}

	public void movePointees() {
		for (int i = 0; i < pointees.size();i++) {
			pointees.get(i).move();
		}
	}
	
	public Coupon getCoupon() {
		return new Coupon(pointees.size());		
	}

}
