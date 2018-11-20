package ru.ifmo.oop.ui.gui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.ifmo.oop.dataAccess.DTO.LanguageDTO;
import ru.ifmo.oop.domain.Language;
import ru.ifmo.oop.ui.gui.UIPlanetManager;

import java.net.URL;
import java.util.ResourceBundle;

public class LanguagePageController implements Initializable {
    public ComboBox chooseType;
    public TextField textName;
    public CheckBox radioDictionary;
    enum Mode{
        UPDATE,
        CREATE
    };
    Mode mode;
    Language language;
    int idPlanet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chooseType.setCellFactory(param -> new ListCell<LanguageDTO.Type>(){
            @Override
            protected void updateItem(LanguageDTO.Type type, boolean empty){
                super.updateItem(type, empty);
                if (type == null || empty) {
                    setText(null);
                } else {
                    setText(type.toString());
                }
            }
        });
        chooseType.setItems(FXCollections.observableArrayList());
        chooseType.getItems().add(LanguageDTO.Type.VISUAL);
        chooseType.getItems().add(LanguageDTO.Type.VIBRATION);
        chooseType.getItems().add(LanguageDTO.Type.VOICE);
    }
    public void setLanguage(Language language){
        this.language = language;
        chooseType.getSelectionModel().select(language.getType() == LanguageDTO.Type.VISUAL ? LanguageDTO.Type.VISUAL : language.getType() == LanguageDTO.Type.VIBRATION ? LanguageDTO.Type.VIBRATION: LanguageDTO.Type.VOICE);
        textName.setText(language.getName());
        radioDictionary.setSelected(language.isAvailableDictionary());
    }
    public void setIdPlanet(int idPlanet){
        this.idPlanet = idPlanet;
    }
    public void setMode(Mode mode){
        this.mode = mode;
    }

    public void btnCancelClicked(ActionEvent actionEvent) {
        ((Stage)textName.getScene().getWindow()).close();
    }

    public void btnOkClicked(ActionEvent actionEvent) {
        try {

            if (mode == Mode.UPDATE) {
                UIPlanetManager.getInstance().changeLanguageOfPlanet(language.getIdPlanet(), language.getId(), new Language(language.getId(), textName.getText(), (LanguageDTO.Type) chooseType.getSelectionModel().getSelectedItem(), radioDictionary.isSelected(), language.getIdPlanet()));
            } else {
                UIPlanetManager.getInstance().addLanguageToPlanet(idPlanet, new Language(textName.getText(), (LanguageDTO.Type) chooseType.getSelectionModel().getSelectedItem(), radioDictionary.isSelected(), idPlanet));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        ((Stage) textName.getScene().getWindow()).close();

    }
}
