package game;

public class Player {
	Bead bead;
	int walls = 10;
	int number;
	String name;

	public int getNumber() { return number; }
	public Bead getBead() { return bead; }

	public Player(int number) {
		this.number = number;
		if (number == 1)
			this.bead = new Bead(8, 0);
		else
			this.bead = new Bead(8, 16);
	}
}
