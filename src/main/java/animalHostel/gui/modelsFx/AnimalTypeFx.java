package animalHostel.gui.modelsFx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AnimalTypeFx
{
    private IntegerProperty idAnimalType = new SimpleIntegerProperty();
    private StringProperty type = new SimpleStringProperty();
    private StringProperty race = new SimpleStringProperty();

    public int getIdAnimalType()
    {
        return idAnimalType.get();
    }

    public IntegerProperty idAnimalTypeProperty()
    {
        return idAnimalType;
    }

    public void setIdAnimalType(int idAnimalType)
    {
        this.idAnimalType.set(idAnimalType);
    }

    public String getType()
    {
        return type.get();
    }

    public StringProperty typeProperty()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type.set(type);
    }

    public String getRace()
    {
        return race.get();
    }

    public StringProperty raceProperty()
    {
        return race;
    }

    public void setRace(String race)
    {
        this.race.set(race);
    }

    @Override
    public String toString()
    {
        return type.get() + " - " + race.get();
    }
}
