package animalHostel.gui.controllers.mocks;

import animalHostel.gui.utils.DialogUtils;
import org.powermock.api.mockito.PowerMockito;

import static org.mockito.ArgumentMatchers.any;

public class DialogUtilsMock
{
    public static void prepareDialogUtilsMock() throws Exception
    {
        PowerMockito.mockStatic(DialogUtils.class);
        PowerMockito.doNothing().when(DialogUtils.class, "infoDialog", any());
    }
}
