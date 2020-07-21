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
		play.getPrimaryStage().setWidth(895);
		play.getPrimaryStage().setHeight(825);
	}
	@FXML
	protected void gotoLoad() throws IOException {
		play.gotoFXML("load.fxml");
		play.getPrimaryStage().setWidth(907);
		play.getPrimaryStage().setHeight(600);
	}
	@FXML
	protected void gotoSettings() throws IOException {
		play.gotoFXML("settings.fxml");
		play.getPrimaryStage().setWidth(907);
		play.getPrimaryStage().setHeight(600);
	}
	@FXML
	protected void gotoMenu() throws IOException {
		play.gotoFXML("mainMenu.fxml");
		play.getPrimaryStage().setWidth(907);
		play.getPrimaryStage().setHeight(600);
	}
}
