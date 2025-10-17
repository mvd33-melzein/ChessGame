package board;

public class Position{
    private int row;
    private int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    public static Position fromNotation(String str){
        char file = Character.toUpperCase(str.charAt(0));
        int col = file - 'A';
        int row = Character.getNumericValue(str.charAt(1)) - 1;

        return new Position(row, col);
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return col;
    }


}