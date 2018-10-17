package animalHostel.gui.modelsFx;

import animalHostel.database.entity.Animal;
import animalHostel.gui.HostelSingleton;
import animalHostel.gui.controllers.mocks.HostelAndDAOMocks;
import animalHostel.gui.utils.converters.ConverterAnimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;


@RunWith(PowerMockRunner.class)
@PrepareForTest({HostelSingleton.class, AddAnimalToHealModel.class, ConverterAnimal.class})
public class AddAnimalToHealModelTest
{
    private AddAnimalToHealModel addAnimalToHealModel;
    HostelAndDAOMocks hostelAndDAOMocks;


    @Before
    public void before() throws SQLException
    {
        hostelAndDAOMocks  = new HostelAndDAOMocks();
        addAnimalToHealModel = new AddAnimalToHealModel();

        addAnimalToHealModel.setAnimalToHealModelObjectProperty(fakeAnimalToFx());
        PowerMockito.when(hostelAndDAOMocks.getHostel().addAnimalToHeal(any(), any())).thenReturn(true);
    }



    @Test
    public void whenAddAnimalToHealThenInvokeAddAnimalToHeal() throws SQLException
    {
        addAnimalToHealModel.addAnimalToHeal();

        Mockito.verify(hostelAndDAOMocks.getHostel()).addAnimalToHeal(any(), any());
    }


    @Test
    public void whenAddAnimalToHealThenInvokeConverterToAnimal() throws SQLException
    {
        PowerMockito.mockStatic(ConverterAnimal.class);

        addAnimalToHealModel.addAnimalToHeal();
        PowerMockito.verifyStatic(times(1));
        ConverterAnimal.convertToAnimal(addAnimalToHealModel.getAnimalToHealModelObjectProperty().getAnimal());
    }


    private AnimalToHealFx fakeAnimalToFx()
    {
        AnimalToHealFx fakeAnimalToHealFx = new AnimalToHealFx();
        fakeAnimalToHealFx.setSymptoms("symptomos");

        AnimalFx fakeAnimalFx = new AnimalFx();
        fakeAnimalFx.setId(1);
        fakeAnimalToHealFx.setAnimal(fakeAnimalFx);
        return fakeAnimalToHealFx;
    }
}