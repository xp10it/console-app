package org.example.repositories;

import org.example.models.Item;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ItemRepositoryTest {

    private static EntityManagerFactory emf;

    @BeforeAll
    public static void init() {
        emf = Persistence.createEntityManagerFactory("ctx_test");
    }

    @AfterAll
    public static void cleanup() {
        emf.close();
    }

    @Test
    public void insertNewItem_ItemIsInserted() {
        // AAA
        // Arrange
        Item i = new Item();
        i.title = "test_item";

        ItemRepository sut = new ItemRepository(emf.createEntityManager());

        // Act
        sut.insert(i);

        // Assert
        List<Item> allItems = sut.selectAll();
        assert allItems.size() == 1;
        assert allItems.get(0).title.equals("test_item");
    }
}
