package animalHostel.gui.modelsFx;

import javafx.beans.property.*;


import java.time.LocalDate;

public class AnimalFx
{
    private IntegerProperty id = new SimpleIntegerProperty();
    //private int idSlot;

    private IntegerProperty slot = new SimpleIntegerProperty();

    private StringProperty name = new SimpleStringProperty();
    private ObjectProperty<AnimalTypeFx> animalTypeFx = new SimpleObjectProperty<>();
    private StringProperty color = new SimpleStringProperty();
    private IntegerProperty weight = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate>  birthDay = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> dateOfRegister = new SimpleObjectProperty<>();

    private ObjectProperty<WorkerFx> patron = new SimpleObjectProperty<>();

    public int getId()
    {
        return id.get();
    }

    public IntegerProperty idProperty()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id.set(id);
    }

    public int getSlot()
    {

        return slot.get();
    }

    public IntegerProperty slotProperty()
    {
        return slot;
    }

    public void setSlot(int slot)
    {
        this.slot.set(slot);
    }

    public String getName()
    {
        return name.get();
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public AnimalTypeFx getAnimalTypeFx()
    {
        return animalTypeFx.get();
    }

    public ObjectProperty<AnimalTypeFx> animalTypeFxProperty()
    {
        return animalTypeFx;
    }

    public void setAnimalTypeFx(AnimalTypeFx animalTypeFx)
    {
        this.animalTypeFx.set(animalTypeFx);
    }

    public String getColor()
    {
        return color.get();
    }

    public StringProperty colorProperty()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color.set(color);
    }

    public int getWeight()
    {
        return weight.get();
    }

    public IntegerProperty weightProperty()
    {
        return weight;
    }

    public void setWeight(int weight)
    {
        this.weight.set(weight);
    }

    public LocalDate getBirthDay()
    {
        return birthDay.get();
    }

    public ObjectProperty<LocalDate> birthDayProperty()
    {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay)
    {
        this.birthDay.set(birthDay);
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

    public WorkerFx getPatron()
    {
        return patron.get();
    }

    public ObjectProperty<WorkerFx> patronProperty()
    {
        return patron;
    }

    public void setPatron(WorkerFx patron)
    {
        this.patron.set(patron);
    }

    @Override
    public String toString()
    {
        return "AnimalFx{" +
                "id=" + id +
                ", slot=" + slot +
                ", name=" + name +
                ", animalTypeFx=" + animalTypeFx +
                ", color=" + color +
                ", weight=" + weight +
                ", birthDay=" + birthDay +
                ", dateOfRegister=" + dateOfRegister +
                ", patron=" + patron +
                '}';
    }
}
