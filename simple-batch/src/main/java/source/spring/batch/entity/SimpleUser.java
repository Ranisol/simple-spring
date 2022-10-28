package source.spring.batch.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Getter
@NoArgsConstructor
public class SimpleUser {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private LocalDate lastLogin;
}
