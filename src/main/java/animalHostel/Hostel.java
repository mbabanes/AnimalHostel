package animalHostel;

import animalHostel.database.DAO;
import animalHostel.database.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Hostel
{
    private String name;
    private int numberOfSlotsForAnimals;
    private int numberOfFreeSlotsForAnimals;


    private DAO dao;

    public Hostel()
    {
    }

    public Hostel(String name, int numberOfSlotsForAnimals)
    {
        this.name = name;
        this.numberOfSlotsForAnimals = numberOfSlotsForAnimals;
        this.numberOfFreeSlotsForAnimals = numberOfSlotsForAnimals;
    }

    public String getName()
    {
        return name;
    }

    public int getNumberOfSlots()
    {
        return numberOfSlotsForAnimals;
    }


    public int getFreeSlotsForAnimals() throws SQLException
    {
        ResultSet result = dao.query("SELECT count(id_slot_animal) FROM slots_for_animal WHERE slots_for_animal.free = 1");

        numberOfFreeSlotsForAnimals = 0;

        while (result.next())
        {
            numberOfFreeSlotsForAnimals = result.getInt(1);
        }

        return numberOfFreeSlotsForAnimals;
    }

    public int getNumberOfFreeSlotsForAnimals()
    {
        return numberOfFreeSlotsForAnimals;
    }


    public void setDao(DAO dao)
    {
        this.dao = dao;
    }

    public List<Animal> getAllAnimals() throws SQLException
    {
        ResultSet result = dao.query("SELECT animals.id_animal, animals_in_slots.id_slot_animal, animals.name, animal_type.id_animal_type, animal_type.type, animal_type.race, color, weight, birthday, date_of_register, worker.id_worker, worker.name, worker.surname FROM animals, animal_type, worker, animals_in_slots WHERE animal_type.id_animal_type = animals.id_animal_type AND animals.id_worker = worker.id_worker AND animals.id_animal = animals_in_slots.id_animal;");
        List<Animal> animals = makeAnimalListIfPossible(result);

        return animals;
    }

    public List<Animal> findAnimalsByType(String type) throws SQLException
    {
        type = type.toUpperCase();
        ResultSet result = dao.query("SELECT animals.id_animal, animals_in_slots.id_slot_animal, animals.name, animal_type.id_animal_type, animal_type.type, animal_type.race, color, weight, birthday, date_of_register, worker.id_worker, worker.name, worker.surname FROM animals, animal_type, worker, animals_in_slots WHERE animal_type.id_animal_type = animals.id_animal_type AND animals.id_worker = worker.id_worker AND animals.id_animal = animals_in_slots.id_animal AND animal_type.type = " + type);

        List<Animal> animals = makeAnimalListIfPossible(result);

        return animals;
    }

    public List<Animal> findAnimalsByTypeAndRace(String type, String race) throws SQLException
    {
        type = type.toUpperCase();
        race = race.toUpperCase();

        ResultSet result = dao.query("SELECT animals.id_animal, animals_in_slots.id_slot_animal, animals.name, animal_type.id_animal_type, animal_type.type, animal_type.race, color, weight, birthday, date_of_register, worker.id_worker, worker.name, worker.surname FROM animals, animal_type, worker, animals_in_slots WHERE animal_type.id_animal_type = animals.id_animal_type AND animals.id_worker = worker.id_worker AND animals.id_animal = animals_in_slots.id_animal AND animal_type.type = " + type + "AND animal_type.race =" + race);

        List<Animal> animals = makeAnimalListIfPossible(result);

        return animals;
    }

    private List<Animal> makeAnimalListIfPossible(ResultSet result) throws SQLException
    {
        List<Animal> animals = new ArrayList<>();
        while (result.next())
        {
            animals.add(new Animal(result.getInt(1),
                    result.getInt(2),
                    result.getString(3),

                    new AnimalType(result.getInt(4),
                            result.getString(5),
                            result.getString(6)),

                    result.getString(7),
                    result.getInt(8),
                    result.getDate(9),
                    result.getDate(10),
                    new Worker(result.getInt(11),
                            result.getString(12),
                            result.getString(13))
            ));
        }
        return animals;
    }


    public int addAnimal(Animal animal) throws SQLException
    {
        SimpleDateFormat dateFormatToDataBase = new SimpleDateFormat("YYYY-MM-dd");

        String birthDay = animal.getBirthDay() == null ? "NULL" : "'" + dateFormatToDataBase.format(animal.getBirthDay()) + "'";

        String query = "INSERT INTO animals value(NULL, '" +
                animal.getName().toUpperCase() + "'," +
                animal.getAnimalType().getIdAnimalType() +
                ",'" + animal.getColor().toUpperCase() + "'," +
                animal.getWeight() + "," +
                birthDay + "," +
                "'" + dateFormatToDataBase.format(animal.getDateOfRegister()) + "'," +
                animal.getPatron().getIdWorker() +
                ");";
        int idAnimal = dao.insertWithIdReturning(query);


        return idAnimal;
    }

    public boolean addAnimalIntoSlotForAnimals(AnimalInSlots animalInSlots) throws SQLException
    {
        SimpleDateFormat dateFormatToDataBase = new SimpleDateFormat("YYYY-MM-dd");

        String query = "INSERT INTO animals_in_slots VALUES(NULL," + animalInSlots.getAnimal().getId() +
                "," + animalInSlots.getSlotsForAnimal().getIdSlotForAnimal() +
                ",'" + dateFormatToDataBase.format(animalInSlots.getDateOfIn()) + "', NULL);";


        boolean inserted = dao.insert(query);
        String updateQuery = "UPDATE `slots_for_animal` SET `free` = '0' WHERE `slots_for_animal`.`id_slot_animal` =" +
                animalInSlots.getSlotsForAnimal().getIdSlotForAnimal() + ";";
        dao.update(updateQuery);
        return inserted;
    }

    public boolean addAnimalToHeal(Animal animal, String symptoms) throws SQLException
    {
        AnimalToHeal animalToHeal = new AnimalToHeal(animal, new Date(), symptoms);
        SimpleDateFormat dateFormatToDataBase = new SimpleDateFormat("YYYY-MM-dd");

        String query = "INSERT INTO animal_to_heal VALUES(NULL," + animalToHeal.getAnimal().getId() + "," +
                "'" + dateFormatToDataBase.format(animalToHeal.getDateOfRegister()) + "'," +
                "'" + animalToHeal.getSymptoms() + "', 0);";

        return dao.insert(query);
    }

    public int addAnimalToHeal(AnimalToHeal animalToHeal) throws SQLException
    {
        SimpleDateFormat dateFormatToDataBase = new SimpleDateFormat("YYYY-MM-dd");

        String query = "INSERT INTO animal_to_heal VALUES(NULL," + animalToHeal.getAnimal().getId() + "," +
                "'" + dateFormatToDataBase.format(animalToHeal.getDateOfRegister()) + "'," +
                "'" + animalToHeal.getSymptoms() + "', 0);";

        return dao.insertAnimalToHeal(query);
    }

    public boolean addNewSlotForAnimal(SlotsForAnimal slotForAnimal) throws SQLException
    {
        SimpleDateFormat dateFormatToDataBase = new SimpleDateFormat("YYYY-MM-dd");

        int inside = slotForAnimal.isInside() ? 1 : 0;
        int free = 1;

        String query = "INSERT INTO slots_for_animal VALUES(NULL," + free + "," +
                "'" + dateFormatToDataBase.format(slotForAnimal.getDateOfOpen()) + "'," +
                slotForAnimal.getAnimalType().getIdAnimalType() + "," +
                slotForAnimal.getArea() + "," +
                inside + "," +
                slotForAnimal.getHeight() + ");";

        return dao.insert(query);
    }


    public boolean addAnimalType(AnimalType animalType) throws SQLException
    {
        String query = "INSERT INTO animal_type VALUES(NULL,'" + animalType.getType().toUpperCase() + "','" +
                animalType.getRace().toUpperCase() + "');";

        return dao.insert(query);
    }

    public List<AnimalType> getAllAnimalTypes() throws SQLException
    {
        String query = "SELECT * FROM animal_type;";
        ResultSet result = dao.query(query);

        List<AnimalType> animalTypes = makeAnimalTypes(result);

        return animalTypes;
    }

    public List<SlotsForAnimal> getAllSlotsForAnimal() throws SQLException
    {
        String query = "SELECT slots_for_animal.id_slot_animal, slots_for_animal.free, slots_for_animal.date_of_open, slots_for_animal.id_animal_type, animal_type.type, animal_type.race, slots_for_animal.area, slots_for_animal.inside, slots_for_animal.heigth FROM slots_for_animal, animal_type WHERE slots_for_animal.id_animal_type = animal_type.id_animal_type";

        ResultSet result = dao.query(query);

        List<SlotsForAnimal> slotsForAnimalList = new ArrayList<>();

        while (result.next())
        {
            slotsForAnimalList.add(new SlotsForAnimal(
                    result.getInt(1),
                    result.getBoolean(2),
                    result.getDate(3),
                    new AnimalType(result.getInt(4), result.getString(5), result.getString(6)),
                    result.getInt(7),
                    result.getBoolean(8),
                    result.getInt(9)
            ));
        }

        return slotsForAnimalList;
    }

    public SlotsForAnimal getSlotForAnimalById(int id) throws SQLException
    {
        String query = "SELECT slots_for_animal.id_slot_animal, slots_for_animal.free, slots_for_animal.date_of_open, slots_for_animal.id_animal_type, animal_type.type, animal_type.race, slots_for_animal.area, slots_for_animal.inside, slots_for_animal.heigth FROM slots_for_animal, animal_type WHERE slots_for_animal.id_animal_type = animal_type.id_animal_type AND slots_for_animal.id_slot_animal =" +
                id + ";";

        ResultSet result = dao.query(query);

        SlotsForAnimal slotsForAnimal = null;

        while (result.next())
        {
            slotsForAnimal = new SlotsForAnimal(
                    result.getInt(1),
                    result.getBoolean(2),
                    result.getDate(3),
                    new AnimalType(result.getInt(4), result.getString(5), result.getString(6)),
                    result.getInt(7),
                    result.getBoolean(8),
                    result.getInt(9)
            );
        }

        return slotsForAnimal;
    }

    public List<AnimalToHeal> getAllAnimalToHeal() throws SQLException
    {
        String query = "SELECT animal_to_heal.id_animal_to_heal, animal_to_heal.id_animal, animals.name, animals_in_slots.id_slot_animal, animals.id_animal_type, animal_type.type, animal_type.race, animals.birthday, animals.id_worker, worker.name, worker.surname, animal_to_heal.date_of_register, animal_to_heal.symptoms, animal_to_heal.done FROM animal_to_heal, animals, animals_in_slots, animal_type, worker WHERE animal_to_heal.id_animal = animals.id_animal AND animals.id_animal_type = animal_type.id_animal_type AND animals.id_worker = worker.id_worker AND animals.id_animal = animals_in_slots.id_animal";

        ResultSet result = dao.query(query);

        return prepareAnimalToHealList(result);
    }

    private List<AnimalToHeal> prepareAnimalToHealList(ResultSet result) throws SQLException
    {
        List<AnimalToHeal> animalToHealList = new ArrayList<>();

        while (result.next())
        {
            AnimalType animalType = new AnimalType(result.getInt(5), result.getString(6), result.getString(7));

            Worker worker = new Worker(result.getInt(9), result.getString(10), result.getString(11));
            //AnimalInSlots animalInSlots = new AnimalInSlots(result.getInt(4));

            Animal animal = new Animal(result.getInt(2), result.getInt(4), result.getString(3), animalType, result.getDate(8), worker);

            animalToHealList.add(new AnimalToHeal(result.getInt(1), animal, result.getDate(12), result.getString(13), result.getBoolean(14)));
        }
        return animalToHealList;
    }

    public List<AnimalToHeal> getAnimalToHealByIdAnimal(int idAnimal) throws SQLException
    {
        String query = "SELECT animal_to_heal.id_animal_to_heal, animal_to_heal.id_animal, animals.name, animals_in_slots.id_slot_animal, animals.id_animal_type, animal_type.type, animal_type.race, animals.birthday, animals.id_worker, worker.name, worker.surname, animal_to_heal.date_of_register, animal_to_heal.symptoms, animal_to_heal.done FROM animal_to_heal, animals, animals_in_slots, animal_type, worker WHERE animals.id_animal_type = animal_type.id_animal_type AND animals.id_worker = worker.id_worker AND animals.id_animal = animals_in_slots.id_animal AND" +
                "animal_to_heal.id_animal = " + idAnimal + ";";

        ResultSet result = dao.query(query);

        return prepareAnimalToHealList(result);
    }

    public boolean addWorker(Worker worker) throws SQLException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String query = "INSERT INTO worker VALUES(" + worker.getName().toUpperCase() + "," + worker.getSurname().toUpperCase() + "," +
                worker.getSalary() + "," + worker.getJobPosition().getIdJobPosition() + ",'" + dateFormat.format(worker.getDateOfEmploym()) + "');";

        return dao.insert(query);
    }

    public Animal findAnimalById(int idAnimal) throws SQLException
    {
        String query = "SELECT animals.id_animal, animals_in_slots.id_slot_animal, animals.name, animal_type.id_animal_type, animal_type.type, animal_type.race, color, weight, birthday, date_of_register, worker.id_worker, worker.name, worker.surname FROM animals, animal_type, worker, animals_in_slots WHERE animal_type.id_animal_type = animals.id_animal_type AND animals.id_worker = worker.id_worker AND animals.id_animal = animals_in_slots.id_animal AND " +
                "animals.id_animal= " + idAnimal;
        ResultSet result = dao.query(query);
        Animal animal = null;
        while (result.next())
        {
            animal = new Animal(result.getInt(1),
                    result.getInt(2),
                    result.getString(3),
                    new AnimalType(result.getInt(4),
                            result.getString(5),
                            result.getString(6)),
                    result.getString(7),
                    result.getInt(8),
                    result.getDate(9),
                    result.getDate(10),
                    new Worker(result.getInt(11),
                            result.getString(12),
                            result.getString(13))
            );
        }

        return animal;
    }

    public List<Animal> findAnimalByIdOrName(int idAnimal, String name) throws SQLException
    {
        String query = "SELECT animals.id_animal, animals_in_slots.id_slot_animal, animals.name FROM animals, animals_in_slots WHERE animals.id_animal = animals_in_slots.id_animal AND (animals.id_animal =" + idAnimal + " OR animals.name='" + name + "')";
        ResultSet resultSet = dao.query(query);

        List<Animal> animalList = new ArrayList<>();

        while (resultSet.next())
        {
            Animal animal = new Animal();
            animal.setId(resultSet.getInt(1));
            animal.setName(resultSet.getString(3));
            animal.setSlot(resultSet.getInt(2));
            animalList.add(animal);
        }

        return animalList;
    }

    public DAO getDao()
    {
        return dao;
    }

    public List<AnimalType> findAnimalTypeByTypeOrRace(String type, String race) throws SQLException
    {
        String query = "SELECT * FROM `animal_type` WHERE animal_type.type='" + type.toUpperCase() + "' or animal_type.race='" + race.toUpperCase() + "'";
        ResultSet result = dao.query(query);
        List<AnimalType> animalTypes = makeAnimalTypes(result);

        return animalTypes;
    }

    private List<AnimalType> makeAnimalTypes(ResultSet result) throws SQLException
    {
        List<AnimalType> animalTypes = new ArrayList<>();
        while (result.next())
        {
            animalTypes.add(new AnimalType(result.getInt(1), result.getString(2), result.getString(3)));
        }
        return animalTypes;
    }

    public AnimalType findAnimalTypeByTypeAndRace(String type, String race) throws SQLException
    {
        String query = "SELECT * FROM `animal_type` WHERE animal_type.type='" + type.toUpperCase() + "' AND animal_type.race='" + race.toUpperCase() + "'";
        ResultSet resultSet = dao.query(query);
        AnimalType animalType = null;
        while (resultSet.next())
        {
            animalType = new AnimalType(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        }

        return animalType;
    }

    public List<Worker> getAllWorkerWithNumberOfPupils() throws SQLException
    {
        String query = "SELECT worker.id_worker, worker.name, worker.surname, count(animals.id_worker) as numbers FROM worker LEFT JOIN animals USING(id_worker) GROUP by worker.id_worker ORDER BY numbers";
        ResultSet result = dao.query(query);
        List<Worker> workers = new ArrayList<>();
        while (result.next())
        {
            Worker worker = new Worker(result.getInt(1), result.getString(2), result.getString(3));
            worker.setNumberOfPupils(result.getInt(4));
            workers.add(worker);
        }

        return workers;
    }

    public List<Animal> getAllAnimalWithLimitOfResult(int indexOfRows, int numberOfRows) throws SQLException
    {
        String query = "SELECT animals.id_animal, animals_in_slots.id_slot_animal, animals.name, animal_type.id_animal_type, animal_type.type, animal_type.race, color, weight, birthday, date_of_register, worker.id_worker, worker.name, worker.surname FROM animals, animal_type, worker, animals_in_slots WHERE animal_type.id_animal_type = animals.id_animal_type AND animals.id_worker = worker.id_worker AND animals.id_animal = animals_in_slots.id_animal LIMIT " + indexOfRows + "," + numberOfRows;
        ResultSet result = dao.query(query);
        List<Animal> animals = makeAnimalListIfPossible(result);

        return animals;
    }

    public List<AnimalInSlots> getSlotsForAnimalWithStats() throws SQLException
    {
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

        ResultSet resultSet = dao.query(query);

        List<AnimalInSlots> animalInSlotsList = makeListAnimalInSlot(resultSet);

        return animalInSlotsList;
    }

    private List<AnimalInSlots> makeListAnimalInSlot(ResultSet resultSet) throws SQLException
    {
        List<AnimalInSlots> animalInSlotsList = new ArrayList<>();
        while (resultSet.next())
        {
            AnimalInSlots animalInSlots = new AnimalInSlots();
            animalInSlots.setCountOfAnimal(resultSet.getInt(2));

            AnimalType animalType = new AnimalType();
            animalType.setRace(resultSet.getString(3));
            animalType.setType(resultSet.getString(4));

            SlotsForAnimal slotsForAnimal = new SlotsForAnimal();

            slotsForAnimal.setAnimalType(animalType);
            slotsForAnimal.setIdSlotForAnimal(resultSet.getInt(1));
            slotsForAnimal.setArea(resultSet.getInt(5));
            slotsForAnimal.setHeight(resultSet.getInt(6));
            slotsForAnimal.setInside(resultSet.getBoolean(7));


            animalInSlots.setSlotsForAnimal(slotsForAnimal);
            animalInSlotsList.add(animalInSlots);
        }
        return animalInSlotsList;
    }
}