package db.db_access.domain_model.identifiers_2_6.generated_identifier;

import org.hibernate.boot.internal.IdGeneratorInterpreterImpl;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
class BookAuto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String title;
}

@Entity
@Table
class BookIdentity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY /** {@link org.hibernate.id.IdentityGenerator} 사용 */
    )
    public Long id;
    public String title;
}

@Entity
@Table
@SequenceGenerator(
        name = "BOOK_S_SEQ_GENERATOR", // 식별자 생성기 이름, 아래 generator 에서 쓰인다
        sequenceName = "BOOK_S_SEQ", // 데이터베이스에 등록된 시퀀스 테이블 이름 (기본값은 hibernate_sequence)
        allocationSize = 1 // 시퀀스 한번 호출에 증가하는 수
)
class BookSequence {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,  /** {@link org.hibernate.id.enhanced.SequenceStyleGenerator} 사용 */
            generator = "BOOK_S_SEQ_GENERATOR"
    )
    public Long id;
    public String title;
}

@Entity
@Table
@TableGenerator(
        name = "BOOK_T_SEQ_GENERATOR",
        table = "BOOK_T_SEQ",
        pkColumnValue = "BOOK_T_SEQ",
        allocationSize = 1
)
class BookTable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,  /** {@link org.hibernate.id.enhanced.TableGenerator} 사용 */
            generator = "BOOK_T_SEQ_GENERATOR"
    )
    public Long id;
    public String title;
}

@Entity
@Table
class BookUUID {
    @Id
    @GeneratedValue
    /**
     {@link  IdGeneratorInterpreterImpl.FallbackInterpreter} 사용 (since hibernate 5.0)
     생성 전략 바꾸면 작동 x
     */
    public UUID id;
    public String title;
}