package animalHostel.gui.utils.converters;

import animalHostel.database.entity.Animal;
import animalHostel.database.entity.AnimalType;
import animalHostel.database.entity.Worker;
import animalHostel.gui.modelsFx.AnimalFx;
import animalHostel.gui.modelsFx.AnimalTypeFx;
import animalHostel.gui.modelsFx.WorkerFx;
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
@PrepareForTest({Utils.class, ConverterWorker.class, ConverterAnimalType.class})
public class ConverterAnimalGuiSpec
{
    @Test
    public void whenConvertToAnimalFxThenReturnAnimalFx()
    {
        Animal animal = fakeAnimal();

        AnimalTypeFx animalTypeFx = prepareAnimalTypeFxMock();

        WorkerFx workerFx = prepareWorkerFxMock();

        LocalDate localDate = prepareLocalDateMock();

        AnimalFx animalFx = ConverterAnimal.convertToAnimalFx(animal);

        Assert.assertEquals(animal.getId(), animalFx.getId());
        Assert.assertEquals(animal.getName(), animalFx.getName());
        Assert.assertEquals(animal.getColor(), animalFx.getColor());
        Assert.assertEquals(animal.getWeight(), animalFx.getWeight());
        Assert.assertEquals(animalTypeFx, animalFx.getAnimalTypeFx());
        Assert.assertEquals(localDate, animalFx.getBirthDay());
        Assert.assertEquals(localDate, animalFx.getDateOfRegister());
        Assert.assertEquals(animal.getSlot(), animalFx.getSlot());
        Assert.assertEquals(workerFx, animalFx.getPatron());
    }




    @Test
    public void whenConvertToAnimalThenReturnAnimal()
    {
        AnimalFx animalFx = prepareAnimalFx();

        Worker worker = prepareWorkerMock();

        AnimalType animalType = prepareAnimalTypeMock();

        Date date = prepareDateMock();

        Animal animal = ConverterAnimal.convertToAnimal(animalFx);

        Assert.assertEquals(animalFx.getId(), animal.getId());
        Assert.assertEquals(animalFx.getColor(), animal.getColor());
        Assert.assertEquals(animalFx.getName(), animal.getName());
        Assert.assertEquals(animalFx.getWeight(), animal.getWeight());
        Assert.assertEquals(worker, animal.getPatron());
        Assert.assertEquals(animalType, animal.getAnimalType());
        Assert.assertEquals(date, animal.getBirthDay());
        Assert.assertEquals(date, animal.getDateOfRegister());
    }

    private Animal fakeAnimal()
    {
        Animal animal = new Animal();
        animal.setId(1);
        animal.setName("Burek");
        animal.setAnimalType(new AnimalType());
        animal.setPatron(new Worker());
        animal.setBirthDay(new Date());
        animal.setColor("Bialy");
        animal.setWeight(20);
        animal.setDateOfRegister(new Date());
        animal.setSlot(2);
        return animal;
    }

    private AnimalTypeFx prepareAnimalTypeFxMock()
    {
        AnimalTypeFx animalTypeFx = new AnimalTypeFx();
        PowerMockito.mockStatic(ConverterAnimalType.class);
        PowerMockito.when(ConverterAnimalType.convertToAnimalTypeFx(any())).thenReturn(animalTypeFx);
        return animalTypeFx;
    }

    private WorkerFx prepareWorkerFxMock()
    {
        WorkerFx workerFx = new WorkerFx();
        PowerMockito.mockStatic(ConverterWorker.class);
        PowerMockito.when(ConverterWorker.convertToWorkerFx(any())).thenReturn(workerFx);
        return workerFx;
    }

    private LocalDate prepareLocalDateMock()
    {
        LocalDate localDate = LocalDate.now();
        PowerMockito.mockStatic(Utils.class);
        PowerMockito.when(Utils.convertToLocalDate(any())).thenReturn(localDate);
        return localDate;
    }

    private AnimalFx prepareAnimalFx()
    {
        AnimalFx animalFx = new AnimalFx();
        animalFx.setId(1);
        animalFx.setDateOfRegister(LocalDate.now());
        animalFx.setAnimalTypeFx(new AnimalTypeFx());
        animalFx.setPatron(new WorkerFx());
        animalFx.setColor("Bialy");
        animalFx.setName("Burek");
        animalFx.setBirthDay(LocalDate.now());
        animalFx.setWeight(20);
        return animalFx;
    }

    private Worker prepareWorkerMock()
    {
        Worker worker = new Worker();
        PowerMockito.mockStatic(ConverterWorker.class);
        PowerMockito.when(ConverterWorker.convertToWorker(any())).thenReturn(worker);
        return worker;
    }

    private AnimalType prepareAnimalTypeMock()
    {
        AnimalType animalType = new AnimalType();
        PowerMockito.mockStatic(ConverterAnimalType.class);
        PowerMockito.when(ConverterAnimalType.convertToAnimalType(any())).thenReturn(animalType);
        return animalType;
    }

    private Date prepareDateMock()
    {
        Date date = new Date();
        PowerMockito.mockStatic(Utils.class);
        PowerMockito.when(Utils.convertToDate(any())).thenReturn(date);
        return date;
    }
}
