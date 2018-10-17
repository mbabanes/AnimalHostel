package animalHostel.gui.controllers;

import animalHostel.gui.modelsFx.AnimalTypeFx;
import animalHostel.gui.modelsFx.AnimalTypeFxModel;
import animalHostel.gui.utils.DialogUtils;
import animalHostel.gui.utils.FXMLUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class FindOrCreateAnimalTypeController
{
    @FXML
    private TextField typeTextField;

    @FXML
    private TextField raceTextField;

    @FXML
    private Button searchButton;

    @FXML
    private Button addAnimalTypeButton;

    @FXML
    private TableView<AnimalTypeFx> animalTypesTable;

    @FXML
    private TableColumn<AnimalTypeFx, Number> idCollumn;

    @FXML
    private TableColumn<AnimalTypeFx, String> typeCollumn;

    @FXML
    private TableColumn<AnimalTypeFx, String> raceCollumn;

    private AnimalTypeFxModel animalTypeFxModel;

    @FXML
    public void initialize()
    {
        animalTypeFxModel = new AnimalTypeFxModel();
        bindingTextFieldsWithAnimalTypeModel();
        bindingTableView();
    }

    @FXML
    public void findAnimalTypeOnAction()
    {
        try
        {
            this.animalTypeFxModel.findAnimalTypesByTypeOrRace();
        }
        catch (SQLException e)
        {
            DialogUtils.errorDialog(e.getMessage());
        }

        showInfoWhenNoFindAnimalType();
    }

    @FXML
    public void addTypeOnAction()
    {
        try
        {
            this.animalTypeFxModel.addAnimalType();
            DialogUtils.infoDialog(FXMLUtils.getResourceBundle().getString("addType.confirming"));
            this.animalTypeFxModel.putOnlyNewerOnList();
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
        searchButton.disableProperty().bind(typeTextField.textProperty().isEmpty().and(raceTextField.textProperty().isEmpty()));
        this.addAnimalTypeButton.disableProperty().bind(typeTextField.textProperty().isEmpty().or(this.raceTextField.textProperty().isEmpty()));

    }

    private void bindingTableView()
    {
        animalTypesTable.setItems(animalTypeFxModel.getAnimalTypeFxObservableList());
        idCollumn.setCellValueFactory(cellDate -> cellDate.getValue().idAnimalTypeProperty());
        typeCollumn.setCellValueFactory(cellDate -> cellDate.getValue().typeProperty());
        raceCollumn.setCellValueFactory(cellDate -> cellDate.getValue().raceProperty());
    }


    private void showInfoWhenNoFindAnimalType()
    {
        if (this.animalTypeFxModel.getAnimalTypeFxObservableList().isEmpty())
        {
            DialogUtils.infoDialog(FXMLUtils.getResourceBundle().getString("find.notFound"));
        }
    }

    private void clearTextFields()
    {
        this.typeTextField.clear();
        this.raceTextField.clear();
    }
}
