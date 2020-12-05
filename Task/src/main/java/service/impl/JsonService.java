package service.impl;

import dao.JsonAccountDao;
import entities.Account;
import service.BankService;
import service.NotEnoughMoneyException;
import service.UnknownAccountException;

import java.io.IOException;
import java.sql.SQLException;

public class JsonService implements BankService {
    private JsonAccountDao accountDao = new JsonAccountDao();

    @Override
    public void createNewTable() throws IOException {
        accountDao.createNewTable();
    }

    @Override
    public Account balance(int id) throws UnknownAccountException, IOException {
        try {
            return accountDao.balance(id);

        } catch (UnknownAccountException | IOException e) {
            e.printStackTrace();
            System.out.println("Неверный счет");

        }
        return accountDao.balance(id);
    }

    @Override
    public Account deposit(int id, int amount) throws UnknownAccountException, IOException {
        try {
            return accountDao.deposit(id, amount);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("Неверный счет");
        }
        return accountDao.deposit(id, amount);
    }

    @Override
    public Account withdraw(int id, int amount) throws NotEnoughMoneyException, UnknownAccountException, IOException {
        try {
            return accountDao.withdraw(id, amount);
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
            System.out.println("Недостаточно средств на счете. Введите новую сумму.");

        } catch (UnknownAccountException | IOException e) {
            e.printStackTrace();
            System.out.println("Неверный счет");

        }
        return accountDao.withdraw(id, amount);
    }

    @Override
    public void transfer(int id1, int id2, int amount) {
        try {
            accountDao.transfer(id1, id2, amount);
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
            System.out.println("Недостаточно средств на счете. Введите новую сумму.");
        } catch (UnknownAccountException | IOException e) {
            e.printStackTrace();
            System.out.println("Неверный счет");
        }
    }

}
