package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author royk
 *  This purpose of the helper class is to test functionality before adding it to chess.
 */
public class Helper extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,1000,950);

			Image whiteKnightImg = new Image(getClass().getResourceAsStream("whiteknight.png"), 100,100,true,true);
			ImageView whiteKnightView = new ImageView(whiteKnightImg);

			whiteKnightView.getImage();

			Rectangle rec = new Rectangle(100,100,Color.BLUE);

			//Setting up Chessboard squares as StackPane nodes
			SquarePane squarePane1 = new SquarePane();

			squarePane1.addPiece("black", "knight"); //index 1
			squarePane1.setLayoutX(100);
			squarePane1.setLayoutY(100);

			SquarePane squarePane2 = new SquarePane();
			squarePane2.setLightSquare();
			squarePane2.setLayoutX(200);
			squarePane2.setLayoutY(100);

			SquarePane squarePane3 = new SquarePane();
			squarePane3.setLayoutX(300);
			squarePane3.setLayoutY(100);




			//squarePane2.getChildren().get(0);
			//squarePane3.getChildren().add(squarePane1.getChildren().get(1));



			/**
			 * In this method, we need a way of storing what type of piece is inside the 
			 * source squarePane. i.e. black knight
			 *
			 * 
			 */
			squarePane1.setOnDragDetected(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event) {
					if(squarePane1.hasPiece()) {
						Dragboard db = squarePane1.startDragAndDrop(TransferMode.ANY);
						ClipboardContent content = new ClipboardContent();
						content.putString(squarePane1.getColor() + squarePane1.getPiece());
						System.out.println("Color: " + squarePane1.getColor());
						System.out.println("Piece: " + squarePane1.getPiece());
						System.out.println("1: " + content.getString());
						db.setContent(content);
						event.consume();
					}
				}
			});

			squarePane3.setOnDragOver(new EventHandler <DragEvent>() {
				@Override
				public void handle(DragEvent event) {
					/* data is dragged over the target */
					System.out.println("onDragOver");

					/* accept it only if it is  not dragged from the same node 
					 * and if it has a string data */
					if (event.getGestureSource() != squarePane3 &&
							event.getDragboard().hasString()) {
						/* allow for moving */
						event.acceptTransferModes(TransferMode.MOVE);
					}

					event.consume();
				}
			});

			//7-4: Using "DRAG_ENTERED" to give visual feedback of the motion.
			squarePane3.setOnDragEntered(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					//System.out.println("dragEntered");
					if (event.getGestureSource() != squarePane3 && 
							event.getDragboard().hasString()) {
						//System.out.println("Drag Entered");
						squarePane3.lighterSquareColor();
					}

					event.consume();
				}
			});

			squarePane3.setOnDragExited(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					/* mouse moved away, remove the graphical cues */
					squarePane3.originalSquareColor();
					event.consume();
				}
			});


			squarePane3.setOnDragDropped(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					/* data dropped */
					/* if there is a string data on dragboard, read it and use it */
					System.out.println(3);
					Dragboard db = event.getDragboard();
					System.out.println(3.1);
					boolean success = false;
					if (db.hasString()) {
						System.out.println(3.2);
						System.out.println(db.getString());
						System.out.println("hell");
						String color = db.getString().substring(0,5);
						System.out.println(color);
						String piece = db.getString().substring(5);
						System.out.println(piece);
						squarePane3.addPiece(color, piece);
						success = true;
						System.out.println(4);
					}
					/* let the source know whether the string was successfully 
					 * transferred and used */
					event.setDropCompleted(success);

					event.consume();
				}
			});
			
			



			squarePane1.setOnDragDone(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {
					/* the drag and drop gesture ended */
					/* if the data was successfully moved, clear it */
					if (event.getTransferMode() == TransferMode.MOVE) {
						squarePane1.removePiece();
					}
					event.consume();
				}
			});





			root.getChildren().addAll(squarePane1, squarePane2, squarePane3);



			/**
			 * want to implement StackPane
			 * want to know if there is a ImageView added to the StackPane
			 * Because this will determine whether or not this particular square should be draggable
			 * In Summary: if(stackpane contains imageview) --> draggable = true
			 *             else (stackpane does not contain imageview) --> draggable = false
			 *             
			 */





			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}



}
