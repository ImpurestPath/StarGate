package ru.ifmo.oop.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ru.ifmo.oop.db.DTO.RaceDB.Behavior;
import ru.ifmo.oop.domain.Country;

import java.io.IOException;

public class CountryListCell extends ListCell<Country> {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label lblName;
    @FXML
    private Label lblInfo;
    @FXML
    private ImageView imageView;
    private FXMLLoader fxmlLoader;
    private Image imgNeutral;
    private Image imgAngry;

    @Override
    protected void updateItem(Country country, boolean empty) {
        super.updateItem(country, empty);
        if(empty || country == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/countrylistcell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            lblName.setText(country.getName());
            lblInfo.setText(String.format("Amount: %d, area: %d",country.getAmountAlive(),country.getArea()));
            if (country.getBehavior() == Behavior.ANGRY){
                if (imgAngry == null){
                    imgAngry = new Image("/images/angry.png");
                }
                imageView.setImage(imgAngry);
            }
            else if (country.getBehavior() == Behavior.NEUTRAL){
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
