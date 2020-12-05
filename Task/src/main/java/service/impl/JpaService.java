package service.impl;

import dao.JpaAccountDao;
import entities.Account;
import service.BankService;
import service.NotEnoughMoneyException;

import java.io.IOException;
import java.sql.SQLException;

public class JpaService implements BankService {
    private final JpaAccountDao accountDao = new JpaAccountDao();

    @Override
    public void createNewTable() {
        accountDao.createNewTable();
    }

    @Override
    public Account balance(int id) {
        return accountDao.balance(id);
    }

    @Override
    public Account deposit(int id, int amount) {
        return accountDao.deposit(id, amount);

    }

    @Override
    public Account withdraw(int id, int amount) throws NotEnoughMoneyException {
        try {
            return accountDao.withdraw(id, amount);
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
            System.out.println("Недостаточно средств на счете. Введите новую сумму.");

        }
        return accountDao.withdraw(id, amount);
    }

    @Override
    public void transfer(int id1, int id2, int amount) throws NotEnoughMoneyException {
        accountDao.transfer(id1, id2, amount);
    }
}
