package graphics;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class LoadController {
	@FXML
	private Button Back = new Button();

	private Play play;

	public void setPlay(Play play) {
		this.play = play;
	}

	@FXML
	protected void gotoMenu() throws IOException {
		Parent menu = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
		this.play.getScene().setRoot(menu);
	}
}
