package animalHostel.database.entity;

import java.util.Date;

public class SlotsForAnimal
{
    private int idSlotForAnimal;
    boolean free;
    private Date dateOfOpen;
    AnimalType animalType;
    private int area;
    private boolean inside;
    private int height;


    public SlotsForAnimal(int idSlotForAnimal, boolean free, Date dateOfOpen, AnimalType animalType, int area, boolean inside, int height)
    {
        this.idSlotForAnimal = idSlotForAnimal;
        this.free = free;
        this.dateOfOpen = dateOfOpen;
        this.animalType = animalType;
        this.area = area;
        this.inside = inside;
        this.height = height;
    }

    public SlotsForAnimal(Date dateOfOpen, AnimalType animalType, int area, boolean inside, int height)
    {
        this.area = area;
        this.dateOfOpen = dateOfOpen;
        this.height = height;
        this.animalType = animalType;
        this.inside = inside;
    }

    public SlotsForAnimal(int idSlotForAnimal)
    {
        this.idSlotForAnimal = idSlotForAnimal;
    }

    public SlotsForAnimal()
    {

    }


    public int getArea()
    {
        return area;
    }

    public Date getDateOfOpen()
    {
        return dateOfOpen;
    }

    public int getHeight()
    {
        return height;
    }

    public AnimalType getAnimalType()
    {
        return animalType;
    }

    public boolean isInside()
    {
        return inside;
    }

    public int getIdSlotForAnimal()
    {
        return idSlotForAnimal;
    }

    public void setIdSlotForAnimal(int idSlotForAnimal)
    {
        this.idSlotForAnimal = idSlotForAnimal;
    }

    public void setAnimalType(AnimalType animalType)
    {
        this.animalType = animalType;
    }

    public void setArea(int area)
    {
        this.area = area;
    }

    public void setInside(boolean inside)
    {
        this.inside = inside;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public boolean isFree()
    {
        return free;
    }

    public void setFree(boolean free)
    {
        this.free = free;
    }

    public void setDateOfOpen(Date dateOfOpen)
    {
        this.dateOfOpen = dateOfOpen;
    }

    @Override
    public String toString()
    {
        return "SlotsForAnimal{" +
                "idSlotForAnimal=" + idSlotForAnimal +
                ", free=" + free +
                ", dateOfOpen=" + dateOfOpen +
                ", animalType=" + animalType +
                ", area=" + area +
                ", inside=" + inside +
                ", height=" + height +
                '}';
    }
}
