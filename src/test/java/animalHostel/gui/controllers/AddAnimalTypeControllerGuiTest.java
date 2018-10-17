package animalHostel.gui.controllers;


import animalHostel.gui.HostelSingleton;

import animalHostel.gui.controllers.mocks.AnimalTypeFxMocks;
import animalHostel.gui.controllers.mocks.DialogUtilsMock;
import animalHostel.gui.controllers.mocks.HostelAndDAOMocks;
import animalHostel.gui.controllers.utils.StageForTest;
import animalHostel.gui.modelsFx.AnimalTypeFx;
import animalHostel.gui.modelsFx.AnimalTypeFxModel;
import animalHostel.gui.utils.DialogUtils;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import org.testfx.matcher.base.NodeMatchers;



import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.testfx.api.FxAssert.verifyThat;


@RunWith(PowerMockRunner.class)
@PrepareForTest({HostelSingleton.class, AnimalTypeFx.class, AnimalTypeFxModel.class, DialogUtils.class, AddAnimalTypeController.class})
public class AddAnimalTypeControllerGuiTest extends StageForTest
{
    private static final String ADD_TYPE_TOGGLE_BUTTON = "#addTypeToggleButton";
    private static final String ADD_TYPE_BUTTON = "#addTypeButton";
    private static final String TYPE_TEXT_FIELD = "#typeTextField";
    private static final String RACE_TEXT_FIELD = "#raceTextField";


    private AnimalTypeFx animalTypeFx;
    private AnimalTypeFxModel animalTypeFxModel;

    private HostelAndDAOMocks hostelAndDAOMocks;


    @Before
    public void prepareMocks() throws Exception
    {
        hostelAndDAOMocks = new HostelAndDAOMocks();
        PowerMockito.when(hostelAndDAOMocks.getDao().insert(anyString())).thenReturn(true);

        animalTypeFx = AnimalTypeFxMocks.prepareAnimalTypeFxMock();
        animalTypeFxModel = AnimalTypeFxMocks.prepareAnimalTypeFxModelMock();

        DialogUtilsMock.prepareDialogUtilsMock();

        clickOn(ADD_TYPE_TOGGLE_BUTTON);
    }


    @Test
    public void whenClickOnAddAnimalTypeToggleButtonThenShowAddAnimalTypeForm()
    {
        verifyThat("#addAnimalTypeForm", NodeMatchers.isVisible());
    }

    @Test
    public void whenInAddAnimalTypeFormThenAddButtonIsDisabled()
    {
        verifyThat(ADD_TYPE_BUTTON, NodeMatchers.isDisabled());
    }

    @Test
    public void whenInAddAnimalTypeFormThenThereAreTextFieldForTypeAndRacePutting()
    {
        verifyThat(TYPE_TEXT_FIELD, NodeMatchers.isVisible());
        verifyThat(RACE_TEXT_FIELD, NodeMatchers.isVisible());
    }

    @Test
    public void whenPutTextIntoTypeTextFieldThenAddButtonIsDisabled()
    {
        clickOn(TYPE_TEXT_FIELD).write("Type");
        verifyThat(ADD_TYPE_BUTTON, NodeMatchers.isDisabled());
    }

    @Test
    public void whenPutTextIntoRaceTextFieldThenAddButtonIsDisabled()
    {
        clickOn(RACE_TEXT_FIELD).write("Race");
        verifyThat(ADD_TYPE_BUTTON, NodeMatchers.isDisabled());
    }

    @Test
    public void whenPutTextIntoTypeAndRaceTextFieldThenAddButtonIsEnabled()
    {
        clickOn(TYPE_TEXT_FIELD).write("Type");
        clickOn(RACE_TEXT_FIELD).write("Race");
        verifyThat(ADD_TYPE_BUTTON, NodeMatchers.isEnabled());
    }

    @Test
    public void whenPutTextIntoTypeAndRaceTextFieldThenTheItSetInAnimalTypeFx()
    {
        clickOn(TYPE_TEXT_FIELD).write("Type");
        clickOn(RACE_TEXT_FIELD).write("Race");

        Assert.assertEquals("Type", animalTypeFx.getType());
        Assert.assertEquals("Race", animalTypeFx.getRace());
    }

    @Test
    public void whenClickAddThenInvokeAddAnimalTypeFromAddAnimalTypeFxModel() throws SQLException
    {
        PowerMockito.doNothing().when(animalTypeFxModel).addAnimalType();

        clickOn(TYPE_TEXT_FIELD).write("Type");
        clickOn(RACE_TEXT_FIELD).write("Race");

        clickOn(ADD_TYPE_BUTTON);

        Mockito.verify(animalTypeFxModel).addAnimalType();
    }
}

