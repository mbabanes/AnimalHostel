package animalHostel.gui.controllers.mocks;

import animalHostel.gui.modelsFx.AddAnimalModel;
import org.powermock.api.mockito.PowerMockito;

public class AddAnimalModelMock
{
    public static AddAnimalModel preparingSpyAddAnimalModel() throws Exception
    {
        AddAnimalModel addAnimalModel = PowerMockito.spy(new AddAnimalModel());
        PowerMockito.whenNew(AddAnimalModel.class).withNoArguments().thenReturn(addAnimalModel);

        return addAnimalModel;
    }
}
