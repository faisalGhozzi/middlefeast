package controller.back.courses;

import entity.Course;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class FindByIdController implements Initializable {

    @FXML
    private Label lblDateDebut;

    @FXML
    private Label lblDateFin;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblDuration;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblMode;

    private Course course;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            lblDescription.setText(course.getDescription());
            lblDateDebut.setText(course.getDateDebut().toString());
            lblDateFin.setText(course.getDateFin().toString());
            lblPrice.setText(String.valueOf(course.getPrice())+ " TND");
            lblDuration.setText(Integer.parseInt(course.getDuree()) < 2 ? course.getDuree() + " Day" : course.getDuree() + " Days");
            lblMode.setText(course.getMode());
        });
    }

    @FXML
    void deleteCourse(ActionEvent event) {

    }

    @FXML
    void updateCourse(ActionEvent event) {

    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
