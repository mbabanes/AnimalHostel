package animalHostel.gui.dialogs;

import animalHostel.gui.modelsFx.AnimalTypeFx;
import animalHostel.gui.utils.FXMLUtils;

public class AnimalTypeFind extends DialogWithTable<AnimalTypeFx>
{
    public AnimalTypeFind()
    {
        setMetaData();
        super.make();
    }

    private void setMetaData()
    {
        super.formPath = "/fxml/FindType.fxml";
        super.tableViewId = "#animalTypesTable";
        super.dialogTitle = FXMLUtils.getResourceBundle().getString("find.animalType");
        super.dialogHeader = FXMLUtils.getResourceBundle().getString("find.animalType");
    }

}
