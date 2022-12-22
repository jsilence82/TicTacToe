package tic_tac_toe;

import tic_tac_toe.players.*;

public class Factory {

    public Player playerFactory(int entry, String playersMark, Board board) {
        return switch (entry) {
         case 1 -> new HumanPlayer(playersMark);
         case 2 -> new Computer(board);
         case 3 -> new AI(board);
         default -> null;
        };
    }
}
