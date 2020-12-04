package dao;

import java.io.IOException;
import java.sql.SQLException;

public interface Dao<Account> {

    void createNewTable() throws IOException;

    Account balance(int id) throws SQLException, IOException;

    Account deposit(int id, int amount) throws SQLException, IOException;

    Account withdraw(int id, int amount) throws SQLException, IOException;

    void transfer(int id1, int id2, int amount) throws SQLException, IOException;
}
