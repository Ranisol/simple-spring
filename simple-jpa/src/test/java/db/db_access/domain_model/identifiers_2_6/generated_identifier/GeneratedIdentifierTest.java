package db.db_access.domain_model.identifiers_2_6.generated_identifier;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


@Import(GeneratedIdentifierScanConfig.class)
@SpringBootTest
class GeneratedIdentifierTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void saveAuto() {
        bookRepository.saveBookA();
        /**
         * select next_val as id_val from hibernate_sequence for update; -> for update 로 조회시 해당 row 에 락을 획득
         * update hibernate_sequence set next_val= 2 where next_val=1;
         * <--------end----------------------------------------------->
         * insert into book_auto (title, id) values (NULL, 1); -> 기본적으로 save 이후 쿼리
         * */
    }

    @Test
    void saveIdentity() {
        bookRepository.saveBookI();
        /**
         * insert into book_identity (title) values (NULL);  -> 유일하게 save 호출시 쿼리(쓰기지연 sql 적용 x), 디비에 한번 갔다옴
         * <--------end----------------------------------------------->
         * */
    }

    @Test
    void saveSequence() {
        bookRepository.saveBookS();
        /**
         * select next_val as id_val from book_s_seq for update;
         * update book_s_seq set next_val= 2 where next_val=1
         * <--------end----------------------------------------------->
         * insert into book_sequence (title, id) values (NULL, 1);
         * */
    }

    @Test
    void saveTable() {
        bookRepository.saveBookT();
        /**
         * select tbl.next_val from book_t_seq tbl where tbl.sequence_name='BOOK_T_SEQ' for update;
         * update book_t_seq set next_val=1  where next_val=0 and sequence_name='BOOK_T_SEQ';
         * <--------end----------------------------------------------->
         * insert into book_table (title, id) values (NULL, 1);
         * */
    }

    @Test
    void saveBookUUID() {
        bookRepository.saveBookUUid();
        /**<--------start----------------------------------------------->
         * <--------end----------------------------------------------->
         * insert into bookuuid (title, id) values (NULL, '18013F44802B499DB4F2990E55864A3F'); -> 쓰기지연 작동 o
         * */
    }

    @Test
    void saveAllBookAuto() {
        bookRepository.saveAllBookA();
    }

    @Test
    void saveAllBookIdentity() {
        bookRepository.saveAllBookI();
    }

    @Test
    void saveAllBookSequence() {
        bookRepository.saveAllBookS();
    }

    @Test
    void saveAllBookTable() {
        bookRepository.saveAllBookT();
    }

    @Test
    void saveAllBookUUID() {
        bookRepository.saveAllBookUUid();
    }
}
