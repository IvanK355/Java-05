package service.impl;

import dao.JsonAccountDao;
import entities.Account;
import service.BankService;
import service.NotEnoughMoneyException;

import java.io.IOException;
import java.sql.SQLException;

public class JsonService implements BankService {
    private final JsonAccountDao accountDao = new JsonAccountDao();

    @Override
    public void createNewTable() throws IOException {
        accountDao.createNewTable();
    }

    @Override
    public Account balance(int id) throws IOException {
        return accountDao.balance(id);
    }

    @Override
    public Account deposit(int id, int amount) throws IOException {
        return accountDao.deposit(id, amount);

    }

    @Override
    public Account withdraw(int id, int amount) throws NotEnoughMoneyException, IOException {
        try {
            return accountDao.withdraw(id, amount);
        } catch (NotEnoughMoneyException | IOException e) {
            e.printStackTrace();
            System.out.println("Недостаточно средств на счете. Введите новую сумму.");

        }
        return accountDao.withdraw(id, amount);
    }

    @Override
    public void transfer(int id1, int id2, int amount) throws IOException, NotEnoughMoneyException {
        accountDao.transfer(id1, id2, amount);
    }
}
