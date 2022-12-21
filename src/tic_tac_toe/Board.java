package tic_tac_toe;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class Board {

    private final String[][] board = new String[3][3];

    public Board() {
        initializeBoard();
        printBoard();
    }

    public void initializeBoard() {
        int count = 1;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    board[i][j] = String.valueOf(count++);
                }
            }
    }

    public void placePlayersMark(int[] playersPick, String playerMark){
        board[playersPick[0]][playersPick[1]] = playerMark;
    }

    public int[] boardCoordinates(int space) {
        int[] coordinates = new int[2];
        switch(space) {
            case 1:
                break;
            case 2:
                coordinates[1] = 1;
                break;
            case 3:
                coordinates[1] = 2;
                break;
            case 4: coordinates[0] = 1;
                break;
            case 5: coordinates[0] = 1; coordinates[1] = 1;
                break;
            case 6: coordinates[0] = 1; coordinates[1] = 2;
                break;
            case 7: coordinates[0] = 2;
                break;
            case 8: coordinates[0] = 2; coordinates[1] = 1;
                break;
            case 9: coordinates[0] = 2; coordinates[1] = 2;
                break;
        }
        return coordinates;
    }

    public void printBoard(){
        System.out.println();
        for (String s : Arrays.asList(board[0][0] + " | " + board[0][1] + " | " + board[0][2],
                "- + - + -",
                board[1][0] + " | " + board[1][1] + " | " + board[1][2],
                "- + - + -",
                board[2][0] + " | " + board[2][1] + " | " + board[2][2])) {
            System.out.println(s);
        }
        System.out.println();
    }

    public boolean spaceOccupied(int[] space){
        return board[space[0]][space[1]].equals("X") || board[space[0]][space[1]].equals("O");
    }

    public boolean boardIsFull(){
        for (String[] strings : board) {
            for (String string : strings) {
                if (Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9").contains(string)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWinner(String playerMark){
        return (IntStream.of(0, 1, 2).allMatch(i -> Objects.equals(board[0][i], playerMark))) ||
                (IntStream.of(0, 1, 2).allMatch(j -> Objects.equals(board[1][j], playerMark))) ||
                (IntStream.of(0, 1, 2).allMatch(k -> Objects.equals(board[2][k], playerMark))) ||
                (IntStream.of(0, 1, 2).allMatch(l -> Objects.equals(board[l][0], playerMark))) ||
                (IntStream.of(0, 1, 2).allMatch(m -> Objects.equals(board[m][1], playerMark))) ||
                (IntStream.of(0, 1, 2).allMatch(n-> Objects.equals(board[n][2], playerMark))) ||
                (Objects.equals(board[0][0], playerMark) && Objects.equals(board[1][1], playerMark) && Objects.equals(board[2][2], playerMark)) ||
                (Objects.equals(board[0][2], playerMark) && Objects.equals(board[1][1], playerMark) && Objects.equals(board[2][0], playerMark));

    }
}
