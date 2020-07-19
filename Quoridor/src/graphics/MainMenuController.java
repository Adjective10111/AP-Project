package graphics;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController {
	@FXML
	private Button NewGame = new Button();
	@FXML
	private Button LoadGame = new Button();
	@FXML
	private Button Settings = new Button();

	private Play play;

	public void setPlay(Play play) {
		this.play = play;
	}

	@FXML
	protected void gotoGame() throws IOException {
		Parent game = FXMLLoader.load(getClass().getResource("game.fxml"));
		this.play.getScene().setRoot(game);
	}
	@FXML
	protected void gotoLoad() throws IOException {
		Parent load = FXMLLoader.load(getClass().getResource("load.fxml"));
		this.play.getScene().setRoot(load);
	}
	@FXML
	protected void gotoSettings() throws IOException {
		Parent settings = FXMLLoader.load(getClass().getResource("settings.fxml"));
		this.play.getScene().setRoot(settings);
	}
}
