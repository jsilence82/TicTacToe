package tic_tac_toe.players;

public abstract class Player {

    private String playerName;
    private final String playersMark;

    public Player(String playerName, String playersMark) {
        this.playerName = playerName;
        this.playersMark = playersMark;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


    public String getPlayersMark() {
        return playersMark;
    }

    public abstract int pickASpace();
}
