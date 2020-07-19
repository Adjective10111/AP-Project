package game;

import java.util.Arrays;
import java.util.InputMismatchException;

public class Board{
	private int[][] board = new int[17][17];
	private TableSync table;
	Player player1 = new Player(8, 0);
	Player player2 = new Player(8, 16);
	Player turn = player1;

	public Board(){
		for(int[] ints : board)
			Arrays.fill(ints,0);
		board[0][8] = 8;
		board[16][8] = 8;

		table = new TableSync(board);
	}

	public void turn() {
		if (this.turn == player1)
			turn = player2;
		else
			turn = player1;
	}

	public void move(int x,int y){
		if (turn.bead.getX() + 2 == x){
			board[turn.bead.getY()][turn.bead.getX()] = 0;
			turn.bead.setX(x);
			board[turn.bead.getY()][turn.bead.getX()] = 8;
		}
        else if (turn.bead.getX() - 2 == x){
			board[turn.bead.getY()][turn.bead.getX()] = 0;
			turn.bead.setX(x);
			board[turn.bead.getY()][turn.bead.getX()] = 8;
		}
        else if (turn.bead.getY() + 2 == y){
			board[turn.bead.getY()][turn.bead.getX()] = 0;
			turn.bead.setY(y);
			board[turn.bead.getY()][turn.bead.getX()] = 8;
		}
        else if (turn.bead.getY() - 2 == y){
			board[turn.bead.getY()][turn.bead.getX()] = 0;
			turn.bead.setY(y);
			board[turn.bead.getY()][turn.bead.getX()] = 8;
		}
        else
			throw new InputMismatchException("invalid place");

        table.set(board);
		turn();
	}
}