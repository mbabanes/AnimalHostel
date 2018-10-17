package animalHostel.gui.modelsFx;

import animalHostel.Hostel;
import animalHostel.database.entity.AnimalInSlots;
import animalHostel.gui.HostelSingleton;
import animalHostel.gui.utils.converters.ConverterAnimalInSlots;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class SlotsForAnimalModel
{
    private ObservableList<AnimalInSlotsFx> animalInSlotsFxObservableList = FXCollections.observableArrayList();



    public void fillList() throws SQLException
    {
        Hostel hostel = HostelSingleton.getHostel();
        List<AnimalInSlots> animalInSlotsList = hostel.getSlotsForAnimalWithStats();

        animalInSlotsList.forEach(animalInSlots -> {
            AnimalInSlotsFx animalInSlotsFx = ConverterAnimalInSlots.convertToAnimalInSlotsFx(animalInSlots);
            animalInSlotsFx.setNumberOfAnimals(animalInSlots.getCountOfAnimal());
            animalInSlotsFxObservableList.add(animalInSlotsFx);
        });
    }

    public ObservableList<AnimalInSlotsFx> getAnimalInSlotsFxObservableList()
    {
        return animalInSlotsFxObservableList;
    }

    public void setAnimalInSlotsFxObservableList(ObservableList<AnimalInSlotsFx> animalInSlotsFxObservableList)
    {
        this.animalInSlotsFxObservableList = animalInSlotsFxObservableList;
    }
}
