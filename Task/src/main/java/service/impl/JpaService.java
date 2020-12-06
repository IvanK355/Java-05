package service.impl;

import dao.JpaAccountDao;
import entities.Account;
import service.BankService;
import service.NotEnoughMoneyException;
import service.UnknownAccountException;
import java.sql.SQLException;

public class JpaService implements BankService {
    private JpaAccountDao accountDao = new JpaAccountDao();

    @Override
    public void createNewTable() {
        accountDao.create();
    }

    @Override
    public Account balance(int id) throws SQLException, UnknownAccountException {
        try {

            Account account = accountDao.read(id);
            if (account.getHolder() == null) {
                throw new UnknownAccountException("Неверный счет");
            }
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        }
        return accountDao.read(id);
    }

    @Override
    public Account deposit(int id, int amount) throws SQLException, UnknownAccountException {

        try {

            Account account = accountDao.read(id);
            int newAmount = account.getAccountAmount() + amount;

            if (account.getHolder() == null) {
                throw new UnknownAccountException("Неверный счет");
            }
            return accountDao.update(id, newAmount);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        }
        return accountDao.read(id);

    }

    @Override
    public Account withdraw(int id, int amount) throws SQLException, UnknownAccountException {
        try {
            Account account = accountDao.read(id);
            int newAmount = account.getAccountAmount() - amount;
            if (newAmount<0) {
                throw new NotEnoughMoneyException("Недостаточно средств");
            }
            return accountDao.update(id, newAmount);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("Неверный счет");
        }
        catch (NotEnoughMoneyException e) {
            e.printStackTrace();
            System.out.println("Недостаточно средств");

        }
        return accountDao.read(id);
    }

    @Override
    public void transfer(int id1, int id2, int amount) throws SQLException {
        try {
            withdraw(id1, amount);
            deposit(id2, amount);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        }
    }
}
