package animalHostel.gui.controllers;

import animalHostel.database.entity.AnimalType;
import animalHostel.gui.HostelSingleton;
import animalHostel.gui.controllers.mocks.*;
import animalHostel.gui.controllers.utils.StageForTest;
import animalHostel.gui.controllers.utils.TableViewUtil;
import animalHostel.gui.dialogs.AnimalTypeFind;

import animalHostel.gui.modelsFx.AddAnimalModel;
import animalHostel.gui.modelsFx.AnimalTypeFx;
import animalHostel.gui.modelsFx.AnimalTypeFxModel;
import animalHostel.gui.utils.DialogUtils;

import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.testfx.matcher.base.NodeMatchers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.testfx.api.FxAssert.verifyThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HostelSingleton.class, AnimalTypeFind.class, DialogUtils.class,
        AddAnimalController.class, FindOrCreateAnimalTypeController.class, AnimalTypeFxModel.class, AddAnimalModel.class})
public class FindOrCreateAnimalTypeControllerGuiTest extends StageForTest
{
    private static final String CHOOSE_ANIMAL_TYPE_BUTTON = "#chooseAnimalTypeButton";
    private static final String SEARCH_BUTTON = "#searchButton";
    private static final String TYPE_TEXT_FIELD = "#typeTextField";
    private static final String RACE_TEXT_FIELD = "#raceTextField";
    private static final String ADD_ANIMAL_TYPE_BUTTON = "#addAnimalTypeButton";
    private static final String ANIMAL_TYPE_TEXT_FIELD_ADD_ANIMAL_FORM = "#animalTypeTextField";
    private static final String ANIMAL_TYPES_TABLE = "#animalTypesTable";

    private AnimalTypeFxModel animalTypeFxModel;
    private AnimalType animalType;
    private AddAnimalModel addAnimalModel;

    private HostelAndDAOMocks hostelAndDAOMocks;


    @Before
    public void prepareMocks() throws Exception
    {
        hostelAndDAOMocks = new HostelAndDAOMocks();

        PowerMockito.when(hostelAndDAOMocks.getHostel().getFreeSlotsForAnimals()).thenReturn(11);

        animalTypeFxModel = AnimalTypeFxMocks.prepareAnimalTypeFxModelMock();

        preparingMockAddAnimalModel();

        preparingMockFindAnimalTypeByTypeOrRace();

        clickOn("#addNewAnimalToggleButton");
    }

