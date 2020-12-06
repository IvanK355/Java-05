package dao;

import entities.Account;
import service.UnknownAccountException;

import javax.persistence.*;

public class JpaAccountDao implements Dao<Account> {
    private final String UPDATE_QUERY = "UPDATE Account e SET e.accountAmount = :increment WHERE e.id = :id";

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");

    public void create() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for (int i = 1; i < 11; i++) {
            Account account = new Account("Holder" + i, 500);
            em.persist(account);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Account read(int id) throws UnknownAccountException {

        EntityManager em = emf.createEntityManager();
        return em.find(Account.class, id);
    }

    @Override
    public Account update(int id, int amount) throws UnknownAccountException {
        read(id);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery(UPDATE_QUERY);
        query.setParameter("increment", amount);
        query.setParameter("id", id);
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
        return read(id);
    }

    @Override
    public Account delete(int id, int amount) {
        return null;
    }
}