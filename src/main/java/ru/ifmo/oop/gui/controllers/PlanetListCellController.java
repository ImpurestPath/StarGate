package ru.ifmo.oop.gui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.UncheckedIOException;

public class PlanetListCellController {
    private AnchorPane anchorPane;
    public PlanetListCellController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/planetlistcell.fxml"));
            loader.setController(this);
            anchorPane = loader.load();
        } catch (IOException exc) {
            throw new UncheckedIOException(exc);
        }
    }
}
