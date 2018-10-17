package animalHostel.database.entity;

public class AnimalType
{
    private int idAnimalType;
    private String type;
    private String race;


    public AnimalType(int idAnimalType)
    {
        this.idAnimalType = idAnimalType;
    }

    public AnimalType(int idAnimalType, String type, String race)
    {
        this.idAnimalType = idAnimalType;
        this.type = type;
        this.race = race;
    }

    public AnimalType(String type, String race)
    {
        this.type = type;
        this.race = race;
    }

    public AnimalType()
    {

    }

    public int getIdAnimalType()
    {
        return idAnimalType;
    }

    public String getType()
    {
        return type;
    }

    public String getRace()
    {
        return race;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setRace(String race)
    {
        this.race = race;
    }

    public void setIdAnimalType(int idAnimalType)
    {
        this.idAnimalType = idAnimalType;
    }

    @Override
    public String toString()
    {
        return "AnimalType{" +
                "idAnimalType=" + idAnimalType +
                ", type='" + type + '\'' +
                ", race='" + race + '\'' +
                '}';
    }
}
