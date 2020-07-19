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

public class Play extends Application {
    private Stage primaryStage;
    private Scene scene;
    private Board board = new Board();
    private ObservableList<TableSync> table = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        Parent menu = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        this.scene = new Scene(menu, 907, 600);
        this.primaryStage.setTitle("Quoridor Game");
        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.jfif")));
        this.primaryStage.setScene(this.scene);
        this.primaryStage.setWidth(907);
        this.primaryStage.setHeight(600);
        this.primaryStage.show();
    }

    protected Scene getScene() { return this.scene; }
    protected Stage getPrimaryStage() { return this.primaryStage; }

    public static void main(String[] args) {
        launch(args);
    }
}
