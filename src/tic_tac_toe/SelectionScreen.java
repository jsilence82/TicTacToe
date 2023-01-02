package tic_tac_toe;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import static java.awt.RenderingHints.*;

public class SelectionScreen {

    Scanner input = new Scanner(System.in);
    public void AsciiArt() {
        BufferedImage image = new BufferedImage(144, 32, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("Dialog", Font.PLAIN, 24));
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(KEY_TEXT_ANTIALIASING,
                VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("TicTacToe", 6, 24);

        for (int y = 0; y < 32; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < 144; x++)
                sb.append(image.getRGB(x, y) == -16777216 ? " " : image.getRGB(x, y) == -1 ? "#" : "*");
            if (sb.toString().trim().isEmpty()) continue;
            System.out.println(sb);

        }

    }

    public int selectPlayer(int playerNumber) {
        System.out.println("\nSelect from the following for Player " + playerNumber + "...");
        System.out.printf("%6s", "1: Human player.");
        System.out.println();
        System.out.printf("%6s", "2: Computer picking randomly (Easy).");
        System.out.println();
        System.out.printf("%6s", "3: Computer AI (Hard).");
        System.out.println();
        System.out.print("\nPlayer " + playerNumber + ": ");
        return validateInput();
    }

    private int validateInput() {
        int playerType = input.nextInt();
        if (playerType == 1 || playerType == 2 || playerType == 3) {
            return playerType;
        } else {
            System.out.print("Select 1, 2, or 3: ");
        }
        return validateInput();
    }
}
