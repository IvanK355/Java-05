package application;

import dao.Dao;
import entities.Account;
import factory.BdTypes;
import factory.AccountFactory;

import java.sql.SQLException;

public class AccountApplication {

    //standard constructors

    public static void main(String[] args) throws SQLException {
        AccountFactory factory = new AccountFactory();
        Dao <Account> accountService = factory.getBdType(BdTypes.BDH2);
        accountService.createNewTable();
        Account account1 = accountService.balance(1);
        Account account2 = accountService.deposit(1,100);
        Account account3 = accountService.withdraw(1,100);
        accountService.transfer(1,2,100);
        Account account4 = accountService.balance(1);
        Account account5 = accountService.balance(2);
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
        System.out.println(account4);
        System.out.println(account5);
    }

}
