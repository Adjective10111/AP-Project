package game;

public class Player {
	Bead bead;
	int walls = 10;
	int number;
	String name;

	public int getNumber() { return number; }
	public Bead getBead() { return bead; }

	public Player(int x, int y) {
		this.bead = new Bead(x, y);
	}
}
