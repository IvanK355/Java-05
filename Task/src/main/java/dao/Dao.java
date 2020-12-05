package dao;

import service.NotEnoughMoneyException;

import java.io.IOException;
import java.sql.SQLException;

public interface Dao<Account> {

    void createNewTable() throws IOException;

    Account balance(int id) throws SQLException, IOException;

    Account deposit(int id, int amount) throws SQLException, IOException, NotEnoughMoneyException;

    Account withdraw(int id, int amount) throws SQLException, IOException, NotEnoughMoneyException;

    void transfer(int id1, int id2, int amount) throws SQLException, IOException, NotEnoughMoneyException;
}
