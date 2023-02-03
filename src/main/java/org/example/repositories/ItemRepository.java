package org.example.repositories;

import org.example.models.Item;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;

public class ItemRepository {
    private EntityManager em;

    public ItemRepository(EntityManager em) {
        this.em = em;
    }

    public Item insert(Item i) {
        em.getTransaction().begin();
        em.persist(i);
        em.getTransaction().commit();

        return i;
    }

    public List<Item> selectAll() {
        String hq = "select i from Item i";

        return em.createQuery(hq).getResultList();
    }

    public void applyInflation() {
        em.getTransaction().begin();
        var session = em.unwrap(Session.class);
        session.doWork((con) -> {
            con.createStatement().execute("call applyInflation()");
        });
        session.close();
        em.getTransaction().commit();
    }

    public List<Item> getExpensiveItems(float lowestPrice) {
        String query = String.format("select * from getExpensiveItems(%f)", lowestPrice);
        return em.createNativeQuery(query, Item.class).getResultList();
    }
}
