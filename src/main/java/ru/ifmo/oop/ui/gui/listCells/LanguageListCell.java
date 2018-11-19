package ru.ifmo.oop.ui.gui.listCells;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ru.ifmo.oop.dataAccess.DTO.LanguageDTO;
import ru.ifmo.oop.domain.Language;

import java.io.IOException;

public class LanguageListCell extends ListCell<Language> {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label lblName;
    @FXML
    private ImageView imageViewType;
    @FXML
    private ImageView imageViewDictionary;
    private FXMLLoader fxmlLoader;
    private Image imgVoice;
    private Image imgVisual;
    private Image imgVibration;
    private Image imgAvailableDictionary;
    private Image imgUnvailableDictionary;

    @Override
    protected void updateItem(Language language, boolean empty) {
        super.updateItem(language, empty);
        if(empty || language == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/languagelistcell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            lblName.setText(language.getName());
            if (language.getType() == LanguageDTO.Type.VOICE){
                if (imgVoice == null){
                    imgVoice = new Image("/images/voice.png");
                }
                imageViewType.setImage(imgVoice);
            }
            else if (language.getType() == LanguageDTO.Type.VISUAL){
                if (imgVisual == null){
                    imgVisual = new Image("/images/visual.png");
                }
                imageViewType.setImage(imgVisual);
            }
            else {
                if (imgVibration == null){
                    imgVibration = new Image("/images/vibration.png");
                }
                imageViewType.setImage(imgVibration);
            }
            if (language.isAvailableDictionary()){
                if (imgAvailableDictionary == null){
                    imgAvailableDictionary = new Image("/images/dictionary.png");
                }
                imageViewDictionary.setImage(imgAvailableDictionary);
            }
            else{
                if (imgUnvailableDictionary == null){
                    imgUnvailableDictionary = new Image("/images/nodictionary.png");
                }
                imageViewDictionary.setImage(imgUnvailableDictionary);
            }
            setText(null);
            setGraphic(anchorPane);
        }

    }
}
