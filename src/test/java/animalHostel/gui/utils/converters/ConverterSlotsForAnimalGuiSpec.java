package animalHostel.gui.utils.converters;

import animalHostel.database.entity.AnimalType;
import animalHostel.database.entity.SlotsForAnimal;
import animalHostel.gui.modelsFx.AnimalTypeFx;
import animalHostel.gui.modelsFx.SlotsForAnimalFx;
import animalHostel.gui.utils.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AnimalType.class, ConverterAnimalType.class, Utils.class})
public class ConverterSlotsForAnimalGuiSpec
{
    @Test
    public void whenConvertToSlotsForAnimalFxThenReturnSlotsForAnimalFx() throws Exception
    {
        AnimalTypeFx animalTypeFx = prepareAnimalTypeFxAndMock();

        LocalDate localDate = prepareLocalDateAndMock();

        SlotsForAnimal slotsForAnimal = prepareSlotsForAnimal();

        SlotsForAnimalFx slotsForAnimalFx = ConverterSlotsForAnimal.convertToSlotsForAnimalFx(slotsForAnimal);

        Assert.assertEquals(slotsForAnimal.getIdSlotForAnimal(), slotsForAnimalFx.getIdSlotForAnimal());
        Assert.assertEquals(animalTypeFx, slotsForAnimalFx.getAnimalType());
        Assert.assertEquals(localDate, slotsForAnimalFx.getDateOfOpen() );
        Assert.assertEquals(slotsForAnimal.isFree(), slotsForAnimalFx.isFree());
        Assert.assertEquals(slotsForAnimal.getArea(), slotsForAnimalFx.getArea());
        Assert.assertEquals(slotsForAnimal.isInside(), slotsForAnimalFx.isInside());
        Assert.assertEquals(slotsForAnimal.getHeight(), slotsForAnimalFx.getHeight());
    }

    @Test
    public void whenConvertToSlotsForAnimalThenReturnSlotsForAnimal()
    {
        SlotsForAnimalFx slotsForAnimalFx = prepareSlotsForAnimalFx();

        Date date = prepareDateAndMock();

        AnimalType animalType = prepareAnimalTypeAndMock();

        SlotsForAnimal slotsForAnimal = ConverterSlotsForAnimal.convertToSlotsForAnimal(slotsForAnimalFx);

        Assert.assertEquals(slotsForAnimalFx.getIdSlotForAnimal(), slotsForAnimal.getIdSlotForAnimal());
        Assert.assertEquals(slotsForAnimalFx.isInside(), slotsForAnimal.isInside());
        Assert.assertEquals(slotsForAnimalFx.getArea(), slotsForAnimal.getArea());
        Assert.assertEquals(slotsForAnimalFx.getHeight(), slotsForAnimal.getHeight());
        Assert.assertEquals(slotsForAnimalFx.isFree(), slotsForAnimal.isFree());
        Assert.assertEquals(animalType, slotsForAnimal.getAnimalType());
        Assert.assertEquals(date, slotsForAnimal.getDateOfOpen());
    }



    private AnimalTypeFx prepareAnimalTypeFxAndMock() throws Exception
    {
        AnimalTypeFx animalTypeFx = new AnimalTypeFx();
        animalTypeFx.setIdAnimalType(1);
        animalTypeFx.setType("Pies");
        animalTypeFx.setRace("Buldog");
        PowerMockito.mockStatic(ConverterAnimalType.class);
        PowerMockito.doReturn(animalTypeFx).when(ConverterAnimalType.class, "convertToAnimalTypeFx", any());
        return animalTypeFx;
    }

    private SlotsForAnimal prepareSlotsForAnimal()
    {
        return new SlotsForAnimal(1, true, new Date(),  new AnimalType(), 10, true, 2);
    }

    private LocalDate prepareLocalDateAndMock() throws Exception
    {
        PowerMockito.mockStatic(Utils.class);
        LocalDate localDate = LocalDate.now();
        PowerMockito.doReturn(localDate).when(Utils.class, "convertToLocalDate", any());
        return localDate;
    }


    private SlotsForAnimalFx prepareSlotsForAnimalFx()
    {
        SlotsForAnimalFx slotsForAnimalFx = new SlotsForAnimalFx();
        slotsForAnimalFx.setIdSlotForAnimal(1);
        slotsForAnimalFx.setInside(true);
        slotsForAnimalFx.setFree(true);
        slotsForAnimalFx.setDateOfOpen(LocalDate.now());
        slotsForAnimalFx.setHeight(2);
        slotsForAnimalFx.setArea(10);
        slotsForAnimalFx.setAnimalType(new AnimalTypeFx());
        return slotsForAnimalFx;
    }

    private Date prepareDateAndMock()
    {
        Date date = new Date();
        PowerMockito.mockStatic(Utils.class);
        PowerMockito.when(Utils.convertToDate(any())).thenReturn(date);
        return date;
    }

    private AnimalType prepareAnimalTypeAndMock()
    {
        AnimalType animalType = new AnimalType();
        animalType.setIdAnimalType(1);
        animalType.setType("Pies");
        animalType.setRace("Buldog");

        PowerMockito.mockStatic(ConverterAnimalType.class);
        PowerMockito.when(ConverterAnimalType.convertToAnimalType(any())).thenReturn(animalType);
        return animalType;
    }
}
