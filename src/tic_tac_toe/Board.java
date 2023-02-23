package tic_tac_toe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class Board {




    private HashMap<Integer, String> board;

    public Board() {
        initializeBoard();
    }

    public HashMap<Integer, String> getBoard() {
        return board;
    }

    private void initializeBoard() {
        board = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            board.put(i, String.valueOf(i));
        }
    }

    public void placePlayersMark(int playersPick, String playersMark){
        board.put(playersPick, playersMark);
    }

    public boolean spaceOccupied(int playersPick){
        return board.get(playersPick).equals("X") || board.get(playersPick).equals("O");
    }

    public boolean boardIsFull(){
        for(Map.Entry<Integer, String> entry : board.entrySet()){
            if(entry.getValue().equals("1") || entry.getValue().equals("2") || entry.getValue().equals("3")
                    || entry.getValue().equals("4") || entry.getValue().equals("5") || entry.getValue().equals("6")
                    || entry.getValue().equals("7") || entry.getValue().equals("8") || entry.getValue().equals("9")){
                return false;
            }
        }
        return true;
    }

    public boolean checkWinner(String playersMark){
        int[][] winningConditions = {{1, 2, 3}, {4,5,6}, {7, 8 ,9}, {1, 4, 7}, {2, 5, 8},
                {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
        for(int[] numbers: winningConditions){
            if(Objects.equals(board.get(numbers[0]), board.get(numbers[1])) &&
                    Objects.equals(board.get(numbers[1]), board.get(numbers[2]))){
                if(Objects.equals(board.get(numbers[0]), playersMark)){
                    return true;
                }
            }
        }
        return false;
    }

    public void printBoard(){
        System.out.println();
        for (String s : Arrays.asList(board.get(1) + " | " + board.get(2) + " | " + board.get(3),
                "- + - + -",
                board.get(4) + " | " + board.get(5) + " | " + board.get(6),
                "- + - + -",
                board.get(7) + " | " + board.get(8) + " | " + board.get(9))) {
            System.out.println(s);
        }
        System.out.println();
    }
}
