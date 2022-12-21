package tic_tac_toe;

import java.util.Random;

public class Computer extends Player{

    private final Random random = new Random();

    public Computer() {
        super("Computer", "O");
    }

    @Override
    public int pickASpace() throws InterruptedException {
        System.out.println("The computer is picking");
        Thread.sleep(1000);
        return random.nextInt(9) + 1;
    }
}
