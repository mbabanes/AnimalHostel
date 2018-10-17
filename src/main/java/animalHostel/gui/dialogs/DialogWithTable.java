package animalHostel.gui.dialogs;

import animalHostel.gui.utils.FXMLUtils;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.util.Optional;

public abstract class DialogWithTable<T>
{
    private Dialog<T> dialog;
    private ButtonType choose;
    private T result;
    private TableView<T> tbl;

    protected String tableViewId;
    protected String formPath;
    protected String dialogTitle;
    protected String dialogHeader;

    protected String dialogId;

    public T showAndGetResult()
    {
        Optional<T> resultFromDialog = dialog.showAndWait();

        T result = null;
        if (resultFromDialog.isPresent())
            result = resultFromDialog.get();

        return result;
    }

    protected void make()
    {
        dialog = new Dialog<>();
        addButtonType();
        setTitle();
        putFindFormAndMarkTableView();
        bindWithChooseButtonAndSetIdButton();
        setResult();
    }

    private void addButtonType()
    {
        dialog.setResizable(true);
        choose = new ButtonType(FXMLUtils.getResourceBundle().getString("choose"), ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(choose, ButtonType.CANCEL);
    }

    private void setTitle()
    {
        dialog.setTitle(dialogTitle);
        dialog.setHeaderText(dialogHeader);
        dialog.getDialogPane().setId(dialogId);
    }

    private void putFindFormAndMarkTableView()
    {
        VBox vBox = (VBox) FXMLUtils.fxmlLoader(formPath);

        tbl = (TableView<T>) vBox.lookup(tableViewId);


        dialog.getDialogPane().setContent(vBox);
    }

    private void bindWithChooseButtonAndSetIdButton()
    {
        Node chooseButton = dialog.getDialogPane().lookupButton(choose);
        chooseButton.setDisable(true);


        tbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            chooseButton.setDisable(observable == null);
            this.result = observable.getValue();
        });

        chooseButton.setId("chooseButtonType");
    }

    private void setResult()
    {
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == choose)
            {
                return result;
            }
            return null;
        });
    }
}
