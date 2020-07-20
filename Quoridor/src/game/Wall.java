package game;

public class Wall {
    private final int BEAD_CELL = -1;
    private final int MIDDLE_WALL = 0;
    private final int EMPTY_WALL = 1;
    private final int WALL = 11;

    private int board[][];
    private Bead bead1;
    private Bead bead2;
    private int bead1_walls = 8;
    private int bead2_walls = 8;

    public Wall(int[][] board, Bead bead1, Bead bead2) {
        this.board = board;
        this.bead1 = bead1;
        this.bead2 = bead2;
    }

    public void update(int[][] board, Bead bead1, Bead bead2) {
        this.board = board;
        this.bead1 = bead1;
        this.bead2 = bead2;
    }

    public boolean placeWall(boolean turn1, int x, int y) {
        if (!allowedWall(x, y))
            return false;

        //horizontal Wall
        if (x % 2 == 0 && y % 2 == 1 && board[y][x + 1] == MIDDLE_WALL &&  board[y][x + 2] == EMPTY_WALL) {

            board[y][x] = WALL;
            board[y][x + 1] = WALL;
            board[y][x + 2] = WALL;

            //decrease number of player walls
            if (turn1)
                bead1_walls--;
            else
                bead2_walls--;

            return true;
        }

        //Vertical Wall
        else if (x % 2 == 1 && y % 2 == 0 && board[y + 1][x] == MIDDLE_WALL && board[y + 2][x] == EMPTY_WALL) {

            board[y][x] = WALL;
            board[y + 1][x] = WALL;
            board[y + 2][x] = WALL;

            //decrease number of player walls
            if (turn1)
                bead1_walls--;
            else
                bead2_walls--;
            return true;
        }

        return false;
    }

    private boolean allowedWall(int x, int y) {
        //bayd check kone k age in divar gharar begire rah baste mishe ya na
        return true;
    }

}
