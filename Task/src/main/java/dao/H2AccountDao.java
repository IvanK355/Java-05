package dao;

import entities.Account;
import service.NotEnoughMoneyException;
import service.UnknownAccountException;
import utils.DbcpDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2AccountDao implements Dao<Account> {

    private final String SELECT_QUERY = "SELECT * FROM account WHERE id = ?";
    private final String UPDATE_QUERY = "update account set amount = ? WHERE id = ?";


    @Override
    public void create() {
        DbcpDataSource.getConnection();
    }

    @Override
    public Account read (int id) throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
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
        preparedStatement.close();
        connection.close();
        return new Account(id, name, amount);
    }

    @Override
    public Account update(int id, int amount) throws SQLException, UnknownAccountException {
        Connection connection;
        PreparedStatement preparedStatement;
        connection = DbcpDataSource.getConnection();
        preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setInt(1, amount);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return read(id);
    }

    @Override
    public Account delete(int id, int amount) throws SQLException, IOException, NotEnoughMoneyException, UnknownAccountException {
        return null;
    }
}
