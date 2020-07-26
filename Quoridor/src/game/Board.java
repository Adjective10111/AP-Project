package game;

import java.util.Arrays;
import java.util.InputMismatchException;

public class Board{
	private int[][] board = new int[17][17];
	Player player1 = new Player('D');
	Player player2 = new Player('U');
	Player turn = player1;

	public Board(){
		for(int[] ints : board)
			Arrays.fill(ints,0);
		board[0][8] = 8;
		board[16][8] = 8;
	}

	public int[][] getBoard() { return board; }
	public Player getTurn() { return turn; }

	public void turn() {
		if (this.turn == player1)
			turn = player2;
		else
			turn = player1;
	}

	public void move(int x, int y) throws InputMismatchException {
		if (canMove(x, y)) {
			board[turn.bead.getY()][turn.bead.getX()] = 0;
			turn.bead.setX(x);
			board[turn.bead.getY()][turn.bead.getX()] = (turn.id == 'D')? 1 : 2;
		}
        else
			throw new InputMismatchException("invalid place");

		turn();
	}

	public boolean canMove(int x, int y) {
		return (turn.bead.getX() + 2 == x && turn.bead.getY() == y)
				|| (turn.bead.getX() - 2 == x && turn.bead.getY() == y)
				|| (turn.bead.getY() + 2 == y && turn.bead.getX() == x)
				|| (turn.bead.getY() - 2 == y && turn.bead.getX() == x);
	}
}