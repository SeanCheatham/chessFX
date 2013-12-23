package chess;

import javafx.scene.paint.Color;

public class Location {
	private int file;
    private int rank;
    private Piece occupant;
    public int whitePressure;
    public int blackPressure;
	
	public Location(int x, int y) {
		file = x;
		rank = y;
		occupant = null;
        whitePressure = 0;
        blackPressure = 0;
	}
	
	public Location(int x, int y, Piece p) {
		file = x;
		rank = y;
		occupant = p;
	}
	
	public String toString(){
        if(this.occupant != null) return "("+this.chessCoordinates()+"); "+this.occupant.getType().getEType()+";"+this.occupant.getTeam();
        else return "("+this.chessCoordinates()+"); Empty";
	}
	
	public String chessCoordinates(){
		String fileValue;
		switch (file) {
		case 0:
			fileValue = "A";
			break;
		case 1:
			fileValue = "B";
			break;
		case 2:
			fileValue = "C";
			break;
		case 3:
			fileValue = "D";
			break;
		case 4:
			fileValue = "E";
			break;
		case 5:
			fileValue = "F";
			break;
		case 6:
			fileValue = "G";
			break;
		case 7:
			fileValue = "H";
			break;
		default:
			fileValue = "";
			break;
		}
		return fileValue+(rank+1);
	}
	
	public void setOccupant(Piece p){
		occupant = p;
	}
	
	public void printAllowableMoves(){
		System.out.println("Calculating possible moves for: "+this);
		this.occupant.calculateAttackableSquares(this);
		for(int i=0; i<=this.occupant.getAttackableSquares().size()-1; i++){
			System.out.println(this.occupant.getAttackableSquares().get(i).chessCoordinates());
		}
	}

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public Piece getOccupant() {
        return occupant;
    }

    public Color getColor(){
        if((this.file+this.rank)%2 == 0) return Color.BLACK;
        else return Color.WHITE;
    }

}
