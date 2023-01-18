package tic_tac_toe.players;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

public class AI extends Player{

    Board board;
    String computer;
    String opponent;
    HashMap<Integer, String> mapped;

    AI(Board board, String playersMark) {
        super("AI Computer", playersMark);
        this.board = board;
        this.computer = playersMark;
        if (playersMark.equals("O")) {
            this.opponent = "X";
        } else {
            this.opponent = "O";
        }
        initializeMap();
        System.out.println(getPlayerName() + " will play as " + getPlayersMark());
    }

    @Override
    public int pickASpace() {
        System.out.println("The AI evaluates and is picking...");
        HashMap<Integer, String> updatedMap = updateMapped();
        return findBestMove(updatedMap);
    }

    private void initializeMap() {
        mapped = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            mapped.put(i, String.valueOf(i));
        }
    }

    private HashMap<Integer, String> updateMapped() {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mapped.put(count, board.getBoard()[i][j]);
                count++;
            }
        }
        return mapped;
    }


    private int evaluate(HashMap<Integer, String> mapped) {
        int[][] winningConditions = {{1, 2, 3}, {4,5,6}, {7, 8 ,9}, {1, 4, 7}, {2, 5, 8},
                {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
        for(int[] numbers: winningConditions){
            if(Objects.equals(mapped.get(numbers[0]), mapped.get(numbers[1])) &&
            Objects.equals(mapped.get(numbers[1]), mapped.get(numbers[2]))){
                if(Objects.equals(mapped.get(numbers[0]), computer)){
                    return +10;
                } else if (Objects.equals(mapped.get(numbers[0]), opponent)) {
                    return -10;
                }
            }
        }
        return 0;
    }

    private boolean mapIsFull(){

            for(Entry<Integer, String> entry : mapped.entrySet()){
            if(entry.getValue().equals("1") || entry.getValue().equals("2") || entry.getValue().equals("3")
            || entry.getValue().equals("4") || entry.getValue().equals("5") || entry.getValue().equals("6")
            || entry.getValue().equals("7") || entry.getValue().equals("8") || entry.getValue().equals("9")){
                return false;
            }
        }
        return true;
    }

    private int findBestMove(HashMap<Integer, String> mapped) {
        int bestValue = -1000;
        int bestMove = -1;

        for (Entry<Integer, String> space : mapped.entrySet()) {
            if (!space.getValue().equals(computer) && !space.getValue().equals(opponent)) {
                String temp = space.getValue();
                int move = space.getKey();
                mapped.put(move, computer);
                int moveValue = minMax(mapped, 0, false);
                mapped.put(move, temp);

                if (moveValue > bestValue) {
                    bestMove = space.getKey();
                    bestValue = moveValue;
                }
            }
        }
        return bestMove;
    }

    private int minMax(HashMap<Integer, String> mapped, int depth, Boolean isMax) {
        int score = evaluate(mapped);
        
        if (score == 10) // Maximizer wins
            return score;
        
        if (score == -10) // Minimizer wins
            return score;

        if (mapIsFull()) // No winner
            return 0;

        //  maximizer's move
        int best;
        if (isMax) {
            best = -1000;
            for (Entry<Integer, String> space : mapped.entrySet()) {
                if (!space.getValue().equals(computer) && !space.getValue().equals(opponent)) {
                    String temp = space.getValue();
                    int move = space.getKey();
                    mapped.put(move, computer);
                    best = Math.max(best, minMax(mapped, depth + 1, false));
                    mapped.put(move, temp);
                }
            }
        }
        // minimizer's move
        else {
            best = 1000;
            for (Entry<Integer, String> space : mapped.entrySet()) {
                if (!space.getValue().equals(computer) && !space.getValue().equals(opponent)) {
                    String temp = space.getValue();
                    int move = space.getKey();
                    mapped.put(move, opponent);
                    best = Math.min(best, minMax(mapped, depth + 1, true));
                    mapped.put(move, temp);
                }
            }
        }
        return best;
    }
}
