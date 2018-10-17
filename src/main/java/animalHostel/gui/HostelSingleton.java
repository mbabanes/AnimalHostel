package animalHostel.gui;

import animalHostel.Hostel;
import animalHostel.database.DAO;

import java.sql.SQLException;

public class HostelSingleton
{
    private static Hostel animalHostel = null;

    public static Hostel getHostel() throws SQLException
    {
        if (animalHostel == null)
        {
            animalHostel = new Hostel();
            animalHostel.setDao(new DAO());
            return animalHostel;
        }
        return animalHostel;
    }


    public static void closeConnection() throws SQLException
    {
        animalHostel.getDao().getDbConnection().close();
        animalHostel = null;
    }
}
