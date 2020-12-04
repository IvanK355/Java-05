package factory;

import dao.Dao;
import entities.Account;
import service.impl.DdH2Service;
import service.impl.JsonService;

public class AccountFactory {

        public Dao <Account> getBdType(BdTypes type) {
            return switch (type) {
                case BDH2 -> new DdH2Service();
                case JSON -> new JsonService();
                default -> throw new IllegalArgumentException("Wrong DB type:" + type);
            };
        }

}
