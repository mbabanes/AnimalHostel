package animalHostel.database.entity;

import java.util.Date;

public class AnimalToHeal
{
    private int idAnimalToHeal;
    private Animal animal;
    private Date dateOfRegister;
    private String symptoms;
    private boolean done;


    public AnimalToHeal()
    {
    }

    public AnimalToHeal(int idAnimalToHeal, Animal animal, Date dateOfRegister, String symptoms, boolean done)
    {
        this.idAnimalToHeal = idAnimalToHeal;
        this.animal = animal;
        this.dateOfRegister = dateOfRegister;
        this.symptoms = symptoms;
        this.done = done;
    }

    public AnimalToHeal(Animal animal, Date dateOfRegister, String symptoms)
    {
        this.animal = animal;
        this.dateOfRegister = dateOfRegister;
        this.symptoms = symptoms;
    }

    public Animal getAnimal()
    {
        return animal;
    }

    public Date getDateOfRegister()
    {
        return dateOfRegister;
    }

    public String getSymptoms()
    {
        return symptoms;
    }

    public int getIdAnimalToHeal()
    {
        return idAnimalToHeal;
    }

    public void setIdAnimalToHeal(int idAnimalToHeal)
    {
        this.idAnimalToHeal = idAnimalToHeal;
    }

    public void setAnimal(Animal animal)
    {
        this.animal = animal;
    }

    public void setDateOfRegister(Date dateOfRegister)
    {
        this.dateOfRegister = dateOfRegister;
    }

    public void setSymptoms(String symptoms)
    {
        this.symptoms = symptoms;
    }

    public boolean isDone()
    {
        return done;
    }

    public void setDone(boolean done)
    {
        this.done = done;
    }
}
