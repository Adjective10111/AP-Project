package graphics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Play extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent menu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
//        Parent game = FXMLLoader.load(getClass().getResource("game.fxml"));
        Scene MainMenu = new Scene(menu, 907, 600);
//        Scene Game = new Scene(game, 900, 600);
        primaryStage.setTitle("Quoridor Game");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.jfif")));
        primaryStage.setScene(MainMenu);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
