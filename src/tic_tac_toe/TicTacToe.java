package tic_tac_toe;

import tic_tac_toe.players.Factory;
import tic_tac_toe.players.Player;

public class TicTacToe {

    private static Player player1;
    private static Player player2;

    public TicTacToe() {
        gameLoop();
    }

    private void playerCreate(Board board) {
        SelectionScreen select = new SelectionScreen();
        int player = select.selectPlayer(1);
        int opponent = select.selectPlayer(2);
        String playerOneMark = "X";
        String playerTwoMark = "O";
        player1 = Factory.playerFactory(player, playerOneMark, board);
        player2 = Factory.playerFactory(opponent, playerTwoMark, board);
    }

    public void gameLoop() {

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
                int playersPick = players[turn].pickASpace();
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
        player1.dispose();
        player2.dispose();
    }
}
