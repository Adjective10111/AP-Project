package game;

import java.util.Arrays;
import java.util.InputMismatchException;

public class Board{
        private char[][] board=new char[17][17];
        Player player1= new Player();
        Player player2= new Player();
        Player turn = player1;
    public Board(){
        for(char[] chars : board)
            Arrays.fill(chars,'e');
        board[0][8] = 'B';
        board[16][8] = 'B';
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
            if (((turn.bead.getX()) +2 ) == x){
                board[turn.bead.getY()][turn.bead.getX()] = 'e';
                turn.bead.setX(x);
                board[turn.bead.getY()][turn.bead.getX()] = 'B';
            }
            else if (((turn.bead.getX()) -2 ) == x){
                board[turn.bead.getY()][turn.bead.getX()] = 'e';
                turn.bead.setX(x);
                board[turn.bead.getY()][turn.bead.getX()] = 'B';
            }
            else if (((turn.bead.getY()) +2 ) == y){
                board[turn.bead.getY()][turn.bead.getX()] = 'e';
                turn.bead.setY(y);
                board[turn.bead.getY()][turn.bead.getX()] = 'B';
            }
            else if (((turn.bead.getY()) -2 ) == y){
                board[turn.bead.getY()][turn.bead.getX()] = 'e';
                turn.bead.setY(y);
                board[turn.bead.getY()][turn.bead.getX()] = 'B';
            }
            else
                throw new InputMismatchException("invalid place");


            turn();
        }

    }


