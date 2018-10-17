package animalHostel.gui.controllers;

import animalHostel.Hostel;
import animalHostel.database.DAO;
import animalHostel.database.entity.AnimalType;
import animalHostel.gui.HostelSingleton;
import animalHostel.gui.dialogs.AnimalTypeFind;
import animalHostel.gui.modelsFx.AddAnimalModel;
import animalHostel.gui.modelsFx.AnimalTypeFx;
import animalHostel.gui.modelsFx.AnimalTypeFxModel;
import animalHostel.gui.utils.DialogUtils;
import animalHostel.gui.utils.FXMLUtils;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.testfx.api.FxAssert.verifyThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HostelSingleton.class, AnimalTypeFind.class, DialogUtils.class,
        AddAnimalController.class, FindOrCreateAnimalTypeController.class, AnimalTypeFxModel.class, AddAnimalModel.class})
public class FindOrCreateAnimalTypeControllerGuiTestCopy extends ApplicationTest
{
    private static final String CHOOSE_ANIMAL_TYPE_BUTTON = "#chooseAnimalTypeButton";
    private static final String SEARCH_BUTTON = "#searchButton";
    private static final String TYPE_TEXT_FIELD = "#typeTextField";
    private static final String RACE_TEXT_FIELD = "#raceTextField";
    private static final String ADD_ANIMAL_TYPE_BUTTON = "#addAnimalTypeButton";
    private static final String ANIMAL_TYPE_TEXT_FIELD_ADD_ANIMAL_FORM = "#animalTypeTextField";
    private static final String ANIMAL_TYPES_TABLE = "#animalTypesTable";

    private Hostel hostel;
    private DAO dao;
    private AnimalTypeFxModel animalTypeFxModel;
    private AnimalType animalType;
    private AddAnimalModel addAnimalModel;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Scene mainWindow = new Scene(FXMLUtils.fxmlLoader("/fxml/MainWindow.fxml"));

        primaryStage.setScene(mainWindow);
        primaryStage.setTitle(FXMLUtils.getResourceBundle().getString("title.application"));

        primaryStage.show();
    }

    @Override
    public void stop() throws TimeoutException
    {
        FxToolkit.cleanupStages();
        FxToolkit.hideStage();
    }

    @Before
    public void prepareMocks() throws Exception
    {
        preparingMockHostelAndHostelSingleton();

        preparingMockDaoAndSetInHostel();

        PowerMockito.when(hostel.getFreeSlotsForAnimals()).thenReturn(11);

        preparingAnimalTypeFxModel();

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
        prepareAnimalTypeFxModelPutOnlyNewerOnListMehtod();
        clickOn(CHOOSE_ANIMAL_TYPE_BUTTON);
        putTextToTypeAndRaceTextField();
        clickOn(ADD_ANIMAL_TYPE_BUTTON);

        Mockito.verify(animalTypeFxModel).addAnimalType();
    }

    @Test
    public void whenAddAnimalTypeThenInvokePutOnlyNewOnList() throws Exception
    {
        prepareDialogUtilsMock();
        prepareAnimalTypeFxModelPutOnlyNewerOnListMehtod();
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
        prepareAnimalTypeFxModelPutOnlyNewerOnListMehtod();
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

        Node tableRow = getTableRowToSelect();
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

        Node tableRow = getTableRowToSelect();
        clickOn(tableRow);

        clickOn("#chooseButtonType");

        AnimalTypeFx actual = addAnimalModel.getAnimalFxObjectProperty().getAnimalTypeFx();
        Assert.assertEquals(animalType.getIdAnimalType(), actual.getIdAnimalType());
        Assert.assertEquals(animalType.getType(), actual.getType());
        Assert.assertEquals(animalType.getRace(), actual.getRace());
    }

    private void preparingMockHostelAndHostelSingleton() throws SQLException
    {
        hostel = PowerMockito.mock(Hostel.class);

        PowerMockito.mockStatic(HostelSingleton.class);
        PowerMockito.when(HostelSingleton.getHostel()).thenReturn(hostel);
    }

    private void preparingMockDaoAndSetInHostel()
    {
        dao = PowerMockito.mock(DAO.class);
        hostel.setDao(dao);
    }

    private void preparingAnimalTypeFxModel() throws Exception
    {
        animalTypeFxModel = PowerMockito.spy(new AnimalTypeFxModel());
        PowerMockito.whenNew(AnimalTypeFxModel.class).withNoArguments().thenReturn(animalTypeFxModel);
    }

    private void preparingMockAddAnimalModel() throws Exception
    {
        addAnimalModel = PowerMockito.spy(new AddAnimalModel());
        PowerMockito.whenNew(AddAnimalModel.class).withNoArguments().thenReturn(addAnimalModel);
    }

    private void preparingMockFindAnimalTypeByTypeOrRace() throws SQLException
    {
        List<AnimalType> animalTypeList = new ArrayList<>();
        animalType = new AnimalType(1, "Pies", "Buldog");
        animalTypeList.add(animalType);
        PowerMockito.when(hostel.findAnimalTypeByTypeOrRace(any(), any())).thenReturn(animalTypeList);
    }

    private Node getTableRowToSelect()
    {
        TableView tableView = (TableView) lookup(ANIMAL_TYPES_TABLE).query();
        List<Node> current = tableView.getChildrenUnmodifiable();

        while( current.size() == 1)
        {
            current = ( (Parent) current.get(0)).getChildrenUnmodifiable();
        }

        current = ( (Parent) current.get(1) ).getChildrenUnmodifiable();

        while (! (current.get(0) instanceof TableRow))
        {
            current = ( (Parent) current.get(0)).getChildrenUnmodifiable();
        }

        Node node = current.get(0);
        TableRow tableRow =(TableRow) node;
        return tableRow;
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
        PowerMockito.when(hostel.findAnimalTypeByTypeAndRace(any(), any())).thenReturn(new AnimalType(1, "Type", "Race"));
    }

    private void prepareAnimalTypeFxModelPutOnlyNewerOnListMehtod() throws SQLException
    {
        PowerMockito.doNothing().when(animalTypeFxModel).putOnlyNewerOnList();
    }

    private void prepareFindAnimalTypeByTypeOrRaceMethodMock() throws SQLException
    {
        PowerMockito.when(hostel.findAnimalTypeByTypeOrRace(any(), any())).thenReturn(new ArrayList<AnimalType>());
    }

    private void prepareDialogUtilsMock() throws Exception
    {
        PowerMockito.mockStatic(DialogUtils.class);
        PowerMockito.doNothing().when(DialogUtils.class, "infoDialog", any());
    }

    private void verifyInfoDialogWasCalled()
    {
        PowerMockito.verifyStatic(times(1));
        DialogUtils.infoDialog(any());
    }
}
