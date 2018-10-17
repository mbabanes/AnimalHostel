package animalHostel.gui.controllers;

import animalHostel.gui.modelsFx.WorkerFx;
import animalHostel.gui.modelsFx.WorkerModel;
import animalHostel.gui.utils.DialogUtils;
import animalHostel.gui.utils.GUIUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class ChoosePatronInAddAnimalController
{
    @FXML
    private TableView<WorkerFx> patronsTableView;

    @FXML
    private TableColumn<WorkerFx, Number> idColumn;

    @FXML
    private TableColumn<WorkerFx, String> surnamePatronColumn;

    @FXML
    private TableColumn<WorkerFx, String> namePatronColumn;

    @FXML
    private TableColumn<WorkerFx, Number> pupilsNumberColumn;

    private WorkerModel workerModel;

    @FXML
    public void initialize()
    {
        workerModel = new WorkerModel();

        try
        {
            workerModel.getAllPatron();
        }
        catch (SQLException e)
        {
            DialogUtils.errorDialog(e.getMessage());
        }

        patronsTableView.setItems(workerModel.getWorkerFxObservableList());

        this.idColumn.setCellValueFactory(cellData -> cellData.getValue().idWorkerProperty());
        this.surnamePatronColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        this.namePatronColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.pupilsNumberColumn.setCellValueFactory(cellData -> cellData.getValue().numberOfPupilsProperty());
        GUIUtils.setColumnContentCenter(patronsTableView);
    }
}
