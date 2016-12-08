package by.gsu.repository;

import by.gsu.repository.util.ResultSetVisitor;
import by.gsu.repository.util.StatementVisitor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Administrator on 21.11.16.
 */
public interface ConnectionManager {
    Connection getConnection();

    <T> T executeQuery(String query, ResultSetVisitor<T> visitor);

    void executeQuery(String query);


}
