package animalHostel.gui.modelsFx;

import javafx.beans.property.*;

import java.time.LocalDate;

public class AnimalToHealFx
{
    private IntegerProperty idAnimalToHeal = new SimpleIntegerProperty();
    private ObjectProperty<AnimalFx> animal = new SimpleObjectProperty<>(new AnimalFx());
    private ObjectProperty<LocalDate> dateOfRegister = new SimpleObjectProperty<>();
    private StringProperty symptoms = new SimpleStringProperty();
    private BooleanProperty done = new SimpleBooleanProperty();

    public int getIdAnimalToHeal()
    {
        return idAnimalToHeal.get();
    }

    public IntegerProperty idAnimalToHealProperty()
    {
        return idAnimalToHeal;
    }

    public void setIdAnimalToHeal(int idAnimalToHeal)
    {
        this.idAnimalToHeal.set(idAnimalToHeal);
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

    public LocalDate getDateOfRegister()
    {
        return dateOfRegister.get();
    }

    public ObjectProperty<LocalDate> dateOfRegisterProperty()
    {
        return dateOfRegister;
    }

    public void setDateOfRegister(LocalDate dateOfRegister)
    {
        this.dateOfRegister.set(dateOfRegister);
    }

    public String getSymptoms()
    {
        return symptoms.get();
    }

    public StringProperty symptomsProperty()
    {
        return symptoms;
    }

    public void setSymptoms(String symptoms)
    {
        this.symptoms.set(symptoms);
    }

    public boolean isDone()
    {
        return done.get();
    }

    public BooleanProperty doneProperty()
    {
        return done;
    }

    public void setDone(boolean done)
    {
        this.done.set(done);
    }
}
