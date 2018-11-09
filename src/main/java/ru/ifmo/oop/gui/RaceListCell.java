package ru.ifmo.oop.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ru.ifmo.oop.db.DTO.RaceDB;
import ru.ifmo.oop.domain.Race;

import java.io.IOException;

public class RaceListCell extends ListCell<Race> {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label lblName;
    @FXML
    private Label lblInfo;
    @FXML
    private ImageView imageView;
    private FXMLLoader fxmlLoader;
    private Image imgAngry;
    private Image imgNeutral;

    @Override
    protected void updateItem(Race race, boolean empty) {
        super.updateItem(race, empty);
        if(empty || race == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/racelistcell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            lblName.setText(race.getName());
            lblInfo.setText(String.format("Amount: %d",race.getAmount()));
            if (race.getBehavior() == RaceDB.Behavior.ANGRY){
                if (imgAngry == null){
                    imgAngry = new Image("/images/angry.png");
                }
                imageView.setImage(imgAngry);
            }
            else if (race.getBehavior() == RaceDB.Behavior.NEUTRAL){
                if (imgNeutral == null){
                    imgNeutral = new Image("/images/neutral.png");
                }
                imageView.setImage(imgNeutral);
            }

            setText(null);
            setGraphic(anchorPane);
        }

    }
}
