package animalHostel.gui.dialogs;

import animalHostel.gui.modelsFx.AnimalInSlotsFx;
import animalHostel.gui.utils.FXMLUtils;

public class ChooseSlot extends DialogWithTable<AnimalInSlotsFx>
{
    public ChooseSlot()
    {
        super.dialogTitle = FXMLUtils.getResourceBundle().getString("slot.chooseSlot");
        super.dialogHeader = FXMLUtils.getResourceBundle().getString("slot.chooseSlot");
        super.formPath = "/fxml/chooseSlot.fxml";
        super.tableViewId = "#slotsTableView";

        super.make();
    }
}
