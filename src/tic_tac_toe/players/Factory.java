package tic_tac_toe.players;

import tic_tac_toe.Board;

public class Factory {

    public static Player playerFactory(int entry, String playersMark, Board board) {
        return switch (entry) {
         case 1 -> new HumanPlayer(playersMark);
         case 2 -> new Computer(board, playersMark);
         case 3 -> new AI(board, playersMark);
         default -> null;
        };
    }
}
