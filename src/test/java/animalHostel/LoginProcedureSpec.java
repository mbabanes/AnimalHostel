package animalHostel;

import animalHostel.database.DAO;

import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginProcedureSpec
{
    private LoginProcedure loginProcedure;

    @Before
    public void before() throws SQLException
    {
        loginProcedure = spy(new LoginProcedure());
    }

    @Test
    public void whenLoginThenInvokeFindEmployeeByLoginAndPass() throws SQLException
    {
        doReturn(new Employee()).when(loginProcedure).findEmployeeByLoginAndPassword();
        loginProcedure.login();
        verify(loginProcedure).login();
    }

    @Test
    public void whenSetPasswordThenPasswordIsIn()
    {
        loginProcedure.setPassword("pass");

        assertEquals("pass", loginProcedure.getPassword());
    }

    @Test
    public void whenSetLoginThenLoginIsIn()
    {
        loginProcedure.setLogin("login");
        assertEquals("login", loginProcedure.getLogin());
    }


    @Test
    public void whenLoginProcedureThenInvokeQueryFormDaoObject() throws SQLException
    {
        DAO dao = mock(DAO.class);

        ResultSet resultSet = mock(ResultSet.class);

        loginProcedure.setDAO(dao);

        loginProcedure.setLogin("1");
        loginProcedure.setPassword("pass");

        String query = "SELECT * FROM worker WHERE id_worker=" + loginProcedure.getLogin() + " AND password=" + loginProcedure.getPassword();
        when(dao.query(query)).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        loginProcedure.login();

        verify(dao, times(1)).query(query);
    }

    @Test
    public void whenIdAndPasswordIsCorrectThenReturnEmployee() throws SQLException
    {
        DAO dao = mock(DAO.class);

        ResultSet resultSet = mock(ResultSet.class);

        loginProcedure.setDAO(dao);

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getString(2)).thenReturn("Jan");
        when(resultSet.getString(3)).thenReturn("Kowalski");
        when(resultSet.getInt("job_position")).thenReturn(1);

        when(dao.query(anyString())).thenReturn(resultSet);

        Employee expected = new Employee(1, "Jan", "Kowalski", 1);

        assertEquals(expected.toString(), loginProcedure.login().toString());
    }

    @Test
    public void whenIdAndPasswordIsIncorrectThenReturnNull() throws SQLException
    {
        DAO dao = mock(DAO.class);

        ResultSet resultSet = mock(ResultSet.class);

        loginProcedure.setDAO(dao);

        when(resultSet.next()).thenReturn(false);
        when(dao.query(anyString())).thenReturn(resultSet);

        assertNull(loginProcedure.login());
    }
}
