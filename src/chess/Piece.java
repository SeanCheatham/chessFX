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
        eType e = this.type.getEType();
        //--- Possible PAWN moves ------------------------------------------------------
        if (e == eType.PAWN) {
            //Calculate moves for White
            if (this.team == 0) {
                //Calculate the move on the right diagonal
                if (loc.getFile() + 1 <= this.board.getWidth() - 1
                        && loc.getRank() + 1 <= this.board.getHeight() - 1
                        && (this.board.getSquares()[loc.getFile() + 1][loc.getRank() + 1].getOccupant() == null
                        || this.board.getSquares()[loc.getFile() + 1][loc.getRank() + 1].getOccupant().team != 0)) {
                    vals.add(this.board.getSquares()[loc.getFile() + 1][loc.getRank() + 1]);
                    this.board.getSquares()[loc.getFile() + 1][loc.getRank() + 1].setPressure(this.team, 1);
                }
                //Calculate the move on the left diagonal
                if (loc.getFile() - 1 >= 0
                        && loc.getRank() + 1 <= this.board.getHeight() - 1
                        && (this.board.getSquares()[loc.getFile() - 1][loc.getRank() + 1].getOccupant() == null
                        || this.board.getSquares()[loc.getFile() - 1][loc.getRank() + 1].getOccupant().team != 0)) {
                    vals.add(this.board.getSquares()[loc.getFile() - 1][loc.getRank() + 1]);
                    this.board.getSquares()[loc.getFile() - 1][loc.getRank() + 1].setPressure(this.team, 1);
                }

            }
            //Calculate moves for Black
            if (this.team == 1) {
                //Calculate the move on the right diagonal
                if (loc.getFile() + 1 <= this.board.getWidth() - 1 && loc.getRank() - 1 >= 0
                        && (this.board.getSquares()[loc.getFile() + 1][loc.getRank() - 1].getOccupant() == null
                        || this.board.getSquares()[loc.getFile() + 1][loc.getRank() - 1].getOccupant().team != 1)) {
                    vals.add(this.board.getSquares()[loc.getFile() + 1][loc.getRank() - 1]);
                    this.board.getSquares()[loc.getFile() + 1][loc.getRank() - 1].setPressure(this.team, 1);
                }
                //Calculate the move on the left diagonal
                if (loc.getFile() - 1 >= 0 && loc.getRank() - 1 >= 0
                        && (this.board.getSquares()[loc.getFile() - 1][loc.getRank() - 1].getOccupant() == null
                        || this.board.getSquares()[loc.getFile() - 1][loc.getRank() - 1].getOccupant().team != 1)) {
                    vals.add(this.board.getSquares()[loc.getFile() - 1][loc.getRank() - 1]);
                    this.board.getSquares()[loc.getFile() - 1][loc.getRank() - 1].setPressure(this.team, 1);
                }

            }
        }
        //--- Possible ROOK moves ------------------------------------------------------
        if (e == eType.ROOK) {
            //Calculate moves for squares on higher files
            for (int i = loc.getFile(); i <= board.getWidth() - 1; i++) {
                Location target = this.board.getSquares()[i][loc.getRank()];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
            //Calculate moves for squares on lower files
            for (int i = loc.getFile(); i >= 0; i--) {
                Location target = this.board.getSquares()[i][loc.getRank()];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
            //Calculate moves for squares on higher ranks
            for (int i = loc.getRank(); i <= board.getHeight() - 1; i++) {
                Location target = this.board.getSquares()[loc.getFile()][i];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
            //Calculate moves for squares on lower ranks
            for (int i = loc.getRank(); i >= 0; i--) {
                Location target = this.board.getSquares()[loc.getFile()][i];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
        }
        //--- Possible BISHOP moves ----------------------------------------------------
        if (e == eType.BISHOP) {
            //Calculate moves for squares on the upper right diagonal
            for (int i = 1; i <= Math.min(board.getWidth() - 1 - loc.getFile(), board.getHeight() - 1 - loc.getRank()); i++) {
                Location target = this.board.getSquares()[loc.getFile() + i][loc.getRank() + i];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
            //Calculate moves for squares on the upper left diagonal
            for (int i = 1; i <= Math.min(loc.getFile(), board.getHeight() - 1 - loc.getRank()); i++) {
                Location target = this.board.getSquares()[loc.getFile() - i][loc.getRank() + i];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
            //Calculate moves for squares on the lower right diagonal
            for (int i = 1; i <= Math.min(board.getWidth() - 1 - loc.getFile(), loc.getRank()); i++) {
                Location target = this.board.getSquares()[loc.getFile() + i][loc.getRank() - i];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
            //Calculate moves for squares on the lower left diagonal
            for (int i = 1; i <= Math.min(loc.getFile(), loc.getRank()); i++) {
                Location target = this.board.getSquares()[loc.getFile() - i][loc.getRank() - i];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
        }
        //--- Possible QUEEN moves ----------------------------------------------------
        if (e == eType.QUEEN) {
            //Calculate moves for squares on the upper right diagonal
            for (int i = 1; i <= Math.min(board.getWidth() - 1 - loc.getFile(), board.getHeight() - 1 - loc.getRank()); i++) {
                Location target = this.board.getSquares()[loc.getFile() + i][loc.getRank() + i];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
            //Calculate moves for squares on the upper left diagonal
            for (int i = 1; i <= Math.min(loc.getFile(), board.getHeight() - 1 - loc.getRank()); i++) {
                Location target = this.board.getSquares()[loc.getFile() - i][loc.getRank() + i];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
            //Calculate moves for squares on the lower right diagonal
            for (int i = 1; i <= Math.min(board.getWidth() - 1 - loc.getFile(), loc.getRank()); i++) {
                Location target = this.board.getSquares()[loc.getFile() + i][loc.getRank() - i];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
            //Calculate moves for squares on the lower left diagonal
            for (int i = 1; i <= Math.min(loc.getFile(), loc.getRank()); i++) {
                Location target = this.board.getSquares()[loc.getFile() - i][loc.getRank() - i];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
            //Calculate moves for squares on higher files
            for (int i = loc.getFile(); i <= board.getWidth() - 1; i++) {
                Location target = this.board.getSquares()[i][loc.getRank()];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
            //Calculate moves for squares on lower files
            for (int i = loc.getFile(); i >= 0; i--) {
                Location target = this.board.getSquares()[i][loc.getRank()];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
            //Calculate moves for squares on higher ranks
            for (int i = loc.getRank(); i <= board.getHeight() - 1; i++) {
                Location target = this.board.getSquares()[loc.getFile()][i];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
            //Calculate moves for squares on lower ranks
            for (int i = loc.getRank(); i >= 0; i--) {
                Location target = this.board.getSquares()[loc.getFile()][i];

                if (target.getOccupant() == null) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                } else if (target.getOccupant() != this) {
                    vals.add(target);
                    target.setPressure(this.team, 1);
                    break;
                }
            }
        }
        //--- Possible KNIGHT moves ------------------------------------------------------
        if (e == eType.KNIGHT) {
            if (loc.getRank() + 2 <= board.getHeight() - 1) {
                if (loc.getFile() + 1 <= board.getWidth() - 1) {
                    vals.add(board.getSquares()[loc.getFile() + 1][loc.getRank() + 2]);
                    board.getSquares()[loc.getFile() + 1][loc.getRank() + 2].setPressure(this.getTeam(), 1);
                }
                if (loc.getFile() - 1 >= 0) {
                    vals.add(board.getSquares()[loc.getFile() - 1][loc.getRank() + 2]);
                    board.getSquares()[loc.getFile() - 1][loc.getRank() + 2].setPressure(this.getTeam(), 1);
                }
            }
            if (loc.getRank() - 2 >= 0) {
                if (loc.getFile() + 1 <= board.getWidth() - 1) {
                    vals.add(board.getSquares()[loc.getFile() + 1][loc.getRank() - 2]);
                    board.getSquares()[loc.getFile() + 1][loc.getRank() - 2].setPressure(this.getTeam(), 1);
                }
                if (loc.getFile() - 1 >= 0) {
                    vals.add(board.getSquares()[loc.getFile() - 1][loc.getRank() - 2]);
                    board.getSquares()[loc.getFile() - 1][loc.getRank() - 2].setPressure(this.getTeam(), 1);
                }
            }
            if (loc.getFile() + 2 <= board.getWidth() - 1) {
                if (loc.getRank() + 1 <= board.getHeight() - 1) {
                    vals.add(board.getSquares()[loc.getFile() + 2][loc.getRank() + 1]);
                    board.getSquares()[loc.getFile() + 2][loc.getRank() + 1].setPressure(this.getTeam(), 1);
                }
                if (loc.getRank() - 1 >= 0) {
                    vals.add(board.getSquares()[loc.getFile() + 2][loc.getRank() - 1]);
                    board.getSquares()[loc.getFile() + 2][loc.getRank() - 1].setPressure(this.getTeam(), 1);
                }
            }
            if (loc.getFile() - 2 >= 0) {
                if (loc.getRank() + 1 <= board.getHeight() - 1) {
                    vals.add(board.getSquares()[loc.getFile() - 2][loc.getRank() + 1]);
                    board.getSquares()[loc.getFile() - 2][loc.getRank() + 1].setPressure(this.getTeam(), 1);
                }
                if (loc.getRank() - 1 >= 0) {
                    vals.add(board.getSquares()[loc.getFile() - 2][loc.getRank() - 1]);
                    board.getSquares()[loc.getFile() - 2][loc.getRank() - 1].setPressure(this.getTeam(), 1);
                }
            }
        }
        //--- Possible KING moves ------------------------------------------------------
        if (e == eType.KING) {
            if (loc.getRank() + 1 <= board.getHeight() - 1) {
                if (loc.getFile() + 1 <= board.getWidth() - 1) {
                    vals.add(board.getSquares()[loc.getFile() + 1][loc.getRank() + 1]);
                    board.getSquares()[loc.getFile() + 1][loc.getRank() + 1].setPressure(this.team, 1);
                }
                if (loc.getFile() - 1 <= board.getWidth() - 1) {
                    vals.add(board.getSquares()[loc.getFile() - 1][loc.getRank() + 1]);
                    board.getSquares()[loc.getFile() - 1][loc.getRank() + 1].setPressure(this.team, 1);
                }
                vals.add(board.getSquares()[loc.getFile()][loc.getRank() + 1]);
                board.getSquares()[loc.getFile()][loc.getRank() + 1].setPressure(this.team, 1);
            }
            if (loc.getRank() - 1 >= 0) {
                if (loc.getFile() + 1 <= board.getWidth() - 1) {
                    vals.add(board.getSquares()[loc.getFile() + 1][loc.getRank() - 1]);
                    board.getSquares()[loc.getFile() + 1][loc.getRank() - 1].setPressure(this.team, 1);
                }
                if (loc.getFile() - 1 <= board.getWidth() - 1) {
                    vals.add(board.getSquares()[loc.getFile() - 1][loc.getRank() - 1]);
                    board.getSquares()[loc.getFile() - 1][loc.getRank() - 1].setPressure(this.team, 1);
                }
                vals.add(board.getSquares()[loc.getFile()][loc.getRank() - 1]);
                board.getSquares()[loc.getFile()][loc.getRank() - 1].setPressure(this.team, 1);
            }
            if (loc.getFile() + 1 <= board.getWidth() - 1) {
                vals.add(board.getSquares()[loc.getFile() + 1][loc.getRank()]);
                board.getSquares()[loc.getFile() + 1][loc.getRank()].setPressure(this.team, 1);
            }
            if (loc.getFile() - 1 >= 0) {
                vals.add(board.getSquares()[loc.getFile() - 1][loc.getRank()]);
                board.getSquares()[loc.getFile() - 1][loc.getRank()].setPressure(this.team, 1);
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
