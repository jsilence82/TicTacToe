package tic_tac_toe.players;

import java.util.Objects;

public class AI extends Player {

    Board board;
    static String computer = "O", opponent = "X";
    static class Move {int row, col;}

    public AI(Board board) {
        super("AI Computer", "O");
        this.board = board;
        System.out.println(getPlayerName() + " will be playing as " + getPlayersMark());
    }

    @Override
    public int pickASpace() {
        System.out.println("The AI evaluates and is picking...");
        Move bestMove = findBestMove(board);
        return boardCoordinatesToSpace(bestMove.row, bestMove.col);
    }

    private int boardCoordinatesToSpace(int row, int column) {
        return Integer.parseInt(board.getBoard()[row][column]);
    }

    private Move findBestMove(Board board) {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!Objects.equals(board.getBoard()[i][j], "X") && !Objects.equals(board.getBoard()[i][j], "O")) {
                    // Temporarily store the placeholder String
                    String temp = board.getBoard()[i][j];
                    // Make the move
                    int[] move = {i, j};
                    board.placePlayersMark(move, computer);

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board.placePlayersMark(move, temp);

                    // If the value of the current move is more than the best value, then update
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    private int evaluate(Board board) {
        for (int row = 0; row < 3; row++) {
            if (Objects.equals(board.getBoard()[row][0], board.getBoard()[row][1]) && Objects.equals(board.getBoard()[row][1], board.getBoard()[row][2])) {
                if (Objects.equals(board.getBoard()[row][0], computer))
                    return +10;
                else if (Objects.equals(board.getBoard()[row][0], opponent))
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {
            if (Objects.equals(board.getBoard()[0][col], board.getBoard()[1][col]) &&
                    Objects.equals(board.getBoard()[1][col], board.getBoard()[2][col])) {
                if (Objects.equals(board.getBoard()[0][col], computer))
                    return +10;

                else if (Objects.equals(board.getBoard()[0][col], opponent))
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (Objects.equals(board.getBoard()[0][0], board.getBoard()[1][1]) && Objects.equals(board.getBoard()[1][1], board.getBoard()[2][2])) {
            if (Objects.equals(board.getBoard()[0][0], computer))
                return +10;
            else if (Objects.equals(board.getBoard()[0][0], opponent))
                return -10;
        }

        if (Objects.equals(board.getBoard()[0][2], board.getBoard()[1][1]) && Objects.equals(board.getBoard()[1][1], board.getBoard()[2][0])) {
            if (Objects.equals(board.getBoard()[0][2], computer))
                return +10;
            else if (Objects.equals(board.getBoard()[0][2], opponent))
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    private int minimax(Board board, int depth, Boolean isMax) {
        int score = evaluate(board);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (board.boardIsFull())
            return 0;

        // If this maximizer's move
        int best;
        if (isMax) {
            best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (!Objects.equals(board.getBoard()[i][j], "X") && !Objects.equals(board.getBoard()[i][j], "O")) {
                        String temp = board.getBoard()[i][j];
                        // Make the move
                        int[] move = {i, j};
                        board.placePlayersMark(move, computer);
                        // board.getBoard()[i][j] = computer;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, false));

                        // Undo the move
                        board.placePlayersMark(move, temp);

                    }
                }
            }
        }

        // If this minimizer's move
        else {
            best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (!Objects.equals(board.getBoard()[i][j], "X") && !Objects.equals(board.getBoard()[i][j], "O")) {
                        // Store the placeholder String
                        String temp = board.getBoard()[i][j];
                        // Make the move
                        int[] move = {i, j};
                        board.placePlayersMark(move, opponent);

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, true));

                        // Undo the move
                        board.placePlayersMark(move, temp);

                    }
                }
            }
        }
        return best;
    }
}



