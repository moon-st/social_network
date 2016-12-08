package by.gsu.repository;

import by.gsu.exption.RepositoryException;
import by.gsu.repository.util.ResultSetVisitor;
import by.gsu.repository.util.StatementVisitor;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * Created by Administrator on 21.11.16.
 */
@Component
public class ConnectionManagerImpl implements ConnectionManager {

    private static volatile Connection conn;

    @Override
    public Connection getConnection() {
        if (conn == null) {
            synchronized (ConnectionManagerImpl.class) {
                if (conn == null) {
                    conn = initConnection();
                }
            }
        }
        return conn;
    }



    @Override
    public <T> T executeQuery(String query, ResultSetVisitor<T> visitor)  {
        try (Statement st  = getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(query);
            T result = visitor.visit(rs);
            rs.close();

            return result;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void executeQuery(String query) {
        try (Statement st  = getConnection().createStatement()) {
            st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }

    public void close() throws SQLException {
        if (!conn.isClosed()) {
            conn.close();
        }
    }

    private Connection initConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:social_network.db");
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }


}
