/**
 * @author winic
 */
package wbarcellosn.main.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;

    private ConnectionFactory(Connection connection){}

    public static Connection getConnection() throws SQLException {
        if (connection == null){
            connection = initConnection();
        }
        else if(connection != null && connection.isClosed()){
            connection = initConnection();
        }

        return connection;
    }

    private static Connection initConnection() {

        try{
           return DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_projeto_3", "postgres", "Wi@0213#ni96");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
