package game;

public class Player {
    Bead bead;
    Wall[] walls = new Wall[8];

    public Player(int x, int y) {
        this.bead = new Bead(x, y);
    }
}
