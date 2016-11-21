package by.gsu.repository.util;


import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Vasili on 22.11.2016.
 */
public interface StatementVisitor {
    void visit(Statement statement) throws SQLException;
}
