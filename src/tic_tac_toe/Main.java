package tic_tac_toe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        new SelectionScreen().AsciiArt();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                new TicTacToe();
                System.out.print("\nAnother game? ");
                String input = scanner.nextLine();
                if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
                    System.out.print("Y or N? ");
                } else if (input.equalsIgnoreCase("n")) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You entered an invalid input. Try again.");
            }
        }
    }
}
