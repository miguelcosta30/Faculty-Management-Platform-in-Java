package projetoF2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainPointRender extends Application {
    private static final double floorSize = 65.0;
    private BuildingMap<Node> controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("university_management.fxml"));
        BuildingMap<Node> buildingMap = new BuildingMap<>();
        loader.setController(new GenericController(buildingMap));



        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("University Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
