package animalHostel.gui.utils.converters;

import animalHostel.database.entity.AnimalToHeal;
import animalHostel.gui.modelsFx.AnimalToHealFx;
import animalHostel.gui.utils.Utils;

public class ConverterAnimalToHeal
{
    public static AnimalToHeal convertToAnimalToHeal(AnimalToHealFx animalToHealFx)
    {
        AnimalToHeal animalToHeal = new AnimalToHeal();

        animalToHeal.setIdAnimalToHeal(animalToHealFx.getIdAnimalToHeal());
        if (animalToHealFx.getAnimal() != null)
            animalToHeal.setAnimal(ConverterAnimal.convertToAnimal(animalToHealFx.getAnimal()));

        if (animalToHealFx.getDateOfRegister() != null)
            animalToHeal.setDateOfRegister(Utils.convertToDate(animalToHealFx.getDateOfRegister()));

        animalToHeal.setDone(animalToHealFx.isDone());

        animalToHeal.setSymptoms(animalToHealFx.getSymptoms());

        return animalToHeal;
    }

    public static AnimalToHealFx convertToAnimalToHealFx(AnimalToHeal animalToHeal)
    {
        throw new UnsupportedOperationException();
    }
}
