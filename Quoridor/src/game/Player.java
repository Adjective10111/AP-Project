 package game;

    public class Player {
        char id;
        String name = "No name";
        Bead bead;
        int walls = 10;
        public boolean skip_level = false;
        public char getId() { return this.id; }
        public String getName() { return this.name; }
        public Bead getBead() { return this.bead; }
        public int getWalls() { return this.walls; }
        public void setBead(int y, int x) { this.bead = new Bead(y, x); }
        public void decreaseWalls() { this.walls--; }

        public Player(String name, char id, int walls) {
            this.name = name;
            this.walls = walls;
            this.id = id;
            if (id == 'U')
                this.bead = new Bead(0, 8);
            else
                this.bead = new Bead(16, 8);
        }
    }
