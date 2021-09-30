package data;

import config.Config;
import exception.DatabaseException;
import org.apache.commons.dbcp2.BasicDataSource;
import program.Shutdownable;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory implements Shutdownable {

    private BasicDataSource basicDataSource = null;

    private ConnectionFactory() {
        setUp();
    }

    private static ConnectionFactory INSTANCE;

    public static ConnectionFactory getInstance() {
        if (INSTANCE == null) INSTANCE = new ConnectionFactory();
        return INSTANCE;
    }

    private void setUp() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:sqlserver://" + Config.dbUrl + ";database=" + Config.dbName);
        basicDataSource.setUsername(Config.dbUsername);
        basicDataSource.setPassword(Config.dbPassword);
        basicDataSource.setMinIdle(2);
        basicDataSource.setMaxIdle(5);
        basicDataSource.setMaxOpenPreparedStatements(100);
    }

    public Connection createConnection() throws DatabaseException {
        try {
            return basicDataSource.getConnection();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    private void close() {
        if (basicDataSource != null) {
            try {
                basicDataSource.close();
            } catch (SQLException e) {
                System.out.println("Close exception");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void shutdown() {
        close();
    }
}
