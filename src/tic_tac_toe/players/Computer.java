package tic_tac_toe.players;

import java.util.Random;

public class Computer extends Player {

    Board board;
    private final Random random = new Random();

    public Computer(Board board) {
        super("Random", "O");
        this.board = board;
        System.out.println(getPlayerName() + " will be playing as " + getPlayersMark());
    }

    @Override
    public int pickASpace() {
        int pick = random.nextInt(9) + 1;
        if(board.spaceOccupied(board.spaceToBoardCoordinates(pick))){
            return pickASpace();
        }
        System.out.println("The computer is randomly picking...");
        return pick;
    }
}
