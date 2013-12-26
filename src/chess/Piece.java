package chess;

import java.util.*;
import javafx.scene.image.Image;

public class Piece {
	private Type type;
    private int team;
    private Board board;
    private ArrayList<Location> attackableSquares;
    private Image image;
	
	public Piece(Type ty, int t, Board b) {
		type = ty;
		team = t;
		board = b;
        switch (type.getEType()){
            case PAWN:
                if(team == 0){
                    image = new Image("chess/resources/images/wpp.png", false);
                }
                else if(team == 1){
                    image = new Image("chess/resources/images/bp.png", false);
                }
                break;
            case KNIGHT:
                if(team == 0){
                    image = new Image("chess/resources/images/wn.png", false);
                }
                else if(team == 1){
                    image = new Image("chess/resources/images/bn.png", false);
                }
                break;
            case BISHOP:
                if(team == 0){
                    image = new Image("chess/resources/images/wb.png", false);
                }
                else if(team == 1){
                    image = new Image("chess/resources/images/bb.png", false);
                }
                break;
            case ROOK:
                if(team == 0){
                    image = new Image("chess/resources/images/wr.png", false);
                }
                else if(team == 1){
                    image = new Image("chess/resources/images/br.png", false);
                }
                break;
            case QUEEN:
                if(team == 0){
                    image = new Image("chess/resources/images/wq.png", false);
                }
                else if(team == 1){
                    image = new Image("chess/resources/images/bq.png", false);
                }
                break;
            case KING:
                if(team == 0){
                    image = new Image("chess/resources/images/wk.png", false);
                }
                else if(team == 1){
                    image = new Image("chess/resources/images/bk.png", false);
                }
                break;
        }
	}
	
