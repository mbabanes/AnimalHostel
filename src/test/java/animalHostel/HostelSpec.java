package animalHostel;

import animalHostel.database.DAO;
import animalHostel.database.entity.*;
import javafx.scene.layout.Pane;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;




public class HostelSpec
{
    private Hostel hostel;

    private ResultSet resultSet;
    private DAO dao;

    @Before
    public final void start()
    {
        int numberOfSlotsForAnimals = 5;
        hostel = new Hostel("Name", numberOfSlotsForAnimals);
    }

    @Test
    public void whenCreateHostelThenHostelHasName()
    {
        assertEquals(String.class, hostel.getName().getClass());
    }


    @Test
    public void whenCreateHostelThenHostelHasNumberOfSlotsForAnimalsMoreThanZero() throws SQLException
    {
        DAO dao = mock(DAO.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt(1)).thenReturn(5);
        when(dao.query("SELECT count(id_slot_animal) FROM slot_for_animal;")).thenReturn(resultSet);

        Hostel hostel = new Hostel("name", dao.query("SELECT count(id_slot_animal) FROM slot_for_animal;").getInt(1));

        boolean result = false;
        if (hostel.getNumberOfSlots() > 0)
            result = true;
        assertTrue(result);
    }


    @Test
    public void hotelHasSomeFreeSlots() throws SQLException
    {
        prepareMocks();
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(resultSet.getInt(1)).thenReturn(5);

        assertEquals(5, hostel.getFreeSlotsForAnimals());
    }


    @Test
    public void hostelHasFreeSlotsForAnimalsZeroOrMoreThanZero()
    {
        boolean result = false;
        if (hostel.getNumberOfFreeSlotsForAnimals() >= 0)
            result = true;

        assertTrue(result);
    }


    private void prepareMocks() throws SQLException
    {
        dao = mock(DAO.class);
        resultSet = mock(ResultSet.class);

        hostel.setDao(dao);

        when(dao.query(any())).thenReturn(resultSet);
    }

    @Test
    public void whenGetAllAnimalThenReturnList() throws SQLException
    {
        prepareMocks();

        Date date = Date.valueOf("2017-08-02");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(1).thenReturn(2).thenReturn(3);
        when(resultSet.getInt(2)).thenReturn(1).thenReturn(2).thenReturn(3);
        when(resultSet.getString(3)).thenReturn("Burek").thenReturn("Azor").thenReturn("Filemon");
        when(resultSet.getInt(4)).thenReturn(1).thenReturn(2).thenReturn(3);
        when(resultSet.getString(5)).thenReturn("pies").thenReturn("pies").thenReturn("Kot");
        when(resultSet.getString(6)).thenReturn("kundel").thenReturn("bernardyn").thenReturn("dachowiec");
        when(resultSet.getString(7)).thenReturn("czarny").thenReturn("bialo-braz").thenReturn("bury");
        when(resultSet.getInt(8)).thenReturn(10).thenReturn(50).thenReturn(2);
        when(resultSet.getDate(9)).thenReturn(date).thenReturn(date).thenReturn(date);
        when(resultSet.getDate(10)).thenReturn(date).thenReturn(date).thenReturn(date);
        when(resultSet.getInt(11)).thenReturn(1).thenReturn(2).thenReturn(3);
        when(resultSet.getString(12)).thenReturn("Jan").thenReturn("Ferdek").thenReturn("Waldek");
        when(resultSet.getString(13)).thenReturn("Kowalski").thenReturn("Kiepski").thenReturn("Kiepski");


        assertEquals(3, hostel.getAllAnimals().size());
    }

    @Test
    public void whenGetAllAnimalWithLimitOfResultThenInvokeQueryFromDaoObject() throws SQLException
    {
        prepareMocks();
        int indexOfRows = 0;
        int numberOfRows = 2;
        String query = "SELECT animals.id_animal, animals_in_slots.id_slot_animal, animals.name, animal_type.id_animal_type, animal_type.type, animal_type.race, color, weight, birthday, date_of_register, worker.id_worker, worker.name, worker.surname FROM animals, animal_type, worker, animals_in_slots WHERE animal_type.id_animal_type = animals.id_animal_type AND animals.id_worker = worker.id_worker AND animals.id_animal = animals_in_slots.id_animal LIMIT " + indexOfRows + "," + numberOfRows;

        when(dao.query(query)).thenReturn(resultSet);
        hostel.getAllAnimalWithLimitOfResult(indexOfRows, numberOfRows);
        verify(dao, times(1)).query(query);
    }

