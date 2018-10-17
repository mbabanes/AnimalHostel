package animalHostel.gui.modelsFx;

import animalHostel.Hostel;
import animalHostel.database.entity.Animal;
import animalHostel.database.entity.AnimalToHeal;
import animalHostel.gui.HostelSingleton;
import animalHostel.gui.reports.AnimalToHealReport;
import animalHostel.gui.utils.converters.ConverterAnimal;
import animalHostel.gui.utils.converters.ConverterAnimalToHeal;
import com.itextpdf.text.DocumentException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddAnimalToHealModel
{
    private ObjectProperty<AnimalToHealFx> animalToHealModelObjectProperty = new SimpleObjectProperty<>(new AnimalToHealFx());
    private AnimalToHeal animalToHeal;

    public void addAnimalToHeal() throws SQLException
    {
        Hostel hostel = HostelSingleton.getHostel();
        animalToHealModelObjectProperty.get().setDateOfRegister(LocalDate.now());

        int id = hostel.addAnimalToHeal(ConverterAnimalToHeal.convertToAnimalToHeal(animalToHealModelObjectProperty.get()));
        animalToHealModelObjectProperty.get().setIdAnimalToHeal(id);
    }


    private void getAnimal() throws SQLException
    {
        Hostel hostel = HostelSingleton.getHostel();
        Animal animal = hostel.findAnimalById(animalToHealModelObjectProperty().get().getAnimal().getId());

        animalToHealModelObjectProperty.get().setAnimal(ConverterAnimal.convertToAnimalFx(animal));

    }

    public void createReport(String file) throws IOException, DocumentException, SQLException
    {
        this.getAnimal();
        AnimalToHealReport animalToHealReport = new AnimalToHealReport(animalToHealModelObjectProperty.get());

        animalToHealReport.createPdf(file);
    }

    public AnimalFx putAnimal(int idAnimal) throws SQLException
    {
        Hostel hostel = HostelSingleton.getHostel();
        Animal animal = hostel.findAnimalByIdOrName(idAnimal, null).get(0);
        AnimalFx animalFx = ConverterAnimal.convertToAnimalFx(animal);
        this.animalToHealModelObjectProperty.get().setAnimal(animalFx);
        return animalFx;
    }

    public AnimalToHealFx getAnimalToHealModelObjectProperty()
    {
        return animalToHealModelObjectProperty.get();
    }

    public ObjectProperty<AnimalToHealFx> animalToHealModelObjectProperty()
    {
        return animalToHealModelObjectProperty;
    }

    public void setAnimalToHealModelObjectProperty(AnimalToHealFx animalToHealModelObjectProperty)
    {
        this.animalToHealModelObjectProperty.set(animalToHealModelObjectProperty);
    }
}
