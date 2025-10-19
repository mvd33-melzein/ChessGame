package game;

import pieces.Color;

public class Player {
    private Color color;

    public Player(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}