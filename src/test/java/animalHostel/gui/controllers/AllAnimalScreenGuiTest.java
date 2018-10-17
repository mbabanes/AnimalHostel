package animalHostel.gui.controllers;

import animalHostel.Hostel;
import animalHostel.database.DAO;
import animalHostel.database.entity.Animal;
import animalHostel.database.entity.AnimalType;
import animalHostel.database.entity.Worker;
import animalHostel.gui.HostelSingleton;
import animalHostel.gui.controllers.utils.StageForTest;
import animalHostel.gui.controllers.mocks.HostelAndDAOMocks;
import animalHostel.gui.modelsFx.AnimalFx;
import animalHostel.gui.modelsFx.AnimalModel;
import javafx.geometry.VerticalDirection;
import javafx.scene.control.TableView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.testfx.matcher.base.NodeMatchers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.testfx.api.FxAssert.verifyThat;


@RunWith(PowerMockRunner.class)
@PrepareForTest({HostelSingleton.class, AllAnimalsController.class, AnimalModel.class})

public class AllAnimalScreenGuiTest extends StageForTest
{
    private static final String ALL_ANIMAL_TABLE_VIEW_ID = "#allAnimalTableView";
    private static final String ANIMALS_TOGGLE_BUTTON_ID = "#animalsToggleButton";

    private Hostel hostel;
    private DAO dao;
    private ResultSet resultSet;

    private AnimalModel animalModel = PowerMockito.spy(new AnimalModel());

    private List<Animal> animalList;



    @Before
    public void prepareMocks() throws Exception
    {
        HostelAndDAOMocks hostelAndDAOMocks = new HostelAndDAOMocks();
        hostel = hostelAndDAOMocks.getHostel();
        dao = hostelAndDAOMocks.getDao();

        resultSet = PowerMockito.mock(ResultSet.class);

        PowerMockito.whenNew(AnimalModel.class).withNoArguments().thenReturn(animalModel);

        PowerMockito.when(dao.query(anyString())).thenReturn(resultSet);
        PowerMockito.when(resultSet.next()).thenReturn(false);


        prepareListOfAnimalMock();

        clickOn(ANIMALS_TOGGLE_BUTTON_ID);
    }




    @Test
    public void whenAllAnimalControllerInitializeThenInvokeGetAnimalFromAnimalModel() throws Exception
    {
        Mockito.verify(animalModel).getAnimals();
    }


    @Test
    public void whenClickAnimalsToggleButtonsThenContainsAllAnimalsGridPane() throws Exception
    {
        verifyThat(ALL_ANIMAL_TABLE_VIEW_ID, NodeMatchers.isNotNull());
    }


    @Test
    public void whenClickAnimalsToggleButtonThenTableViewHasSomeData() throws SQLException
    {
        verifyThat(ALL_ANIMAL_TABLE_VIEW_ID, (TableView<AnimalFx> tableView) -> {
            return !tableView.getItems().isEmpty();
        });
    }


    @Test
    public void whenOnTableViewWithAnimalScrollDownThenInvokeGetAnimalsFromAnimalModel() throws SQLException
    {
        moveTo(ALL_ANIMAL_TABLE_VIEW_ID);
        scroll(VerticalDirection.DOWN);
        Mockito.verify(animalModel, times(2)).getAnimals(); //2 poniewaz jedno wywolanie jest w initialize
    }

    @Test
    public void whenOnTableViewWithAnimalsScrollDownThenListHasMoreData() throws SQLException
    {
        moveTo(ALL_ANIMAL_TABLE_VIEW_ID);
        scroll(VerticalDirection.DOWN);
        verifyThat(ALL_ANIMAL_TABLE_VIEW_ID, (TableView<AnimalFx> tableView) -> {
            return (tableView.getItems().size() > 1);
        });
    }

    private void prepareListOfAnimalMock() throws SQLException
    {
        animalList = new ArrayList<>();
        Animal animal = new Animal(1,
                2,
                "Buras",
                new AnimalType(1,
                        "Pies",
                        "Buldog"),

                "Biały",
                20,
                new Date(),
                new Date(),
                new Worker(1,
                        "Jan",
                        "Kowalski"));
        animalList.add(animal);

        PowerMockito.when(hostel.getAllAnimalWithLimitOfResult(anyInt(), anyInt())).thenReturn(animalList);
    }
}
