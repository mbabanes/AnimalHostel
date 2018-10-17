package animalHostel.database;

import java.sql.*;

public class DAO
{
    public static final String DB_URL = "jdbc:mysql://localhost:3306/animalhostel?useUnicode=true&characterEncoding=UTF-8";
    public static final String DB_USR = "root";
    public static final String DB_PASS = "";

    private static Connection dbConnection;



    public DAO() throws SQLException
    {
        if(dbConnection == null)
            dbConnection = makeConnection();
    }

    protected DAO(Connection connection)
    {
        dbConnection = connection;
    }

    protected Connection makeConnection() throws SQLException
    {
        return DriverManager.getConnection(DB_URL, DB_USR, DB_PASS);
    }


    public ResultSet query(String query) throws SQLException
    {
        return dbConnection.createStatement().executeQuery(query);
    }

    public Connection getDbConnection()
    {
        return dbConnection;
    }

    public boolean insert(String query) throws SQLException
    {
        return dbConnection.createStatement().execute(query);
    }

    public int insertWithIdReturning(String query) throws SQLException
    {
        PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        int idAnimal = executeQueryAndGetId(ps);

        return idAnimal;
    }

    public int insertAnimalToHeal(String query) throws SQLException
    {
        PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        return executeQueryAndGetId(ps);
    }

    public boolean update(String query) throws SQLException
    {
        return dbConnection.createStatement().execute(query);
    }

    private int executeQueryAndGetId(PreparedStatement pr) throws SQLException
    {
        int idAnimal = -1;
        final int idAnimalColumn = 1;
        pr.executeUpdate();
        ResultSet rs = pr.getGeneratedKeys();
        if (rs.next())
        {
            idAnimal = rs.getInt(idAnimalColumn);
        }
        return idAnimal;
    }
}