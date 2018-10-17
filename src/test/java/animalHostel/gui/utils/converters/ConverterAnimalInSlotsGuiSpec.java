package animalHostel.gui.utils.converters;

import animalHostel.database.entity.*;
import animalHostel.gui.modelsFx.AnimalFx;
import animalHostel.gui.modelsFx.AnimalInSlotsFx;
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
@PrepareForTest({Utils.class,
                 ConverterAnimal.class,
                 ConverterSlotsForAnimal.class,
                 ConverterAnimalType.class,
                 ConverterWorker.class })
public class ConverterAnimalInSlotsGuiSpec
{
    @Test
    public void whenConvertToAnimalInSlotsThenReturnAnimalInSlots()
    {
        AnimalInSlotsFx animalInSLotsFx = prepareAnimalInSlotsFx();

        Animal animal = prepareAnimalMock();

        SlotsForAnimal slotsForAnimal = prepareSlotsForAnimalMock();

        prepareAnimalTypeMock();

        Date date = prepareDateAndMock();
        prepareWorkerAndMock();

        AnimalInSlots animalInSlots = ConverterAnimalInSlots.convertToAnimalInSlots(animalInSLotsFx);

        Assert.assertEquals(animalInSLotsFx.getIdAnimalInSlot(), animalInSlots.getIdAnimalInSlot());
        Assert.assertEquals(animal, animalInSlots.getAnimal());
        Assert.assertEquals(slotsForAnimal, animalInSlots.getSlotsForAnimal());
        Assert.assertEquals(date, animalInSlots.getDateOfIn());
        Assert.assertEquals(date, animalInSlots.getDateOfOut());
    }

    @Test
    public void whenConvertToAnimalInSlotsFxThenReturnAnimalInSlotsFx()
    {
        AnimalInSlots animalInSlots = prepareAnimalInSlots();

        AnimalFx animalFx = prepareAnimalFxMock();

        LocalDate localDate = prepareLocalDateMock();

        SlotsForAnimalFx slotsForAnimalFx = prepareSlotsForAnimalFxMock();

        prepareAnimalTypeFxMock();

        AnimalInSlotsFx animalInSlotsFx = ConverterAnimalInSlots.convertToAnimalInSlotsFx(animalInSlots);

        Assert.assertEquals(animalInSlots.getIdAnimalInSlot(), animalInSlotsFx.getIdAnimalInSlot());
        Assert.assertEquals(slotsForAnimalFx, animalInSlotsFx.getSlotsForAnimal());
        Assert.assertEquals(animalFx, animalInSlotsFx.getAnimal());
        Assert.assertEquals(localDate, animalInSlotsFx.getDateOfIn());
        Assert.assertEquals(localDate, animalInSlotsFx.getDateOfOut());
    }


    private AnimalInSlotsFx prepareAnimalInSlotsFx()
    {
        AnimalInSlotsFx animalInSLotsFx = new AnimalInSlotsFx();
        animalInSLotsFx.setAnimal(new AnimalFx());
        animalInSLotsFx.setDateOfIn(LocalDate.now());
        animalInSLotsFx.setSlotsForAnimal(new SlotsForAnimalFx());
        animalInSLotsFx.setIdAnimalInSlot(1);
        animalInSLotsFx.setDateOfOut(LocalDate.now());
        return animalInSLotsFx;
    }

    private Animal prepareAnimalMock()
    {
        Animal animal = new Animal();
        PowerMockito.mockStatic(ConverterAnimal.class);
        PowerMockito.when(ConverterAnimal.convertToAnimal(any())).thenReturn(animal);
        return animal;
    }

    private SlotsForAnimal prepareSlotsForAnimalMock()
    {
        SlotsForAnimal slotsForAnimal = new SlotsForAnimal();

        PowerMockito.mockStatic(ConverterSlotsForAnimal.class);
        PowerMockito.when(ConverterSlotsForAnimal.convertToSlotsForAnimal(any())).thenReturn(slotsForAnimal);
        return slotsForAnimal;
    }

    private void prepareAnimalTypeMock()
    {
        AnimalType animalType = new AnimalType();
        PowerMockito.mockStatic(ConverterAnimalType.class);
        PowerMockito.when(ConverterAnimalType.convertToAnimalType(any())).thenReturn(animalType);
    }

    private Date prepareDateAndMock()
    {
        Date date = new Date();
        PowerMockito.mockStatic(Utils.class);
        PowerMockito.when(Utils.convertToDate(any())).thenReturn(date);
        return date;
    }

    private void prepareWorkerAndMock()
    {
        Worker worker = new Worker();
        PowerMockito.mockStatic(ConverterWorker.class);
        PowerMockito.when(ConverterWorker.convertToWorker(any())).thenReturn(worker);
    }


    private AnimalInSlots prepareAnimalInSlots()
    {
        AnimalInSlots animalInSlots = new AnimalInSlots();
        animalInSlots.setIdAnimalInSlot(1);
        animalInSlots.setAnimal(new Animal());
        animalInSlots.setDateOfOut(new Date());
        animalInSlots.setDateOfIn(new Date());
        animalInSlots.setSlotsForAnimal(new SlotsForAnimal());
        return animalInSlots;
    }

    private AnimalFx prepareAnimalFxMock()
    {
        AnimalFx animalFx = new AnimalFx();
        PowerMockito.mockStatic(ConverterAnimal.class);
        PowerMockito.when(ConverterAnimal.convertToAnimalFx(any())).thenReturn(animalFx);
        return animalFx;
    }

    private LocalDate prepareLocalDateMock()
    {
        LocalDate localDate = LocalDate.now();
        PowerMockito.mockStatic(Utils.class);
        PowerMockito.when(Utils.convertToLocalDate(any())).thenReturn(localDate);
        return localDate;
    }

    private SlotsForAnimalFx prepareSlotsForAnimalFxMock()
    {
        SlotsForAnimalFx slotsForAnimalFx = new SlotsForAnimalFx();
        PowerMockito.mockStatic(ConverterSlotsForAnimal.class);
        PowerMockito.when(ConverterSlotsForAnimal.convertToSlotsForAnimalFx(any())).thenReturn(slotsForAnimalFx);
        return slotsForAnimalFx;
    }

    private void prepareAnimalTypeFxMock()
    {
        AnimalTypeFx animalTypeFx = new AnimalTypeFx();
        PowerMockito.mockStatic(ConverterAnimalType.class);
        PowerMockito.when(ConverterAnimalType.convertToAnimalTypeFx(any())).thenReturn(animalTypeFx);
    }
}