    @Test
    public void whenGetAllAnimalWithLimitOfResultThenReturnListWithExactlySetRows() throws SQLException
    {
        prepareMocks();
        int indexOfRows = 0;
        int numberOfRows = 3;
        when(dao.query(anyString())).thenReturn(resultSet);


        Date date = Date.valueOf("2017-08-02");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(1).thenReturn(2).thenReturn(3);
        when(resultSet.getInt(2)).thenReturn(1).thenReturn(2).thenReturn(3);
        when(resultSet.getString(3)).thenReturn("Burek").thenReturn("Azor").thenReturn("Filemon");
        when(resultSet.getInt(4)).thenReturn(1).thenReturn(2).thenReturn(3);
        when(resultSet.getString(5)).thenReturn("pies").thenReturn("pies").thenReturn("Kot");
        when(resultSet.getString(6)).thenReturn("kundel").thenReturn("bernardyn").thenReturn("dachowiec");
        when(resultSet.getString(7)).thenReturn("czarny").thenReturn("bialo-braz").thenReturn("bury");
        when(resultSet.getInt(8)).thenReturn(10).thenReturn(50).thenReturn(2);
        when(resultSet.getDate(9)).thenReturn(date).thenReturn(date).thenReturn(date);
        when(resultSet.getDate(10)).thenReturn(date).thenReturn(date).thenReturn(date);
        when(resultSet.getInt(11)).thenReturn(1).thenReturn(2).thenReturn(3);
        when(resultSet.getString(12)).thenReturn("Jan").thenReturn("Ferdek").thenReturn("Waldek");
        when(resultSet.getString(13)).thenReturn("Kowalski").thenReturn("Kiepski").thenReturn("Kiepski");

        List<Animal> animals = hostel.getAllAnimalWithLimitOfResult(indexOfRows, numberOfRows);
        int actual = animals.size();
        int expected = numberOfRows;

        assertEquals(expected, actual);

    }

    @Test
    public void whenFindAnimalByIdThenInvokeQueryFromDaoObject() throws SQLException
    {
        prepareMocks();
        int idAnimal = 1;
        String query = "SELECT animals.id_animal, animals_in_slots.id_slot_animal, animals.name, animal_type.id_animal_type, animal_type.type, animal_type.race, color, weight, birthday, date_of_register, worker.id_worker, worker.name, worker.surname FROM animals, animal_type, worker, animals_in_slots WHERE animal_type.id_animal_type = animals.id_animal_type AND animals.id_worker = worker.id_worker AND animals.id_animal = animals_in_slots.id_animal AND " +
                "animals.id_animal= " + idAnimal;

        when(dao.query(query)).thenReturn(resultSet);

        hostel.findAnimalById(idAnimal);
        verify(dao, times(1)).query(query);
    }

    @Test
    public void whenFindAnimalByIdThenReturnAnimal() throws SQLException
    {
        prepareMocks();
        int idAnimal = 1;
        when(dao.query(anyString())).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        Date date = Date.valueOf("2017-08-02");


        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getInt(2)).thenReturn(1);
        when(resultSet.getString(3)).thenReturn("Burek");
        when(resultSet.getInt(4)).thenReturn(1);
        when(resultSet.getString(5)).thenReturn("pies");
        when(resultSet.getString(6)).thenReturn("kundel");
        when(resultSet.getString(7)).thenReturn("czarny");
        when(resultSet.getInt(8)).thenReturn(10);
        when(resultSet.getDate(9)).thenReturn(date);
        when(resultSet.getDate(10)).thenReturn(date);
        when(resultSet.getInt(11)).thenReturn(1);
        when(resultSet.getString(12)).thenReturn("Jan");
        when(resultSet.getString(13)).thenReturn("Kowalski");


