package animalHostel.gui.controllers;

import animalHostel.gui.modelsFx.AnimalTypeFxModel;
import animalHostel.gui.utils.DialogUtils;
import animalHostel.gui.utils.FXMLUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddAnimalTypeController
{
    @FXML
    private TextField typeTextField;

    @FXML
    private TextField raceTextField;

    @FXML
    private Button addTypeButton;

    private AnimalTypeFxModel animalTypeFxModel;


    @FXML
    public void initialize()
    {
        this.animalTypeFxModel = new AnimalTypeFxModel();

        this.addTypeButton.disableProperty().bind(this.typeTextField.textProperty().isEmpty().or(this.raceTextField.textProperty().isEmpty()));

        bindingTextFieldsWithAnimalTypeModel();
    }


    @FXML
    public void addAnimalTypeOnAction()
    {
        try
        {
            this.animalTypeFxModel.addAnimalType();
            DialogUtils.infoDialog(FXMLUtils.getResourceBundle().getString("addAnimal.confirming"));
            clearTextFields();
        }
        catch (SQLException e)
        {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    private void bindingTextFieldsWithAnimalTypeModel()
    {
        this.animalTypeFxModel.getAnimalTypeFxObjectProperty().typeProperty().bind(this.typeTextField.textProperty());
        this.animalTypeFxModel.getAnimalTypeFxObjectProperty().raceProperty().bind(this.raceTextField.textProperty());
    }

    private void clearTextFields()
    {
        this.typeTextField.clear();
        this.raceTextField.clear();
    }
}
