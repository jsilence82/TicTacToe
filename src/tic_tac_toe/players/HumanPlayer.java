package tic_tac_toe.players;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner input = new Scanner(System.in);


    HumanPlayer(String playersMark) {
        super("name", playersMark);
        System.out.print("Give player " + playerNumber + " a name: ");
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
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Your pick should be between 1 and 9");
        } catch (InputMismatchException e) {
            System.out.println("Invalid. Your pick should be between 1 and 9. Try again.");
        }
        return pickASpace();
    }
}
