package animalHostel.gui.utils.converters;

import animalHostel.database.entity.JobPosition;
import animalHostel.database.entity.Worker;
import animalHostel.gui.modelsFx.JobPositionFx;
import animalHostel.gui.modelsFx.WorkerFx;
import animalHostel.gui.utils.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Utils.class, ConverterJobPosition.class})
public class ConverterWorkerGuiSpec
{
    @Test
    public void whenConvertToWorkerThenReturnWorker()
    {
        WorkerFx workerFx = prepareWorkerFx();

        JobPosition jobPosition = prepareJobPositionMock();

        Date date = prepareDateMock();

        Worker worker = ConverterWorker.convertToWorker(workerFx);

        Assert.assertEquals(workerFx.getIdWorker(), worker.getIdWorker());
        Assert.assertEquals(workerFx.getSurname(), worker.getSurname());
        Assert.assertEquals(workerFx.getName(), worker.getName());
        Assert.assertEquals(workerFx.getSalary(), worker.getSalary(), 0.0f);
        Assert.assertEquals(workerFx.getEmail(), worker.getEmail());
        Assert.assertEquals(jobPosition, worker.getJobPosition());
        Assert.assertEquals(date, worker.getDateOfEmploym());
    }

    @Test
    public void whenConvertToWorkerFxThenReturnWorkerFx()
    {
        Worker worker = prepareWorker();

        JobPositionFx jobPositionFx = prepareJobPositionFxMock();

        LocalDate localDate = prepareLocalDateMock();

        WorkerFx workerFx = ConverterWorker.convertToWorkerFx(worker);

        Assert.assertEquals(worker.getIdWorker(), workerFx.getIdWorker());
        Assert.assertEquals(worker.getSurname(), workerFx.getSurname());
        Assert.assertEquals(worker.getName(), workerFx.getName());
        Assert.assertEquals(worker.getSalary(), workerFx.getSalary(), 0.0f);
        Assert.assertEquals(worker.getEmail(), workerFx.getEmail());
        Assert.assertEquals(jobPositionFx, workerFx.getJobPositionFx());
        Assert.assertEquals(localDate, workerFx.getDateOfEmploym());
    }


    private WorkerFx prepareWorkerFx()
    {
        WorkerFx workerFx = new WorkerFx();
        workerFx.setIdWorker(1);
        workerFx.setSurname("Kowalski");
        workerFx.setName("Jan");
        workerFx.setSalary(2000.0f);
        workerFx.setEmail("email@kp.pl");
        workerFx.setDateOfEmploym(LocalDate.now());
        workerFx.setJobPositionFx(new JobPositionFx());
        return workerFx;
    }

    private JobPosition prepareJobPositionMock()
    {
        JobPosition jobPosition = new JobPosition();
        jobPosition.setIdJobPosition(1);
        jobPosition.setJobPositionName("Stanowisko");

        PowerMockito.mockStatic(ConverterJobPosition.class);
        PowerMockito.when(ConverterJobPosition.convertToJobPosition(any())).thenReturn(jobPosition);
        return jobPosition;
    }

    private Date prepareDateMock()
    {
        Date date = new Date();

        PowerMockito.mockStatic(Utils.class);
        PowerMockito.when(Utils.convertToDate(any())).thenReturn(date);
        return date;
    }

    private Worker prepareWorker()
    {
        Worker worker = new Worker();
        worker.setIdWorker(1);
        worker.setSurname("Kowalski");
        worker.setName("Jan");
        worker.setSalary(2000.0f);
        worker.setEmail("email@kp.pl");
        worker.setJobPosition(new JobPosition());
        worker.setDateOfEmploym(new Date());
        return worker;
    }


    private JobPositionFx prepareJobPositionFxMock()
    {
        JobPositionFx jobPositionFx = new JobPositionFx();
        jobPositionFx.setIdJobPosition(1);
        jobPositionFx.setJobPositionName("Stanowisko");
        PowerMockito.mockStatic(ConverterJobPosition.class);
        PowerMockito.when( ConverterJobPosition.convertToJobPositionFx( any() ) ).thenReturn(jobPositionFx);
        return jobPositionFx;
    }

    private LocalDate prepareLocalDateMock()
    {
        LocalDate localDate = LocalDate.now();

        PowerMockito.mockStatic(Utils.class);
        PowerMockito.when( Utils.convertToLocalDate( any() ) ).thenReturn(localDate);
        return localDate;
    }
}
