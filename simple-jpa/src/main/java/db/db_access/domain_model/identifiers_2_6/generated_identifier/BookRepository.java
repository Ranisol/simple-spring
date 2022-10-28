package db.db_access.domain_model.identifiers_2_6.generated_identifier;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@Repository
@RequiredArgsConstructor
@Transactional
public class BookRepository {
    private final EntityManager entityManager;

    private void executeSave(Runnable runnable) {
        System.out.println("<--------start----------------------------------------------->");
        runnable.run();
        System.out.println("<--------end----------------------------------------------->");
    }

    public void saveBookA() {
        executeSave(() -> {
            BookAuto book = new BookAuto();
            entityManager.persist(book);
        });
    }

    public void saveBookI() {
        executeSave(() -> {
            BookIdentity book = new BookIdentity();
            entityManager.persist(book);
        });
    }

    public void saveBookS() {
        executeSave(() -> {
            BookSequence book = new BookSequence();
            entityManager.persist(book);
        });
    }

    public void saveBookT() {
        executeSave(() -> {
            BookTable book = new BookTable();
            entityManager.persist(book);
        });
    }

    public void saveBookUUid() {
        executeSave(() -> {
            BookUUID book = new BookUUID();
            entityManager.persist(book);
        });
    }

    public void saveAllBookA() {
        executeSave(() -> {
            BookAuto book1 = new BookAuto();
            BookAuto book2 = new BookAuto();
            entityManager.persist(book1);
            System.out.println("persisted book1: " + book1.id);
            entityManager.persist(book2);
            System.out.println("persisted book2: " + book2.id);
        });
    }

    public void saveAllBookI() {
        executeSave(() -> {
            BookIdentity book1 = new BookIdentity();
            BookIdentity book2 = new BookIdentity();
            entityManager.persist(book1);
            System.out.println("persisted book1: " + book1.id);
            entityManager.persist(book2);
            System.out.println("persisted book2: " + book2.id);
        });
    }

    public void saveAllBookS() {
        executeSave(() -> {
            BookSequence book1 = new BookSequence();
            BookSequence book2 = new BookSequence();
            entityManager.persist(book1);
            System.out.println("persisted book1: " + book1.id);
            entityManager.persist(book2);
            System.out.println("persisted book2: " + book2.id);
        });
    }

    public void saveAllBookT() {
        executeSave(() -> {
            BookTable book1 = new BookTable();
            BookTable book2 = new BookTable();
            entityManager.persist(book1);
            System.out.println("persisted book1: " + book1.id);
            entityManager.persist(book2);
            System.out.println("persisted book2: " + book2.id);
        });
    }

    public void saveAllBookUUid() {
        executeSave(() -> {
            BookUUID book1 = new BookUUID();
            BookUUID book2 = new BookUUID();
            entityManager.persist(book1);
            System.out.println("persisted book1: " + book1.id);
            entityManager.persist(book2);
            System.out.println("persisted book2: " + book2.id);
        });
    }
}
