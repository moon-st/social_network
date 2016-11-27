package by.gsu.repository;

import by.gsu.repository.util.ResultSetVisitor;

import java.sql.Connection;

/**
 * Created by Administrator on 21.11.16.
 */
public interface ConnectionManager {
    Connection getConnection();

    <T> T executeQuery(String query, ResultSetVisitor<T> visitor);

    void executeQuery(String query);


}
