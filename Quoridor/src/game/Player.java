package game;

public class Player {
    private Bead bead;
    private char id;

    public Player(int x, int y, char id) {
        this.bead = new Bead(x, y);
        this.id = id;
    }

    public Bead getBead() {
        return bead;
    }

    public char getId() {
        return id;
    }

}
