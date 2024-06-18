package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;
import utils.DBConnection;

public class UserDAO {
    private final Connection connection;
    private String query;
    private PreparedStatement statement;
    private ResultSet result, resultCountRows;
    private int insertedLines = 0;

    public UserDAO() throws SQLException {
        connection = DBConnection.getConnection();
    }

    public boolean createUser(User user) throws SQLException {
        query = "INSERT INTO User VALUES(?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, user.getUserID());
        statement.setString(2, user.getUsername());
        statement.setString(3, user.getUserPassword());
        statement.setString(4, user.getUserEmail());
        statement.setInt(5, user.getUserRole());
        insertedLines = statement.executeUpdate();
        return (insertedLines != 0);
    }

    public User readUser(String username) throws SQLException {
        query = "SELECT * FROM User WHERE username = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, username);
        result = statement.executeQuery();
        if (result.next()) {
            return new User(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getInt(5));
        }
        return null;
    }

    public boolean updateUser(User user) throws SQLException {
        query = "UPDATE User SET full_name = ?, password = ?, email = ?, access_level = ? WHERE username = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, user.getUserID());
        statement.setString(2, user.getUsername());
        statement.setString(3, user.getUserPassword());
        statement.setString(4, user.getUserEmail());
        statement.setInt(5, user.getUserRole());
        insertedLines = statement.executeUpdate();
        return (insertedLines != 0);
    }

    public boolean deleteUser(String username) throws SQLException {
        query = "DELETE FROM User WHERE username = ?";
        statement = connection.prepareStatement(query);
        statement.setString(2, username);
        insertedLines = statement.executeUpdate();
        return (insertedLines != 0);
    }

    public String[] readAllAttendants() throws SQLException {
        int rows = 0;
        resultCountRows = connection.prepareStatement("SELECT COUNT(*) FROM User WHERE role = 1").executeQuery();
        if (resultCountRows.next())
            rows = resultCountRows.getInt(1);

        query = "SELECT username FROM User WHERE access_level = 1";
        statement = connection.prepareStatement(query);
        result = statement.executeQuery();
        List<String> attendantsList = new ArrayList<>();
        while (result.next()) {
            attendantsList.add(result.getString(1));
        }

        rows++;
        String[] attendants = new String[rows];
        attendants[0] = null;
        for (int i = 1; i < rows; i++) {
            attendants[i] = attendantsList.get(i - 1);
        }
        return attendants;
    }

    public String[][] readUsersTableData() throws SQLException {
        int rows = 0, columns = 5, aux = 0;
        resultCountRows = connection.prepareStatement("SELECT COUNT(*) FROM User").executeQuery();
        if(resultCountRows.next()) rows = resultCountRows.getInt(1);

        query = "SELECT * FROM User";
        statement = connection.prepareStatement(query);
        result = statement.executeQuery();
        List<String> usersList = new ArrayList<>();
        while(result.next()){
            for(int i = 1; i <= columns; i++){
                usersList.add(result.getString(i));
            }
        }

        String[][] users = new String[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                users[i][j] = usersList.get(aux++);
            }
        }
        return users;
    }

    public void close() throws SQLException {
        connection.close();
    }

}
