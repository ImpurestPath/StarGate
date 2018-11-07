package ru.ifmo.oop.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import ru.ifmo.oop.gui.Controller;
import ru.ifmo.oop.gui.PlanetGUI;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    public ListView listView1;
    public Label lblName;
    public Label lblPressure;
    public Label lblTemperature;
    public Label lblBehavior;
    public Label lblArea;
    public Label lblAmount;
    public Button infobutton;
    private ObservableList<PlanetGUI> observableList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.observableList = FXCollections.observableArrayList(Controller.getInstance().getPlanetUIList());
        listView1.setItems(observableList);
        listView1.setCellFactory(new Callback<ListView<PlanetGUI>, ListCell<PlanetGUI>>() {
            @Override
            public ListCell<PlanetGUI> call(ListView<PlanetGUI> param) {
                //тут нужно собрать объект ListCellContact, и вернуть его
                ListCell<PlanetGUI> listCell = new ListCell<PlanetGUI>() {

                    @Override
                    protected void updateItem(PlanetGUI item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            //тут нужно загрузить fxml файл
                            // пробовал способом ниже , но не получается

                            setText(item.toString());

                        }
                    }
                };
                listCell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                    if (event.getButton()== MouseButton.PRIMARY && (! listCell.isEmpty())) {
                        PlanetGUI item = listCell.getItem();
                        System.out.println("clicked "+item.toString());
                        lblName.setText(item.getPlanet().getName());
                        lblAmount.setText(Long.toString(item.getPlanet().getAmountAlive()));
                        lblArea.setText(Long.toString(item.getPlanet().getArea()));
                        lblBehavior.setText(item.getPlanet().getBehavior().toString());
                        lblPressure.setText(Long.toString(item.getPlanet().getPressure()));
                        lblTemperature.setText(Integer.toString(item.getPlanet().getTemperature()));
                    }
                });
                return listCell;
            }
        });

    }

    public void btnInfoClicked(ActionEvent actionEvent) {
    }
}
