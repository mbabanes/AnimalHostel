package animalHostel.gui.dialogs;

import animalHostel.gui.modelsFx.AnimalFx;
import animalHostel.gui.utils.FXMLUtils;

public class AnimalFindDialog extends DialogWithTable<AnimalFx>
{
    public AnimalFindDialog()
    {
        setMetaData();
        super.make();
    }

    private void setMetaData()
    {
        super.formPath = "/fxml/chooseAnimalToHeal.fxml";
        super.tableViewId = "#animalTableView";
        super.dialogTitle = FXMLUtils.getResourceBundle().getString("animal.choose");
        super.dialogHeader = FXMLUtils.getResourceBundle().getString("animal.choose");
    }
}
