package animalHostel.gui.utils.converters;

import animalHostel.database.entity.AnimalType;
import animalHostel.gui.modelsFx.AnimalTypeFx;

public class ConverterAnimalType
{
    public static AnimalTypeFx convertToAnimalTypeFx(AnimalType animalType)
    {
        AnimalTypeFx animalTypeFx = new AnimalTypeFx();

        animalTypeFx.setIdAnimalType(animalType.getIdAnimalType());

        animalTypeFx.setRace(animalType.getRace());

        animalTypeFx.setType(animalType.getType());


        return animalTypeFx;
    }

    public static AnimalType convertToAnimalType(AnimalTypeFx animalTypeFx)
    {
        AnimalType animalType = new AnimalType();
        animalType.setIdAnimalType(animalTypeFx.getIdAnimalType());
        animalType.setType(animalTypeFx.getType());
        animalType.setRace(animalTypeFx.getRace());

        return animalType;
    }
}
