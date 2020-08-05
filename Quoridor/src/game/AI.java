package game;

import java.util.InputMismatchException;

public class AI extends Player{

    private final int EMPTY = 0;

    private int min = -1000;
    private int max = 1000;

    public AI(String name, char id, int walls) {
        super(name, id, walls);
    }

    // This will return the best possible move for the player
    public int[] findBestMove(Board board)
    {
        int bestVal = min;
        int[] bestMove = new int[2];
        bestMove[0] = -1;
        bestMove[1] = -1;

        // Check all cells and evaluate minimax function for all empty cells. And return the best move
        for (int i = 0; i < 17; i++)
        {
            for (int j = 0; j < 17; j++)
            {
                int moveVal = min;

                // Check if cell is empty
                if (board.getBoard()[i][j] == EMPTY)
                {
                    if (i % 2 == 0 && j % 2 == 0)
                    {
                        try {
                            board.move(j, i);
                            board.turn();
                            // compute evaluation function for this move.
                            moveVal = minimax(board, 0, false, min, max);
                            board.undoMove(j, i);
                        } catch (InputMismatchException ignored) {}
                    }
                    else if ( (i % 2 == 0 && j % 2 == 1 && j <16 && i < 15) ||
                            (i % 2 == 1 && j % 2 == 0 && j <15 && i < 16) )
                    {
                        try {
                            board.placeWall(j, i);
                            board.turn();
                            // compute evaluation function for this move.
                            moveVal = minimax(board, 0, false, min, max);
                            board.undoMove(j, i);
                        } catch (InputMismatchException ignored) {}
                    }

                    // If the value of the current move is more than the best value, then update best
                    if (moveVal > bestVal)
                    {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }

    //It considers all the possible ways the game can go and returns the value of the board
    private int minimax(Board board, int depth, Boolean isMax, int alpha, int beta)
    {
        int score = evaluate(board);

        if (score == 0) {
            // If this maximizer's move
            if (isMax)
            {
                int best = min;

                // Traverse all cells
                for (int i = 0; i < 17; i++)
                {
                    for (int j = 0; j < 17; j++)
                    {
                        // Check if cell is empty
                        if (board.getBoard()[i][j] == EMPTY)
                        {
                            if (i % 2 == 0 && j % 2 == 0)
                            {
                                try {
                                    board.move(j, i);
                                    board.turn();
                                    // Call minimax recursively and choose the maximum value
                                    best = Math.max(best, minimax(board, depth + 1, !isMax, alpha, beta));
                                    alpha = Math.max(alpha, best);
                                    board.undoMove(j, i);
                                } catch (InputMismatchException ignored) {}
                            }
                            else if ( (i % 2 == 0 && j % 2 == 1 && j <16 && i < 15) ||
                                    (i % 2 == 1 && j % 2 == 0 && j <15 && i < 16) )
                            {
                                try {
                                    board.placeWall(j, i);
                                    board.turn();
                                    // Call minimax recursively and choose the maximum value
                                    best = Math.max(best, minimax(board, depth + 1, !isMax, alpha, beta));
                                    alpha = Math.max(alpha, best);
                                    board.undoMove(j, i);
                                } catch (InputMismatchException ignored) {}
                            }

                        }
                    }
                }
                return best;
            }
            // If this minimizer's move
            else
            {
                int best = max;

                // Traverse all cells
                for (int i = 0; i < 17; i++)
                {
                    for (int j = 0; j < 17; j++)
                    {
                        // Check if cell is empty
                        if (board.getBoard()[i][j] == EMPTY)
                        {
                            if (i % 2 == 0 && j % 2 == 0)
                            {
                                try {
                                    board.move(j, i);
                                    board.turn();
                                    // Call minimax recursively and choose the minimum value
                                    best = Math.min(best, minimax(board, depth + 1, !isMax, alpha, beta));
                                    beta = Math.min(beta, best);
                                    board.undoMove(j, i);
                                } catch (InputMismatchException ignored) {}
                            }
                            else if ( (i % 2 == 0 && j % 2 == 1 && j <16 && i < 15) ||
                                    (i % 2 == 1 && j % 2 == 0 && j <15 && i < 16) )
                            {
                                try {
                                    board.placeWall(j, i);
                                    board.turn();
                                    // Call minimax recursively and choose the minimum value
                                    best = Math.min(best, minimax(board, depth + 1, !isMax, alpha, beta));
                                    beta = Math.min(beta, best);
                                    board.undoMove(j, i);
                                } catch (InputMismatchException ignored) {}
                            }
                        }
                    }
                }
                return best;
            }
        }
        // If Maximizer has won the game return his/her evaluated score
        else if (score == 100)
            return score - depth;

        // If Minimizer has won the game return his/her evaluated score
        else
            return score + depth;

    }

    // This is the evaluation function
    private int evaluate(Board board)
    {
        // Else if none of them have won then return 0
        if (board.win() == null) {
            return 0;
        }
        // Checking for player1 or player2 victory.
        else if (board.win().getId() == this.id) {
            return 1000;
        }
        else {
            return -1000;
        }
    }
}