	public void calculateAttackableSquares(Location loc){
		ArrayList<Location> vals = new ArrayList<Location>();
		Location currentLocation = loc;

        switch(this.type.getEType())
        {
            //--- Possible PAWN moves ------------------------------------------------------
            case PAWN:
                if(this.team == 0){
                    if(currentLocation.getFile()+1 <= this.board.getWidth()-1
                            && currentLocation.getRank()+1 <= this.board.getHeight()-1
                            && (this.board.getSquares()[currentLocation.getFile()+1][currentLocation.getRank()+1].getOccupant() == null
                            || this.board.getSquares()[currentLocation.getFile()+1][currentLocation.getRank()+1].getOccupant().team != 0)){
                        vals.add(this.board.getSquares()[currentLocation.getFile()+1][currentLocation.getRank()+1]);
                        this.board.getSquares()[currentLocation.getFile()+1][currentLocation.getRank()+1].pressure[this.team]++;
                    }

                    if(currentLocation.getFile()-1 >= 0
                            && currentLocation.getRank()+1 <= this.board.getHeight()-1
                            && (this.board.getSquares()[currentLocation.getFile()-1][currentLocation.getRank()+1].getOccupant() == null
                            || this.board.getSquares()[currentLocation.getFile()-1][currentLocation.getRank()+1].getOccupant().team != 0)){
                        vals.add(this.board.getSquares()[currentLocation.getFile()-1][currentLocation.getRank()+1]);
                        this.board.getSquares()[currentLocation.getFile()-1][currentLocation.getRank()+1].pressure[this.team]++;
                    }

                }
                if(this.team == 1){
                    if(currentLocation.getFile()+1 <= this.board.getWidth()-1 && currentLocation.getRank()-1 >= 0
                            && (this.board.getSquares()[currentLocation.getFile()+1][currentLocation.getRank()-1].getOccupant() == null
                            || this.board.getSquares()[currentLocation.getFile()+1][currentLocation.getRank()-1].getOccupant().team != 1)){
                        vals.add(this.board.getSquares()[currentLocation.getFile()+1][currentLocation.getRank()-1]);
                        this.board.getSquares()[currentLocation.getFile()+1][currentLocation.getRank()-1].pressure[this.team]++;
                    }

                    if(currentLocation.getFile()-1 >= 0 && currentLocation.getRank()-1 >= 0
                            && (this.board.getSquares()[currentLocation.getFile()-1][currentLocation.getRank()-1].getOccupant() == null
                            || this.board.getSquares()[currentLocation.getFile()-1][currentLocation.getRank()-1].getOccupant().team != 1)){
                        vals.add(this.board.getSquares()[currentLocation.getFile()-1][currentLocation.getRank()-1]);
                        this.board.getSquares()[currentLocation.getFile()-1][currentLocation.getRank()-1].pressure[this.team]++;
                    }

                }
                break;
            //--- Possible ROOK moves ------------------------------------------------------
            case ROOK:
                //Calculate moves for squares on higher files
                for(int i = currentLocation.getFile(); i <= board.getWidth()-1; i++){
                    Location target = this.board.getSquares()[i][currentLocation.getRank()];
                    if(target.getOccupant() == null){
                        vals.add(target);
                        target.pressure[this.team]++;
                    }
                    else if(target.getOccupant().team != this.team){
                        vals.add(target);
                        target.pressure[this.team]++;
                        break;
                    }
                }
                //Calculate moves for squares on lower files
                for(int i = currentLocation.getFile(); i >= 0; i--){
                    Location target = this.board.getSquares()[i][currentLocation.getRank()];
                    if(target.getOccupant() == null){
                        vals.add(target);
                        target.pressure[this.team]++;
                    }
                    else if(target.getOccupant().team != this.team){
                        vals.add(target);
                        target.pressure[this.team]++;
                        break;
                    }
                }
                //Calculate moves for squares higher ranks
                for(int i = currentLocation.getRank(); i <= board.getHeight()-1; i++){
                    Location target = this.board.getSquares()[currentLocation.getFile()][i];
                    if(target.getOccupant() == null){
                        vals.add(target);
                        target.pressure[this.team]++;
                    }
                    else if(target.getOccupant().team != this.team){
                        vals.add(target);
                        target.pressure[this.team]++;
                        break;
                    }
                }
                //Calculate moves for squares on lower ranks
                for(int i = currentLocation.getRank(); i >= 0; i--){
                    Location target = this.board.getSquares()[currentLocation.getFile()][i];
                    if(target.getOccupant() == null){
                        vals.add(target);
                        target.pressure[this.team]++;
                    }
                    else if(target.getOccupant().team != this.team){
                        vals.add(target);
                        target.pressure[this.team]++;
                        break;
                    }
                }
                break;
            //--- Possible BISHOP moves ----------------------------------------------------
            case BISHOP:
                for(int i = 0; i <= Math.min(board.getWidth()-1-currentLocation.getFile(), board.getHeight()-1-currentLocation.getRank()); i++){
                    if(this.board.getSquares()[currentLocation.getFile()+i][currentLocation.getRank()+i].getOccupant() == null)
                        vals.add(this.board.getSquares()[currentLocation.getFile()+i][currentLocation.getRank()+i]);
                    else if(this.board.getSquares()[currentLocation.getFile()+i][currentLocation.getRank()+i].getOccupant().team != this.team){
                        vals.add(this.board.getSquares()[currentLocation.getFile()+i][currentLocation.getRank()+i]);
                        break;
                    }
                }
                for(int i = 0; i <= Math.min(board.getWidth()-1-currentLocation.getFile(), board.getHeight()-1-currentLocation.getRank()); i++){
                    if(this.board.getSquares()[currentLocation.getFile()+i][currentLocation.getRank()+i].getOccupant() == null)
                        vals.add(this.board.getSquares()[currentLocation.getFile()+i][currentLocation.getRank()+i]);
                    else if(this.board.getSquares()[currentLocation.getFile()+i][currentLocation.getRank()+i].getOccupant().team != this.team){
                        vals.add(this.board.getSquares()[currentLocation.getFile()+i][currentLocation.getRank()+i]);
                        break;
                    }
                }
                break;
            default:
                break;
        }
		attackableSquares = vals;
	}

    public Type getType() {
        return type;
    }

    public int getTeam() {
        return team;
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Location> getAttackableSquares() {
        return attackableSquares;
    }

    public Image getImage() {
        String piecePath = "chess/resources/images/";
        if(this.team == 0){
            piecePath += "w";
        }
        else if(this.team == 1){
            piecePath += "b";
        }
        piecePath += Character.toString(this.getType().getShortName()).toLowerCase();
        piecePath += "";
        piecePath += ".png";
        return new Image(piecePath);
    }
}
