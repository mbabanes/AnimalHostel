package animalHostel.gui.utils.converters;

import animalHostel.database.entity.JobPosition;
import animalHostel.gui.modelsFx.JobPositionFx;

public class ConverterJobPosition
{
    public static JobPositionFx convertToJobPositionFx(JobPosition jobPosition)
    {
        JobPositionFx jobPositionFx = new JobPositionFx();
        jobPositionFx.setIdJobPosition(jobPosition.getIdJobPosition());
        jobPositionFx.setJobPositionName(jobPosition.getJobPositionName());

        return jobPositionFx;
    }

    public static JobPosition convertToJobPosition(JobPositionFx jobPositionFx)
    {
        JobPosition jobPosition = new JobPosition();
        jobPosition.setIdJobPosition(jobPositionFx.getIdJobPosition());
        jobPosition.setJobPositionName(jobPositionFx.getJobPositionName());

        return jobPosition;
    }
}
