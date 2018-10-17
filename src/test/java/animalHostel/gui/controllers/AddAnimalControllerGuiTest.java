package animalHostel.gui.controllers;

import animalHostel.Hostel;
import animalHostel.database.DAO;
import animalHostel.gui.HostelSingleton;
import animalHostel.gui.controllers.mocks.HostelAndDAOMocks;
import animalHostel.gui.controllers.utils.StageForTest;
import animalHostel.gui.modelsFx.AddAnimalModel;
import animalHostel.gui.utils.DialogUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HostelSingleton.class, DialogUtils.class,AddAnimalModel.class, Hostel.class, DAO.class, AddAnimalController.class})
public class AddAnimalControllerGuiTest extends StageForTest
{
    private  static final String ADD_ANIMAL_BUTTON = "#addAnimalButton";

    private AddAnimalController addAnimalController;

    @Before
    public void before() throws Exception
    {
        HostelAndDAOMocks hostelAndDAOMocks = new HostelAndDAOMocks();
//        addAnimalController = PowerMockito.spy(new AddAnimalController());
//        PowerMockito.whenNew(AddAnimalController.class).withNoArguments().thenReturn(addAnimalController);

        AddAnimalModel addAnimalModel = PowerMockito.spy(new AddAnimalModel());
        PowerMockito.whenNew(AddAnimalModel.class).withNoArguments().thenReturn(addAnimalModel);

        PowerMockito.when(hostelAndDAOMocks.getHostel().getFreeSlotsForAnimals()).thenReturn(10);

        clickOn("#addNewAnimalToggleButton");
    }

    @Test
    public void whenOpenAddNewAnimalThenThereAreSixTextFieldsOneDataPickerThreeChooseButtonOneAndAddButton()
    {
        checkThereAreSixTextFields();
        checkThereIsDatePicker();
        checkThereAreThreeChooseButtons();
        checkThereIsOneAddAnimalButton();
    }

    @Test
    public void whenOpenAddAnimalFormThenAddAnimalButtonIsDisabled()
    {
        verifyThat(ADD_ANIMAL_BUTTON, NodeMatchers.isDisabled());
    }


    private void checkThereAreSixTextFields()
    {
        verifyThat("#animalNameTextField", NodeMatchers.isVisible());
        verifyThat("#animalTypeTextField", NodeMatchers.isVisible());
        verifyThat("#colorTextField", NodeMatchers.isVisible());
        verifyThat("#weightTextField", NodeMatchers.isVisible());
        verifyThat("#patronTextField", NodeMatchers.isVisible());
        verifyThat("#slotNumberTextField", NodeMatchers.isVisible());
    }

    private void checkThereIsDatePicker()
    {
        verifyThat("#birthdayDataPicker", NodeMatchers.isVisible());
    }

    private void checkThereAreThreeChooseButtons()
    {
        verifyThat("#chooseAnimalTypeButton", NodeMatchers.isVisible());
        verifyThat("#chooseSlotNumberButton", NodeMatchers.isVisible());
        verifyThat("#choosePatronButton", NodeMatchers.isVisible());
    }

    private void checkThereIsOneAddAnimalButton()
    {
        verifyThat(ADD_ANIMAL_BUTTON, NodeMatchers.isVisible());
    }
}
