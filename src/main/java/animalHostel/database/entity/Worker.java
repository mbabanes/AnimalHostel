package animalHostel.database.entity;

import java.util.Date;

public class Worker
{
    private int idWorker;
    private String name;
    private String surname;
    private double salary;
    private JobPosition jobPosition;
    private String email;
    private Date dateOfEmploym;

    private int numberOfPupils;


    public Worker(int idWorker)
    {
        this.idWorker = idWorker;
    }


    public Worker(String name, String surname, double salary, JobPosition jobPosition, String email, Date dateOfEmploym)
    {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.jobPosition = jobPosition;
        this.email = email;
        this.dateOfEmploym = dateOfEmploym;
    }

    public Worker(int idWorker, String name, String surname)
    {
        this.idWorker = idWorker;
        this.name = name;
        this.surname = surname;
    }

    public Worker()
    {

    }

    public int getIdWorker()
    {
        return idWorker;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public double getSalary()
    {
        return salary;
    }

    public JobPosition getJobPosition()
    {
        return jobPosition;
    }

    public Date getDateOfEmploym()
    {
        return dateOfEmploym;
    }

    public String getEmail()
    {
        return email;
    }

    public void setIdWorker(int idWorker)
    {
        this.idWorker = idWorker;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public int getNumberOfPupils()
    {
        return numberOfPupils;
    }

    public void setNumberOfPupils(int numberOfPupils)
    {
        this.numberOfPupils = numberOfPupils;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public void setJobPosition(JobPosition jobPosition)
    {
        this.jobPosition = jobPosition;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setDateOfEmploym(Date dateOfEmploym)
    {
        this.dateOfEmploym = dateOfEmploym;
    }

    @Override
    public String toString()
    {
        return "Worker{" +
                "idWorker=" + idWorker +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", jobPosition=" + jobPosition +
                ", email='" + email + '\'' +
                '}';
    }
}
