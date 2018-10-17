package animalHostel.gui.utils.converters;

import animalHostel.database.entity.Animal;
import animalHostel.gui.modelsFx.AnimalFx;
import animalHostel.gui.utils.Utils;

public class ConverterAnimal
{
    public static AnimalFx convertToAnimalFx(Animal animal)
    {
        AnimalFx animalFx = new AnimalFx();

        animalFx.setId(animal.getId());

        animalFx.setSlot(animal.getSlot());

        animalFx.setColor(animal.getColor());

        animalFx.setName(animal.getName());

        if (animal.getAnimalType() != null)
            animalFx.setAnimalTypeFx(ConverterAnimalType.convertToAnimalTypeFx(animal.getAnimalType()));

        if (animal.getBirthDay() != null)
            animalFx.setBirthDay(Utils.convertToLocalDate(animal.getBirthDay()));

        if (animal.getDateOfRegister() != null)
            animalFx.setDateOfRegister(Utils.convertToLocalDate(animal.getDateOfRegister()));

        if (animal.getPatron() != null)
            animalFx.setPatron(ConverterWorker.convertToWorkerFx(animal.getPatron()));

        animalFx.setWeight(animal.getWeight());
        return animalFx;
    }

    public static Animal convertToAnimal(AnimalFx animalFx)
    {
        Animal animal = new Animal();
        animal.setId(animalFx.getId());
        animal.setName(animalFx.getName());
        if (animalFx.getAnimalTypeFx() != null)
            animal.setAnimalType(ConverterAnimalType.convertToAnimalType(animalFx.getAnimalTypeFx()));
        animal.setColor(animalFx.getColor());
        animal.setWeight(animalFx.getWeight());

        if (animalFx.getBirthDay() != null)
            animal.setBirthDay(Utils.convertToDate(animalFx.getBirthDay()));

        if (animalFx.getDateOfRegister() != null)
            animal.setDateOfRegister(Utils.convertToDate(animalFx.getDateOfRegister()));

        if (animalFx.getPatron() != null)
            animal.setPatron(ConverterWorker.convertToWorker(animalFx.getPatron()));

        return animal;
    }
}
