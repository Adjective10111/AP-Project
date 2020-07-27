package game;

import java.util.Arrays;
import java.util.InputMismatchException;

public class Board{
	private int[][] board = new int[17][17];
	Player player1;
	Player player2;
	Player turn;

	public Board(){
		for(int[] ints : board)
			Arrays.fill(ints,0);
		board[0][8] = 1;
		board[16][8] = 2;
		player1 = new Player('U');
		player2 = new Player('D');
		turn = player1;
	}
	public Board(String[] player1info, String[] player2info) {
		for(int[] ints : board)
			Arrays.fill(ints,0);
		player1 = new Player(player1info[0], Integer.parseInt(player1info[1]));
		player2 = new Player(player2info[0], Integer.parseInt(player2info[1]));
	}

	public int[][] getBoard() { return board; }
	public Player getTurn() { return turn; }
	public Player getPlayer1() { return player1; }
	public Player getPlayer2() { return player2; }
	public void setCell(int y, int x, int value) {
		board[y][x] = value;
		if (value == 1)
			player1.setBead(y, x);
		else if (value == 2)
			player2.setBead(y, x);
	}

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
			turn.bead.setY(y);
			board[turn.bead.getY()][turn.bead.getX()] = (turn.id == 'U')? 1 : 2;
		}
        else
			throw new InputMismatchException("invalid place");

		turn();
	}

	public boolean canMove(int x, int y) {
		return (Math.abs(turn.bead.getX() - x) == 2 && turn.bead.getY() == y)
				|| (Math.abs(turn.bead.getY() - y) == 2 && turn.bead.getX() == x);
	}

	public Player win() {
		if (turn.id == 'U') {
			for (int cell : board[16]) {
				if (cell == 1)
					return player1;
			}
		} else {
			for (int cell : board[0]) {
				if (cell == 2)
					return player2;
			}
		}
		return null;
	}
}