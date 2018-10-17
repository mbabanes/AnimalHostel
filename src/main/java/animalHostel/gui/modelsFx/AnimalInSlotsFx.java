package animalHostel.gui.modelsFx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class AnimalInSlotsFx
{
    private ObjectProperty<LocalDate> dateOfIn = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> dateOfOut = new SimpleObjectProperty<>();
    private ObjectProperty<AnimalFx> animal = new SimpleObjectProperty<>();
    private IntegerProperty idAnimalInSlot = new SimpleIntegerProperty();
    private ObjectProperty<SlotsForAnimalFx> slotsForAnimal = new SimpleObjectProperty<>();

    private IntegerProperty numberOfAnimals = new SimpleIntegerProperty();

    public LocalDate getDateOfIn()
    {
        return dateOfIn.get();
    }

    public ObjectProperty<LocalDate> dateOfInProperty()
    {
        return dateOfIn;
    }

    public void setDateOfIn(LocalDate dateOfIn)
    {
        this.dateOfIn.set(dateOfIn);
    }

    public LocalDate getDateOfOut()
    {
        return dateOfOut.get();
    }

    public ObjectProperty<LocalDate> dateOfOutProperty()
    {
        return dateOfOut;
    }

    public void setDateOfOut(LocalDate dateOfOut)
    {
        this.dateOfOut.set(dateOfOut);
    }

    public AnimalFx getAnimal()
    {
        return animal.get();
    }

    public ObjectProperty<AnimalFx> animalProperty()
    {
        return animal;
    }

    public void setAnimal(AnimalFx animal)
    {
        this.animal.set(animal);
    }

    public int getIdAnimalInSlot()
    {
        return idAnimalInSlot.get();
    }

    public IntegerProperty idAnimalInSlotProperty()
    {
        return idAnimalInSlot;
    }

    public void setIdAnimalInSlot(int idAnimalInSlot)
    {
        this.idAnimalInSlot.set(idAnimalInSlot);
    }

    public SlotsForAnimalFx getSlotsForAnimal()
    {
        return slotsForAnimal.get();
    }

    public ObjectProperty<SlotsForAnimalFx> slotsForAnimalProperty()
    {
        return slotsForAnimal;
    }

    public void setSlotsForAnimal(SlotsForAnimalFx slotsForAnimal)
    {
        this.slotsForAnimal.set(slotsForAnimal);
    }

    public int getNumberOfAnimals()
    {
        return numberOfAnimals.get();
    }

    public IntegerProperty numberOfAnimalsProperty()
    {
        return numberOfAnimals;
    }

    public void setNumberOfAnimals(int numberOfAnimals)
    {
        this.numberOfAnimals.set(numberOfAnimals);
    }
}
