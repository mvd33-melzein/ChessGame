package game;

import pieces.PlayerColor;

public class Player {
    private PlayerColor color;

    public Player(PlayerColor color) {
        this.color = color;
    }

    public PlayerColor getPlayerColor() {
        return color;
    }
}