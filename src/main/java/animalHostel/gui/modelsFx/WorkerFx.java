package animalHostel.gui.modelsFx;

import javafx.beans.property.*;

import java.time.LocalDate;

public class WorkerFx
{
    private IntegerProperty idWorker = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private DoubleProperty salary = new SimpleDoubleProperty();
    private ObjectProperty<JobPositionFx> jobPositionFx = new SimpleObjectProperty<>();
    private StringProperty email = new SimpleStringProperty();
    private ObjectProperty<LocalDate> dateOfEmploym = new SimpleObjectProperty<>();

    private IntegerProperty numberOfPupils = new SimpleIntegerProperty();

    public int getNumberOfPupils()
    {
        return numberOfPupils.get();
    }

    public IntegerProperty numberOfPupilsProperty()
    {
        return numberOfPupils;
    }

    public void setNumberOfPupils(int numberOfPupils)
    {
        this.numberOfPupils.set(numberOfPupils);
    }

    public int getIdWorker()
    {
        return idWorker.get();
    }

    public IntegerProperty idWorkerProperty()
    {
        return idWorker;
    }

    public void setIdWorker(int idWorker)
    {
        this.idWorker.set(idWorker);
    }

    public String getName()
    {
        return name.get();
    }

    public StringProperty nameProperty()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    public String getSurname()
    {
        return surname.get();
    }

    public StringProperty surnameProperty()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname.set(surname);
    }

    public double getSalary()
    {
        return salary.get();
    }

    public DoubleProperty salaryProperty()
    {
        return salary;
    }

    public void setSalary(double salary)
    {
        this.salary.set(salary);
    }

    public JobPositionFx getJobPositionFx()
    {
        return jobPositionFx.get();
    }

    public ObjectProperty<JobPositionFx> jobPositionFxProperty()
    {
        return jobPositionFx;
    }

    public void setJobPositionFx(JobPositionFx jobPositionFx)
    {
        this.jobPositionFx.set(jobPositionFx);
    }

    public String getEmail()
    {
        return email.get();
    }

    public StringProperty emailProperty()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email.set(email);
    }

    public LocalDate getDateOfEmploym()
    {
        return dateOfEmploym.get();
    }

    public ObjectProperty<LocalDate> dateOfEmploymProperty()
    {
        return dateOfEmploym;
    }

    public void setDateOfEmploym(LocalDate dateOfEmploym)
    {
        this.dateOfEmploym.set(dateOfEmploym);
    }

    @Override
    public String toString()
    {
        return surname.get() + " " + name.get();
    }
}
