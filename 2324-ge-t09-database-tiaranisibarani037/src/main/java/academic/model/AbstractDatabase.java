package academic.model;

/**
 * @author 12S22037 Tiarani Sibarani
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDatabase {
    static final String USER = "root";
    static final String PASS = "tiarani0987";

    protected String url = "jdbc:mysql://localhost:3306/Academicdb";
    protected Connection connection = null;
    
    public AbstractDatabase(String url) throws SQLException {
        this.url = url;
        this.prepareTables();
    }

    protected Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, USER, PASS);
        }
        return connection;
    }

    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    protected abstract void prepareTables() throws SQLException;{
        this.createTables();
        this.seedTables();
    }

    protected abstract void createTables() throws SQLException;{
        // Create tables
    }

    protected abstract void seedTables() throws SQLException;{
        // Seed tables
    }
}
