package tic_tac_toe.players;

import java.util.Objects;

public class AI extends Player {

    Board board;
    String computer;
    String opponent;

    static class Move {
        int row, col;
    }

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
        Move bestMove = findBestMove(board);
        return board.boardCoordinatesToSpace(bestMove.row, bestMove.col);
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

        for (int col = 0; col < 3; col++) {
            if (Objects.equals(board.getBoard()[0][col], board.getBoard()[1][col]) &&
                    Objects.equals(board.getBoard()[1][col], board.getBoard()[2][col])) {
                if (Objects.equals(board.getBoard()[0][col], computer))
                    return +10;

                else if (Objects.equals(board.getBoard()[0][col], opponent))
                    return -10;
            }
        }

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
        return 0;
    }

    private Move findBestMove(Board board) {
        int bestValue = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!Objects.equals(board.getBoard()[i][j], opponent) && !Objects.equals(board.getBoard()[i][j], computer)) {
                    String temp = board.getBoard()[i][j];
                    int[] move = {i, j};
                    board.placePlayersMark(move, computer);
                    int moveValue = minMax(board, 0, false);
                    board.placePlayersMark(move, temp);

                    // If the value of the current move is more than the best value, then update
                    if (moveValue > bestValue) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minMax(Board board, int depth, Boolean isMax) {
        int score = evaluate(board);

        // If Maximizer wins
        if (score == 10)
            return score;

        // If Minimizer wins
        if (score == -10)
            return score;

        // N more moves and no winner
        if (board.boardIsFull())
            return 0;

        // If maximizer's move
        int best;
        if (isMax) {
            best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!Objects.equals(board.getBoard()[i][j], opponent) && !Objects.equals(board.getBoard()[i][j], computer)) {
                        String temp = board.getBoard()[i][j];
                        int[] move = {i, j};
                        board.placePlayersMark(move, computer);
                        best = Math.max(best, minMax(board, depth + 1, false));
                        board.placePlayersMark(move, temp);
                    }
                }
            }
        }
        // If minimizer's move
        else {
            best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!Objects.equals(board.getBoard()[i][j], opponent) && !Objects.equals(board.getBoard()[i][j], computer)) {
                        String temp = board.getBoard()[i][j];
                        int[] move = {i, j};
                        board.placePlayersMark(move, opponent);
                        best = Math.min(best, minMax(board, depth + 1, true));
                        board.placePlayersMark(move, temp);
                    }
                }
            }
        }
        return best;
    }
}



