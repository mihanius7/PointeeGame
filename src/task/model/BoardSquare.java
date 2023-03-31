package task.model;

import java.util.ArrayList;
import java.util.List;

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
	
	public boolean addOnePointee(Bird bird) {
		return pointees.add(new Pointee(this, bird));
	}

	public List<Pointee> getPointees() {
		return pointees;
	}
	
	public int getPoints() {
		return pointees.size();
	}

	public void movePointees() {
		for (Pointee p : pointees) {
			p.move();
		}
	}
	
}
