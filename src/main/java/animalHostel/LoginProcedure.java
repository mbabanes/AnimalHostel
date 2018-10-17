package animalHostel;

import animalHostel.database.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginProcedure
{

    private String login;
    private String password;
    private DAO DAO;

    public LoginProcedure() throws SQLException
    {
        DAO = new DAO();
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Employee login() throws SQLException
    {
        return findEmployeeByLoginAndPassword();
    }

    protected Employee findEmployeeByLoginAndPassword() throws SQLException
    {
        ResultSet result = DAO.query("SELECT * FROM worker WHERE id_worker=" + login + " AND password=" + password);

        if(result.next())
        {
            return new Employee(result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getInt("job_position"));
        }

        return null;
    }


    protected void setDAO(DAO DAO)
    {
        this.DAO = DAO;
    }

    protected String getPassword()
    {
        return password;
    }

    protected String getLogin()
    {
        return login;
    }
}
