package animalHostel.gui.controllers.utils;

import animalHostel.gui.utils.FXMLUtils;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.TimeoutException;

public class StageForTest extends ApplicationTest
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Scene mainWindow = new Scene(FXMLUtils.fxmlLoader("/fxml/MainWindow.fxml"));

        primaryStage.setScene(mainWindow);
        primaryStage.setTitle(FXMLUtils.getResourceBundle().getString("title.application"));

        primaryStage.show();
    }

    @Override
    public void stop() throws TimeoutException
    {
        FxToolkit.cleanupStages();
        FxToolkit.hideStage();
    }
}
