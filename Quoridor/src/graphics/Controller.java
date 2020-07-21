package graphics;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Controller {
	private Play play;
	public void setPlay(Play play) {
		this.play = play;
	}

	@FXML
	private Button NewGame = new Button();
	@FXML
	private Button LoadGame = new Button();
	@FXML
	private Button Settings = new Button();
	@FXML
	protected void gotoGame() throws IOException {
		play.gotoFXML("game.fxml");
		play.getPrimaryStage().setWidth(895);
		play.getPrimaryStage().setHeight(825);
	}
	@FXML
	protected void gotoLoad() throws IOException {
		play.gotoFXML("load.fxml");
	}
	@FXML
	protected void gotoSettings() throws IOException {
		play.gotoFXML("settings.fxml");
	}

	@FXML
	private Button loadBack = new Button();
	@FXML
	protected void gotoMenu() throws IOException {
		play.gotoFXML("mainMenu.fxml");
	}
}
