package ru.ifmo.oop.ui.gui.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.ifmo.oop.MainGUI;
import ru.ifmo.oop.domain.mappers.TransformerToEntity;
import ru.ifmo.oop.ui.gui.UIPlanetManager;
import ru.ifmo.oop.ui.gui.PlanetGUI;
import ru.ifmo.oop.ui.gui.UIUserManager;
import ru.ifmo.oop.ui.gui.listCells.PlanetListCell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    public ListView<PlanetGUI> listView1;
    public Label lblName;
    public Label lblPressure;
    public Label lblTemperature;
    public Label lblBehavior;
    public Label lblArea;
    public Label lblAmount;
    public Button infobutton;
    public AnchorPane mainPane;
    public ImageView imageView;
    public Label lblPlanetManagment;
    public ToolBar planetManagmentPanel;
    public Label lblUserManagment;
    public ToolBar userManagmentPanel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<PlanetGUI> observableList = FXCollections.observableArrayList(UIPlanetManager.getInstance().getPlanetUIList());
        listView1.setItems(observableList);
        listView1.setCellFactory(param -> {
                    PlanetListCell listCell = new PlanetListCell();
                    listCell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                        if (event.getButton() == MouseButton.PRIMARY && (!listCell.isEmpty())) {
                            PlanetGUI item = listCell.getItem();
                            System.out.println("clicked " + item.toString());
                            imageView.setImage(item.getImage());
                            lblName.setText(item.getName());
                            lblAmount.setText(Long.toString(item.getAmountAlive()));
                            lblArea.setText(Long.toString(item.getArea()));
                            lblBehavior.setText(item.getBehavior().toString());
                            lblPressure.setText(Long.toString(item.getPressure()));
                            lblTemperature.setText(Integer.toString(item.getTemperature()));
                        }
                    });
                    return listCell;
                }
        );
    }
    public void updateMode(){
        //TODO add other panels
        if (UIUserManager.getInstance().getMode() == UIUserManager.UserMode.USER) planetManagmentPanel.setVisible(false);
        else planetManagmentPanel.setVisible(true);
    }

    public void btnInfoClicked(ActionEvent actionEvent) {
        PlanetGUI item = listView1.getSelectionModel().getSelectedItem();
        if (item == null) return;
        Stage info = new Stage();
        info.initOwner(mainPane.getScene().getWindow());
        info.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/planetinfo.fxml"));
            Parent parent = loader.load();
            info.setScene(new Scene(parent));
            PlanetInfoController planetInfoController = loader.getController();
            planetInfoController.setMode(PlanetInfoController.Mode.VIEW);
            planetInfoController.setPlanet(item);
            info.showAndWait();
            this.listView1.setItems(FXCollections.observableArrayList(UIPlanetManager.getInstance().getPlanetUIList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnOpenGateClicked(ActionEvent actionEvent) {
        //TODO add warning
        PlanetGUI item = listView1.getSelectionModel().getSelectedItem();
        if (item == null) return;
        Stage stage = new Stage();
        stage.initOwner(mainPane.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gate.fxml"));
            Parent parent = loader.load();
            stage.setScene(new Scene(parent));
            GateController gateController = loader.getController();
            gateController.setPlanetName(item.getName());
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnAddPlanetClicked(ActionEvent actionEvent) {
        Stage info = new Stage();
        info.initOwner(mainPane.getScene().getWindow());
        info.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/planetpage.fxml"));
            Parent parent = loader.load();
            info.setScene(new Scene(parent));
            PlanetPageController planetPageController = loader.getController();
            planetPageController.setMode(PlanetPageController.Mode.CREATE);
            info.showAndWait();
            this.listView1.setItems(FXCollections.observableArrayList(UIPlanetManager.getInstance().getPlanetUIList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnEditPlanetClicked(ActionEvent actionEvent) {
        PlanetGUI item = listView1.getSelectionModel().getSelectedItem();
        if (item == null) return;
        Stage info = new Stage();
        info.initOwner(mainPane.getScene().getWindow());
        info.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/planetpage.fxml"));
            Parent parent = loader.load();
            info.setScene(new Scene(parent));
            PlanetPageController planetPageController = loader.getController();
            planetPageController.setMode(PlanetPageController.Mode.UPDATE);
            planetPageController.setPlanet(item);
            info.showAndWait();
            this.listView1.setItems(FXCollections.observableArrayList(UIPlanetManager.getInstance().getPlanetUIList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnDeletePlanetClicked(ActionEvent actionEvent) {
        //TODO add warning
        try {
            PlanetGUI item = listView1.getSelectionModel().getSelectedItem();
            if (item == null) return;
            UIPlanetManager.getInstance().deletePlanet(TransformerToEntity.toPlanet(item));
            this.listView1.setItems(FXCollections.observableArrayList(UIPlanetManager.getInstance().getPlanetUIList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnEditInfoPlanetClicked(ActionEvent actionEvent) {
        PlanetGUI item = listView1.getSelectionModel().getSelectedItem();
        if (item == null) return;
        Stage info = new Stage();
        info.initOwner(mainPane.getScene().getWindow());
        info.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/planetinfo.fxml"));
            Parent parent = loader.load();
            info.setScene(new Scene(parent));
            PlanetInfoController planetInfoController = loader.getController();
            planetInfoController.setMode(PlanetInfoController.Mode.CHANGE);
            planetInfoController.setPlanet(item);
            info.showAndWait();
            this.listView1.setItems(FXCollections.observableArrayList(UIPlanetManager.getInstance().getPlanetUIList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnExitClicked(ActionEvent actionEvent) {
        Platform.runLater(MainGUI::loadNext);
    }

    public void btnAddUserClicked(ActionEvent actionEvent) {
        Stage info = new Stage();
        info.initOwner(mainPane.getScene().getWindow());
        info.initModality(Modality.APPLICATION_MODAL);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userpage.fxml"));
            Parent parent = loader.load();
            info.setScene(new Scene(parent));
            UserPageController userPageController = loader.getController();
            userPageController.setMode(UserPageController.Mode.CREATE);
            info.showAndWait();
            this.listView1.setItems(FXCollections.observableArrayList(UIPlanetManager.getInstance().getPlanetUIList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnEditUserClicked(ActionEvent actionEvent) {

    }

    public void btnDeleteUserClicked(ActionEvent actionEvent) {
    }

    public void btnFindUserClicked(ActionEvent actionEvent) {
    }
}
