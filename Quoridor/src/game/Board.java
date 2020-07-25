package game;

import java.util.Arrays;
import java.util.InputMismatchException;

public class Board{
    private final int BEAD_CELL = 8;
    private final int EMPTY = 0;
    private final int WALL = 1;
    private int[][] board = new int[17][17];
    Player player1 = new Player(0, 8, 'U');
    Player player2 = new Player(16, 8, 'D');
    Player turn = player1;
    Wall wall = new Wall();

    public Board(){
        for(int[] ints : board)
            Arrays.fill(ints,EMPTY);
        board[0][8] = BEAD_CELL;
        board[16][8] = BEAD_CELL;
    }

    public int[][] getBoard() { return board; }

    public void turn() {
        if (this.turn == player1)
            turn = player2;
        else
            turn = player1;
    }

    public void move(int x, int y) throws InputMismatchException {
        if (turn.getBead().getX() + 2 == x && turn.getBead().getY() == y){
            board[turn.getBead().getY()][turn.getBead().getX()] = EMPTY;
            turn.getBead().setX(x);
            board[turn.getBead().getY()][turn.getBead().getX()] = BEAD_CELL;
        }
        else if (turn.getBead().getX() - 2 == x && turn.getBead().getY() == y) {
            board[turn.getBead().getY()][turn.getBead().getX()] = EMPTY;
            turn.getBead().setX(x);
            board[turn.getBead().getY()][turn.getBead().getX()] = BEAD_CELL;
        }
        else if (turn.getBead().getY() + 2 == y && turn.getBead().getX() == x) {
            board[turn.getBead().getY()][turn.getBead().getX()] = EMPTY;
            turn.getBead().setY(y);
            board[turn.getBead().getY()][turn.getBead().getX()] = BEAD_CELL;
        }
        else if (turn.getBead().getY() - 2 == y && turn.getBead().getX() == x) {
            board[turn.getBead().getY()][turn.getBead().getX()] = EMPTY;
            turn.getBead().setY(y);
            board[turn.getBead().getY()][turn.getBead().getX()] = BEAD_CELL;
        }
        else
            throw new InputMismatchException("invalid place");

        turn();
    }

    public void placeWall(int x, int y) throws InputMismatchException {
        if (wall.placeWall(board, player1, player2, turn, x, y)){
            //horizontal Wall
            if (y % 2 == 0 && x % 2 == 1) {
                board[x][y] = WALL;
                board[x][y + 1] = WALL;
                board[x][y + 2] = WALL;
            }
            //Vertical Wall
            else if (y % 2 == 1 && x % 2 == 0) {
                board[x][y] = WALL;
                board[x + 1][y] = WALL;
                board[x + 2][y] = WALL;
            }
            else
                throw new InputMismatchException("invalid place");
        }
        else
            throw new InputMismatchException("invalid place");
    }
}