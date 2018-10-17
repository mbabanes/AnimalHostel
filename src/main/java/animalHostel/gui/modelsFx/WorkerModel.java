package animalHostel.gui.modelsFx;

import animalHostel.Hostel;
import animalHostel.database.entity.Worker;
import animalHostel.gui.HostelSingleton;
import animalHostel.gui.utils.converters.ConverterWorker;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class WorkerModel
{
    ObjectProperty<WorkerFx> workerFxObjectProperty = new SimpleObjectProperty<>(new WorkerFx());

    ObservableList<WorkerFx> workerFxObservableList = FXCollections.observableArrayList();


    public void getAllPatron() throws SQLException
    {
        Hostel hostel = HostelSingleton.getHostel();

        List<Worker> patrons = hostel.getAllWorkerWithNumberOfPupils();
        workerFxObservableList.clear();
        patrons.forEach(worker -> {
           WorkerFx workerFx = ConverterWorker.convertToWorkerFx(worker);
           workerFxObservableList.add(workerFx);
        });
    }

    public WorkerFx getWorkerFxObjectProperty()
    {
        return workerFxObjectProperty.get();
    }

    public ObjectProperty<WorkerFx> workerFxObjectPropertyProperty()
    {
        return workerFxObjectProperty;
    }

    public void setWorkerFxObjectProperty(WorkerFx workerFxObjectProperty)
    {
        this.workerFxObjectProperty.set(workerFxObjectProperty);
    }

    public ObservableList<WorkerFx> getWorkerFxObservableList()
    {
        return workerFxObservableList;
    }

    public void setWorkerFxObservableList(ObservableList<WorkerFx> workerFxObservableList)
    {
        this.workerFxObservableList = workerFxObservableList;
    }
}
