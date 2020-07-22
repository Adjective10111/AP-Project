package graphics;

import javafx.fxml.FXML;

import java.io.IOException;

public class Controller {
	private Play play;
	public void setPlay(Play play) {
		this.play = play;
	}

	@FXML
	protected void gotoGame() throws IOException {
		play.gotoFXML("game.fxml");
	}
	@FXML
	protected void gotoLoad() throws IOException {
		play.gotoFXML("load.fxml");
	}
	@FXML
	protected void gotoMenu() throws IOException {
		play.gotoFXML("mainMenu.fxml");
	}
	@FXML
	protected void Exit() {
		play.getPrimaryStage().close();
	}
}
