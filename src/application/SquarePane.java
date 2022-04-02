package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquarePane extends StackPane{

	private boolean pieceOnSquare;
	private String piece;
	private String color;
	private boolean darkSquare;
	private Rectangle rec;

	public SquarePane() {
		super();
		pieceOnSquare = false;
		piece = "";
		color = "";
		darkSquare = true;
		rec = new Rectangle(100,100);
		rec.setFill(Color.web("#2626ED"));
		this.getChildren().add(rec);
	}


	/**
	 * This method provides the functionality for the setOnDragExited and setOnDragDropped methods
	 * If a drag is moved over the square, make it slightly darker/lighter.
	 */

	public void setLightSquare() {
		rec.setFill(Color.web("#65B9E0"));
		darkSquare = false;
	}

	public void setDarkSquare() {
		rec.setFill(Color.web("#2626ED"));
		darkSquare = true;
	}
	
	public void setLightSquareHover() {
		rec.setFill(Color.web("#74C7ED"));
		darkSquare = false;
	}
	
	public void setDarkSquareHover() {
		rec.setFill(Color.web("#4545FF"));
		darkSquare = true;
	}

	public void originalSquareColor() {
		if(darkSquare) {
			setDarkSquare();
		} else {
			setLightSquare();
		}
	}

	public void lighterSquareColor() {
		if(darkSquare) {
			setDarkSquareHover();
		} else {
			setLightSquareHover();
		}
	}


	public void addPiece(String color, String piece) {
		this.getChildren().add(getImageView(color, piece));
		pieceOnSquare = true;
		this.color = color;
		this.piece = piece;
	}

	public ImageView getImageView(String color, String piece) {
		switch(piece) {
		case "knight": 
			if(color.equals("white")) {
				Image whiteKnightImg = new Image(getClass().getResourceAsStream("whiteknight.png"), 100,100,true,true);
				ImageView whiteKnightView = new ImageView(whiteKnightImg);
				return whiteKnightView;
			} else {
				Image blackKnightImg = new Image(getClass().getResourceAsStream("blackknight.png"), 100,100,true,true);
				ImageView blackKnightView = new ImageView(blackKnightImg);
				return blackKnightView;
			}
		case "rook": 
			if(color.equals("white")) {
				Image whiteRookImg = new Image(getClass().getResourceAsStream("whiterook.png"), 100,100,true,true);
				ImageView whiteRookView = new ImageView(whiteRookImg);
				return whiteRookView;
			} else {
				Image blackRookImg = new Image(getClass().getResourceAsStream("blackrook.png"), 100,100,true,true);
				ImageView blackRookView = new ImageView(blackRookImg);
				return blackRookView;
			}
		case "bishop": 
			if(color.equals("white")) {
				Image whiteBishopImg = new Image(getClass().getResourceAsStream("whitebishop.png"), 100,100,true,true);
				ImageView whiteBishopView = new ImageView(whiteBishopImg);
				return whiteBishopView;
			} else {
				Image blackBishopImg = new Image(getClass().getResourceAsStream("blackbishop.png"), 100,100,true,true);
				ImageView blackBishopView = new ImageView(blackBishopImg);
				return blackBishopView;
			}	
		default:
			Image no = new Image(getClass().getResourceAsStream("dot.png"), 100,100,true,true);
			ImageView dotView = new ImageView(no);
			System.out.println("Dot");
			return dotView;

		}
	}
	
	public void removePiece() {
		if(pieceOnSquare) {
			this.getChildren().remove(1);
		} else {
			System.out.println("No children!");
		}
	}

	//GETTERS & SETTERS
	public boolean hasPiece() {
		return pieceOnSquare;
	}

	public void setPieceOnSquare(boolean pieceOnSquare) {
		this.pieceOnSquare = pieceOnSquare;
	}

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	

}
