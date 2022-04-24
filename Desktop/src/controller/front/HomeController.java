package controller.front;

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
    private Button btnProfile;

    @FXML
    private AnchorPane displyaArea;

    @FXML
    void close_app(MouseEvent event) {

    }

    @FXML
    void loadArticles(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../../gui/front/articles/Articles.fxml"));
        displyaArea.getChildren().clear();
        displyaArea.getChildren().add(fxml);
    }

    @FXML
    void loadCourses(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../../gui/front/courses/Courses.fxml"));
        displyaArea.getChildren().clear();
        displyaArea.getChildren().add(fxml);
    }

    @FXML
    void loadTutorials(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../../gui/front/tutorials/Tutorials.fxml"));
        displyaArea.getChildren().clear();
        displyaArea.getChildren().add(fxml);
    }

    @FXML
    void loadProfile(ActionEvent event) {

    }

    @FXML
    void minimize_app(MouseEvent event) {

    }

    @FXML
    void signout(ActionEvent event) {

    }

}
