package animalHostel.database;

import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class DAOInteg
{
    @Test
    public void whenDAOInstantiatedThenInvokeMakeConnection() throws SQLException
    {
        DAO dao = mock(DAO.class);
        Connection conn = mock(Connection.class);

        when(dao.makeConnection()).thenReturn(conn);

        //dao = spy(DAO.class);
        assertNull(dao.getDbConnection());
        Mockito.verify(dao, times(1)).makeConnection();
    }

    @Test
    public void whenDAOInstantiatedThenConnectIsNotNull() throws SQLException
    {
        DAO dao = new DAO();

        assertNotNull(dao.getDbConnection());
    }


}
