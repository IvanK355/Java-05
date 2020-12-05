package service.impl;

import dao.H2AccountDao;
import entities.Account;
import service.BankService;
import service.NotEnoughMoneyException;
import service.UnknownAccountException;

import java.sql.SQLException;

public class DdH2Service implements BankService {
    private H2AccountDao accountDao = new H2AccountDao();

    @Override
    public void createNewTable() {
        accountDao.createNewTable();
    }

    @Override
    public Account balance(int id) throws SQLException, UnknownAccountException {
        try {
            return accountDao.balance(id);

        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("Неверный счет");

        }
        return accountDao.balance(id);
    }

    @Override
    public Account deposit(int id, int amount) throws SQLException, UnknownAccountException {
        try {
            return accountDao.deposit(id, amount);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("Неверный счет");
        }
        return accountDao.deposit(id, amount);
    }

    @Override
    public Account withdraw(int id, int amount) throws SQLException, NotEnoughMoneyException, UnknownAccountException {
        try {
            return accountDao.withdraw(id, amount);
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
            System.out.println("Недостаточно средств на счете. Введите новую сумму.");

        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("Неверный счет");

        }
        return accountDao.withdraw(id, amount);
    }

    @Override
    public void transfer(int id1, int id2, int amount) throws SQLException {
        try {
            accountDao.transfer(id1, id2, amount);
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
            System.out.println("Недостаточно средств на счете. Введите новую сумму.");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("Неверный счет");
        }
    }
}
