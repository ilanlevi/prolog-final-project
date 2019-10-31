package gui;

/**
 * Sample Skeleton for "AboutPane.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

import consts.AboutPageConst;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;


public class AboutPaneController extends Pane {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AboutProjectPane;

    @FXML
    private WebView webView;


    @FXML
    void initialize() {
        try {
            assert AboutProjectPane != null : "fx:id=\"AboutProjectPane\" was not injected: check your FXML file 'AboutPane.fxml'.";
            assert webView != null : "fx:id=\"webView\" was not injected: check your FXML file 'AboutPane.fxml'.";
            getStyleClass().add("browser");
            getStyleClass().add("rtl");
//            double width = AboutProjectPane.getScene().getWidth();
//            double h = AboutProjectPane.getScene().getHeight();
            final WebEngine webEngine = webView.getEngine();

            webEngine.load(URL.class.getResource(AboutPageConst.HTML_ROUTE).toString());
//            getChildren().add(webView);
//            webView.resize(width, h);
//            System.out.println(webEngine.getLocation());
//            System.out.println(webEngine.getDocument());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
