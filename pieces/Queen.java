package pieces;

import board.*;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{

public Queen(PlayerColor color, Position position){
        super(color, position);
    }

    @Override
    public List<Position> possibleMoves(Board board){
        List<Position> moves = new ArrayList<>();
        int row = getPosition().getRow();
        int col = getPosition().getCol();

        // Queen combines rook and bishop moves (all 8 directions)
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};

        // Slide in each direction until blocked by a piece or board edge
        for (int[] d : directions) {
            int r = row + d[0];
            int c = col + d[1];
            while (board.isInBounds(new Position(r, c))) {
                Piece target = board.getPiece(new Position(r, c));
                if (target == null) {
                    moves.add(new Position(r, c));
                } else {
                    // Can capture enemy piece, but stops here
                    if (target.getPlayerColor() != getPlayerColor()) {
                        moves.add(new Position(r, c)); 
                    }
                    break;
                }
                r += d[0];
                c += d[1];
            }
        }
        return moves;
    }

    @Override
    public String toString(){
        if(getPlayerColor() == PlayerColor.WHITE){
            return "wQ";
        }
        else{
            return "bQ";
        }
    }
    
    @Override
    public String getImagePath(){
        if(getPlayerColor() == PlayerColor.WHITE){
            return "images/WhiteQueen.png";
        }
        else{
            return "images/BlackQueen.png";
        }
    }
}