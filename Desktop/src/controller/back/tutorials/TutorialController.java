package controller.back.tutorials;

import controller.back.courses.AddCourseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TutorialController {
    @FXML
    private Button addCourseBtn;

    @FXML
    private AnchorPane productDisplayArea;

    @FXML
    private Button viewCoursesBtn;

    @FXML
    void loadAddCourse(ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../gui/back/tutorials/AddTutorial.fxml"));
        root = (Parent) fxmlLoader.load();
        AddCourseController addCourseController = fxmlLoader.<AddCourseController>getController();
        addCourseController.setCourse(null);
        productDisplayArea.getChildren().removeAll();
        productDisplayArea.getChildren().add(root);
    }

    @FXML
    void loadViewCourses(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../../../gui/back/tutorials/ShowAllTutorials.fxml"));
        productDisplayArea.getChildren().removeAll();
        productDisplayArea.getChildren().add(fxml);
    }
}
