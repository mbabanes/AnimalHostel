package animalHostel.gui.utils.converters;

import animalHostel.database.entity.Worker;
import animalHostel.gui.modelsFx.WorkerFx;
import animalHostel.gui.utils.Utils;

import java.util.GregorianCalendar;

public class ConverterWorker
{
    public static WorkerFx convertToWorkerFx(Worker worker)
    {
        WorkerFx workerFx = new WorkerFx();
        workerFx.setIdWorker(worker.getIdWorker());
        workerFx.setEmail(worker.getEmail());
        workerFx.setName(worker.getName());
        workerFx.setSurname(worker.getSurname());
        workerFx.setSalary(worker.getSalary());

        workerFx.setNumberOfPupils(worker.getNumberOfPupils());

        if (worker.getDateOfEmploym() != null)
            workerFx.setDateOfEmploym(Utils.convertToLocalDate(worker.getDateOfEmploym()));



        if (worker.getJobPosition() != null)
            workerFx.setJobPositionFx(ConverterJobPosition.convertToJobPositionFx(worker.getJobPosition()));

        return workerFx;
    }

    public static Worker convertToWorker(WorkerFx workerFx)
    {
        Worker worker = new Worker();
        worker.setIdWorker(workerFx.getIdWorker());
        worker.setName(workerFx.getName());
        worker.setSurname(workerFx.getSurname());
        worker.setSalary(workerFx.getSalary());
        if (workerFx.getJobPositionFx() != null)
            worker.setJobPosition(ConverterJobPosition.convertToJobPosition(workerFx.getJobPositionFx()));
        worker.setEmail(workerFx.getEmail());

        if (workerFx.getDateOfEmploym() != null)
            worker.setDateOfEmploym(Utils.convertToDate(workerFx.getDateOfEmploym()));


        return worker;
    }
}
