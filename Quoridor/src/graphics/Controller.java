package graphics;

import game.Board;
import game.Player;
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
import javafx.scene.paint.Paint;
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
	@FXML protected AnchorPane game_pane;
	@FXML protected Label player1info;
	@FXML protected Label player2info;
	@FXML protected AnchorPane table;
	@FXML protected AnchorPane bead1;
	@FXML protected AnchorPane bead2;
	// traverses through scenes (actually changing major nodes)
	@FXML
	protected void gotoGame() throws IOException { play.gotoFXML("game.fxml"); initializeGame(); }
	@FXML
	protected void gotoLoad() throws IOException { play.gotoFXML("load.fxml"); }
	@FXML
	protected void gotoMenu() throws IOException { play.gotoFXML("mainMenu.fxml"); }
	@FXML
	protected void Exit() { play.getPrimaryStage().close(); }

	protected void initializeGame() {
		// creating the board
		table = (AnchorPane)(play.getScene().lookup("#table"));
		game_pane = (AnchorPane)(play.getScene().lookup("#game_pane"));
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
				if (((j % 2 == 0 && i % 2 == 1) || (j % 2 == 1 && i % 2 == 0)) && i != 16 && j != 16) {
					cell.setOnMouseEntered(this::canPlace);
					cell.setOnMouseExited(this::baseColor);
				}
				else if (cell.getStyle().equals("-fx-background-color: maroon")) {
					cell.setOnMouseEntered(this::canMove);
					cell.setOnMouseExited(this::baseColor);
				}

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
		// initialize the labels
		{
			// initialize player1's label
			player1info = new Label(board.getPlayer1().getName() + '\t' + "Remaining Walls: " + board.getPlayer1().getWalls());
			player1info.setId("player1info");
			player1info.setTextFill(Color.ROYALBLUE);
			player1info.setLayoutY(0);
			player1info.setPrefSize(520, 45);
			// initialize player2's label
			player2info = new Label(board.getPlayer2().getName() + '\t' + "Remaining Walls: " + board.getPlayer2().getWalls());
			player2info.setId("player2info");
			player2info.setTextFill(Color.LIMEGREEN);
			player2info.setLayoutY(45);
			player2info.setPrefSize(520, 45);
			// add to scene
			game_pane.getChildren().addAll(player1info, player2info);
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
			AnchorPane bead = (board.getTurn().getId() == 'U') ? bead1 : bead2;

			bead.setLayoutX(clicked.getLayoutX());
			bead.setLayoutY(clicked.getLayoutY());
		} catch (InputMismatchException exception) {
			// get label's current status
			Label player_info = (board.getTurn().getId() == 'U') ? player1info : player2info;
			String info = player_info.getText();
			Paint textFill = player_info.getTextFill();
			// change it
			player_info.setFont(new Font(player_info.getFont().getStyle(), 20));
			player_info.setTextFill(Color.RED);
			player_info.setText(exception.getMessage().toUpperCase());
			// wait and return to previous status
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			player_info.setFont(new Font(player_info.getFont().getStyle(), 16));
			player_info.setTextFill(textFill);
			player_info.setText(info);
		}
		win();
	}
	@FXML
	protected void canMove(MouseEvent event) {
		int index = Integer.parseInt(((AnchorPane)event.getSource()).getId().substring(4));
		int x = index % 100, y = index / 100;
		if (board.canMove(x, y))
			if (board.getTurn().getId() == 'D')
				((AnchorPane) event.getSource()).setStyle("-fx-background-color: deepskyblue");
			else
				((AnchorPane) event.getSource()).setStyle("-fx-background-color: lawngreen");
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
		// todo: merge the function
//		if (allowedWall(board.getPlayer1(), board.getPlayer2(), x, y))
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

	private void win() {
		Player winner = board.win();
		if (winner == null) {
			return;
		}

		Label winner_info;
		Label loser_info;
		if (winner.getId() == 'U') {
			winner_info = player1info;
			loser_info = player2info;
		}
		else {
			winner_info = player2info;
			loser_info = player1info;
		}

		winner_info.setFont(new Font(winner_info.getFont().getStyle(), 20));
		winner_info.setText("You Won!!");
		loser_info.setFont(new Font(loser_info.getFont().getStyle(), 20));
		loser_info.setText("You Lost :)");

		for (int i = 0; i < 17; ++i)
			for (int j = 0; j < 17; j++) {
				String id = "#cell" + ((i < 10) ? "0" + i : i) + ((j < 10) ? "0" + j : j);
				AnchorPane cell = (AnchorPane) (play.getScene().lookup(id));
				// remove functionality
				cell.setOnMouseClicked(null);
				cell.setOnMouseEntered(null);
				cell.setOnMouseExited(null);
			}
	}
}
