package controller.back.courses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CoursesController {
    @FXML
    private Button addCourseBtn;

    @FXML
    private AnchorPane productDisplayArea;

    @FXML
    private Button viewCoursesBtn;

    @FXML
    void loadAddCourse(ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../gui/back/courses/Add.fxml"));
        root = (Parent) fxmlLoader.load();
        AddController addController = fxmlLoader.<AddController>getController();
        addController.setCourse(null);
        productDisplayArea.getChildren().removeAll();
        productDisplayArea.getChildren().add(root);
    }

    @FXML
    void loadViewCourses(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../../../gui/back/courses/ShowAll.fxml"));
        productDisplayArea.getChildren().removeAll();
        productDisplayArea.getChildren().add(fxml);
    }
}
