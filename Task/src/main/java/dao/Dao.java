package dao;

import service.NotEnoughMoneyException;
import service.UnknownAccountException;

import java.io.IOException;
import java.sql.SQLException;

public interface Dao<Account> {

    void create() throws IOException;

    Account read(int id) throws SQLException, IOException, UnknownAccountException;

    Account update(int id, int amount) throws SQLException, IOException, NotEnoughMoneyException, UnknownAccountException;

    Account delete(int id, int amount) throws SQLException, IOException, NotEnoughMoneyException, UnknownAccountException;

}
