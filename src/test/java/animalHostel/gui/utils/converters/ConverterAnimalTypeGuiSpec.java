package animalHostel.gui.utils.converters;

import animalHostel.database.entity.AnimalType;
import animalHostel.gui.modelsFx.AnimalTypeFx;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;



public class ConverterAnimalTypeGuiSpec
{
    private AnimalType animalType;
    private AnimalTypeFx animalTypeFx;

    @Test
    public void whenConvertToAnimalTypeFxThenReturnAnimalTypeFx()
    {
        prepareAnimalType();
        animalTypeFx = ConverterAnimalType.convertToAnimalTypeFx(animalType);

        Assert.assertEquals(animalType.getIdAnimalType(), animalTypeFx.getIdAnimalType());
        Assert.assertEquals(animalType.getType(), animalTypeFx.getType());
        Assert.assertEquals(animalType.getRace(), animalTypeFx.getRace());
    }


    @Test
    public void whenConvertToAnimalTypeThenReturnAnimalType()
    {
        prepareAnimalTypeFx();

        animalType = ConverterAnimalType.convertToAnimalType(animalTypeFx);

        Assert.assertEquals(animalTypeFx.getIdAnimalType(), animalType.getIdAnimalType());
        Assert.assertEquals(animalTypeFx.getRace(), animalType.getRace());
        Assert.assertEquals(animalTypeFx.getType(), animalType.getType());
    }


    private void prepareAnimalType()
    {
        animalType = new AnimalType(1, "Pies", "Buldog");
    }

    private void prepareAnimalTypeFx()
    {
        animalTypeFx = new AnimalTypeFx();
        animalTypeFx.setIdAnimalType(1);
        animalTypeFx.setRace("Buldog");
        animalTypeFx.setType("Pies");
    }
}
