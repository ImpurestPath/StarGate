package ru.ifmo.oop.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PlanetListCell extends ListCell<PlanetGUI> {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label name;
    @FXML
    private Label info;
    @FXML
    private ImageView imageView;
    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(PlanetGUI planet, boolean empty) {
        super.updateItem(planet, empty);
        if(empty || planet == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/planetlistcell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            name.setText(planet.getPlanet().getName());
            info.setText(planet.getPlanet().getBehavior().toString());

            setText(null);
            setGraphic(anchorPane);
        }

    }
}