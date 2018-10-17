package animalHostel.gui.controllers;

import animalHostel.Hostel;
import animalHostel.gui.HostelSingleton;
import animalHostel.gui.dialogs.AnimalTypeFind;
import animalHostel.gui.dialogs.ChooseSlot;
import animalHostel.gui.dialogs.PatronFind;
import animalHostel.gui.modelsFx.*;
import animalHostel.gui.utils.DialogUtils;
import animalHostel.gui.utils.FXMLUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.sql.SQLException;

public class AddAnimalController
{
    @FXML
    private TextField animalNameTextField;

    @FXML
    private TextField animalTypeTextField;

    @FXML
    private TextField colorTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private DatePicker birthdayDataPicker;

    @FXML
    private TextField patronTextField;

    @FXML
    private TextField slotNumberTextField;

    @FXML
    private Button addAnimalButton;

    private AddAnimalModel addAnimalModel;


    @FXML
    public void initialize()
    {
        this.addAnimalModel = new AddAnimalModel();

        checkIsThereFreeSlots();

        addAnimalButton.disableProperty().bind(animalTypeTextField.textProperty().isEmpty()
                                                .or(patronTextField.textProperty().isEmpty())
                                                .or(slotNumberTextField.textProperty().isEmpty()));
        this.addAnimalModel.getAnimalFxObjectProperty().nameProperty().bind(this.animalNameTextField.textProperty());
        this.addAnimalModel.getAnimalFxObjectProperty().colorProperty().bind(this.colorTextField.textProperty());



        this.addAnimalModel.getAnimalFxObjectProperty().birthDayProperty().bind(birthdayDataPicker.valueProperty());
        setValidateContentOnWeightTextField();
    }


    @FXML
    void chooseTypeOnKeyReleased(KeyEvent event)
    {
        if (F2KeyReleased(event))
        {
            chooseAnimalType();
        }
    }

    @FXML
    public void chooseTypeAnimalButtonOnAction()
    {
        chooseAnimalType();
    }

    @FXML
    public void choosePatronOnKeyReleased(KeyEvent keyEvent)
    {
        if (F2KeyReleased(keyEvent))
        {
            choosePatron();
        }
    }

    @FXML
    public void slotNumberTextFieldOnKezReleased(KeyEvent keyEvent)
    {
        if (F2KeyReleased(keyEvent))
        {
            chooseSlot();
        }
    }

    @FXML
    public void chooseSlotNumberOnAction()
    {
        chooseSlot();
    }

    @FXML
    public void choosePatronButtonOnAction()
    {
        choosePatron();
    }

    public void addAnimalOnAction()
    {
        try
        {
            this.addAnimalModel.addAnimal();
            if (!this.slotNumberTextField.getText().isEmpty())
                this.addAnimalModel.addAnimalInSlot();

            DialogUtils.infoDialog(FXMLUtils.getResourceBundle().getString("addType.confirming"));
        }
        catch (SQLException e)
        {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    private void checkIsThereFreeSlots()
    {
        try
        {
            Hostel hostel = HostelSingleton.getHostel();
            if (hostel.getFreeSlotsForAnimals() == 0)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(FXMLUtils.getResourceBundle().getString("warning"));
                alert.setHeaderText(FXMLUtils.getResourceBundle().getString("addAnimal.warningInfo"));
                alert.showAndWait();
            }
        }
        catch (SQLException e)
        {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    private void setValidateContentOnWeightTextField()
    {
        this.weightTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
            {
                if (!checkWeightTextFieldContentIsCorrect())
                {
                    this.weightTextField.setText("");
                    showAlertError();
                }
                else
                {
                    this.addAnimalModel.getAnimalFxObjectProperty().weightProperty().setValue(Integer.valueOf(this.weightTextField.getText()));
                }
            }

        });
    }

    private boolean checkWeightTextFieldContentIsCorrect()
    {
        return this.weightTextField.getText().matches("[1-9]{1}[0-9]{0,2}");
    }

    private void showAlertError()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(FXMLUtils.getResourceBundle().getString("error.title"));
        alert.setHeaderText(FXMLUtils.getResourceBundle().getString("error.title"));

        alert.setContentText(FXMLUtils.getResourceBundle().getString("addAnimal.error.weight"));
        alert.showAndWait();
    }


    private boolean F2KeyReleased(KeyEvent event)
    {
        return event.getCode().equals(KeyCode.F2);
    }

    private void chooseAnimalType()
    {
        AnimalTypeFind animalTypeFindDialog = new AnimalTypeFind();
        AnimalTypeFx animalTypeFx = animalTypeFindDialog.showAndGetResult();
        if (animalTypeFx != null)
        {
            this.animalTypeTextField.setText(animalTypeFx.toString());
            this.addAnimalModel.getAnimalFxObjectProperty().setAnimalTypeFx(animalTypeFx);
        }
    }

    private void choosePatron()
    {
        PatronFind dialog = new PatronFind();
        WorkerFx workerFx = dialog.showAndGetResult();
        if (workerFx != null)
        {
            this.patronTextField.setText(workerFx.toString());
            this.addAnimalModel.getAnimalFxObjectProperty().setPatron(workerFx);
        }
    }




    private void chooseSlot()
    {
        ChooseSlot dialog = new ChooseSlot();
        AnimalInSlotsFx animalInSlotsFx = dialog.showAndGetResult();
        if (animalInSlotsFx != null)
        {
            this.addAnimalModel.setAnimalInSlotsFxObjectProperty(animalInSlotsFx);
            this.slotNumberTextField.setText(String.valueOf(animalInSlotsFx.getSlotsForAnimal().getIdSlotForAnimal()));
        }
    }


}
