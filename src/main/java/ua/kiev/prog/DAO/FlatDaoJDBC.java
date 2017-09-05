package ua.kiev.prog.DAO;

import ua.kiev.prog.entity.Flat;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Вадим on 02.09.2017.
 */
public class FlatDaoJDBC implements FlatDAO {

    private static Logger LOGGER = Logger.getLogger(FlatDaoJDBC.class);

    private Connection connection;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS flats (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "district VARCHAR(20) NOT NULL, " +
            "address VARCHAR(50) NOT NULL, " +
            "square DOUBLE DEFAULT 0, " +
            "rooms INT DEFAULT 0," +
            "price INT DEFAULT 0)";

    private static final String SAVE = "INSERT INTO flats (district,address,square,rooms,price) VALUES(?,?,?,?,?)";

    private static final String DELETE = "DELETE FROM flats WHERE id = ?";

    private static final String UPDATE = "UPDATE flats SET price = ? WHERE id = ?";

    private static final String FIND_ALL = "SELECT * FROM flats";

    private static final String FIND_BY_PARAMS = "SELECT * FROM flats WHERE district LIKE ? AND address LIKE ? AND square >=? AND rooms >=? AND price <=? ";

    private static final String GET_LAST_ID = "SELECT LAST_INSERT_ID()";

    public FlatDaoJDBC(Connection connection) {
        this.connection = connection;
        initDB();
    }

    @Override
    public void add(Flat flat) {

        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
                 PreparedStatement psLastInsertedId = connection.prepareStatement(GET_LAST_ID)) {
                preparedStatement.setString(1, flat.getDistrict());
                preparedStatement.setString(2, flat.getAddress());
                preparedStatement.setDouble(3, flat.getSquare());
                preparedStatement.setInt(4, flat.getRooms());
                preparedStatement.setInt(5, flat.getPrice());
                preparedStatement.execute();
                ResultSet resultSet = psLastInsertedId.executeQuery();
                resultSet.next();
                int id = resultSet.getInt(1);
                LOGGER.info("Flat successfully stored in db with id " + id);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during saving operation, cause - " + e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            }
            LOGGER.info("Flat successfully deleted from db with id " + id);
        } catch (SQLException e) {
            LOGGER.error("Error during remove operation, cause - " + e.getMessage());
        }

    }

    @Override
    public void updatePrice(int id, int price) {
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                preparedStatement.setInt(1, price);
                preparedStatement.setInt(2, id);
                preparedStatement.execute();
            }
            LOGGER.info("Flat successfully updated in db with id " + id);
        } catch (SQLException e) {
            LOGGER.error("Error during updating operation, cause - " + e.getMessage());
        }

    }

    @Override
    public List<Flat> findAll() {
        List<Flat> flats = new ArrayList<>();
        try {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                    flats = getFlats(resultSet);
                }
            }
            if (flats.isEmpty()){
                LOGGER.warn("Empty list!");
            } else{
            LOGGER.info("All flats get successfully!");
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting operation, cause - " + e.getMessage());
        }
        return flats;

    }

    @Override
    public List<Flat> findByParams(String district, String address, double square, int rooms, int price){
        List<Flat> flats = new ArrayList<>();
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PARAMS)) {
                preparedStatement.setString(1, "%" + district + "%");
                preparedStatement.setString(2, "%" + address + "%");
                preparedStatement.setDouble(3, square);
                preparedStatement.setInt(4, rooms);
                preparedStatement.setInt(5, price);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    flats = getFlats(resultSet);
                }
            }
            if (flats.isEmpty()){
                LOGGER.warn("Empty list!");
            } else{
                LOGGER.info("All flats get successfully!");
            }
        } catch (SQLException e) {
            LOGGER.error("Error during Find by Parameters method, cause - " + e.getMessage());
        }
        return flats;
    }

    private void initDB() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE);
            LOGGER.info("Initialization successfully!");
        } catch (SQLException e) {
            LOGGER.error("Error during initialization , cause - " + e.getMessage());
        }
    }

    private List<Flat> getFlats(ResultSet resultSet) throws SQLException {
        List<Flat> flats = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String district = resultSet.getString(2);
            String address = resultSet.getString(3);
            double square = resultSet.getDouble(4);
            int rooms = resultSet.getInt(5);
            int price = resultSet.getInt(6);
            Flat flat = new Flat(id, district, address, square, rooms, price);
            flats.add(flat);
        }
        return flats;
    }
}
