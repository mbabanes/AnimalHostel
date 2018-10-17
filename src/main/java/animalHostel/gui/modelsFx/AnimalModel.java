package animalHostel.gui.modelsFx;

import animalHostel.Hostel;
import animalHostel.database.entity.Animal;
import animalHostel.gui.HostelSingleton;
import animalHostel.gui.utils.Utils;
import animalHostel.gui.utils.converters.ConverterAnimal;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

import java.time.LocalDate;

import java.util.Date;

import java.util.List;
import java.util.Random;

public class AnimalModel
{
    private ObservableList<AnimalFx> animalFxObservableList = FXCollections.observableArrayList();
    private ObjectProperty<AnimalFx> animalFxObjectProperty = new SimpleObjectProperty<>(new AnimalFx());

    private int lastIndexOfRow = 0;
    private int dataPortion = 1;

    private IntegerProperty idAnimalProperty = new SimpleIntegerProperty();
    private StringProperty nameOfAnimalProperty = new SimpleStringProperty();

    static Random rand = new Random();
    public void getAnimals() throws SQLException
    {
        Hostel animalHostel = HostelSingleton.getHostel();
        List<Animal> animalList = animalHostel.getAllAnimalWithLimitOfResult(lastIndexOfRow, dataPortion);

        if (animalList.size() > 0)
        {
            animalList.forEach(animal -> {
                AnimalFx animalFx = ConverterAnimal.convertToAnimalFx(animal);

                animalFxObservableList.add(animalFx);

            });

            lastIndexOfRow += dataPortion;
        }
//        fillListWithFakeData();
    }

    public void nextData()
    {
        fillListWithFakeData();
    }

    public void findAnimalByIdOrName() throws SQLException
    {
        Hostel animalHostel = HostelSingleton.getHostel();
        List<Animal> animalList = animalHostel.findAnimalByIdOrName(idAnimalProperty.get(), nameOfAnimalProperty.get());
        animalFxObservableList.clear();
        animalList.forEach(animal -> {
            AnimalFx animalFx = ConverterAnimal.convertToAnimalFx(animal);
            animalFxObservableList.add(animalFx);
        });
    }

    private void fillListWithFakeData()
    {
        for (int i = 1; i < 10; i++)
        {
            AnimalFx animalFx = new AnimalFx();
            animalFx.setId(i);
            animalFx.setWeight(10);
            WorkerFx patron = new WorkerFx();
            patron.setIdWorker(1);
            patron.setName("Jan");
            patron.setSurname("Kowalski");
            animalFx.setPatron(patron);
            animalFx.setDateOfRegister(Utils.convertToLocalDate(new Date()));
            animalFx.setBirthDay(LocalDate.now());
            animalFx.setSlot(1);

            AnimalTypeFx animalTypeFx = new AnimalTypeFx();
            animalTypeFx.setIdAnimalType(1);
            animalTypeFx.setType("Pies");
            animalTypeFx.setRace("Owczarek niemiecki");
            animalFx.setAnimalTypeFx(animalTypeFx);
            animalFx.setName("Azor");
            animalFx.setColor("Brąz");

            animalFxObservableList.add(animalFx);
        }
    }

    public ObservableList<AnimalFx> getAnimalFxObservableList()
    {
        return animalFxObservableList;
    }


    public void setAnimalFxObservableList(ObservableList<AnimalFx> animalFxObservableList)
    {
        this.animalFxObservableList = animalFxObservableList;
    }

    public AnimalFx getAnimalFxObjectProperty()
    {
        return animalFxObjectProperty.get();
    }

    public ObjectProperty<AnimalFx> animalFxObjectPropertyProperty()
    {
        return animalFxObjectProperty;
    }

    public void setAnimalFxObjectProperty(AnimalFx animalFxObjectProperty)
    {
        this.animalFxObjectProperty.set(animalFxObjectProperty);
    }

    public int getIdAnimalProperty()
    {
        return idAnimalProperty.get();
    }

    public IntegerProperty idAnimalProperty()
    {
        return idAnimalProperty;
    }

    public void setIdAnimalProperty(int idAnimalProperty)
    {
        this.idAnimalProperty.set(idAnimalProperty);
    }

    public String getNameOfAnimalProperty()
    {
        return nameOfAnimalProperty.get();
    }

    public StringProperty nameOfAnimalProperty()
    {
        return nameOfAnimalProperty;
    }

    public void setNameOfAnimalProperty(String nameOfAnimalProperty)
    {
        this.nameOfAnimalProperty.set(nameOfAnimalProperty);
    }
}
