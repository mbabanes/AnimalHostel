package animalHostel.gui.modelsFx;

import animalHostel.Hostel;
import animalHostel.database.entity.Animal;
import animalHostel.database.entity.AnimalInSlots;
import animalHostel.gui.HostelSingleton;
import animalHostel.gui.utils.converters.ConverterAnimal;
import animalHostel.gui.utils.converters.ConverterAnimalInSlots;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.SQLException;
import java.time.LocalDate;

public class AddAnimalModel
{
    private ObjectProperty<AnimalFx> animalFxObjectProperty = new SimpleObjectProperty<>(new AnimalFx());
    private ObjectProperty<AnimalInSlotsFx> animalInSlotsFxObjectProperty = new SimpleObjectProperty<>(new AnimalInSlotsFx());



    public void addAnimal() throws SQLException
    {
        Hostel hostel = HostelSingleton.getHostel();

        this.animalFxObjectProperty.get().setDateOfRegister(LocalDate.now());

        Animal newAnimal = ConverterAnimal.convertToAnimal(this.animalFxObjectProperty.get());

        int idAnimal = hostel.addAnimal(newAnimal);

        this.animalFxObjectProperty.get().setId(idAnimal);
        this.animalInSlotsFxObjectProperty.get().setAnimal(this.animalFxObjectProperty.get());
    }


    public void addAnimalInSlot() throws SQLException
    {
        Hostel hostel = HostelSingleton.getHostel();

        this.animalInSlotsFxObjectProperty.get().setDateOfIn(LocalDate.now());

        AnimalInSlots animalInSlots = ConverterAnimalInSlots.convertToAnimalInSlots(this.animalInSlotsFxObjectProperty.get());

        hostel.addAnimalIntoSlotForAnimals(animalInSlots);
    }

    public AnimalFx getAnimalFxObjectProperty()
    {
        return animalFxObjectProperty.get();
    }

    public ObjectProperty<AnimalFx> animalFxObjectPropertyProperty()
    {
        return animalFxObjectProperty;
    }

    public void setAnimalFxObjectProperty(AnimalFx animalFxObjectProperty)
    {
        this.animalFxObjectProperty.set(animalFxObjectProperty);
    }

    public AnimalInSlotsFx getAnimalInSlotsFxObjectProperty()
    {
        return animalInSlotsFxObjectProperty.get();
    }

    public ObjectProperty<AnimalInSlotsFx> animalInSlotsFxObjectPropertyProperty()
    {
        return animalInSlotsFxObjectProperty;
    }

    public void setAnimalInSlotsFxObjectProperty(AnimalInSlotsFx animalInSlotsFxObjectProperty)
    {
        this.animalInSlotsFxObjectProperty.set(animalInSlotsFxObjectProperty);
    }
}
