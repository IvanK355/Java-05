package dao;

import service.NotEnoughMoneyException;
import service.UnknownAccountException;

import java.io.IOException;
import java.sql.SQLException;

public interface Dao<Account> {

    void createNewTable() throws IOException;

    Account balance(int id) throws SQLException, IOException, UnknownAccountException;

    Account deposit(int id, int amount) throws SQLException, IOException, NotEnoughMoneyException, UnknownAccountException;

    Account withdraw(int id, int amount) throws SQLException, IOException, NotEnoughMoneyException, UnknownAccountException;

    void transfer(int id1, int id2, int amount) throws SQLException, IOException, NotEnoughMoneyException, UnknownAccountException;
}
