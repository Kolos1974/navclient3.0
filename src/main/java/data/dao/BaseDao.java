package data.dao;

import data.ConnectionFactory;
import exception.DatabaseException;

import java.sql.Connection;

public abstract class BaseDao {

    protected Connection getConnection() throws DatabaseException {
        return ConnectionFactory.getInstance().createConnection();
    }

    protected abstract String getTableName();

}
