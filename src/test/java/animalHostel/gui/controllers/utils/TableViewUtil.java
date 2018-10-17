package animalHostel.gui.controllers.utils;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.util.List;

public class TableViewUtil
{
    public static TableRow getTableRowToSelect(TableView tableView)
    {
        List<Node> current = tableView.getChildrenUnmodifiable();

        while (current.size() == 1)
        {
            current = ((Parent) current.get(0)).getChildrenUnmodifiable();
        }

        current = ((Parent) current.get(1)).getChildrenUnmodifiable();

        while (!(current.get(0) instanceof TableRow))
        {
            current = ((Parent) current.get(0)).getChildrenUnmodifiable();
        }

        Node node = current.get(0);
        TableRow tableRow = (TableRow) node;
        return tableRow;
    }
}
