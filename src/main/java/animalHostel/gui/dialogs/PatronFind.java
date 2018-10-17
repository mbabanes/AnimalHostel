package animalHostel.gui.dialogs;

import animalHostel.gui.modelsFx.WorkerFx;
import animalHostel.gui.utils.FXMLUtils;

public class PatronFind extends DialogWithTable<WorkerFx>
{
    public PatronFind()
    {
        setMetaData();
        super.make();
    }

    private void setMetaData()
    {
        super.dialogTitle = FXMLUtils.getResourceBundle().getString("findPatron.title");
        super.dialogHeader = FXMLUtils.getResourceBundle().getString("findPatron.title");
        super.formPath = "/fxml/findPatron.fxml";
        super.tableViewId = "#patronsTableView";
        super.dialogId = "choosePatronDialog";
    }
}
