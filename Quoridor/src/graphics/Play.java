package graphics;

import game.Board;
import game.TableSync;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Play extends Application {
    private Stage primaryStage;
    private Scene scene;
    private Board board = new Board();
    private ObservableList<TableSync> table = FXCollections.observableArrayList();
    Observable observable = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        start();
    }

    public void start() throws IOException {
        scene = new Scene(new AnchorPane(), 520, 600);
        gotoFXML("mainMenu.fxml");
        primaryStage.setTitle("Quoridor Game");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.jfif")));
        primaryStage.setScene(this.scene);
        primaryStage.show();
    }

    protected void gotoFXML(String URL) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(URL));
        Parent parent = loader.load();
        this.getScene().setRoot(parent);
        Controller controller = loader.getController();
        controller.setPlay(this);
        controller.setBoard(this.board);
    }

    protected Scene getScene() { return this.scene; }
    protected Stage getPrimaryStage() { return this.primaryStage; }

    public static void main(String[] args) {
        launch(args);
    }
}