    @Test
    public void whenClickOnChooseAnimalTypeButtonThenInvokeDialog() throws Exception
    {
        AnimalTypeFind animalTypeFind = prepareAnimalTypeFindMock();

        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);
        Mockito.verify(animalTypeFind).showAndGetResult();
    }


    @Test
    public void whenInAnimalTypeTextFieldAndReleasedF2KeyThenInvokeDialog() throws Exception
    {
        AnimalTypeFind animalTypeFind = prepareAnimalTypeFindMock();

        clickOn(ANIMAL_TYPE_TEXT_FIELD_ADD_ANIMAL_FORM).type(KeyCode.F2).release(KeyCode.F2);
        //clickOn("#typeTextField").release(KeyCode.F2);
        Mockito.verify(animalTypeFind).showAndGetResult();
    }

    @Test
    public void whenClickChooseAnimalTypeButtonThenOpenDialogWithTableView() throws Exception
    {
        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);
        verifyThat(ANIMAL_TYPES_TABLE, NodeMatchers.isNotNull());
    }


    @Test
    public void whenClickChooseAnimalTypeButtonThenThereAreTypeLabelTypeTextFieldRaceLabelRaceTextFieldSearchButton()
    {
        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);
        verifyThat("#typeLabel", NodeMatchers.isNotNull());
        verifyThat(TYPE_TEXT_FIELD, NodeMatchers.isNotNull());
        verifyThat("#raceLabel", NodeMatchers.isNotNull());
        verifyThat(RACE_TEXT_FIELD, NodeMatchers.isNotNull());
        verifyThat(SEARCH_BUTTON, NodeMatchers.isNotNull());
    }


    @Test
    public void whenClickChooseAnimalTypeButtonThenSearchButtonIsDisabled()
    {
        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);
        verifyThat(SEARCH_BUTTON, NodeMatchers.isDisabled());
    }

    @Test
    public void whenOpenFindTypeDialogThenAddButtonIsDisabled()
    {
        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);
        verifyThat(ADD_ANIMAL_TYPE_BUTTON, NodeMatchers.isDisabled());
    }

    @Test
    public void whenPutTextInTypeTextFieldAndRaceTextFieldThenAddAnimalTypeButtonIsEnabled()
    {
        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);
        putTextToTypeAndRaceTextField();
        verifyThat(ADD_ANIMAL_TYPE_BUTTON, NodeMatchers.isEnabled());
    }

    @Test
    public void whenAddAnimalTypeThenInvokeAddAnimalType() throws Exception
    {
        prepareDialogUtilsMock();
        prepareAnimalTypeFxModelPutOnlyNewerOnListMethod();
        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);
        putTextToTypeAndRaceTextField();
        clickOn(ADD_ANIMAL_TYPE_BUTTON);

        Mockito.verify(animalTypeFxModel).addAnimalType();
    }

    @Test
    public void whenAddAnimalTypeThenInvokePutOnlyNewOnList() throws Exception
    {
        prepareDialogUtilsMock();
        prepareAnimalTypeFxModelPutOnlyNewerOnListMethod();
        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);
        putTextToTypeAndRaceTextField();
        clickOn(ADD_ANIMAL_TYPE_BUTTON);

        Mockito.verify(animalTypeFxModel).putOnlyNewerOnList();
    }

    @Test
    public void whenAddAnimalTypeThenThatTypeIsInTableView() throws Exception
    {
        prepareDialogUtilsMock();
        prepareMockFindAnimalTypeByTypeAndRaceFromHostel();

        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);

        putTextToTypeAndRaceTextField();
        clickOn(ADD_ANIMAL_TYPE_BUTTON);

        verifyThat(ANIMAL_TYPES_TABLE, (TableView tableView) ->{
            return (tableView.getItems().size() == 1);
        });
    }

    @Test
    public void whenPutTextInTypeTextFieldThenSearchButtonIsEnabled()
    {
        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);
        clickOn(TYPE_TEXT_FIELD).write("Type");

        verifyThat(SEARCH_BUTTON, (Button button) -> {
            return !button.isDisable();
        });
    }

    @Test
    public void whenPutTextInRaceTextFieldThenSearchButtonIsEnabled()
    {
        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);
        clickOn(RACE_TEXT_FIELD).write("Race");

        verifyThat(SEARCH_BUTTON, (Button button) -> {
            return !button.isDisable();
        });
    }

    @Test
    public void whenSearchThenTableViewIsFill() throws SQLException
    {
        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);

        putTextToTypeAndRaceTextField();

        clickOn(SEARCH_BUTTON);

        verifyThat(ANIMAL_TYPES_TABLE, (TableView tableView) -> {
            return !tableView.getItems().isEmpty();
        });
    }

    @Test
    public void whenAnimalTypesNotFoundThenInvokeDialogInfo() throws Exception
    {
        prepareDialogUtilsMock();
        prepareAnimalTypeFxModelPutOnlyNewerOnListMethod();
        prepareFindAnimalTypeByTypeOrRaceMethodMock();

        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);

        putTextToTypeAndRaceTextField();

        clickOn(SEARCH_BUTTON);

        verifyInfoDialogWasCalled();
    }


    @Test
    public void whenChooseAnimalTypeThenInAnimalTypeTextFieldIsThat() throws Exception
    {
        prepareDialogUtilsMock();

        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);

        putTextToTypeAndRaceTextField();
        clickOn(SEARCH_BUTTON);
        TableView tableView = (TableView) lookup(ANIMAL_TYPES_TABLE).query();

        TableRow tableRow = TableViewUtil.getTableRowToSelect(tableView);
        clickOn(tableRow);

        clickOn("#chooseButtonType");

        verifyThat(ANIMAL_TYPE_TEXT_FIELD_ADD_ANIMAL_FORM, (TextField textField)->
        {
            String text = textField.getText();
            return text.contains(animalType.getType() + " - " + animalType.getRace());
        });
    }

    @Test
    public void whenChooseAnimalTypeFromDialogThenInAnimalTypeFxInAddAnimalModelIsThat() throws Exception
    {
        prepareDialogUtilsMock();

        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);

        putTextToTypeAndRaceTextField();
        clickOn(SEARCH_BUTTON);

        TableView tableView = (TableView) lookup(ANIMAL_TYPES_TABLE).query();

        TableRow tableRow = TableViewUtil.getTableRowToSelect(tableView);
        clickOn(tableRow);

        clickOn("#chooseButtonType");

        AnimalTypeFx actual = addAnimalModel.getAnimalFxObjectProperty().getAnimalTypeFx();
        Assert.assertEquals(animalType.getIdAnimalType(), actual.getIdAnimalType());
        Assert.assertEquals(animalType.getType(), actual.getType());
        Assert.assertEquals(animalType.getRace(), actual.getRace());
    }


    private void preparingMockAddAnimalModel() throws Exception
    {
        addAnimalModel = AddAnimalModelMock.preparingSpyAddAnimalModel();
    }

    private void preparingMockFindAnimalTypeByTypeOrRace() throws SQLException
    {
        List<AnimalType> animalTypeList = new ArrayList<>();
        animalType = new AnimalType(1, "Pies", "Buldog");
        animalTypeList.add(animalType);
        PowerMockito.when(hostelAndDAOMocks.getHostel().findAnimalTypeByTypeOrRace(any(), any())).thenReturn(animalTypeList);
    }

    private AnimalTypeFind prepareAnimalTypeFindMock() throws Exception
    {
        AnimalTypeFind animalTypeFind = PowerMockito.mock(AnimalTypeFind.class);
        PowerMockito.whenNew(AnimalTypeFind.class).withNoArguments().thenReturn(animalTypeFind);
        return animalTypeFind;
    }

    private void putTextToTypeAndRaceTextField()
    {
        clickOn(TYPE_TEXT_FIELD).write("Pies");
        clickOn(RACE_TEXT_FIELD).write("Buldog");
    }

    private void prepareMockFindAnimalTypeByTypeAndRaceFromHostel() throws SQLException
    {
        PowerMockito.when(hostelAndDAOMocks.getHostel().findAnimalTypeByTypeAndRace(any(), any())).thenReturn(new AnimalType(1, "Type", "Race"));
    }

    private void prepareAnimalTypeFxModelPutOnlyNewerOnListMethod() throws SQLException
    {
        PowerMockito.doNothing().when(animalTypeFxModel).putOnlyNewerOnList();
    }

    private void prepareFindAnimalTypeByTypeOrRaceMethodMock() throws SQLException
    {
        PowerMockito.when(hostelAndDAOMocks.getHostel().findAnimalTypeByTypeOrRace(any(), any())).thenReturn(new ArrayList<AnimalType>());
    }

    private void prepareDialogUtilsMock() throws Exception
    {
        DialogUtilsMock.prepareDialogUtilsMock();
    }

    private void verifyInfoDialogWasCalled()
    {
        PowerMockito.verifyStatic(times(1));
        DialogUtils.infoDialog(any());
    }
}
