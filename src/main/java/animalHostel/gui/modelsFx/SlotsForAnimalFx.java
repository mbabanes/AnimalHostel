package animalHostel.gui.modelsFx;

import javafx.beans.property.*;

import java.time.LocalDate;

public class SlotsForAnimalFx
{
    private IntegerProperty idSlotForAnimal = new SimpleIntegerProperty();
    private BooleanProperty free = new SimpleBooleanProperty();
    private ObjectProperty<LocalDate> dateOfOpen = new SimpleObjectProperty<>();
    private ObjectProperty<AnimalTypeFx> animalType = new SimpleObjectProperty<>();
    private IntegerProperty area = new SimpleIntegerProperty();
    private BooleanProperty inside = new SimpleBooleanProperty();
    private IntegerProperty height = new SimpleIntegerProperty();

    public int getIdSlotForAnimal()
    {
        return idSlotForAnimal.get();
    }

    public IntegerProperty idSlotForAnimalProperty()
    {
        return idSlotForAnimal;
    }

    public void setIdSlotForAnimal(int idSlotForAnimal)
    {
        this.idSlotForAnimal.set(idSlotForAnimal);
    }

    public boolean isFree()
    {
        return free.get();
    }

    public BooleanProperty freeProperty()
    {
        return free;
    }

    public void setFree(boolean free)
    {
        this.free.set(free);
    }

    public LocalDate getDateOfOpen()
    {
        return dateOfOpen.get();
    }

    public ObjectProperty<LocalDate> dateOfOpenProperty()
    {
        return dateOfOpen;
    }

    public void setDateOfOpen(LocalDate dateOfOpen)
    {
        this.dateOfOpen.set(dateOfOpen);
    }

    public AnimalTypeFx getAnimalType()
    {
        return animalType.get();
    }

    public ObjectProperty<AnimalTypeFx> animalTypeProperty()
    {
        return animalType;
    }

    public void setAnimalType(AnimalTypeFx animalType)
    {
        this.animalType.set(animalType);
    }

    public int getArea()
    {
        return area.get();
    }

    public IntegerProperty areaProperty()
    {
        return area;
    }

    public void setArea(int area)
    {
        this.area.set(area);
    }

    public boolean isInside()
    {
        return inside.get();
    }

    public BooleanProperty insideProperty()
    {
        return inside;
    }

    public void setInside(boolean inside)
    {
        this.inside.set(inside);
    }

    public int getHeight()
    {
        return height.get();
    }

    public IntegerProperty heightProperty()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height.set(height);
    }
}
