package controller.back.courses;

import entity.Course;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.CourseService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    CourseService courseService = new CourseService();

    @FXML
    private Button buttonAdd;

    @FXML
    private ComboBox<String> cbMode;

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

    private Course course;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            if(course != null){
                buttonAdd.setText("Update");
                cbMode.setValue(course.getMode());
                startDate.setValue(course.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                endDate.setValue(course.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                taDescription.setText(course.getDescription());
                tfPrice.setText(String.valueOf(course.getPrice()));
                tfDuration.setText(course.getDuree());
            }
        });
        cbMode.getItems().addAll("Online", "On-site");
    }

    @FXML
    void ajouter(ActionEvent event) throws SQLException {
        Course newCourse = new Course(Integer.parseInt(tfPrice.getText()), cbMode.getValue(), Date.from(startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(endDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), tfDuration.getText(), taDescription.getText());
        if(course == null){
            courseService.add(newCourse);
            openInfoScreen("Added new entry successfully");
        }else{
            newCourse.setId(course.getId());
            courseService.update(newCourse);
            openInfoScreen("Course updated successfully");
        }

    }

    void openInfoScreen(String message){
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../gui/back/courses/InfoScreen.fxml"));
            root = (Parent)fxmlLoader.load();
            InfoScreenController infoScreenController = fxmlLoader.<InfoScreenController>getController();
            infoScreenController.setMessage(message);
            Stage stage = new Stage();
            stage.setTitle("Confirmation");
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
