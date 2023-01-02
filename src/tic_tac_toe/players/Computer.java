package tic_tac_toe.players;

import java.util.Random;

public class Computer extends Player {

    Board board;
    private final Random random = new Random();

    public Computer(Board board,String playersMark) {
        super("Random", playersMark);
        this.board = board;
        System.out.println(getPlayerName() + " will play as " + getPlayersMark());
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
