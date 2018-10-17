package animalHostel.gui.controllers;

import animalHostel.gui.Main;
import animalHostel.gui.dialogs.AnimalFindDialog;
import animalHostel.gui.modelsFx.AddAnimalToHealModel;
import animalHostel.gui.modelsFx.AnimalFx;
import animalHostel.gui.utils.DialogUtils;
import animalHostel.gui.utils.FXMLUtils;
import com.itextpdf.text.DocumentException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AddAnimalToHealController
{
    @FXML
    private TextField animalTextField;

    @FXML
    private TextArea symptomsTextArea;

    @FXML
    private Button addButton;

    private AddAnimalToHealModel addAnimalToHealModel;

    public AddAnimalToHealController()
    {
        addAnimalToHealModel = new AddAnimalToHealModel();
    }

    public AddAnimalToHealController(AnimalFx animalFx)
    {
        this();
        addAnimalToHealModel.getAnimalToHealModelObjectProperty().setAnimal(animalFx);
    }

    public void initialize()
    {
        bindings();
        System.out.println("Initialize");
        putAnimalIfSet();
    }




    public void chooseAnimalButton()
    {
        chooseAnimalFx();
    }


    public void addAnimalToHealOnAction()
    {
        try
        {
            addAnimalToHealModel.addAnimalToHeal();
            confirmAndAskAboutCreatingReport();
        }
        catch (SQLException e)
        {
            DialogUtils.errorDialog(e.getMessage());
        }
        catch (DocumentException e)
        {
            DialogUtils.errorDialog(e.getMessage());
        }
        catch (IOException e)
        {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void chooseAnimalKeyReleased(KeyEvent keyEvent)
    {
        if (F2KeyReleased(keyEvent))
        {
            chooseAnimalFx();
        }
    }

    private void putAnimalIfSet()
    {
        AnimalFx animalFx = addAnimalToHealModel.getAnimalToHealModelObjectProperty().getAnimal();
        if (animalIsSet(animalFx))
            animalTextField.setText(animalFx.getId() + " | " + animalFx.getName() + " | " + animalFx.getSlot() );
    }

    private boolean animalIsSet(AnimalFx animalFx)
    {
        return animalFx.getId() != 0;
    }

    private void confirmAndAskAboutCreatingReport() throws DocumentException, SQLException, IOException
    {
        Optional<ButtonType> buttonType = showConfirmingDialog();

        if (canSaveReport(buttonType))
        {
            File file = selectPath();
            if (notCancelSaving(file))
            {
                addAnimalToHealModel.createReport(file.getPath());
            }
        }
    }

    private boolean notCancelSaving(File file)
    {
        return file != null;
    }

    private boolean canSaveReport(Optional<ButtonType> buttonType)
    {
        return buttonType.get().getButtonData().isDefaultButton();
    }

    private Optional<ButtonType> showConfirmingDialog()
    {
        String titleMessage = FXMLUtils.getResourceBundle().getString("addAnimalToHeal.confirmingTitle");
        String headerMessage = FXMLUtils.getResourceBundle().getString("addAnimalToHeal.confirming");

        return DialogUtils.confirmationAndSavingConfirmationDialog(titleMessage, headerMessage );
    }

    private File selectPath()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(FXMLUtils.getResourceBundle().getString("saveAs"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Protable Document Format (*.pdf", "*.pdf"));

        int idAnimalToHeal = addAnimalToHealModel.getAnimalToHealModelObjectProperty().getIdAnimalToHeal();
        String dateOfRegister = addAnimalToHealModel.getAnimalToHealModelObjectProperty().getDateOfRegister().format(DateTimeFormatter.ofPattern("ddMMYYYY"));

        String fileName = FXMLUtils.getResourceBundle().getString("addAnimalToHeal.report.fileName") + idAnimalToHeal + dateOfRegister;
        fileChooser.setInitialFileName(fileName);
        return fileChooser.showSaveDialog(Main.getPrimaryStage().getOwner());
    }


    private void bindings()
    {
        addAnimalToHealModel.getAnimalToHealModelObjectProperty().symptomsProperty().bind(symptomsTextArea.textProperty());

        addButton.disableProperty().bind(symptomsTextArea.textProperty().isEmpty().or(animalTextField.textProperty().isEmpty()));
    }

    private boolean F2KeyReleased(KeyEvent keyEvent)
    {
        return keyEvent.getCode().equals(KeyCode.F2);
    }

    private void chooseAnimalFx()
    {
        AnimalFindDialog animalFindDialog = new AnimalFindDialog();
        AnimalFx animalFx = animalFindDialog.showAndGetResult();

        if (animalFx != null)
        {
            animalTextField.setText(animalFx.getId() + " | " + animalFx.getName() + " | " + animalFx.getSlot());
            addAnimalToHealModel.getAnimalToHealModelObjectProperty().setAnimal(animalFx);
        }
    }
}
