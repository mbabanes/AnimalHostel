package animalHostel.gui.controllers;

import animalHostel.database.entity.Animal;
import animalHostel.gui.modelsFx.AnimalFx;
import animalHostel.gui.modelsFx.AnimalModel;
import animalHostel.gui.modelsFx.WorkerFx;
import animalHostel.gui.utils.DialogUtils;
import animalHostel.gui.utils.GUIUtils;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollToEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ScrollEvent;
import javafx.util.Callback;

import java.sql.SQLException;
import java.time.LocalDate;

public class AllAnimalsController
{
    @FXML
    private TableView<AnimalFx> allAnimalTableView;

    @FXML
    private TableColumn<AnimalFx, Integer> idTableIColumn;

    @FXML
    private TableColumn<AnimalFx, Integer> slotTableIColumn;

    @FXML
    private TableColumn<AnimalFx, String> nameTableIColumn;

    @FXML
    private TableColumn<AnimalFx, String> typeTableIColumn;

    @FXML
    private TableColumn<AnimalFx, String> raceTableIColumn;

    @FXML
    private TableColumn<AnimalFx, String> colorTableIColumn;

    @FXML
    private TableColumn<AnimalFx, Integer> weightTableIColumn;

    @FXML
    private TableColumn<AnimalFx, LocalDate> birthdayTableIColumn;

    @FXML
    private TableColumn<AnimalFx, LocalDate> registeredTableIColumn;

    @FXML
    private TableColumn<AnimalFx, Number> patronIdTableIColumn;

    @FXML
    private TableColumn<AnimalFx, String> patronNameTableIColumn;

    @FXML
    private TableColumn<AnimalFx, String> patronSurnameTableIColumn;

    private AnimalModel animalModel;


    @FXML
    public void initialize()
    {
        this.animalModel = new AnimalModel();
        try
        {
            this.animalModel.getAnimals();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            DialogUtils.errorDialog(e.getMessage());
        }

        this.allAnimalTableView.setItems(this.animalModel.getAnimalFxObservableList());

        this.idTableIColumn.setCellValueFactory(new PropertyValueFactory<AnimalFx, Integer>("id"));
        this.slotTableIColumn.setCellValueFactory(new PropertyValueFactory<AnimalFx, Integer>("slot"));
        this.nameTableIColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.typeTableIColumn.setCellValueFactory(cellData -> cellData.getValue().getAnimalTypeFx().typeProperty());
        this.raceTableIColumn.setCellValueFactory(cellData -> cellData.getValue().getAnimalTypeFx().raceProperty());
        this.colorTableIColumn.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
        this.weightTableIColumn.setCellValueFactory(new PropertyValueFactory<AnimalFx, Integer>("weight"));
        this.birthdayTableIColumn.setCellValueFactory(new PropertyValueFactory<AnimalFx, LocalDate>("birthDay"));
        this.registeredTableIColumn.setCellValueFactory(new PropertyValueFactory<AnimalFx, LocalDate>("dateOfRegister"));
        //this.patronIdTableIColumn.setCellValueFactory(new PropertyValueFactory<AnimalFx, Integer>("patron"));
        this.patronIdTableIColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AnimalFx, Number>, ObservableValue<Number>>()
        {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<AnimalFx, Number> param)
            {
                return param.getValue().getPatron().idWorkerProperty();
            }
        });
        this.patronNameTableIColumn.setCellValueFactory(cellData -> cellData.getValue().getPatron().nameProperty());
        this.patronSurnameTableIColumn.setCellValueFactory(cellData -> cellData.getValue().getPatron().surnameProperty());

    }

    public void onScrollDownOnScrollToFinished(ScrollEvent scrollEvent)
    {
        int lastViewRow = allAnimalTableView.getItems().size() - 10;

        if (scrollEvent.getDeltaY() < 0)
        {
            try
            {
                this.animalModel.getAnimals();
                //this.allAnimalTableView.lastViewRow
            }
            catch (SQLException e)
            {
                DialogUtils.errorDialog(e.getMessage());
            }

            this.allAnimalTableView.scrollTo(lastViewRow);
        }
    }



    public void registerAnimalToHeal()
    {
        AnimalFx animalFx = allAnimalTableView.getSelectionModel().getSelectedItem();
        MainController.getMainController().goToAddAnimalToHeal(animalFx);
    }
}
