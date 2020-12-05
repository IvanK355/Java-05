package service;

import dao.Dao;
import entities.Account;

import java.io.IOException;
import java.sql.SQLException;

public interface BankService extends Dao <Account> {
    void createNewTable() throws IOException;
    Account balance(int id) throws SQLException, IOException;
    Account deposit(int id, int amount) throws SQLException, IOException;
    Account withdraw(int id, int amount) throws SQLException, IOException, NotEnoughMoneyException;
    void transfer(int id1, int id2, int amount) throws SQLException, IOException, NotEnoughMoneyException;
}
