package controller.back.courses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddController {

    @FXML
    private Button buttonAdd;

    @FXML
    private ComboBox<?> cbMode;

    @FXML
    private DatePicker endDate;

    @FXML
    private DatePicker startDate;

    @FXML
    private TextArea taDescription;

    @FXML
    private TextField tfDuration;

    @FXML
    private TextField tfPrice;

    @FXML
    void ajouter(ActionEvent event) {

    }

}
