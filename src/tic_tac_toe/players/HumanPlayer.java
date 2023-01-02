package tic_tac_toe.players;

import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner input = new Scanner(System.in);


    public HumanPlayer(String playersMark) {
        super("name", playersMark);
        System.out.print("Player " + playerNumber + " name ?: ");
        String playersName = input.next();
        setPlayerName(playersName);
        System.out.println(getPlayerName() + " will play as " + getPlayersMark());
    }

    public String getPlayerName() {
        return super.getPlayerName();
    }

    public String getPlayersMark() {
        return super.getPlayersMark();
    }

    public int pickASpace() {
        input = new Scanner(System.in);
        System.out.print("It is " + getPlayerName() + "'s turn: ");
        try {
            int pick = input.nextInt();
            if (pick <= 9 && pick > 0) {
                return pick;
            }
            System.out.println("Not a valid space. Try again.");
        } catch (Exception e) {
            System.out.println("Something went wrong. Try again.");
        }
        return pickASpace();
    }
}
