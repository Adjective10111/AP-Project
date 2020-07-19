package graphics;

import game.Board;
import game.TableSync;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Play extends Application {
    private Stage primaryStage;
    private Scene scene;
    private Board board = new Board();
    private ObservableList<TableSync> table = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        start();
    }

    public void start() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent menu = loader.load();
        scene = new Scene(menu, 907, 600);
        primaryStage.setTitle("Quoridor Game");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.jfif")));
        primaryStage.setScene(this.scene);
        primaryStage.show();
        MainMenuController menuController = loader.getController();
        menuController.setPlay(this);
    }

    protected Scene getScene() { return this.scene; }
    protected Stage getPrimaryStage() { return this.primaryStage; }

    public static void main(String[] args) {
        launch(args);
    }
}
