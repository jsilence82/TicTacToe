package tic_tac_toe.players;

import java.util.HashMap;
import java.util.Objects;

public class AI extends Player {

    private final Board board;
    private final String computer;
    private final String opponent;

    public AI(Board board, String playersMark) {
        super("AI Computer", playersMark);
        this.board = board;
        this.computer = playersMark;
        if (playersMark.equals("O")) {
            this.opponent = "X";
        } else {
            this.opponent = "O";
        }
        System.out.println(getPlayerName() + " will play as " + getPlayersMark());
    }

    @Override
    public int pickASpace() {
        System.out.println("The AI evaluates and is picking...");
        int[] bestMove = findBestMove(board);
        return board.boardCoordinatesToSpace(bestMove[0], bestMove[1]);
    }

    private int evaluate(Board board) {
        HashMap<Integer, String> mappedBoard = new HashMap<>();
        int hashKey = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mappedBoard.put(hashKey, board.getBoard()[i][j]);
                hashKey++;
            }
        }

        int[][] winningConditions = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, // rows
                {1, 4, 7}, {2, 5, 8}, {3, 6, 9},   // columns
                {1, 5, 9}, {3, 5, 7}}; // diagonals

        for (int[] spaces : winningConditions) {
            if (Objects.equals(mappedBoard.get(spaces[0]), mappedBoard.get(spaces[1])) &&
                    Objects.equals(mappedBoard.get(spaces[1]), mappedBoard.get(spaces[2]))) {
                if (Objects.equals(mappedBoard.get(spaces[0]), computer)) {
                    return +10;
                } else if (Objects.equals(mappedBoard.get(spaces[0]), opponent)) {
                    return -10;
                }
            }
        }
        return 0;
    }

    private int[] findBestMove(Board board) {
        int bestValue = -1000;
        int[] bestMove = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Objects.equals(board.getBoard()[i][j], opponent) || Objects.equals(board.getBoard()[i][j], computer)) {
                    continue;
                }
                String temp = board.getBoard()[i][j];
                int[] move = {i, j};
                board.placePlayersMark(move, computer);
                int moveValue = minMax(board, 0, false);
                board.placePlayersMark(move, temp);

                // If the value of the current move is more than the best value, then update
                if (moveValue > bestValue) {
                    bestMove[0] = i;
                    bestMove[1] = j;
                    bestValue = moveValue;
                }
            }
        }
        return bestMove;
    }

    private int minMax(Board board, int depth, Boolean isMaximizer) {
        int score = evaluate(board);

        // If AI wins return 10. Opponent win return -10. If tie return 0.
        if (score == 10)
            return score;

        if (score == -10)
            return score;

        if (board.boardIsFull())
            return 0;

        int best;
        if (isMaximizer) {      // Maximizer. AI move
            best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (Objects.equals(board.getBoard()[i][j], opponent) || Objects.equals(board.getBoard()[i][j], computer)) {
                        continue;
                    }
                    String temp = board.getBoard()[i][j];
                    int[] move = {i, j};
                    board.placePlayersMark(move, computer);
                    best = Math.max(best, minMax(board, depth + 1, false));
                    board.placePlayersMark(move, temp);
                }
            }
        } else {            // Minimizer opponent move
            best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (Objects.equals(board.getBoard()[i][j], opponent) || Objects.equals(board.getBoard()[i][j], computer)) {
                        continue;
                    }
                    String temp = board.getBoard()[i][j];
                    int[] move = {i, j};
                    board.placePlayersMark(move, opponent);
                    best = Math.min(best, minMax(board, depth + 1, true));
                    board.placePlayersMark(move, temp);
                }
            }
        }
        return best;
    }
}

