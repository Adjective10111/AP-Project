package game;

public class Player {
    Bead bead;
    int walls = 10;
    char id;
    String name;

    public char getId() { return id; }
    public Bead getBead() { return bead; }

    public Player(char id) {
        this.id = id;
        if (id == 'D')
            this.bead = new Bead(8, 0);
        else
            this.bead = new Bead(8, 16);
    }
}
