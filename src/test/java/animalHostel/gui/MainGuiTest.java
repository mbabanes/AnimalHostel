package animalHostel.gui;



import animalHostel.gui.controllers.mocks.HostelAndDAOMocks;
import animalHostel.gui.utils.FXMLUtils;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.junit.*;

import org.junit.runner.RunWith;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.api.FxAssert.*;
import static org.testfx.matcher.control.LabeledMatchers.hasText;


@RunWith(PowerMockRunner.class)
@PrepareForTest({HostelSingleton.class})
public class MainGuiTest extends ApplicationTest
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Scene mainWindow = new Scene(FXMLUtils.fxmlLoader("/fxml/MainWindow.fxml"));

        primaryStage.setScene(mainWindow);
        primaryStage.setTitle(FXMLUtils.getResourceBundle().getString("title.application"));

        primaryStage.show();
    }

    @Before
    public void before() throws Exception
    {
        HostelAndDAOMocks hostelAndDAOMocks = new HostelAndDAOMocks();
        ApplicationTest.launch(Main.class, "");
    }



    @Test
    public void whenSceneShowThenHasToggleButtons()
    {
        verifyThat("#addNewAnimalToggleButton", hasText(FXMLUtils.getResourceBundle().getString("topMenu.addAnimal")));

        verifyThat("#addTypeToggleButton", hasText(FXMLUtils.getResourceBundle().getString("topMenu.addType")));

        verifyThat("#animalsToggleButton", hasText(FXMLUtils.getResourceBundle().getString("topMenu.animals")));

        verifyThat("#addAnimalToHealToggleButton", hasText(FXMLUtils.getResourceBundle().getString("animalToHeal.AddAnimalToHeal")));
    }
}