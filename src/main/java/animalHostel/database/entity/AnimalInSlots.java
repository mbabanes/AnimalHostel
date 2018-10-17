package animalHostel.database.entity;

import java.util.Date;

public class AnimalInSlots
{
    private int idAnimalInSlot;
    private Date dateOfIn;
    private Date dateOfOut;
    private Animal animal;
    private SlotsForAnimal slotsForAnimal;

    private int countOfAnimal;


    public AnimalInSlots( Animal animal, SlotsForAnimal slotsForAnimal, Date dateOfIn)
    {
        this.dateOfIn = dateOfIn;
        this.animal = animal;
        this.slotsForAnimal = slotsForAnimal;
    }

    public AnimalInSlots(int idAnimalInSlot)
    {
        this.idAnimalInSlot = idAnimalInSlot;
    }

    public AnimalInSlots()
    {

    }

    public Date getDateOfIn()
    {
        return dateOfIn;
    }

    public Animal getAnimal()
    {
        return animal;
    }


    public SlotsForAnimal getSlotsForAnimal()
    {
        return slotsForAnimal;
    }


    public Date getDateOfOut()
    {
        return dateOfOut;
    }

    public int getIdAnimalInSlot()
    {
        return idAnimalInSlot;
    }

    public void setSlotsForAnimal(SlotsForAnimal slotsForAnimal)
    {
        this.slotsForAnimal = slotsForAnimal;
    }

    public void setCountOfAnimal(int countOfAnimal)
    {
        this.countOfAnimal = countOfAnimal;
    }

    public int getCountOfAnimal()
    {
        return countOfAnimal;
    }

    public void setIdAnimalInSlot(int idAnimalInSlot)
    {
        this.idAnimalInSlot = idAnimalInSlot;
    }

    public void setDateOfIn(Date dateOfIn)
    {
        this.dateOfIn = dateOfIn;
    }

    public void setDateOfOut(Date dateOfOut)
    {
        this.dateOfOut = dateOfOut;
    }

    public void setAnimal(Animal animal)
    {
        this.animal = animal;
    }

    @Override
    public String toString()
    {
        return "AnimalInSlots{" +
                "idAnimalInSlot=" + idAnimalInSlot +
                '}';
    }
}
