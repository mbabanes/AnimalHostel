package animalHostel.gui.utils.converters;

import animalHostel.database.entity.AnimalInSlots;
import animalHostel.gui.modelsFx.AnimalInSlotsFx;
import animalHostel.gui.utils.Utils;

public class ConverterAnimalInSlots
{
    public static AnimalInSlotsFx convertToAnimalInSlotsFx(AnimalInSlots animalInSlots)
    {
        AnimalInSlotsFx animalInSlotsFx = new AnimalInSlotsFx();

        if (animalInSlots.getAnimal() != null)
            animalInSlotsFx.setAnimal(ConverterAnimal.convertToAnimalFx(animalInSlots.getAnimal()));

        if (animalInSlots.getDateOfIn() != null)
            animalInSlotsFx.setDateOfIn(Utils.convertToLocalDate(animalInSlots.getDateOfIn()));

        if (animalInSlots.getDateOfOut() != null)
            animalInSlotsFx.setDateOfOut(Utils.convertToLocalDate(animalInSlots.getDateOfOut()));


        animalInSlotsFx.setIdAnimalInSlot(animalInSlots.getIdAnimalInSlot());

        animalInSlotsFx.setSlotsForAnimal(ConverterSlotsForAnimal.convertToSlotsForAnimalFx(animalInSlots.getSlotsForAnimal()));

        return animalInSlotsFx;
    }

    public static AnimalInSlots convertToAnimalInSlots(AnimalInSlotsFx animalInSlotsFx)
    {
        AnimalInSlots animalInSlots = new AnimalInSlots();
        animalInSlots.setIdAnimalInSlot(animalInSlotsFx.getIdAnimalInSlot());
        if (animalInSlotsFx.getSlotsForAnimal() != null)
            animalInSlots.setSlotsForAnimal(ConverterSlotsForAnimal.convertToSlotsForAnimal(animalInSlotsFx.getSlotsForAnimal()));

        if (animalInSlotsFx.getDateOfIn() != null)
            animalInSlots.setDateOfIn(Utils.convertToDate(animalInSlotsFx.getDateOfIn()));

        if (animalInSlotsFx.getDateOfOut() != null)
            animalInSlots.setDateOfOut(Utils.convertToDate(animalInSlotsFx.getDateOfOut()));

        if (animalInSlotsFx.getAnimal() != null)
            animalInSlots.setAnimal(ConverterAnimal.convertToAnimal(animalInSlotsFx.getAnimal()));

        return  animalInSlots;
    }

}