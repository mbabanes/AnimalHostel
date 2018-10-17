package animalHostel.gui.utils.converters;

import animalHostel.database.entity.JobPosition;
import animalHostel.gui.modelsFx.JobPositionFx;
import org.junit.Assert;
import org.junit.Test;


public class ConverterJobPositionGuiSpec
{
    @Test
    public void whenConvertToJobPositionThenReturnJobPosition()
    {
        JobPositionFx jobPositionFx = prepareJobPositionFx();

        JobPosition jobPosition = ConverterJobPosition.convertToJobPosition(jobPositionFx);

        Assert.assertEquals(jobPositionFx.getIdJobPosition(), jobPosition.getIdJobPosition());
        Assert.assertEquals(jobPositionFx.getJobPositionName(), jobPosition.getJobPositionName());
    }


    @Test
    public void whenConvertToJobPositionFxThenReturnJobPositionFX()
    {
        JobPosition jobPosition = prepareJobPosition();

        JobPositionFx jobPositionFx = ConverterJobPosition.convertToJobPositionFx(jobPosition);

        Assert.assertEquals(jobPosition.getIdJobPosition(), jobPositionFx.getIdJobPosition());
        Assert.assertEquals(jobPosition.getJobPositionName(), jobPositionFx.getJobPositionName());
    }

    private JobPositionFx prepareJobPositionFx()
    {
        JobPositionFx jobPositionFx = new JobPositionFx();
        jobPositionFx.setIdJobPosition(1);
        jobPositionFx.setJobPositionName("Stanowisko");
        return jobPositionFx;
    }

    private JobPosition prepareJobPosition()
    {
        JobPosition jobPosition = new JobPosition();
        jobPosition.setIdJobPosition(1);
        jobPosition.setJobPositionName("Stanowisko");
        return jobPosition;
    }
}
