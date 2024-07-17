package com.example.examen;
import RepositoryTxt.SongRepoTxt;
import configReader.ConfigurationReader;
import entity.Song;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repositorySQL.DatabaseConn;
import repositorySQL.SongRepoSQL;
import service.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class HelloApp extends Application {
    static Service<Song> SongService;
    static SongRepoTxt songrepotext;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        VBox secondVerticalBox = new VBox();
        ListView<Song> SongListView = new ListView<>();
        ObservableList<Song> songs = FXCollections.observableArrayList(SongService.getAll());
        SongListView.setItems(songs);
        secondVerticalBox.getChildren().add(SongListView);

        GridPane SongGridPane = new GridPane();
        Label idLabel = new Label("id");
        TextField idTextField = new TextField();
        Label formatieLabel = new Label("formatie");
        TextField formatieTextField = new TextField();
        Label titluLabel = new Label("titlu");
        TextField titluTextField = new TextField();
        Label genLabel = new Label("gen muzical");
        TextField genTextField = new TextField();
        Label durataLabel = new Label("durata");
        TextField durataTextField = new TextField();

        SongGridPane.add(idLabel, 0, 0);
        SongGridPane.add(idTextField, 1, 0);
        SongGridPane.add(formatieLabel, 0, 1);
        SongGridPane.add(formatieTextField, 1, 1);
        SongGridPane.add(titluLabel, 0, 2);
        SongGridPane.add(titluTextField, 1, 2);
        SongGridPane.add(genLabel, 0, 3);
        SongGridPane.add(genTextField, 1, 3);
        SongGridPane.add(durataLabel, 0, 4);
        SongGridPane.add(durataTextField, 1, 4);
        secondVerticalBox.getChildren().add(SongGridPane);
        HBox SongActionsHorizontalBox = new HBox();
        Button addSongButton = new Button("adaugare");
        SongActionsHorizontalBox.getChildren().add(addSongButton);

        secondVerticalBox.getChildren().add(SongActionsHorizontalBox);

        addSongButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (idTextField.getText().isBlank() || formatieTextField.getText().isBlank() || titluTextField.getText().isBlank() || genTextField.getText().isBlank() || durataTextField.getText().isBlank()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText("Va rugam sa completati toate campurile!");
                    alert.showAndWait();
                    return;
                }

                try {
                    Integer minutes = Integer.parseInt(durataTextField.getText().split(":")[0]);
                    if (minutes < 1 || minutes > 10) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Eroare");
                        alert.setContentText("Durata trebuie sa fie intre 1 si 10 minute.");
                        alert.showAndWait();
                        return;
                    }
                    String id = idTextField.getText();
                    String band = formatieTextField.getText();
                    String title = titluTextField.getText();
                    String genre = genTextField.getText();
                    String duration = durataTextField.getText();
                    Song song = new Song(id,band,title,genre, duration);
                    SongService.add(song);
                    songs.setAll(SongService.getAll());
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }});


        secondVerticalBox.setPadding(new Insets(10, 10, 10, 10));


        VBox vBox = new VBox(secondVerticalBox);
        Scene tabs = new Scene(vBox);

        stage.setTitle("HEYOOO!");
        stage.setScene(tabs);
        stage.show();
    }
    public static void main(String[] args) throws IOException, SQLException {
        ConfigurationReader configReader = new ConfigurationReader();
        Map<String,String> config = configReader.config();

            DatabaseConn connector = new DatabaseConn();
            connector.connect();
            SongRepoSQL repoSong = new SongRepoSQL(connector.getConn());
            SongService = new Service<Song>(repoSong);
            songrepotext = new SongRepoTxt(config.get("Song"), SongService);
            launch();
    }
}