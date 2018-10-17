package animalHostel.gui.controllers;

import animalHostel.database.entity.AnimalInSlots;
import animalHostel.gui.modelsFx.AnimalInSlotsFx;
import animalHostel.gui.modelsFx.AnimalTypeFx;
import animalHostel.gui.modelsFx.SlotsForAnimalFx;
import animalHostel.gui.modelsFx.SlotsForAnimalModel;
import animalHostel.gui.utils.DialogUtils;
import animalHostel.gui.utils.FXMLUtils;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class ChooseSlotForAnimalController
{
    @FXML
    private TableView<AnimalInSlotsFx> slotsTableView;

    @FXML
    private TableColumn<AnimalInSlotsFx, Number> slotNUmberColumn;

    @FXML
    private TableColumn<AnimalInSlotsFx, Number> numberOfAnimalsColumn;

    @FXML
    private TableColumn<AnimalInSlotsFx, String> typeOfAnimalColumn;

    @FXML
    private TableColumn<AnimalInSlotsFx, String> raceOfAnimalColumn;

    @FXML
    private TableColumn<AnimalInSlotsFx, Number> areaColumn;

    @FXML
    private TableColumn<AnimalInSlotsFx, Number> heightColumn;

    @FXML
    private TableColumn<AnimalInSlotsFx, String> insideColumn;


    private SlotsForAnimalModel slotsForAnimalModel;

    @FXML
    public void initialize()
    {
        this.slotsForAnimalModel = new SlotsForAnimalModel();

        try
        {
            this.slotsForAnimalModel.fillList();
        }
        catch (SQLException e)
        {
            DialogUtils.errorDialog(e.getMessage());
        }
        slotsTableView();
    }

    private void slotsTableView()
    {
        this.slotsTableView.setItems(this.slotsForAnimalModel.getAnimalInSlotsFxObservableList());

        this.slotNUmberColumn.setCellValueFactory(cellData -> cellData.getValue().slotsForAnimalProperty().get().idSlotForAnimalProperty());
        this.numberOfAnimalsColumn.setCellValueFactory(cellData -> cellData.getValue().numberOfAnimalsProperty());
        this.typeOfAnimalColumn.setCellValueFactory(cellData -> cellData.getValue().getSlotsForAnimal().animalTypeProperty().get().typeProperty());
        this.raceOfAnimalColumn.setCellValueFactory(cellData -> cellData.getValue().getSlotsForAnimal().animalTypeProperty().get().raceProperty());
        this.areaColumn.setCellValueFactory(cellData -> cellData.getValue().getSlotsForAnimal().areaProperty());
        this.heightColumn.setCellValueFactory(cellData -> cellData.getValue().getSlotsForAnimal().heightProperty());


        this.insideColumn.setCellValueFactory(cellData ->{
            StringBinding as = new StringBinding()
            {
                @Override
                protected String computeValue()
                {
                    String yes = FXMLUtils.getResourceBundle().getString("yes");
                    String no = FXMLUtils.getResourceBundle().getString("no");
                    return cellData.getValue().getSlotsForAnimal().insideProperty().get() ? yes : no;
                }
            };
            return as;
        } );
    }

}
