package game;

public class Player {
    char id;
    String name = "No name";
    Bead bead;
    //int walls = 10;

    public char getId() { return id; }
    public String getName() { return name; }
    public Bead getBead() { return bead; }
    //public int getWalls() { return walls; }
    public void setBead(int y, int x) { this.bead = new Bead(y, x); }

    public Player(char id) {
        this.id = id;
        if (id == 'U')
            this.bead = new Bead(0, 8);
        else
            this.bead = new Bead(16, 8);
    }
    public Player(String name, int walls) { this.name = name; /*this.walls = walls;*/ }
}