package animalHostel.database.entity;

import java.util.Date;

/**
 * Created by mbabane on 29.05.17.
 */
public class Animal
{
    private int id;

    private int slot;

    private String name;
    private AnimalType animalType;
    private String color;
    private int weight;
    private Date birthDay;
    private Date dateOfRegister;

    private Worker patron;


    public Animal()
    {
    }

    public Animal(int id)
    {
        this.id = id;
    }

    public Animal(int id, int slot, String name, AnimalType animalType, Date birthDay, Worker patron)
    {
        this.id = id;
        this.slot = slot;
        this.name = name;
        this.animalType = animalType;
        this.birthDay = birthDay;
        this.patron = patron;
    }

    public Animal(int id, int slot, String name, AnimalType animalType, String color, int weight, Date birthDay, Date dateOfRegister, Worker patron)
    {
        this.id = id;
        this.slot = slot;
        this.name = name;
        this.animalType = animalType;
        this.color = color;
        this.weight = weight;
        this.birthDay = birthDay;
        this.dateOfRegister = dateOfRegister;
        this.patron = patron;
    }




    @Override
    public String toString()
    {
        return "Animal{" +
                "id=" + id +
                ", slot=" + slot +
                ", name='" + name + '\'' +
                ", animalType=" + animalType +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", birthDay=" + birthDay +
                ", dateOfRegister=" + dateOfRegister +
                ", patron=" + patron +
                '}';
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public AnimalType getAnimalType()
    {
        return animalType;
    }

    public String getColor()
    {
        return color;
    }

    public int getWeight()
    {
        return weight;
    }

    public Date getBirthDay()
    {
        return birthDay;
    }

    public Date getDateOfRegister()
    {
        return dateOfRegister;
    }

    public Worker getPatron()
    {
        return patron;
    }

    public int getSlot()
    {
        return slot;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setSlot(int slot)
    {
        this.slot = slot;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAnimalType(AnimalType animalType)
    {
        this.animalType = animalType;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public void setBirthDay(Date birthDay)
    {
        this.birthDay = birthDay;
    }

    public void setDateOfRegister(Date dateOfRegister)
    {
        this.dateOfRegister = dateOfRegister;
    }

    public void setPatron(Worker patron)
    {
        this.patron = patron;
    }
}
