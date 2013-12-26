package chess;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("board.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED);
        GridPane grid = new GridPane();
        GridPane board = new GridPane();
        TextArea console = new TextArea();

        primaryStage.setTitle("Chess");



        Board b = new Board(8,8);
        console.appendText("Constructed board of width: "+Integer.toString(b.getWidth())+" and Height: "+Integer.toString(b.getHeight())+"\n");
        addPieces(b);
        console.appendText(b.toString());
        for(int j = b.getHeight() - 1; j >= 0; j--){
            for(int i = 0; i <= b.getWidth() - 1; i++){
                final Pane p = new Pane();
                p.setPrefHeight(60);
                p.setPrefWidth(60);
                p.setId(""+i+","+j);
                String baseColor = "#323232";
                if(b.getSquares()[i][j].getColor() == Color.WHITE) baseColor = "#ffffff";
                int pressure = b.getSquares()[i][j].pressure[0]-b.getSquares()[i][j].pressure[1];
                //System.out.println(pressure);
                int pressureVariant = 64 * (1+Math.abs(pressure)/2);
                if(pressure == 0){
                    p.setStyle("display:block;-fx-background-color:"+baseColor+";");
                }
                else if(pressure > 0){
                    //p.setStyle("display:block;-fx-background-color:radial-gradient(focus-angle 0deg, focus-distance 0%, center 50% 50%, radius 100%,"+baseColor+", derive(rgb(0,255,0),"+Math.abs(pressureVariant)+"));");
                    p.setStyle("display:block;-fx-background-color:rgb(0,"+pressureVariant+",0);");
                }
                else if(pressure < 0){
                    //p.setStyle("display:block;-fx-background-color:radial-gradient(focus-angle 0deg, focus-distance 0%, center 50% 50%, radius 100%,"+baseColor+", derive(rgb(255,0,0),"+Math.abs(pressureVariant)+"));");
                    p.setStyle("display:block;-fx-background-color:rgb("+pressureVariant+",0,0);");
                }
                if(b.getSquares()[i][j].getOccupant() != null && b.getSquares()[i][j].getOccupant().getImage() != null){
                    Image img = b.getSquares()[i][j].getOccupant().getImage();
                    ImageView r = new ImageView(img);
                    final Piece currentPiece = b.getSquares()[i][j].getOccupant();
                    final String storedStyle = p.getStyle();

                    //Print clicked piece to console and toggle yellow color to show selection
                    r.setOnMouseClicked(new EventHandler<MouseEvent>()
                    {
                        @Override
                        public void handle(MouseEvent t) {

                            System.out.println("CLICKED: " +
                                    ((currentPiece.getTeam()==0)?"WHITE":"BLACK")
                                    + " " + currentPiece.getType().getEType());

                            if(p.getStyle().equals("display:block;-fx-background-color:yellow"))
                                p.setStyle(storedStyle);
                            else
                                p.setStyle("display:block;-fx-background-color:yellow");
                        }
                    });
                    p.getChildren().add(r);
                }
                board.add(p,i,b.getHeight()-1-j);
            }
        }
        grid.add(board,0,0);
        grid.add(console,0,1);
        primaryStage.setScene(new Scene(grid));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }

    private void addPieces(Board b){
        //White's back line
        b.getSquares()[0][0].setOccupant(new Piece(new Type(eType.ROOK),0,b));
        b.getSquares()[1][0].setOccupant(new Piece(new Type(eType.KNIGHT),0,b));
        b.getSquares()[2][0].setOccupant(new Piece(new Type(eType.BISHOP), 0, b));
        b.getSquares()[3][0].setOccupant(new Piece(new Type(eType.QUEEN),0,b));
        b.getSquares()[4][0].setOccupant(new Piece(new Type(eType.KING),0,b));
        b.getSquares()[5][0].setOccupant(new Piece(new Type(eType.BISHOP),0,b));
        b.getSquares()[6][0].setOccupant(new Piece(new Type(eType.KNIGHT),0,b));
        b.getSquares()[7][0].setOccupant(new Piece(new Type(eType.ROOK),0,b));
        //White's front line
        /*//b.getSquares()[0][1].setOccupant(new Piece(new Type(eType.PAWN),0,b));
        b.getSquares()[1][1].setOccupant(new Piece(new Type(eType.PAWN),0,b));
        b.getSquares()[2][1].setOccupant(new Piece(new Type(eType.PAWN),0,b));
        b.getSquares()[3][3].setOccupant(new Piece(new Type(eType.PAWN), 0, b));
        b.getSquares()[4][1].setOccupant(new Piece(new Type(eType.PAWN), 0, b));
        b.getSquares()[5][3].setOccupant(new Piece(new Type(eType.PAWN),0,b));
        b.getSquares()[6][1].setOccupant(new Piece(new Type(eType.PAWN),0,b));
        //b.getSquares()[7][1].setOccupant(new Piece(new Type(eType.PAWN),0,b));*/
        //Black's front line
        //b.getSquares()[0][6].setOccupant(new Piece(new Type(eType.PAWN),1,b));
        b.getSquares()[1][6].setOccupant(new Piece(new Type(eType.PAWN),1,b));
        b.getSquares()[2][6].setOccupant(new Piece(new Type(eType.PAWN),1,b));
        b.getSquares()[3][4].setOccupant(new Piece(new Type(eType.PAWN), 1, b));
        b.getSquares()[4][6].setOccupant(new Piece(new Type(eType.PAWN),1,b));
        b.getSquares()[5][6].setOccupant(new Piece(new Type(eType.PAWN),1,b));
        b.getSquares()[6][6].setOccupant(new Piece(new Type(eType.PAWN),1,b));
        //b.getSquares()[7][6].setOccupant(new Piece(new Type(eType.PAWN),1,b));
        //Black's back line
        b.getSquares()[0][7].setOccupant(new Piece(new Type(eType.ROOK),1,b));
        /*b.getSquares()[1][7].setOccupant(new Piece(new Type(eType.KNIGHT),1,b));
        b.getSquares()[2][7].setOccupant(new Piece(new Type(eType.BISHOP),1,b));
        b.getSquares()[3][7].setOccupant(new Piece(new Type(eType.QUEEN),1,b));
        b.getSquares()[4][7].setOccupant(new Piece(new Type(eType.KING),1,b));
        b.getSquares()[5][7].setOccupant(new Piece(new Type(eType.BISHOP), 1, b));
        b.getSquares()[6][7].setOccupant(new Piece(new Type(eType.KNIGHT), 1, b));*/
        //b.getSquares()[7][7].setOccupant(new Piece(new Type(eType.ROOK), 1, b));
        for(Location[] locArray : b.getSquares()){
            for(Location loc : locArray){
                if(loc.getOccupant() != null) loc.getOccupant().calculateAttackableSquares(loc);
            }
        }
    }

}
