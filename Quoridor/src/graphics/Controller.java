package graphics;

import game.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.InputMismatchException;

public class Controller {
	private Play play;
	private Board board;
	public void setPlay(Play play) {
		this.play = play;
	}
	public void setBoard(Board board) { this.board = board; }
	// shall be changed objects
	@FXML protected Label info = new Label();
	@FXML protected AnchorPane table;
	@FXML protected AnchorPane bead1;
	@FXML protected AnchorPane bead2;
	// traverses through major nodes ... some how changing scene
	@FXML
	protected void gotoGame() throws IOException { play.gotoFXML("game.fxml"); initializeGame(); }
	@FXML
	protected void gotoLoad() throws IOException { play.gotoFXML("load.fxml"); }
	@FXML
	protected void gotoMenu() throws IOException { play.gotoFXML("mainMenu.fxml"); }
	@FXML
	protected void Exit() { play.getPrimaryStage().close(); }
	// create the board
	@FXML
	protected void initializeGame() {
		// creating the board
		table = (AnchorPane)(play.getScene().lookup("#table"));
		final double NARROW = 10, THICK = 40;

		int y = 0;
		for (int i = 0; i < 17; ++i) {
			int x = 0;
			for (int j = 0; j < 17; j++) {
				String id = "cell" + ((i < 10)? "0" + i : i) + ((j < 10)? "0" + j : j);
				AnchorPane cell = new AnchorPane();
				cell.setId(id);

				cell.setLayoutX(x);
				cell.setLayoutY(y);
				if (i % 2 == 0)
					cell.setPrefHeight(THICK);
				else {
					cell.setPrefHeight(NARROW);
				}
				if (j % 2 == 0) {
					if (i % 2 == 0)
						cell.setStyle("-fx-background-color: maroon");
					else
						cell.setStyle("-fx-background-color: chocolate");
					cell.setPrefWidth(THICK);
					x += THICK;
				}
				else {
					if (i % 2 == 1)
						cell.setStyle("-fx-background-color: firebrick");
					else
						cell.setStyle("-fx-background-color: chocolate");
					cell.setPrefWidth(NARROW);
					x += NARROW;
				}
				// add functionality
				cell.setOnMouseClicked(this::BeadMover);
				if (cell.getStyle().equals("-fx-background-color: chocolate"))
					cell.setOnMouseEntered(this::canPlace);
				else if (cell.getStyle().equals("-fx-background-color: maroon"))
					cell.setOnMouseEntered(this::canMove);
				if (!cell.getStyle().equals("-fx-background-color: firebrick"))
					cell.setOnMouseExited(this::baseColor);

				table.getChildren().add(cell);
			}
			if (i % 2 == 0)
				y += THICK;
			else
				y += NARROW;
		}
		// initialize the beads
		{
			bead1 = new AnchorPane();
			bead1.setPrefSize(THICK, THICK);
			bead1.setLayoutX(play.getScene().lookup("#cell0008").getLayoutX());
			bead1.setLayoutY(play.getScene().lookup("#cell0008").getLayoutY());
			bead2 = new AnchorPane();
			bead2.setPrefSize(THICK, THICK);
			bead2.setLayoutX(play.getScene().lookup("#cell1608").getLayoutX());
			bead2.setLayoutY(play.getScene().lookup("#cell1608").getLayoutY());
			// adding style
			bead1.setStyle("-fx-background-size: 40 40;" +
					               "-fx-background-radius: 40;" +
					               "-fx-border-radius: 40;" +
					               "-fx-background-color: royalblue");
			bead2.setStyle("-fx-background-size: 40 40;" +
					               "-fx-background-radius: 40;" +
					               "-fx-border-radius: 40;" +
					               "-fx-background-color: limegreen");
			// add to the table
			table.getChildren().addAll(bead1, bead2);
		}
	}
	@FXML
	protected void BeadMover(MouseEvent event) {
		int index = Integer.parseInt(((AnchorPane)event.getSource()).getId().substring(4));
		int x = index % 100, y = index / 100;
		try {
			this.board.move(x, y);
			// move the on-screen bead
			AnchorPane clicked = (AnchorPane)event.getSource();
			AnchorPane bead = (board.getTurn().getId() == 'D') ? bead1 : bead2;

			bead.setLayoutX(clicked.getLayoutX());
			bead.setLayoutY(clicked.getLayoutY());
		} catch (InputMismatchException exception) {
			String info = this.info.getText();
			this.info.setFont(new Font(this.info.getFont().getStyle(), 30));
			this.info.setText(exception.getMessage().toUpperCase());

			try { Thread.sleep(2500); } catch (InterruptedException e) { e.printStackTrace(); }
			this.info.setText(info);
			this.info.setFont(new Font(this.info.getFont().getStyle(), 16));
		}
	}
	@FXML
	protected void canMove(MouseEvent event) {
		int index = Integer.parseInt(((AnchorPane)event.getSource()).getId().substring(4));
		int x = index % 100, y = index / 100;
		if (board.canMove(x, y))
			((AnchorPane) event.getSource()).setStyle("-fx-background-color: deepskyblue");
		else
			((AnchorPane) event.getSource()).setStyle("-fx-background-color: red");
	}
	@FXML
	protected void canPlace(MouseEvent event) {
		int index = Integer.parseInt(((AnchorPane)event.getSource()).getId().substring(4));
		int x = index % 100, y = index / 100;
		String color = "-fx-background-color: ";
		String id1 = "#cell";
		String id2 = "#cell";
		if (y % 2 == 1) {
			String y_value = ((y < 10) ? "0" + y : y).toString();
			id1 += y_value + ((x + 1 < 10) ? "0" + (x + 1) : x + 1);
			id2 += y_value + ((x + 2 < 10) ? "0" + (x + 2) : x + 2);
		}
		else {
			String x_value = ((x < 10) ? "0" + x : x).toString();
			id1 += ((y + 1 < 10) ? "0" + (y + 1) : y + 1) + x_value;
			id2 += ((y + 2 < 10) ? "0" + (y + 2) : y + 2) + x_value;
		}
		// todo: find the function for walls
//		if ()
//			color += "cornflowerblue";
//		else
			color += "orangered";

		((AnchorPane) event.getSource()).setStyle(color);
		play.getScene().lookup(id1).setStyle(color);
		play.getScene().lookup(id2).setStyle(color);
	}
	@FXML
	protected void baseColor(MouseEvent event) {
		int index = Integer.parseInt(((AnchorPane)event.getSource()).getId().substring(4));
		int x = index % 100, y = index / 100;
		if (x % 2 == 0 && y % 2 == 0)
			((AnchorPane) event.getSource()).setStyle("-fx-background-color: maroon");
		else {
			((AnchorPane) event.getSource()).setStyle("-fx-background-color: chocolate");
			String id1 = "#cell";
			String id2 = "#cell";
			if (y % 2 == 1) {
				String y_value = ((y < 10) ? "0" + y : y).toString();
				id1 += y_value + ((x + 1 < 10) ? "0" + (x + 1) : x + 1);
				id2 += y_value + ((x + 2 < 10) ? "0" + (x + 2) : x + 2);
			}
			else {
				String x_value = ((x < 10) ? "0" + x : x).toString();
				id1 += ((y + 1 < 10) ? "0" + (y + 1) : y + 1) + x_value;
				id2 += ((y + 2 < 10) ? "0" + (y + 2) : y + 2) + x_value;
			}
			play.getScene().lookup(id1).setStyle("-fx-background-color: firebrick");
			play.getScene().lookup(id2).setStyle("-fx-background-color: chocolate");
		}
	}
}
