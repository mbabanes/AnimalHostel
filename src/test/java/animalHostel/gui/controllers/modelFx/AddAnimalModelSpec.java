package animalHostel.gui.controllers.modelFx;

import animalHostel.database.DAO;
import animalHostel.gui.HostelSingleton;
import animalHostel.gui.controllers.mocks.AddAnimalModelMock;
import animalHostel.gui.controllers.mocks.HostelAndDAOMocks;
import animalHostel.gui.modelsFx.*;
import animalHostel.gui.utils.converters.ConverterAnimal;
import animalHostel.gui.utils.converters.ConverterAnimalInSlots;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HostelSingleton.class,
                AddAnimalModel.class,
                DAO.class,
                ConverterAnimal.class,
                ConverterAnimalInSlots.class,
                LocalDate.class
                })
public class AddAnimalModelSpec
{
    private AddAnimalModel addAnimalModel;
    private HostelAndDAOMocks hostelAndDAOMocks;

    @Before
    public void before() throws Exception
    {
        addAnimalModel = AddAnimalModelMock.preparingSpyAddAnimalModel();
        hostelAndDAOMocks = new HostelAndDAOMocks();

        addAnimalModel.setAnimalFxObjectProperty( fakeAnimalFx() );
        PowerMockito.when(hostelAndDAOMocks.getHostel().addAnimal(any())).thenReturn(1);

        addAnimalModel.setAnimalInSlotsFxObjectProperty(fakeAnimalInSlotsFx());
        PowerMockito.when(hostelAndDAOMocks.getHostel().addAnimalIntoSlotForAnimals(any())).thenReturn(true);
    }

    @Test
    public void whenAddAnimalThenInvokeAddAnimalFromHostelObject() throws SQLException
    {
        addAnimalModel.addAnimal();

        Mockito.verify(hostelAndDAOMocks.getHostel()).addAnimal(any());
    }

    @Test
    public void whenAddAnimalThenInvokeConverterToAnimal() throws SQLException
    {
        PowerMockito.mockStatic(ConverterAnimal.class);

        addAnimalModel.addAnimal();

        PowerMockito.verifyStatic(times(1));
        ConverterAnimal.convertToAnimal(addAnimalModel.getAnimalFxObjectProperty());
    }

    @Test
    public void whenAddAnimalThenIdAnimalIsSetAndAnimalIsSetIntoAnimalInSlotFx() throws SQLException
    {
        addAnimalModel.addAnimal();
        Assert.assertEquals(1, addAnimalModel.getAnimalFxObjectProperty().getId());
        Assert.assertEquals(addAnimalModel.getAnimalFxObjectProperty(), addAnimalModel.getAnimalInSlotsFxObjectProperty().getAnimal());
    }

    @Test
    public void whenAddAnimalThenDataOfRegisterIsSet() throws Exception
    {
        addAnimalModel.addAnimal();
        Assert.assertNotNull(addAnimalModel.getAnimalFxObjectProperty().getDateOfRegister());
    }


    @Test
    public void whenAddAnimalIntoSlotThenInvokeAddAnimalIntoSlotForAnimalsFromHostel() throws SQLException
    {
        addAnimalModel.addAnimalInSlot();
        Mockito.verify(hostelAndDAOMocks.getHostel()).addAnimalIntoSlotForAnimals(any());
    }

    @Test
    public void whenAddAnimalIntoSlotThenInvokeConverterToAnimalInSlot() throws SQLException
    {
        PowerMockito.mockStatic(ConverterAnimalInSlots.class);

        addAnimalModel.addAnimalInSlot();
        PowerMockito.verifyStatic(times(1));
        ConverterAnimalInSlots.convertToAnimalInSlots(addAnimalModel.getAnimalInSlotsFxObjectProperty());
    }

    @Test
    public void whenAddAnimalIntoSlotThenDateOfInIsSet() throws SQLException
    {
        addAnimalModel.addAnimalInSlot();
        Assert.assertNotNull(addAnimalModel.getAnimalInSlotsFxObjectProperty().getDateOfIn());
    }

    private AnimalInSlotsFx fakeAnimalInSlotsFx()
    {
        AnimalInSlotsFx fakeAnimalInSlotsFx = new AnimalInSlotsFx();
        fakeAnimalInSlotsFx.setAnimal(fakeAnimalFx());

        SlotsForAnimalFx fakeSlotsForAnimalFx = new SlotsForAnimalFx();
        fakeSlotsForAnimalFx.setIdSlotForAnimal(1);
        fakeAnimalInSlotsFx.setSlotsForAnimal(fakeSlotsForAnimalFx);

        return  fakeAnimalInSlotsFx;
    }


    private AnimalFx fakeAnimalFx()
    {
        AnimalFx fakeAnimalFx = new AnimalFx();

        WorkerFx workerFx = new WorkerFx();
        workerFx.setIdWorker(1);
        fakeAnimalFx.setPatron(workerFx);

        AnimalTypeFx animalTypeFx = new AnimalTypeFx();
        animalTypeFx.setIdAnimalType(1);
        fakeAnimalFx.setAnimalTypeFx(animalTypeFx);

        return fakeAnimalFx;
    }
}
