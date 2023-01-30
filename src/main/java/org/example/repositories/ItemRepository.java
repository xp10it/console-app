package org.example.repositories;

import org.example.models.Item;

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
}
