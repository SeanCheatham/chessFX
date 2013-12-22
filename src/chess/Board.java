package chess;

public class Board {
	private int height;
    private int width;
    private Location[][] squares;
	
	public Board(int w, int h){
		width = w;
		height = h;
		squares = new Location[width][height];
		for(int i = 0; i <= width-1; i++){
			for(int j = 0; j <= height-1; j++){
				squares[i][j] = new Location(i,j,null);
			}
		}
	}
	
	public void drawBoard(){
		for(int i = height-1; i >= 0; i--){
			System.out.print("["+(i+1)+"]");
			for(int j = 0; j <= width-1; j++){
				if(squares[j][i].getOccupant() != null) System.out.print(" "+squares[j][i].getOccupant().getType().getShortName()+" ");
				else System.out.print(" _ ");
			}
			System.out.println();
		}
		System.out.println("   [A][B][C][D][E][F][G][H]");
	}
	
	public void listPieces(){
		for(int i = 0; i <= height - 1; i++){
			for(int j = 0; j <= width-1; j++){
				if(squares[i][j] != null) System.out.println(squares[i][j]);
			}
		}
	}
	
	public void movePiece(Location from, int toX, int toY){
		this.squares[toX][toY] = new Location(toX, toY, from.getOccupant());
		System.out.println(from.getOccupant().getType().getShortName()+from.chessCoordinates()+" to "+squares[toX][toY].chessCoordinates());
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

    public String toString(){
        String ret = "";
        for(int i = 0; i <= height - 1; i++){
            for(int j = 0; j <= width-1; j++){
                if(squares[i][j] != null) ret += squares[i][j]+"\n";
            }
        }
        return ret;
    }
}
