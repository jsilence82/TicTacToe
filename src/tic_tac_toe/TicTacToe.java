package tic_tac_toe;

import java.util.InputMismatchException;
import java.util.Scanner;


public class TicTacToe {

    private static Player player1;
    private static Player player2;
    private final Scanner input = new Scanner(System.in);

    public TicTacToe() throws InterruptedException {
        gameLoop();
    }

    private void playerCreate(){
        int numberOfPlayers = selectNumberOfPlayers();
            try{
                System.out.print("Player 1's name: ");
                String player1Name = input.next();
                player1 = new Player(player1Name, "X");
                System.out.println(player1.getPlayerName() + " will be playing as " + player1.getPlayersMark());
                if(numberOfPlayers == 2){
                    System.out.print("Player 2's name: ");
                    String player2Name = input.next();
                    player2 = new Player(player2Name,"O");
                } else{
                    player2 = new Computer();
                }
            }catch (InputMismatchException e){
                System.out.println("You entered an invalid value. Try again.");
            }
        System.out.println(player2.getPlayerName() + " will be playing as " + player2.getPlayersMark());
    }

    private int selectNumberOfPlayers(){
        System.out.print("\nHow many players? ");
        int numberOfPlayers = input.nextInt();
        while (true){
            if (numberOfPlayers == 1 || numberOfPlayers == 2) {
                break;
            } else {
                System.out.print("How many players? 1 or 2? ");
                numberOfPlayers = input.nextInt();
            }
        }
        return numberOfPlayers;
    }


    public void gameLoop() throws InterruptedException {

        playerCreate();

        Player[] players = {player1, player2};
        int turn = 0;
        Board board = new Board();

        while (true){
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
                    }else{
                        turn = (turn + 1) % 2;
                    }
                }
            }
        }
    }
}
