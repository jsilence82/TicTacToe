package tic_tac_toe;

import java.util.Scanner;

public class Player {

    private final String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int pickASpace() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.print("It is Player " + playerName + "'s turn: " );
        try{
            int pick = input.nextInt();
            if(pick <= 9 && pick > 0) {
                return pick;
            }
            System.out.println("Not a valid space. Try again.");
        } catch (Exception e){
            System.out.println("Something went wrong. Try again.");
        }
        return pickASpace();
    }
}
