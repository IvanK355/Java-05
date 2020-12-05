package dao;

import entities.Account;
import service.NotEnoughMoneyException;

import javax.persistence.*;

public class JpaAccountDao implements Dao<Account> {
    private final String UPDATE_DEPOSIT_QUERY = "UPDATE Account e SET e.accountAmount = e.accountAmount + :increment WHERE e.id = :id";
    private final String UPDATE_WITHDRAW_QUERY = "UPDATE Account e SET e.accountAmount = e.accountAmount - :increment WHERE e.id = :id";

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");

    public void createNewTable() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for (int i = 1; i < 11; i++) {
            Account account = new Account("Holder" + i, 500 );
            em.persist(account);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Account balance(int id) {

        EntityManager em = emf.createEntityManager();
        return em.find(Account.class, id);
    }

    @Override
    public Account deposit(int id, int amount)  {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery(UPDATE_DEPOSIT_QUERY);
        query.setParameter("increment", amount);
        query.setParameter("id", id);
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
        return balance(id);
    }

    @Override
    public Account withdraw(int id, int amount) throws NotEnoughMoneyException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Account account = balance(id);
        if((account.getAccountAmount()-amount)<0) {
            throw new NotEnoughMoneyException("Исключение создано в JPA метод withdraw");
        }

        Query query = em.createQuery(UPDATE_WITHDRAW_QUERY);
        query.setParameter("increment", amount);
        query.setParameter("id", id);
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
        return balance(id);
    }

    @Override
    public void transfer(int id1, int id2, int amount) throws NotEnoughMoneyException {
        withdraw(id1, amount);
        deposit(id2, amount);
    }
}