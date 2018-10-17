package animalHostel.gui.controllers.mocks;

import animalHostel.gui.modelsFx.AnimalTypeFx;
import animalHostel.gui.modelsFx.AnimalTypeFxModel;
import org.powermock.api.mockito.PowerMockito;

public class AnimalTypeFxMocks
{


    public static AnimalTypeFx prepareAnimalTypeFxMock() throws Exception
    {
        AnimalTypeFx animalTypeFx = PowerMockito.spy(new AnimalTypeFx());
        PowerMockito.whenNew(AnimalTypeFx.class).withNoArguments().thenReturn(animalTypeFx);

        return  animalTypeFx;
    }

    public static AnimalTypeFxModel prepareAnimalTypeFxModelMock() throws Exception
    {
        AnimalTypeFxModel animalTypeFxModel = PowerMockito.spy(new AnimalTypeFxModel());
        PowerMockito.whenNew(AnimalTypeFxModel.class).withNoArguments().thenReturn(animalTypeFxModel);

        return animalTypeFxModel;
    }


}
