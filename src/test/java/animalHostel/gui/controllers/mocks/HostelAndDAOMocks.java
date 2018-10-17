package animalHostel.gui.controllers.mocks;

import animalHostel.Hostel;
import animalHostel.database.DAO;
import animalHostel.gui.HostelSingleton;
import org.powermock.api.mockito.PowerMockito;

import java.sql.SQLException;

public class HostelAndDAOMocks
{
    private Hostel hostel;
    private DAO dao;


    public HostelAndDAOMocks() throws SQLException
    {
        preparingMockHostelAndHostelSingleton();

        preparingMockDaoAndSetInHostel();

    }


    public Hostel getHostel()
    {
        return hostel;
    }

    public DAO getDao()
    {
        return dao;
    }

    private void preparingMockHostelAndHostelSingleton() throws SQLException
    {
        hostel = PowerMockito.mock(Hostel.class);

        PowerMockito.mockStatic(HostelSingleton.class);
        PowerMockito.when(HostelSingleton.getHostel()).thenReturn(hostel);
    }

    private void preparingMockDaoAndSetInHostel()
    {
        dao = PowerMockito.mock(DAO.class);
        hostel.setDao(dao);
    }

}
