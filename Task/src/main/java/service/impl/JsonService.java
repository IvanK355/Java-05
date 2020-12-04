package service.impl;

import entities.Account;
import service.BankService;

import java.sql.SQLException;

public class JsonService implements BankService {
    @Override
    public void createNewTable() {

    }

    @Override
    public Account balance(int id) {
        return null;
    }

    @Override
    public Account deposit(int id, int amount) {
        return null;
    }

    @Override
    public Account withdraw(int id, int amount) {
        return null;
    }

    @Override
    public void transfer(int id1, int id2, int amount) {

    }
}
