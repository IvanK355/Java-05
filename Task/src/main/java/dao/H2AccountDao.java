package dao;

import entities.Account;
import utils.DbcpDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2AccountDao implements Dao<Account> {

    private final String SELECT_QUERY = "SELECT * FROM account WHERE id = ?";
    private final String UPDATE_DEPOSIT_QUERY = "update account set amount = amount +  ? WHERE id = ?";
    private final String UPDATE_WITHDRAW_QUERY = "update account set amount = amount -  ? WHERE id = ?";


    @Override
    public void createNewTable() {
        //DriverManager
        //      .getConnection("jdbc:h2:mem:ACCOUNT;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM './Task/src/main/resources/data/schema.sql'\\;RUNSCRIPT FROM './Task/src/main/resources/data/data.sql'");
        DbcpDataSource.getConnection();
    }

    @Override
    public Account balance(int id) throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        //connection = DriverManager
        //        .getConnection("jdbc:h2:mem:ACCOUNT");
        connection = DbcpDataSource.getConnection();

        preparedStatement = connection.prepareStatement(SELECT_QUERY);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        String name = null;
        int amount = 0;
        while (resultSet.next()) {
            name = resultSet.getString(2);
            amount = resultSet.getInt(3);
        }
        Account account = new Account(id, name, amount);
        preparedStatement.close();
        connection.close();
        return account;
    }

    @Override
    public Account deposit(int id, int amount) throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;

        connection = DbcpDataSource.getConnection();
        preparedStatement = connection.prepareStatement(UPDATE_DEPOSIT_QUERY);
        preparedStatement.setInt(1, amount);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    return balance(id);
    }

    @Override
    public Account withdraw(int id, int amount) throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;

        connection = DbcpDataSource.getConnection();
        preparedStatement = connection.prepareStatement(UPDATE_WITHDRAW_QUERY);
        preparedStatement.setInt(1, amount);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return balance(id);
    }

    @Override
    public void transfer(int id1, int id2, int amount) throws SQLException {
        withdraw(id1, amount);
        deposit(id2, amount);

    }
}
