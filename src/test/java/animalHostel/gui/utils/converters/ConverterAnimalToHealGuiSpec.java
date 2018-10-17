package animalHostel.gui.utils.converters;

import animalHostel.database.entity.AnimalToHeal;
import animalHostel.gui.modelsFx.AnimalFx;
import animalHostel.gui.modelsFx.AnimalToHealFx;
import animalHostel.gui.utils.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.util.Date;

import static animalHostel.gui.utils.converters.ConverterAnimalToHeal.convertToAnimalToHeal;
import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Utils.class)
public class ConverterAnimalToHealGuiSpec
{
    @Test
    public void whenConvertToAnimalToHealThenReturnThat()
    {
        AnimalToHealFx fakeAnimalToHealFx = new AnimalToHealFx();
        fakeAnimalToHealFx.setIdAnimalToHeal(1);
        fakeAnimalToHealFx.setSymptoms("symptoms");
        fakeAnimalToHealFx.setDateOfRegister(LocalDate.now());
        fakeAnimalToHealFx.setDone(false);


        Date expectedDate = new Date();
        PowerMockito.mockStatic(Utils.class);
        PowerMockito.when(Utils.convertToDate(any())).thenReturn(expectedDate);

        AnimalFx fakeAnimalFx = new AnimalFx();
        fakeAnimalFx.setId(1);
        fakeAnimalFx.setName("Name");
        fakeAnimalToHealFx.setAnimal(fakeAnimalFx);

        AnimalToHeal animalToHeal = ConverterAnimalToHeal.convertToAnimalToHeal(fakeAnimalToHealFx);

        Assert.assertEquals(fakeAnimalToHealFx.getIdAnimalToHeal(), animalToHeal.getIdAnimalToHeal());
        Assert.assertEquals(fakeAnimalToHealFx.getSymptoms(), animalToHeal.getSymptoms());
        Assert.assertEquals(expectedDate, animalToHeal.getDateOfRegister());
        Assert.assertEquals(fakeAnimalToHealFx.isDone(), animalToHeal.isDone());
        Assert.assertNotNull(animalToHeal.getAnimal());

    }
}
