package chess;

public class Type {
    private eType name;
    private char shortName;
    private int value;

    public Type() {
        this(eType.NONE);
    }

    public Type(eType n) {
        name = n;

        switch (n) {
            case PAWN:
                value = 1;
                shortName = 'P';
                break;
            case BISHOP:
                value = 3;
                shortName = 'B';
                break;
            case KNIGHT:
                value = 3;
                shortName = 'N';
                break;
            case ROOK:
                value = 6;
                shortName = 'R';
                break;
            case QUEEN:
                value = 10;
                shortName = 'Q';
                break;
            case KING:
                value = 0;
                shortName = 'K';
                break;
            default:
                value = 0;
                shortName = '*';
        }
    }

    public eType getEType() {
        return name;
    }

    public char getShortName() {
        return shortName;
    }

    public int getValue() {
        return value;
    }
}
