package game;

public class Wall {

    private final int EMPTY = 0;

    private BoardGraph boardGraph;
    private int Player1_walls = 10;
    private int Player2_walls = 10;

    public Wall() {
        boardGraph = new BoardGraph();
    }

    public boolean placeWall(int[][] board, Player player1, Player player2, Player turn, int x, int y) {
        int wallRemaining = turn.getId() == 'U' ? Player1_walls : Player2_walls;

        //horizontal Wall
        if ( (wallRemaining > 0 && y % 2 == 0 && x % 2 == 1 && board[x][y + 1] == EMPTY && board[x][y + 2] == EMPTY) ||
             (wallRemaining > 0 && y % 2 == 1 && x % 2 == 0 && board[x + 1][y] == EMPTY && board[x + 2][y] == EMPTY) ) {

            if (allowedWall(player1, player2, x, y)) {

                //decrease number of player walls
                if (turn.getId() == 'U')
                    Player1_walls--;
                else
                    Player2_walls--;

                return true;
            }

        }
        return false;
    }

    //Checks that no bead is locked
    private boolean allowedWall(Player player1, Player player2, int x, int y) {

        return boardGraph.allowedRemoveEdge(player1, player2, x, y);

    }

}
