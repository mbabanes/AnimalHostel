package animalHostel.gui.modelsFx;

import animalHostel.Hostel;
import animalHostel.database.entity.AnimalType;
import animalHostel.gui.HostelSingleton;
import animalHostel.gui.utils.converters.ConverterAnimalType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class AnimalTypeFxModel
{
    private ObjectProperty<AnimalTypeFx> animalTypeFxObjectProperty = new SimpleObjectProperty<>(new AnimalTypeFx());

    private ObservableList<AnimalTypeFx> animalTypeFxObservableList = FXCollections.observableArrayList();

    public void addAnimalType() throws SQLException
    {
        Hostel hostel = HostelSingleton.getHostel();
        AnimalType animalType = ConverterAnimalType.convertToAnimalType(animalTypeFxObjectProperty.get());

        hostel.addAnimalType(animalType);
    }


    public void findAnimalTypesByTypeOrRace() throws SQLException
    {
        Hostel hostel = HostelSingleton.getHostel();
        List<AnimalType> animalTypes = hostel.findAnimalTypeByTypeOrRace(animalTypeFxObjectProperty.get().getType(), animalTypeFxObjectProperty.get().getRace());

        animalTypeFxObservableList.clear();
        animalTypes.forEach(animalType -> {
            AnimalTypeFx animalTypeFx = ConverterAnimalType.convertToAnimalTypeFx(animalType);

            animalTypeFxObservableList.add(animalTypeFx);
        });
    }

    public void putOnlyNewerOnList() throws SQLException
    {
        Hostel hostel = HostelSingleton.getHostel();
        AnimalType animalType = hostel.findAnimalTypeByTypeAndRace(animalTypeFxObjectProperty.get().getType(), animalTypeFxObjectProperty.get().getRace());
        AnimalTypeFx animalTypeFx = ConverterAnimalType.convertToAnimalTypeFx(animalType);

        animalTypeFxObservableList.clear();
        animalTypeFxObservableList.add(animalTypeFx);

    }

    public AnimalTypeFx getAnimalTypeFxObjectProperty()
    {
        return animalTypeFxObjectProperty.get();
    }

    public ObjectProperty<AnimalTypeFx> animalTypeFxObjectPropertyProperty()
    {
        return animalTypeFxObjectProperty;
    }

    public void setAnimalTypeFxObjectProperty(AnimalTypeFx animalTypeFxObjectProperty)
    {
        this.animalTypeFxObjectProperty.set(animalTypeFxObjectProperty);
    }


    public ObservableList<AnimalTypeFx> getAnimalTypeFxObservableList()
    {
        return animalTypeFxObservableList;
    }

    public void setAnimalTypeFxObservableList(ObservableList<AnimalTypeFx> animalTypeFxObservableList)
    {
        this.animalTypeFxObservableList = animalTypeFxObservableList;
    }
}
