package animalHostel.gui.utils;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.util.Optional;
import java.util.ResourceBundle;

public class DialogUtils
{
    static ResourceBundle bundle = FXMLUtils.getResourceBundle();

//    public static void dialogAboutApplication()
//    {
//        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
//        informationAlert.setTitle(bundle.getString("about.title"));
//        informationAlert.setHeaderText(bundle.getString("about.header"));
//        informationAlert.setContentText(bundle.getString("about.content"));
//        informationAlert.showAndWait();
//    }
//
    public static Optional<ButtonType> confirmationAndSavingConfirmationDialog(String title, String header)
    {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(title);
        confirmationDialog.setHeaderText(header);

        confirmationDialog.getButtonTypes().remove(0, 2);
        ButtonType saveButton = new ButtonType(bundle.getString("save"), ButtonBar.ButtonData.OK_DONE);
        ButtonType noButton = new ButtonType(bundle.getString("no"), ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmationDialog.getButtonTypes().add(0, saveButton);
        confirmationDialog.getButtonTypes().add(1, noButton);

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result;
    }
//
    public static void errorDialog(String error)
    {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);


        errorAlert.setTitle(bundle.getString("error.title"));
        errorAlert.setHeaderText(bundle.getString("error.header"));

        TextArea textArea = new TextArea(error);

        errorAlert.getDialogPane().setContent(textArea);

        errorAlert.showAndWait();

    }

    public static void infoDialog(String info)
    {
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setHeaderText(info);
        //informationAlert.getButtonTypes().get
        informationAlert.showAndWait();
    }
//
//    public static String editDialog(String value)
//    {
//        TextInputDialog dialog = new TextInputDialog(value);
//        dialog.setTitle(bundle.getString("edit.tile"));
//        dialog.setHeaderText(bundle.getString("edit.header"));
//        dialog.setContentText(bundle.getString("edit.content"));
//
//        Optional<String> result = dialog.showAndWait();
//        if (result.isPresent())
//            return result.get();
//        return null;
//    }
}
