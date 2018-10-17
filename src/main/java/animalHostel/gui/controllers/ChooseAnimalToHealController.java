package animalHostel.gui.controllers;

import animalHostel.gui.modelsFx.AnimalFx;
import animalHostel.gui.modelsFx.AnimalModel;
import animalHostel.gui.utils.DialogUtils;
import animalHostel.gui.utils.FXMLUtils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class ChooseAnimalToHealController
{
    @FXML
    private TextField animalIdTextField;

    @FXML
    private TextField animalNameTextField;

    @FXML
    private TableView<AnimalFx> animalTableView;

    @FXML
    private TableColumn<AnimalFx, Number> animalIdColumn;

    @FXML
    private TableColumn<AnimalFx, String> animalNameColumn;

    @FXML
    private TableColumn<AnimalFx, Number> animalSlotColumn;

    private AnimalModel animalModel;

    @FXML
    public void initialize()
    {
        animalModel = new AnimalModel();

        setValidateOnIdAnimalTextFieldAndPutValueInAnimalModel();
        animalModel.nameOfAnimalProperty().bind(animalNameTextField.textProperty());

        animalTableView.setItems(animalModel.getAnimalFxObservableList());
        animalIdColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        animalNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        animalSlotColumn.setCellValueFactory(cellData -> cellData.getValue().slotProperty());
    }

    private void setValidateOnIdAnimalTextFieldAndPutValueInAnimalModel()
    {
        this.animalIdTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue && !animalIdTextField.getText().isEmpty())
            {
                if (!checkWeightTextFieldContentIsCorrect())
                {
                    this.animalIdTextField.setText("");
                    showAlertError();
                }
                else
                {
                    this.animalModel.setIdAnimalProperty(Integer.valueOf(this.animalIdTextField.getText()));
                }
            }

        });
    }

    private boolean checkWeightTextFieldContentIsCorrect()
    {
        return this.animalIdTextField.getText().matches("[0-9]+||\"\"");
    }


    private void showAlertError()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(FXMLUtils.getResourceBundle().getString("error.title"));
        alert.setHeaderText(FXMLUtils.getResourceBundle().getString("error.title"));

        alert.setContentText(FXMLUtils.getResourceBundle().getString("error.haveToBeNumber"));
        alert.showAndWait();
    }

    public void searchOnAction()
    {
        try
        {
            animalModel.findAnimalByIdOrName();
        }
        catch (SQLException e)
        {
            DialogUtils.errorDialog(e.getMessage());
        }
    }
}
