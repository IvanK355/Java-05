package service;

import dao.Dao;
import entities.Account;

import java.io.IOException;
import java.sql.SQLException;

public interface BankService {
    void createNewTable() throws IOException;
    Account balance(int id) throws SQLException, IOException, UnknownAccountException;
    Account deposit(int id, int amount) throws SQLException, IOException, UnknownAccountException;
    Account withdraw(int id, int amount) throws SQLException, IOException, NotEnoughMoneyException, UnknownAccountException;
    void transfer(int id1, int id2, int amount) throws SQLException, IOException, NotEnoughMoneyException;
}
