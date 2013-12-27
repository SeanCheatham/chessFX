package chess;

import javafx.scene.image.Image;

import java.util.ArrayList;

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
        String piecePath = "chess/resources/images/";
        if (this.team == 0) {
            piecePath += "w";
        } else if (this.team == 1) {
            piecePath += "b";
        }
        piecePath += Character.toString(this.getType().getShortName()).toLowerCase();
        piecePath += "";
        piecePath += ".png";
        image = new Image(piecePath);
    }
	
	public void calculateAttackableSquares(Location loc){
		ArrayList<Location> vals = new ArrayList<Location>();

            //--- Possible PAWN moves ------------------------------------------------------
        if (this.type.getEType() == eType.PAWN) {
            //Calculate moves for White
            if (this.team == 0) {
                //Calculate the move on the right diagonal
                if (loc.getFile() + 1 <= this.board.getWidth() - 1
                        && loc.getRank() + 1 <= this.board.getHeight() - 1
                        && (this.board.getSquares()[loc.getFile() + 1][loc.getRank() + 1].getOccupant() == null
                        || this.board.getSquares()[loc.getFile() + 1][loc.getRank() + 1].getOccupant().team != 0)) {
                    vals.add(this.board.getSquares()[loc.getFile() + 1][loc.getRank() + 1]);
                    this.board.getSquares()[loc.getFile() + 1][loc.getRank() + 1].pressure[this.team]++;
                }
                //Calculate the move on the left diagonal
                if (loc.getFile() - 1 >= 0
                        && loc.getRank() + 1 <= this.board.getHeight() - 1
                        && (this.board.getSquares()[loc.getFile() - 1][loc.getRank() + 1].getOccupant() == null
                        || this.board.getSquares()[loc.getFile() - 1][loc.getRank() + 1].getOccupant().team != 0)) {
                    vals.add(this.board.getSquares()[loc.getFile() - 1][loc.getRank() + 1]);
                    this.board.getSquares()[loc.getFile() - 1][loc.getRank() + 1].pressure[this.team]++;
                }

            }
            //Calculate moves for Black
            if (this.team == 1) {
                //Calculate the move on the right diagonal
                if (loc.getFile() + 1 <= this.board.getWidth() - 1 && loc.getRank() - 1 >= 0
                        && (this.board.getSquares()[loc.getFile() + 1][loc.getRank() - 1].getOccupant() == null
                        || this.board.getSquares()[loc.getFile() + 1][loc.getRank() - 1].getOccupant().team != 1)) {
                    vals.add(this.board.getSquares()[loc.getFile() + 1][loc.getRank() - 1]);
                    this.board.getSquares()[loc.getFile() + 1][loc.getRank() - 1].pressure[this.team]++;
                }
                //Calculate the move on the left diagonal
                if (loc.getFile() - 1 >= 0 && loc.getRank() - 1 >= 0
                        && (this.board.getSquares()[loc.getFile() - 1][loc.getRank() - 1].getOccupant() == null
                        || this.board.getSquares()[loc.getFile() - 1][loc.getRank() - 1].getOccupant().team != 1)) {
                    vals.add(this.board.getSquares()[loc.getFile() - 1][loc.getRank() - 1]);
                    this.board.getSquares()[loc.getFile() - 1][loc.getRank() - 1].pressure[this.team]++;
                }

            }
        }
        //--- Possible ROOK moves ------------------------------------------------------
        else if (this.type.getEType() == eType.ROOK) {
            //Calculate moves for squares on higher files
            for (int i = loc.getFile(); i <= board.getWidth() - 1; i++) {
                Location target = this.board.getSquares()[i][loc.getRank()];
                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.pressure[this.team]++;
                } else if (target.getOccupant().team != this.team) {
                    vals.add(target);
                    target.pressure[this.team]++;
                    break;
                } else {
                    break;
                }
            }
            //Calculate moves for squares on lower files
            for (int i = loc.getFile(); i >= 0; i--) {
                Location target = this.board.getSquares()[i][loc.getRank()];
                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.pressure[this.team]++;
                } else if (target.getOccupant().team != this.team) {
                    vals.add(target);
                    target.pressure[this.team]++;
                    break;
                } else {
                    break;
                }
            }
            //Calculate moves for squares on higher ranks
            for (int i = loc.getRank(); i <= board.getHeight() - 1; i++) {
                Location target = this.board.getSquares()[loc.getFile()][i];
                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.pressure[this.team]++;
                } else if (target.getOccupant().team != this.team) {
                    vals.add(target);
                    target.pressure[this.team]++;
                    break;
                } else {
                    break;
                }
            }
            //Calculate moves for squares on lower ranks
            for (int i = loc.getRank(); i >= 0; i--) {
                Location target = this.board.getSquares()[loc.getFile()][i];
                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.pressure[this.team]++;
                } else if (target.getOccupant().team != this.team) {
                    vals.add(target);
                    target.pressure[this.team]++;
                    break;
                } else {
                    break;
                }
            }
        }
        //--- Possible BISHOP moves ----------------------------------------------------
        else if (this.type.getEType() == eType.BISHOP)
            //Calculate moves for squares on the upper right diagonal
            for (int i = 0; i <= Math.min(board.getWidth() - 1 - loc.getFile(), board.getHeight() - 1 - loc.getRank()); i++) {
                Location target = this.board.getSquares()[loc.getFile() + i][loc.getRank() + i];
                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.pressure[this.team]++;
                } else if (target.getOccupant().team != this.team) {
                    vals.add(target);
                    target.pressure[this.team]++;
                    break;
                } else {
                    break;
                }
            }
        //Calculate moves for squares on the upper left diagonal
        for (int i = 0; i <= Math.min(loc.getFile(), board.getHeight() - 1 - loc.getRank()); i++) {
            Location target = this.board.getSquares()[loc.getFile() - i][loc.getRank() + i];
            if (target.getOccupant() == null) {
                vals.add(target);
                target.pressure[this.team]++;
            } else if (target.getOccupant().team != this.team) {
                vals.add(target);
                target.pressure[this.team]++;
                break;
            } else {
                break;
            }
        }
        //Calculate moves for squares on the lower right diagonal
        for (int i = 0; i <= Math.min(board.getWidth() - 1 - loc.getFile(), loc.getRank()); i++) {
            Location target = this.board.getSquares()[loc.getFile() + i][loc.getRank() - i];
            if (target.getOccupant() == null) {
                vals.add(target);
                target.pressure[this.team]++;
            } else if (target.getOccupant().team != this.team) {
                vals.add(target);
                target.pressure[this.team]++;
                break;
            } else {
                break;
            }
        }
        //Calculate moves for squares on the lower left diagonal
        for (int i = 0; i <= Math.min(loc.getFile(), loc.getRank()); i++) {
            Location target = this.board.getSquares()[loc.getFile() - i][loc.getRank() - i];
            if (target.getOccupant() == null) {
                vals.add(target);
                target.pressure[this.team]++;
            } else if (target.getOccupant().team != this.team) {
                vals.add(target);
                target.pressure[this.team]++;
                break;
            } else {
                break;
            }
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
        return image;
    }
}
