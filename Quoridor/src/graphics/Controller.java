package graphics;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
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
}
