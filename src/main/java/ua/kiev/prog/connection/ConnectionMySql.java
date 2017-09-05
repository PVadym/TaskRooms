package ua.kiev.prog.connection;

import org.apache.log4j.Logger;
import ua.kiev.prog.DAO.FlatDaoJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Вадим on 02.09.2017.
 */
public class ConnectionMySql {


    private static Logger LOGGER = Logger.getLogger(ConnectionMySql.class);

    private Connection connection;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/roomsdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection(){
        try {
            connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            LOGGER.info("Connection created successfully!");
        } catch (SQLException e) {
            LOGGER.info("Connection eror!");
            throw new RuntimeException(e.getMessage());
        }
        return connection;
    }

    public void close(){
        if(connection!=null){
            try {
                connection.close();
                LOGGER.info("Connection closed!");
            } catch (SQLException e) {
                LOGGER.error("Error during close connection " + e.getMessage());
            }
        }
    }
}