        assertEquals(Animal.class, hostel.findAnimalById(idAnimal).getClass());
    }

    @Test
    public void whenFindByTypeThenInvokeQueryFromDaoObject() throws SQLException
    {
        prepareMocks();
        String type = "PiEs";
        String query = "SELECT animals.id_animal, animals_in_slots.id_slot_animal, animals.name, animal_type.id_animal_type, animal_type.type, animal_type.race, color, weight, birthday, date_of_register, worker.id_worker, worker.name, worker.surname FROM animals, animal_type, worker, animals_in_slots WHERE animal_type.id_animal_type = animals.id_animal_type AND animals.id_worker = worker.id_worker AND animals.id_animal = animals_in_slots.id_animal AND animal_type.type = " +
                type.toUpperCase();

        when(dao.query(query)).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        hostel.findAnimalsByType(type);

        verify(dao, times(1)).query(query);
    }


    @Test
    public void whenFindAnimalByTypeThenReturnThatType() throws SQLException
    {
        prepareMocks();
        Date date = Date.valueOf("2017-08-02");

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(1).thenReturn(2);
        when(resultSet.getInt(2)).thenReturn(1).thenReturn(2);
        when(resultSet.getString(3)).thenReturn("Burek").thenReturn("Azor");
        when(resultSet.getInt(4)).thenReturn(1).thenReturn(2);
        when(resultSet.getString(5)).thenReturn("pies").thenReturn("pies");
        when(resultSet.getString(6)).thenReturn("kundel").thenReturn("bernardyn");
        when(resultSet.getString(7)).thenReturn("czarny").thenReturn("bialo-braz");
        when(resultSet.getInt(8)).thenReturn(10).thenReturn(50);
        when(resultSet.getDate(9)).thenReturn(date).thenReturn(date);
        when(resultSet.getDate(10)).thenReturn(date).thenReturn(date);
        when(resultSet.getInt(11)).thenReturn(1).thenReturn(2);
        when(resultSet.getString(12)).thenReturn("Jan").thenReturn("Ferdek");
        when(resultSet.getString(13)).thenReturn("Kowalski").thenReturn("Kiepski");

        assertEquals(2, hostel.findAnimalsByType("pies").size());
    }


    @Test
    public void whenFindByTypeAndRaceThenInvokeQueryFromDaoObject() throws SQLException
    {
        prepareMocks();
        String race = "owczarek niemiecki".toUpperCase();
        String type = "pies".toUpperCase();
        String query = "SELECT animals.id_animal, animals_in_slots.id_slot_animal, animals.name, animal_type.id_animal_type, animal_type.type, animal_type.race, color, weight, birthday, date_of_register, worker.id_worker, worker.name, worker.surname FROM animals, animal_type, worker, animals_in_slots WHERE animal_type.id_animal_type = animals.id_animal_type AND animals.id_worker = worker.id_worker AND animals.id_animal = animals_in_slots.id_animal AND animal_type.type = " + type + "AND animal_type.race =" + race;
        when(dao.query(query)).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        hostel.findAnimalsByTypeAndRace(type, race);
        verify(dao, times(1)).query(query);
    }

    @Test
    public void whenFindByTypeAndRaceThenInvokeToLowerCaseFromStringClass() throws SQLException
    {
        prepareMocks();
        String race = "OwCzARek niemiecki";
        String type = "pies";
        String query = "SELECT animals.id_animal, animals_in_slots.id_slot_animal, animals.name, animal_type.id_animal_type, animal_type.type, animal_type.race, color, weight, birthday, date_of_register, worker.id_worker, worker.name, worker.surname FROM animals, animal_type, worker, animals_in_slots WHERE animal_type.id_animal_type = animals.id_animal_type AND animals.id_worker = worker.id_worker AND animals.id_animal = animals_in_slots.id_animal AND animal_type.type = " + type.toUpperCase() + "AND animal_type.race =" + race.toUpperCase();
        when(dao.query(query)).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        hostel.findAnimalsByTypeAndRace(type, race);
        verify(dao, times(1)).query(query);
    }

    @Test
    public void whenFindByTypeAndRaceThenReturnThat() throws SQLException
    {
        prepareMocks();
        Date date = Date.valueOf("2017-08-02");

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(1).thenReturn(2);
        when(resultSet.getInt(2)).thenReturn(1).thenReturn(2);
        when(resultSet.getString(3)).thenReturn("Burek").thenReturn("Azor");
        when(resultSet.getInt(4)).thenReturn(1).thenReturn(2);
        when(resultSet.getString(5)).thenReturn("pies").thenReturn("pies");
        when(resultSet.getString(6)).thenReturn("owczarek niemiecki").thenReturn("owczarek niemiecki");
        when(resultSet.getString(7)).thenReturn("czarny").thenReturn("czarny");
        when(resultSet.getInt(8)).thenReturn(10).thenReturn(50);
        when(resultSet.getDate(9)).thenReturn(date).thenReturn(date);
        when(resultSet.getDate(10)).thenReturn(date).thenReturn(date);
        when(resultSet.getInt(11)).thenReturn(1).thenReturn(2);
        when(resultSet.getString(12)).thenReturn("Jan").thenReturn("Ferdek");
        when(resultSet.getString(13)).thenReturn("Kowalski").thenReturn("Kiepski");

        String type = "pies";
        String race = "owczarek niemiecki";

        assertEquals(2, hostel.findAnimalsByTypeAndRace(type, race).size());
    }


    private Animal prepareAnimal()
    {
        return new Animal(1,
                1,
                "Burek",
                new AnimalType(1),
                "Biały",
                10,
                new java.util.Date(),
                new java.util.Date(),
                new Worker(1));
    }

    @Test
    public void whenAddAnimalThenDAOInvokeExecute() throws SQLException
    {
        Animal animal = prepareAnimal();
        prepareMocks();

        when(dao.query(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(5);

        SimpleDateFormat dateFormatToDataBase = new SimpleDateFormat("YYYY-MM-dd");
        String query = "INSERT INTO animals value(NULL, '" +
                animal.getName().toUpperCase() + "'," +
                animal.getAnimalType().getIdAnimalType() +
                ",'" + animal.getColor().toUpperCase() + "'," +
                animal.getWeight() + "," +
                "'" + dateFormatToDataBase.format(animal.getBirthDay()) + "'," +
                "'" + dateFormatToDataBase.format(animal.getDateOfRegister()) + "'," +
                animal.getPatron().getIdWorker() +
                ");";

        when(dao.insertWithIdReturning(query)).thenReturn(12);

        hostel.addAnimal(animal);

        verify(dao, times(1)).insertWithIdReturning(query);
    }

    @Test
    public void whenAddAnimalThenReturnId() throws SQLException
    {
        Animal animal = prepareAnimal();
        prepareMocks();
        animal.setId(1);
        when(dao.insertWithIdReturning(anyString())).thenReturn(1);


        assertEquals(animal.getId(), hostel.addAnimal(animal));
    }

    @Test
    public void whenAddAnimalAndBirthDayIsNullThenQueryIsWithNull() throws SQLException
    {
        prepareMocks();
        Animal animal = prepareAnimal();
        animal.setBirthDay(null);

        SimpleDateFormat dateFormatToDataBase = new SimpleDateFormat("YYYY-MM-dd");
        String query = "INSERT INTO animals value(NULL, '" +
                animal.getName().toUpperCase() + "'," +
                animal.getAnimalType().getIdAnimalType() +
                ",'" + animal.getColor().toUpperCase() + "'," +
                animal.getWeight() + "," +
                "NULL," +
                "'" + dateFormatToDataBase.format(animal.getDateOfRegister()) + "'," +
                animal.getPatron().getIdWorker() +
                ");";
        when(dao.insertWithIdReturning(query)).thenReturn(1);
        hostel.addAnimal(animal);

        verify(dao, times(1)).insertWithIdReturning(query);
    }

    @Test
    public void whenAddAnimalIntoSlotForAnimalThenInvokeUpdateSlotsForAnimal() throws SQLException
    {
        prepareMocks();
        when(dao.insert(anyString())).thenReturn(true);
        int idAnimal = 1;
        int idSlotForAnimal = 1;
        java.util.Date dateOfIn = new java.util.Date();
        AnimalInSlots animalInSlots = new AnimalInSlots(new Animal(idAnimal), new SlotsForAnimal(idSlotForAnimal), dateOfIn);

        when(dao.update(anyString())).thenReturn(true);
        hostel.addAnimalIntoSlotForAnimals(animalInSlots);
        verify(dao, times(1)).update(anyString());
    }

    @Test
    public void whenAddAnimalInToSlotForAnimalThenReturnTrue() throws SQLException
    {
        prepareMocks();
        int idAnimal = 1;
        int idSlotForAnimal = 1;
        java.util.Date dateOfIn = new java.util.Date();
        AnimalInSlots animalInSlots = new AnimalInSlots(new Animal(idAnimal), new SlotsForAnimal(idSlotForAnimal), dateOfIn);

        when(dao.insert(anyString())).thenReturn(true);
        assertTrue(hostel.addAnimalIntoSlotForAnimals(animalInSlots));
    }


    @Test
    public void whenAddAnimalToHealThenInvokeInsertFromDaoObject() throws SQLException
    {
        prepareMocks();

        Animal animal = prepareAnimal();
        String symptoms = "Wypadająca sierść";

        AnimalToHeal animalToHeal = new AnimalToHeal(animal, new java.util.Date(), symptoms);
        SimpleDateFormat dateFormatToDataBase = new SimpleDateFormat("YYYY-MM-dd");

        String query = "INSERT INTO animal_to_heal VALUES(NULL," + animalToHeal.getAnimal().getId() + "," +
                "'" + dateFormatToDataBase.format(animalToHeal.getDateOfRegister()) + "'," +
                "'" + animalToHeal.getSymptoms() + "', 0);";

        when(dao.insert(query)).thenReturn(true);

        hostel.addAnimalToHeal(animal, symptoms);

        verify(dao, times(1)).insert(query);
    }

    @Test
    public void whenAddAnimalToHealAndEveryThinkIsGoodThenReturnTrue() throws SQLException
    {
        prepareMocks();

        when(dao.insert(anyString())).thenReturn(true);

        Animal animal = prepareAnimal();
        String symptoms = "Wypadająca sierść";
        assertTrue(hostel.addAnimalToHeal(animal, symptoms));
    }

    @Test
    public void whenGetAllAnimalToHealThenReturnListOfThat() throws SQLException
    {
        prepareMocks();

        Date date = Date.valueOf("2017-08-02");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        when(resultSet.getInt(1)).thenReturn(1).thenReturn(2);
        when(resultSet.getInt(2)).thenReturn(1).thenReturn(2);
        when(resultSet.getString(3)).thenReturn("Azor").thenReturn("Filemon");
        when(resultSet.getInt(4)).thenReturn(1).thenReturn(2);
        when(resultSet.getInt(5)).thenReturn(1).thenReturn(2);
        when(resultSet.getString(6)).thenReturn("Pies").thenReturn("Kot");
        when(resultSet.getString(7)).thenReturn("Labrador").thenReturn("Dachowiec");
        when(resultSet.getDate(8)).thenReturn(date).thenReturn(date);
        when(resultSet.getInt(9)).thenReturn(1).thenReturn(2);
        when(resultSet.getString(10)).thenReturn("Jan").thenReturn("Roman");
        when(resultSet.getString(11)).thenReturn("Nowak").thenReturn("Kowalski");
        when(resultSet.getDate(12)).thenReturn(date).thenReturn(date);
        when(resultSet.getString(13)).thenReturn("Pies ma goraczke").thenReturn("Katar");
        when(resultSet.getBoolean(14)).thenReturn(false).thenReturn(true);

        List<AnimalToHeal> animalToHealsList = hostel.getAllAnimalToHeal();
        int expected = 2;
        int actual = animalToHealsList.size();

        assertEquals(expected, actual);
    }

    @Test
    public void whenGetAnimalsToHealByAnimalIdThenReturnListOfThat() throws SQLException
    {
        prepareMocks();
        Date date = Date.valueOf("2017-08-02");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        when(resultSet.getInt(1)).thenReturn(1).thenReturn(2);
        when(resultSet.getInt(2)).thenReturn(1).thenReturn(1);
        when(resultSet.getString(3)).thenReturn("Azor").thenReturn("Azor");
        when(resultSet.getInt(4)).thenReturn(1).thenReturn(1);
        when(resultSet.getInt(5)).thenReturn(1).thenReturn(1);
        when(resultSet.getString(6)).thenReturn("Pies").thenReturn("Pies");
        when(resultSet.getString(7)).thenReturn("Labrador").thenReturn("Labrador");
        when(resultSet.getDate(8)).thenReturn(date).thenReturn(date);
        when(resultSet.getInt(9)).thenReturn(1).thenReturn(1);
        when(resultSet.getString(10)).thenReturn("Jan").thenReturn("Jan");
        when(resultSet.getString(11)).thenReturn("Nowak").thenReturn("Nowak");
        when(resultSet.getDate(12)).thenReturn(date).thenReturn(date);
        when(resultSet.getString(13)).thenReturn("Pies ma goraczke").thenReturn("Katar");
        when(resultSet.getBoolean(14)).thenReturn(false).thenReturn(true);

        List<AnimalToHeal> animaltToHealByIdAnimal = hostel.getAnimalToHealByIdAnimal(1);
        int expected = 2;
        int actual = animaltToHealByIdAnimal.size();
        assertEquals(expected, actual);
    }

    @Test
    public void whenAddNewSlotForAnimalThenInvokeInsertFromDaoObject() throws SQLException
    {
        prepareMocks();

        SlotsForAnimal slotForAnimal = new SlotsForAnimal(new java.util.Date(), new AnimalType(1), 20, false, 2);
        SimpleDateFormat dateFormatToDataBase = new SimpleDateFormat("YYYY-MM-dd");
        int inside = slotForAnimal.isInside() ? 1 : 0;
        int free = 1;
        String query = "INSERT INTO slots_for_animal VALUES(NULL," + free + "," +
                "'" + dateFormatToDataBase.format(slotForAnimal.getDateOfOpen()) + "'," +
                slotForAnimal.getAnimalType().getIdAnimalType() + "," +
                slotForAnimal.getArea() + "," +
                inside + "," +
                slotForAnimal.getHeight() + ");";

        when(dao.insert(query)).thenReturn(true);

        hostel.addNewSlotForAnimal(slotForAnimal);

        verify(dao, times(1)).insert(query);
    }

    @Test
    public void whenAddNewSlotForAnimalAndEveryThinkIsGoodThenReturnTrue() throws SQLException
    {
        prepareMocks();

        when(dao.insert(anyString())).thenReturn(true);

        SlotsForAnimal slotForAnimal = new SlotsForAnimal(new java.util.Date(), new AnimalType(1), 20, false, 2);

        assertTrue(hostel.addNewSlotForAnimal(slotForAnimal));
    }


    @Test
    public void whenGetAllSlotsForAnimalThenReturnListOfThat() throws SQLException
    {
        prepareMocks();

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        Date date = Date.valueOf("2017-08-02");

        when(resultSet.getInt(1)).thenReturn(1).thenReturn(2);
        when(resultSet.getBoolean(2)).thenReturn(true).thenReturn(false);
        when(resultSet.getDate(3)).thenReturn(date).thenReturn(date);
        when(resultSet.getInt(4)).thenReturn(1).thenReturn(2);
        when(resultSet.getString(5)).thenReturn("Pies").thenReturn("Kot");
        when(resultSet.getString(6)).thenReturn("Labrador").thenReturn("Pers");
        when(resultSet.getInt(7)).thenReturn(20).thenReturn(5);
        when(resultSet.getBoolean(8)).thenReturn(true).thenReturn(true);
        when(resultSet.getInt(9)).thenReturn(2).thenReturn(2);

        List<SlotsForAnimal> slotsForAnimalList = hostel.getAllSlotsForAnimal();

        int expected = 2;
        int actual = slotsForAnimalList.size();
        assertEquals(expected, actual);
    }


    @Test
    public void whenGetSlotForAnimalByIdThenReturnOne() throws SQLException
    {
        prepareMocks();
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        Date date = Date.valueOf("2017-08-02");

        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getBoolean(2)).thenReturn(true);
        when(resultSet.getDate(3)).thenReturn(date);
        when(resultSet.getInt(4)).thenReturn(1);
        when(resultSet.getString(5)).thenReturn("Pies");
        when(resultSet.getString(6)).thenReturn("Labrador");
        when(resultSet.getInt(7)).thenReturn(20);
        when(resultSet.getBoolean(8)).thenReturn(true);
        when(resultSet.getInt(9)).thenReturn(2);

        SlotsForAnimal expected = new SlotsForAnimal(1, true, date, new AnimalType(1, "Pies", "Labrador"), 20, true, 2);
        SlotsForAnimal actual = hostel.getSlotForAnimalById(1);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void whenAddNewTypeOfAnimalThenInvokeInsertFromDaoObject() throws SQLException
    {
        prepareMocks();

        AnimalType animalType = new AnimalType("Słoń", "Afrykański");
        String query = "INSERT INTO animal_type VALUES(NULL,'" + animalType.getType().toUpperCase() + "','" +
                animalType.getRace().toUpperCase() + "');";

        when(dao.insert(query)).thenReturn(true);

        hostel.addAnimalType(animalType);

        verify(dao, times(1)).insert(query);
    }

    @Test
    public void whenAddNewTypeOfAnimalThenReturnTrue() throws SQLException
    {
        prepareMocks();

        when(dao.insert(anyString())).thenReturn(true);
        AnimalType animalType = new AnimalType("Słoń", "Afrykański");
        assertTrue(hostel.addAnimalType(animalType));
    }

    @Test
    public void whenGetAllAnimalTypeThenReturnListOfThat() throws SQLException
    {
        prepareMocks();

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        when(resultSet.getInt(1)).thenReturn(1).thenReturn(2);
        when(resultSet.getString(2)).thenReturn("Pies").thenReturn("Kot");
        when(resultSet.getString(3)).thenReturn("Labrador").thenReturn("Pers");

        List<AnimalType> animalTypesList = hostel.getAllAnimalTypes();

        int expected = 2;
        assertEquals(expected, animalTypesList.size());
    }

    @Test
    public void whenFindAnimalTypeByTypeOrRaceThenInvokeQueryFromDAOObject() throws SQLException
    {
        prepareMocks();

        String type = "pies";
        String race = "kundel";
        String query = "SELECT * FROM `animal_type` WHERE animal_type.type='" + type.toUpperCase() + "' or animal_type.race='" + race.toUpperCase() +"'";

        when(dao.query(query)).thenReturn(resultSet);

        hostel.findAnimalTypeByTypeOrRace(type,race);
        verify(dao, times(1)).query(query);
    }

    @Test
    public void whenFindAnimalTypeByTypeOrRaceThenReturnListOfThatResult() throws SQLException
    {
        prepareMocks();
        String type = "pies";
        String race = "kundel";

        when(dao.query(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        when(resultSet.getInt(1)).thenReturn(1).thenReturn(2);
        when(resultSet.getString(2)).thenReturn("PIES").thenReturn("PIES");
        when(resultSet.getString(4)).thenReturn("KUNDEL").thenReturn("OWCZAREK NIEMIECKI");

        List<AnimalType> animalTypes = hostel.findAnimalTypeByTypeOrRace(type, race);

        int expected = 2;
        int actual = animalTypes.size();

        assertEquals(expected, actual);
    }

    @Test
    public void whenFindAnimalTypeByTypeAndRaceThenInvokeQueryFormDaoObject() throws SQLException
    {
        prepareMocks();
        String type = "pies";
        String race = "kundel";
        String query = "SELECT * FROM `animal_type` WHERE animal_type.type='" + type.toUpperCase() + "' AND animal_type.race='" + race.toUpperCase() +"'";
            when(dao.query(query)).thenReturn(resultSet);
            hostel.findAnimalTypeByTypeAndRace(type, race);

            verify(dao, times(1)).query(query);
    }


    @Test
    public void whenFindAnimalTypeByTypeAndRaceThenReturnOneAnimalType() throws SQLException
    {
        prepareMocks();
        String type = "pies";
        String race = "kundel";

        when(dao.query(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("pies".toUpperCase());
        when(resultSet.getString(3)).thenReturn("kundel".toUpperCase());
        AnimalType expected = new AnimalType(1, "PIES", "KUNDEL");
        AnimalType actual = hostel.findAnimalTypeByTypeAndRace(type, race);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void whenAddNewWorkerThenInvokeInsertFromDaoObject() throws SQLException
    {
        prepareMocks();
        Worker worker = new Worker("Jan", "Kowalski", 2300.00d, new JobPosition(1), "email@mail.pl",
                new java.util.Date());

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String query = "INSERT INTO worker VALUES(" + worker.getName().toUpperCase() + "," + worker.getSurname().toUpperCase() + "," +
                worker.getSalary() + "," + worker.getJobPosition().getIdJobPosition() + ",'" + dateFormat.format(worker.getDateOfEmploym()) + "');";

        when(dao.insert(query)).thenReturn(true);

        hostel.addWorker(worker);

        verify(dao, times(1)).insert(query);
    }

    @Test
    public void whenAddNewWorkerThenReturnTrue() throws SQLException
    {
        prepareMocks();

        Worker worker = new Worker("Jan", "Kowalski", 2300.00d, new JobPosition(1), "email@mail.pl",
                new java.util.Date());

        when(dao.insert(anyString())).thenReturn(true);

        assertTrue(hostel.addWorker(worker));
    }

    @Test
    public void whenGetAllWorkerWithNumberOfPupilsThenInvokeQueryFromDaoObject() throws SQLException
    {
        prepareMocks();

        String query = "SELECT worker.id_worker, worker.name, worker.surname, count(animals.id_worker) as numbers FROM worker LEFT JOIN animals USING(id_worker) GROUP by worker.id_worker ORDER BY numbers";
        when(dao.query(query)).thenReturn(resultSet);

        hostel.getAllWorkerWithNumberOfPupils();

        verify(dao, times(1)).query(query);
    }

    @Test
    public void whenGetAllWorkerWithNumberOfPupilsThenReturnListOfWorker() throws SQLException
    {
        prepareMocks();

        when(dao.query(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);

        when(resultSet.getInt(1)).thenReturn(1).thenReturn(2).thenReturn(3);
        when(resultSet.getString(2)).thenReturn("Kowalski").thenReturn("Kiepski").thenReturn("Boczek");
        when(resultSet.getString(3)).thenReturn("Marian").thenReturn("Ferdynand").thenReturn("Arnold");
        when(resultSet.getInt(4)).thenReturn(3).thenReturn(5).thenReturn(8);

        List<Worker> actual = hostel.getAllWorkerWithNumberOfPupils();

        int expected = 3;

        assertEquals(expected, actual.size());
    }

    @Test
    public void whenSlotsForAnimalsWithStatsThenInvokeQueryFromDaoObject() throws SQLException
    {
        prepareMocks();
        String query = "SELECT " +
                "slots_for_animal.id_slot_animal," +
                "COUNT(animals_in_slots.id_slot_animal) AS ilosc," +
                "animal_type.type," +
                "animal_type.race," +
                "slots_for_animal.area," +
                "slots_for_animal.heigth," +
                "slots_for_animal.inside " +
                "FROM " +
                "animal_type " +
                "INNER JOIN slots_for_animal USING(id_animal_type) " +
                "LEFT JOIN animals_in_slots USING(id_slot_animal) " +
                "WHERE animals_in_slots.date_of_out IS NULL " +
                "GROUP BY " +
                "slots_for_animal.id_slot_animal";

        when(dao.query(query)).thenReturn(resultSet);

        hostel.getSlotsForAnimalWithStats();

        verify(dao, times(1)).query(query);
    }

    @Test
    public void whenSlotsForAnimalsWithStatsThenResultList() throws SQLException
    {
        prepareMocks();
        when(dao.query(anyString())).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(1).thenReturn(2);
        when(resultSet.getInt(2)).thenReturn(3).thenReturn(0);
        when(resultSet.getString(3)).thenReturn("PIES").thenReturn("PIES");
        when(resultSet.getString(4)).thenReturn("OWCZAREK NIEMIECKI").thenReturn("PUDEL");
        when(resultSet.getInt(5)).thenReturn(20).thenReturn(3);
        when(resultSet.getInt(6)).thenReturn(3).thenReturn(2);
        when(resultSet.getBoolean(7)).thenReturn(true).thenReturn(true);

        List<AnimalInSlots> animalInSlotsList = hostel.getSlotsForAnimalWithStats();
        int actual = animalInSlotsList.size();
        int expected = 2;

        assertEquals(expected, actual);
    }


    @Test
    public void whenFindAnimalByIdOrNameThenInvokeQueryFormDAOObject() throws SQLException
    {
        prepareMocks();
        int idAnimal = 1;
        String name = "name";

        String query = "SELECT animals.id_animal, animals_in_slots.id_slot_animal, animals.name FROM animals, animals_in_slots WHERE animals.id_animal = animals_in_slots.id_animal AND (animals.id_animal =" + idAnimal + " OR animals.name='" + name + "')";
        when(dao.query(query)).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        hostel.findAnimalByIdOrName(idAnimal, name);

        verify(dao).query(query);
    }

    @Test
    public void whenFindAnimalByIdOrNameThenReturnListOfThat() throws SQLException
    {
        prepareMocks();
        int idAnimal = 1;
        String name = "name";

        when(resultSet.next()).thenReturn(true, true, false);

        when(resultSet.getInt(1)).thenReturn(1, 2);
        when(resultSet.getInt(2)).thenReturn(1, 2);
        when(resultSet.getString(3)).thenReturn("Burek", "Azor");

        List<Animal> listAnimal = hostel.findAnimalByIdOrName(idAnimal, name);

        Assert.assertEquals(2, listAnimal.size());
    }
}