package by.gsu.repository.util;

import java.sql.ResultSet;

/**
 * Created by Vasili on 22.11.2016.
 */
public interface ResultSetVisitor<T> {
    T visit(ResultSet rs);
}
