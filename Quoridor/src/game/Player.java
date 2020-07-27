package game;

public class Player {
	char id;
	String name = "No name";
	Bead bead;
	int walls = 10;

	public char getId() { return id; }
	public String getName() { return name; }
	public Bead getBead() { return bead; }
	public int getWalls() { return walls; }

	public Player(char id) {
		this.id = id;
		if (id == 'D')
			this.bead = new Bead(8, 0);
		else
			this.bead = new Bead(8, 16);
	}
}
