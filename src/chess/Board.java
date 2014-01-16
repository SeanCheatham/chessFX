package chess;

import java.util.Stack;

public class Board {
    private int height;
    private int width;
    private Location[][] squares;

    public Board(int w, int h) {
        width = w;
        height = h;
        squares = new Location[width][height];
        for (int i = 0; i <= width - 1; i++) {
            for (int j = 0; j <= height - 1; j++) {
                squares[i][j] = new Location(i, j, null);
            }
        }
    }

    public void drawBoard() {
        for (int i = height - 1; i >= 0; i--) {
            System.out.print("[" + (i + 1) + "]");
            for (int j = 0; j <= width - 1; j++) {
                if (squares[j][i].getOccupant() != null)
                    System.out.print(" " + squares[j][i].getOccupant().getType().getShortName() + " ");
                else
                    System.out.print(" _ ");
            }
            System.out.println();
        }
        System.out.println("   [A][B][C][D][E][F][G][H]");
    }

    public void setupFromFEN(String FEN) {
        Location[][] fenBoard = new Location[width][height];

        //Parse through FEN
        //Setup board

        //-----SCRAP-----
        //fenBoard[0][0].setOccupant(new Piece(new Type(eType.ROOK),0,this));

    }

    public void getFEN() {
        Stack<Character> fenBuffer = new Stack<Character>();
        String fenOutput = "";

        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j <= width - 1; j++) {
                if (squares[j][i].getOccupant() != null) {
                    char occupant = squares[j][i].getOccupant().getType().getShortName();
                    if (squares[j][i].getOccupant().getTeam() == 1)
                        fenBuffer.push(String.valueOf(occupant).toLowerCase().charAt(0));
                    else
                        fenBuffer.push(occupant);
                } else
                    fenBuffer.push('1');
            }

            if (i != 0) fenBuffer.push('/');
        }

        while (!fenBuffer.isEmpty()) {
            Character tempChar = fenBuffer.pop();
            if (Character.isDigit(tempChar) && Character.isDigit(fenBuffer.peek())) {
                fenBuffer.push(String.valueOf(Character.getNumericValue(fenBuffer.pop()) + Character.getNumericValue(tempChar)).charAt(0));
            } else fenOutput += tempChar;
        }

        System.out.println(new StringBuilder(fenOutput).reverse().toString());
    }

    public void listPieces() {
        for (int i = 0; i <= height - 1; i++) {
            for (int j = 0; j <= width - 1; j++) {
                if (squares[i][j] != null) System.out.println(squares[i][j]);
            }
        }
    }

    public void movePiece(Location from, int toX, int toY) {
        this.squares[toX][toY] = new Location(toX, toY, from.getOccupant());
        System.out.println(from.getOccupant().getType().getShortName() + from.chessCoordinates() + " to " + squares[toX][toY].chessCoordinates());
        this.squares[from.getFile()][from.getRank()] = null;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Location[][] getSquares() {
        return squares;
    }

    public String toString() {
        String ret = "";
        for (int i = 0; i <= height - 1; i++) {
            for (int j = 0; j <= width - 1; j++) {
                if (squares[i][j] != null) ret += squares[i][j] + "\n";
            }
        }
        return ret;
    }

    public Location[][] runCalculator() {

        for (Location[] locArray : this.squares) {
            for (Location loc : locArray) {
                if (loc.getOccupant() != null) loc.getOccupant().calculateAttackableSquares(loc);
            }
        }
        return squares;
    }
}
