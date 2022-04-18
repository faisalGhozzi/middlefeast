package controller.back;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button btnArticles;

    @FXML
    private Button btnCourses;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnTutorials;

    @FXML
    private Button btnUsers;

    @FXML
    private AnchorPane displyaArea;

    @FXML
    void close_app(MouseEvent event) {

    }

    @FXML
    void loadArticles(ActionEvent event) {

    }

    @FXML
    void loadCourses(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../../gui/back/courses/Courses.fxml"));
        displyaArea.getChildren().clear();
        displyaArea.getChildren().add(fxml);
    }

    @FXML
    void loadTutorials(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../../gui/back/tutorials/Tutorials.fxml"));
        displyaArea.getChildren().clear();
        displyaArea.getChildren().add(fxml);
    }

    @FXML
    void loadUsers(ActionEvent event) {

    }

    @FXML
    void minimize_app(MouseEvent event) {

    }

    @FXML
    void signout(ActionEvent event) {

    }

}
