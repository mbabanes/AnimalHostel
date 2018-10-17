package animalHostel.database.entity;

public class JobPosition
{
    private int idJobPosition;
    private String jobPositionName;

    public JobPosition(int idJobPosition)
    {
        this.idJobPosition = idJobPosition;
    }

    public JobPosition()
    {

    }


    public int getIdJobPosition()
    {
        return idJobPosition;
    }

    public String getJobPositionName()
    {
        return jobPositionName;
    }

    public void setIdJobPosition(int idJobPosition)
    {
        this.idJobPosition = idJobPosition;
    }

    public void setJobPositionName(String jobPositionName)
    {
        this.jobPositionName = jobPositionName;
    }

    @Override
    public String toString()
    {
        return "JobPosition{" +
                "idJobPosition=" + idJobPosition +
                ", jobPositionName='" + jobPositionName + '\'' +
                '}';
    }
}
