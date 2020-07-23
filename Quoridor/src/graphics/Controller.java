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
	@FXML
	protected Label info = new Label();
	@FXML
	protected AnchorPane table;
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
	protected void initializeGame() throws IOException {
		table = (AnchorPane)(play.getScene().lookup("#table"));
		final double NARROW = table.getPrefWidth() / 44, THICK = NARROW * 4;

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
				else
					cell.setPrefHeight(NARROW);
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
				cell.setOnMouseClicked(mouseEvent -> { BeadMover(mouseEvent); });
				table.getChildren().add(cell);
			}
			if (i % 2 == 0)
				y += THICK;
			else
				y += NARROW;
		}
	}
	@FXML
	protected void BeadMover(MouseEvent event) {
		int index = Integer.parseInt(((Button)event.getSource()).getId().substring(4));
		int x = index % 100, y = index / 100;
		try {
			this.board.move(x, y);
		} catch (InputMismatchException exception) {
			String info = this.info.getText();
			this.info.setFont(new Font(this.info.getFont().getStyle(), 30));
			this.info.setText(exception.getMessage().toUpperCase());
			// todo: after 5 seconds
			this.info.setText(info);
			this.info.setFont(new Font(this.info.getFont().getStyle(), 16));
		}
	}

	public void changeColor(int[][] map) {
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[i].length; ++j) {

			}
		}
	}
}
