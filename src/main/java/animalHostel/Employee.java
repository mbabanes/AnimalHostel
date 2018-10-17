package animalHostel;

public class Employee
{
    private int id;
    private String name;
    private String surname;
    private int jobPosition;


    public Employee(){}

    public Employee(int id, String name, String surname, int jobPosition)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.jobPosition = jobPosition;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }


    @Override
    public String toString()
    {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", jobPosition=" + jobPosition +
                '}';
    }
}
