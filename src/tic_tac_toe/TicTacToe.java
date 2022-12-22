package tic_tac_toe;

import tic_tac_toe.players.Board;
import tic_tac_toe.players.Player;
import java.util.Scanner;

public class TicTacToe {

    private static Player player1;
    private static Player player2;
    private static final Factory factory = new Factory();
    private final Scanner input = new Scanner(System.in);

    public TicTacToe() {
        gameLoop();
    }


    private void playerCreate(Board board) {
        int opponent = selectOpponent();
        player1 = factory.playerFactory(1, "X", board);
        player2 = factory.playerFactory(opponent, "O", board);
    }

    private int selectOpponent(){
        System.out.println("\nSelect from the following opponents:");
        System.out.println();
        System.out.printf("%6s", "1: Another human player." );
        System.out.println();
        System.out.printf("%6s", "2: Computer picking randomly (Easy)." );
        System.out.println();
        System.out.printf("%6s", "3: Computer AI (Hard)." );
        System.out.print("\nYour Opponent: ");
        int opponent;
        opponent = input.nextInt();
        while (true) {
            if (opponent == 1 || opponent == 2 || opponent == 3) {
                break;
            } else {
                System.out.print("Select 1, 2, 3 ");
                opponent = input.nextInt();
            }
        }
        return opponent;
    }

    public void gameLoop(){

        Board board = new Board();
        playerCreate(board);

        Player[] players = {player1, player2};
        int turn = 0;
        board.printBoard();
        while (true) {
            if (board.boardIsFull()) {
                System.out.println("\nIt's a draw!");
                break;
            } else {
                int[] playersPick = board.boardCoordinates(players[turn].pickASpace());
                if (board.spaceOccupied(playersPick)) {
                    System.out.println("\nOops. That spot is taken. Try again.");
                    board.printBoard();
                } else {
                    board.placePlayersMark(playersPick, players[turn].getPlayersMark());
                    board.printBoard();
                    if (board.checkWinner(players[turn].getPlayersMark())) {
                        System.out.println("\n" + players[turn].getPlayerName() + " Wins!");
                        break;
                    } else {
                        turn = (turn + 1) % 2;
                    }
                }
            }
        }
    }
}
