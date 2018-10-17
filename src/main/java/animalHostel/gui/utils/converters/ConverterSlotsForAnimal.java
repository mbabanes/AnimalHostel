package animalHostel.gui.utils.converters;

import animalHostel.database.entity.SlotsForAnimal;
import animalHostel.gui.modelsFx.SlotsForAnimalFx;
import animalHostel.gui.utils.Utils;

public class ConverterSlotsForAnimal
{
    public static SlotsForAnimalFx convertToSlotsForAnimalFx(SlotsForAnimal slotsForAnimal)
    {
        SlotsForAnimalFx slotsForAnimalFx = new SlotsForAnimalFx();

        slotsForAnimalFx.setIdSlotForAnimal(slotsForAnimal.getIdSlotForAnimal());
        slotsForAnimalFx.setAnimalType(ConverterAnimalType.convertToAnimalTypeFx(slotsForAnimal.getAnimalType()));
        slotsForAnimalFx.setArea(slotsForAnimal.getArea());
        slotsForAnimalFx.setHeight(slotsForAnimal.getHeight());
        if (slotsForAnimal.getDateOfOpen() != null)
            slotsForAnimalFx.setDateOfOpen(Utils.convertToLocalDate(slotsForAnimal.getDateOfOpen()));

        slotsForAnimalFx.setFree(slotsForAnimal.isFree());
        slotsForAnimalFx.setInside(slotsForAnimal.isInside());

        return slotsForAnimalFx;
    }

    public static SlotsForAnimal convertToSlotsForAnimal(SlotsForAnimalFx slotsForAnimalFx)
    {
        SlotsForAnimal slotsForAnimal = new SlotsForAnimal();
        slotsForAnimal.setIdSlotForAnimal(slotsForAnimalFx.getIdSlotForAnimal());
        if (slotsForAnimalFx.getAnimalType() != null)
            slotsForAnimal.setAnimalType(ConverterAnimalType.convertToAnimalType(slotsForAnimalFx.getAnimalType()));
        slotsForAnimal.setInside(slotsForAnimalFx.isInside());
        slotsForAnimal.setHeight(slotsForAnimalFx.getHeight());
        slotsForAnimal.setArea(slotsForAnimalFx.getArea());
        slotsForAnimal.setFree(slotsForAnimalFx.isFree());

        if (slotsForAnimalFx.getDateOfOpen() != null)
            slotsForAnimal.setDateOfOpen(Utils.convertToDate(slotsForAnimalFx.getDateOfOpen()));

        return slotsForAnimal;
    }
}
