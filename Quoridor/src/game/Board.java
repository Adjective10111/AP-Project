package game;

import java.util.Arrays;
import java.util.InputMismatchException;

public class Board{
    private int[][] board = new int[17][17];
    Player player1 = new Player(8, 0);
    Player player2 = new Player(8, 16);
    Player turn = player1;

    public Board(){
        for(int[] ints : board)
            Arrays.fill(ints,0);

        board[0][8] = 8;
        board[16][8] = 8;

        player1.bead.setY(0);
        player1.bead.setX(8);


        player2.bead.setY(16);
        player2.bead.setX(8);
    }

    public void turn() {
        if (this.turn == player1) {
            turn = player2;
        }
        else {
            turn = player1;
        }
    }

    public void move(int x,int y){

        if (turn.bead.getX() + 2 == x  &&  turn.bead.getY() == y){
            board[turn.bead.getY()][turn.bead.getX()] = 0;
            turn.bead.setX(x);
            board[turn.bead.getY()][turn.bead.getX()] = 8;
        }
        else if (turn.bead.getX() - 2 == x  &&  turn.bead.getY() == y){
            board[turn.bead.getY()][turn.bead.getX()] = 0;
            turn.bead.setX(x);
            board[turn.bead.getY()][turn.bead.getX()] = 8;
        }
        else if (turn.bead.getY() + 2 == y  &&  turn.bead.getX() == x){
            board[turn.bead.getY()][turn.bead.getX()] = 0;
            turn.bead.setY(y);
            board[turn.bead.getY()][turn.bead.getX()] = 8;
        }
        else if (turn.bead.getY() - 2 == y  &&  turn.bead.getX() == x){
            board[turn.bead.getY()][turn.bead.getX()] = 0;
            turn.bead.setY(y);
            board[turn.bead.getY()][turn.bead.getX()] = 8;
        }
        else
            throw new InputMismatchException("invalid place");

        turn();
    }
}
