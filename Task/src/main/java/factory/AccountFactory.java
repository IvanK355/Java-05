package factory;

import dao.Dao;
import entities.Account;
import service.BankService;
import service.impl.DdH2Service;
import service.impl.JpaService;
import service.impl.JsonService;

public class AccountFactory {

        public BankService getBdType(BdTypes type) {
            return switch (type) {
                case BDH2 -> new DdH2Service();
                case JSON -> new JsonService();
                case JPA -> new JpaService();
                default -> throw new IllegalArgumentException("Wrong DB type:" + type);
            };
        }

}
