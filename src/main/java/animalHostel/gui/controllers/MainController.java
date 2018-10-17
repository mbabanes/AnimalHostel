package animalHostel.gui.controllers;

import animalHostel.gui.modelsFx.AnimalFx;
import animalHostel.gui.utils.DialogUtils;
import animalHostel.gui.utils.FXMLUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;

public class MainController
{
    public static final String ADD_ANIMAL_TO_HEAL_FXML = "/fxml/addAnimalToHeal.fxml";
    @FXML
    BorderPane borderPane;

    @FXML
    private TopMenuButtonsController topMenuButtonsController;

    private static MainController MAIN_CONTROLLER;

    public static MainController getMainController()
    {
        return MAIN_CONTROLLER;
    }

    @FXML
    public void initialize()
    {
        MAIN_CONTROLLER = this;
        topMenuButtonsController.setMainController(this);
    }



    public void setCenter(String fxmlPath)
    {
        ScrollPane scp = (ScrollPane)borderPane.getCenter();
        scp.setContent(FXMLUtils.fxmlLoader(fxmlPath));
    }


    public void goToAddAnimalToHeal()
    {
        loadAnimalToHealForm(new AddAnimalToHealController());
    }



    public void goToAddAnimalToHeal(AnimalFx animalFx)
    {
        loadAnimalToHealForm(new AddAnimalToHealController(animalFx));
    }


    private void loadAnimalToHealForm(AddAnimalToHealController controller)
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(ADD_ANIMAL_TO_HEAL_FXML));
        loader.setResources(FXMLUtils.getResourceBundle());
        loader.setController(controller);
        Pane pane = null;
        try
        {
            pane = (Pane) loader.load();
            ScrollPane scp = (ScrollPane) borderPane.getCenter();
            scp.setContent(pane);


            ObservableList<Toggle> toggles = topMenuButtonsController.getTopMenuToggleButtons().getToggles();
            for (Toggle toggle : toggles)
            {
                ToggleButton tglB = (ToggleButton) toggle;
                if (tglB.getId().equals("addAnimalToHealToggleButton"))
                {
                    topMenuButtonsController.getTopMenuToggleButtons().selectToggle(toggle);
                    break;
                }
            }
        }
        catch (IOException e)
        {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

}
