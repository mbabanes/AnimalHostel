package animalHostel.database;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.plaf.nimbus.State;

import static org.mockito.Mockito.*;

import java.sql.*;

import static org.junit.Assert.*;

public class DAOSpec
{
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Connection conn;
    private Statement statement;
    private DAO dao;

    @Test
    public void whenInstantiatedDAOThenHasURLSet()
    {
        String url = "jdbc:mysql://localhost:3306/animalhostel?useUnicode=true&characterEncoding=UTF-8";
        assertEquals(url, DAO.DB_URL);
    }

    @Test
    public void whenInstantiatedDAOThenHasUserSet()
    {
        String user = "root";
        assertEquals(user, DAO.DB_USR);
    }

    @Test
    public void whenInstantiatedDAOThenHasPasswordSet()
    {
        String pass = "";
        assertEquals(pass, DAO.DB_PASS);
    }

    @Test
    public void whenQueryIsCorrectThenReturnResultSet() throws SQLException
    {
        prepareMocks();

        String correctQuery = "SELECT * FROM worker";
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString(1)).thenReturn("Foo");

        when(conn.createStatement()).thenReturn(statement);
        when(conn.createStatement().executeQuery(correctQuery)).thenReturn(resultSet);

        assertEquals("Foo", dao.query(correctQuery).getString(1));
    }

    @Test
    public void whenQueryIsIncorrectThenThrowSQLException() throws SQLException
    {
        prepareMocks();
        String incorrectQuery = "asdwq";

        when(conn.createStatement()).thenReturn(statement);
        when(conn.createStatement().executeQuery(incorrectQuery)).thenThrow(new SQLException("IncorrectQuery"));

        exception.expect(SQLException.class);
        dao.query(incorrectQuery);
    }

    @Test
    public void whenInvokeInsertThenInvokeExecute() throws SQLException
    {
        prepareMocks();
        when(conn.createStatement()).thenReturn(statement);
        when(conn.createStatement().execute(any())).thenReturn(true);

        dao.insert("query");

        verify(conn.createStatement(), times(1)).execute(any());
    }

    @Test
    public void whenInvokeUpdateThenInvokeExecute() throws SQLException
    {
        prepareMocks();
        when(conn.createStatement()).thenReturn(statement);
        when(conn.createStatement().execute(any())).thenReturn(true);

        dao.update("query");

        verify(conn.createStatement(), times(1)).execute(any());
    }

    private void prepareMocks()
    {
        conn = mock(Connection.class);
        statement = mock(Statement.class);
        dao = new DAO(conn);
    }

    @Test
    public void whenDAOisInstantiatedThenDbConnectionIsSet() throws SQLException
    {
        Connection conn = mock(Connection.class);




    }
}
