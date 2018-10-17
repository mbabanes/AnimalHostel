package animalHostel.gui.modelsFx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JobPositionFx
{
    private IntegerProperty idJobPosition = new SimpleIntegerProperty();
    private StringProperty jobPositionName = new SimpleStringProperty();

    public int getIdJobPosition()
    {
        return idJobPosition.get();
    }

    public IntegerProperty idJobPositionProperty()
    {
        return idJobPosition;
    }

    public void setIdJobPosition(int idJobPosition)
    {
        this.idJobPosition.set(idJobPosition);
    }

    public String getJobPositionName()
    {
        return jobPositionName.get();
    }

    public StringProperty jobPositionNameProperty()
    {
        return jobPositionName;
    }

    public void setJobPositionName(String jobPositionName)
    {
        this.jobPositionName.set(jobPositionName);
    }
}
