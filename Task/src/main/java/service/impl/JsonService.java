package service.impl;

import dao.JsonAccountDao;
import entities.Account;
import service.BankService;
import service.NotEnoughMoneyException;
import service.UnknownAccountException;

import java.io.IOException;

public class JsonService implements BankService {
    private JsonAccountDao accountDao = new JsonAccountDao();


    @Override
    public void createNewTable() throws IOException {
        accountDao.create();
    }

    @Override
    public Account balance(int id) throws IOException, NullPointerException, UnknownAccountException {
        try {

            return accountDao.read(id);

        } catch (NullPointerException e) {
            throw new UnknownAccountException("Неверный счет");
        }
    }

    @Override
    public Account deposit(int id, int amount) throws IOException, UnknownAccountException {

        Account account = balance(id);
        int newAmount = account.getAccountAmount() + amount;
        return accountDao.update(id, newAmount);
    }

    @Override
    public Account withdraw(int id, int amount) throws IOException, UnknownAccountException {
        Account account = balance(id);
        try {
            int newAmount = account.getAccountAmount() - amount;
            if (newAmount < 0) {
                throw new NotEnoughMoneyException("Недостаточно средств");
            }
            return accountDao.update(id, newAmount);
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
            System.out.println("Недостаточно средств");
        }
        return null;
    }

    @Override
    public void transfer(int id1, int id2, int amount) throws IOException, UnknownAccountException {
        withdraw(id1, amount);
        deposit(id2, amount);
    }
}
