package org.example;

import org.example.models.Item;
import org.example.repositories.ItemRepository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.user", "postgres");
        properties.put("javax.persistence.jdbc.password", "rootPWD3!");

        EntityManager em = Persistence.createEntityManagerFactory("main_ctx", properties)
                .createEntityManager();

        ItemRepository repo = new ItemRepository(em);

        boolean isRunning = true;

        while(isRunning) {
            String input;
            byte[] buffer = new byte[64];

            try {
                int read = System.in.read(buffer, 0, 64);
                input = new String(buffer, 0, read).strip();
            } catch (Exception e) {
                System.out.println(e.getMessage());

                isRunning = false;

                continue;
            }

            System.out.println("User input " +  input);

            if (input.equals("-q")) {
                isRunning = false;
            } else {
                Item item = new Item();
                item.setTitle(input);

                item = repo.insert(item);

                System.out.println("Saved item " + item.getId() + " " + item.getTitle());
                System.out.println("All item");

                for (Item i : repo.selectAll()) {
                    System.out.println(i.getTitle());
                }
            }
        }

        em.close();
    }
}