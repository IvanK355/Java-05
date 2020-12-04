package service;

import dao.Dao;
import entities.Account;

import java.sql.SQLException;

public interface BankService extends Dao <Account> {
    void createNewTable();
    Account balance(int id) throws SQLException;
    Account deposit(int id, int amount) throws SQLException;
    Account withdraw(int id, int amount) throws SQLException;
    void transfer(int id1, int id2, int amount) throws SQLException;
}
