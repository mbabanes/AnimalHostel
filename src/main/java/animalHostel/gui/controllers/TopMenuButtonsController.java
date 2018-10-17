package animalHostel.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.util.Locale;

public class TopMenuButtonsController
{
    @FXML
    private MainController mainController;

    @FXML
    private ToggleGroup TopMenuToggleButtons;


    public void allAnimalsTable( ActionEvent actionEvent)
    {
        setFormOnCenter((Toggle)actionEvent.getSource(), "/fxml/AllAnimals.fxml");
    }

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public void addAnimalTypeFromShowOnAction(ActionEvent actionEvent)
    {
        setFormOnCenter((Toggle)actionEvent.getSource(), "/fxml/AddAnimalType.fxml");
    }

    public void addAnimalFormShowOnAction(ActionEvent actionEvent)
    {
        setFormOnCenter((Toggle)actionEvent.getSource(), "/fxml/AddAnimal.fxml");
    }

    public void addAnimalToHealViewOnAction( ActionEvent actionEvent )
    {
        mainController.goToAddAnimalToHeal();
    }

    private void setFormOnCenter(Toggle source, String fxmlPath)
    {
        if (source == TopMenuToggleButtons.getSelectedToggle())
            mainController.setCenter(fxmlPath);
        else
            TopMenuToggleButtons.selectToggle(source);
    }

    public ToggleGroup getTopMenuToggleButtons()
    {
        return TopMenuToggleButtons;
    }
}
