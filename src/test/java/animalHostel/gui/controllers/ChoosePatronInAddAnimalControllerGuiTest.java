package animalHostel.gui.controllers;

import animalHostel.database.entity.Worker;
import animalHostel.gui.HostelSingleton;
import animalHostel.gui.controllers.utils.StageForTest;
import animalHostel.gui.controllers.mocks.HostelAndDAOMocks;
import animalHostel.gui.controllers.utils.TableViewUtil;
import animalHostel.gui.dialogs.DialogWithTable;
import animalHostel.gui.dialogs.PatronFind;
import animalHostel.gui.modelsFx.AddAnimalModel;
import animalHostel.gui.modelsFx.WorkerModel;
import animalHostel.gui.utils.DialogUtils;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

import static org.testfx.api.FxAssert.verifyThat;


@RunWith(PowerMockRunner.class)
@PrepareForTest({HostelSingleton.class, DialogUtils.class,
        AddAnimalController.class, AddAnimalModel.class, PatronFind.class, DialogWithTable.class,
        ChoosePatronInAddAnimalController.class, WorkerModel.class})
public class ChoosePatronInAddAnimalControllerGuiTest extends StageForTest
{
    private static final String CHOOSE_PATRON_BUTTON = "#choosePatronButton";
    private static final String PATRONS_TABLE_VIEW = "#patronsTableView";


    private HostelAndDAOMocks hostelAndDAOMocks;
    private WorkerModel workerModel;
    AddAnimalModel addAnimalModel = PowerMockito.spy(new AddAnimalModel());


    @Before
    public void prepareMocks() throws Exception
    {
        hostelAndDAOMocks = new HostelAndDAOMocks();

        PowerMockito.when(hostelAndDAOMocks.getHostel().getFreeSlotsForAnimals()).thenReturn(11);
        PowerMockito.whenNew(AddAnimalModel.class).withNoArguments().thenReturn(addAnimalModel);

        workerModel = PowerMockito.spy(new WorkerModel());
        PowerMockito.whenNew(WorkerModel.class).withNoArguments().thenReturn(workerModel);


        clickOn("#addNewAnimalToggleButton");
    }




    @Test
    public void whenClickOnChoosePatronButtonThenInvokeShowAndGetResultFromPatronFindInAddAnimalController() throws Exception
    {
        PatronFind patronFind = PowerMockito.mock(PatronFind.class);
        PowerMockito.whenNew(PatronFind.class).withNoArguments().thenReturn(patronFind);
        clickOn(CHOOSE_PATRON_BUTTON);

        Mockito.verify(patronFind).showAndGetResult();
    }

    @Test
    public void whenClickOnChoosePatronButtonThenShowChoosePatronDialog()
    {
        clickOn(CHOOSE_PATRON_BUTTON);

        verifyThat("#choosePatronDialog", NodeMatchers.isVisible());
    }

    @Test
    public void whenReleaseF2KeyOnChoosePatronTextFieldThenShowDialog()
    {
        clickOn("#patronTextField").type(KeyCode.F2);

        verifyThat("#choosePatronDialog", NodeMatchers.isVisible());
    }

    @Test
    public void whenClickOnChoosePatronButtonThenInvokeGetAllPatron() throws SQLException
    {
        clickOn(CHOOSE_PATRON_BUTTON);
        Mockito.verify(workerModel).getAllPatron();
    }

    @Test
    public void whenClickOnChoosePatronButtonThenThereIsTableView()
    {
        clickOn(CHOOSE_PATRON_BUTTON);
        verifyThat(PATRONS_TABLE_VIEW, NodeMatchers.isNotNull());
    }

    @Test
    public void whenChoosePatronDialogOpenThenTableIsNotEmpty() throws SQLException
    {
        prepareFakeWorkerListWithNumberOfPupils();

        clickOn(CHOOSE_PATRON_BUTTON);


        verifyThat(PATRONS_TABLE_VIEW, (TableView tableView) -> {
            return !tableView.getItems().isEmpty();
        });
    }

    @Test
    public void whenChoosePatronThenChooseButtonIsEnable() throws SQLException
    {
        prepareFakeWorkerListWithNumberOfPupils();

        clickOn(CHOOSE_PATRON_BUTTON);

        TableView tableView = (TableView) lookup(PATRONS_TABLE_VIEW).query();
        TableRow tableRow = TableViewUtil.getTableRowToSelect(tableView);

        clickOn(tableRow);

        verifyThat("#chooseButtonType", NodeMatchers.isEnabled());
    }

    @Test
    public void whenChoosePatronThenNameIsInAddAnimalControllerPatronTextField() throws SQLException
    {
        prepareFakeWorkerListWithNumberOfPupils();
        clickOn(CHOOSE_PATRON_BUTTON);
        TableView tableView = (TableView) lookup(PATRONS_TABLE_VIEW).query();
        TableRow tableRow = TableViewUtil.getTableRowToSelect(tableView);


        clickOn(tableRow);
        clickOn("#chooseButtonType");

        verifyThat("#patronTextField", (TextField textField) ->
        {
            String text = textField.getText();
            return text.contains("Kowalski Jan");
        });
    }

    @Test
    public void whenChoosePatronThenIsSetInWorkerFxObjectInAddAnimalModel() throws SQLException
    {
        prepareFakeWorkerListWithNumberOfPupils();
        clickOn(CHOOSE_PATRON_BUTTON);
        TableView tableView = (TableView) lookup(PATRONS_TABLE_VIEW).query();
        TableRow tableRow = TableViewUtil.getTableRowToSelect(tableView);

        clickOn(tableRow);
        clickOn("#chooseButtonType");

        Worker expectedWorker = hostelAndDAOMocks.getHostel().getAllWorkerWithNumberOfPupils().get(0);
        Assert.assertEquals(expectedWorker.getIdWorker(), addAnimalModel.getAnimalFxObjectProperty().getPatron().getIdWorker());
        Assert.assertEquals(expectedWorker.getName(), addAnimalModel.getAnimalFxObjectProperty().getPatron().getName());
        Assert.assertEquals(expectedWorker.getSurname(), addAnimalModel.getAnimalFxObjectProperty().getPatron().getSurname());
    }

    private void prepareFakeWorkerListWithNumberOfPupils() throws SQLException
    {
        List<Worker> workersFakeList = new ArrayList<>();
        Worker fakeWorker = new Worker();
        fakeWorker.setIdWorker(1);
        fakeWorker.setName("Jan");
        fakeWorker.setSurname("Kowalski");
        fakeWorker.setNumberOfPupils(1);
        workersFakeList.add(fakeWorker);

        PowerMockito.when(hostelAndDAOMocks.getHostel().getAllWorkerWithNumberOfPupils()).thenReturn(workersFakeList);
    }
}
