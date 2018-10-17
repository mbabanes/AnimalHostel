package animalHostel.gui;

import animalHostel.gui.utils.DialogUtils;
import animalHostel.gui.utils.FXMLUtils;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.sql.SQLException;
import java.util.Locale;

public class Main extends Application
{

    public static final String MAIN_WINDOW_FXML = "/fxml/MainWindow.fxml";
    private static Stage primaryStage;

    public static void main(String args[])
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception
    {
//        Locale.setDefault(new Locale("en"));

        Scene mainWindow = new Scene(FXMLUtils.fxmlLoader(MAIN_WINDOW_FXML));
        this.primaryStage = primaryStage;

        primaryStage.setScene(mainWindow);
        primaryStage.setTitle(FXMLUtils.getResourceBundle().getString("title.application"));

        primaryStage.show();

        try
        {
            HostelSingleton.getHostel();
        }catch (SQLException e)
        {
            DialogUtils.errorDialog(e.getMessage());
            Platform.exit();
            System.exit(0);
        }
    }

    @Override
    public void stop()
    {
        try
        {
            HostelSingleton.closeConnection();
        }
        catch (SQLException e)
        {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public static Stage getPrimaryStage()
    {
        return primaryStage;
    }
}
