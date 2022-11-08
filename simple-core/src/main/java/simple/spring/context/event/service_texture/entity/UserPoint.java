package simple.spring.context.event.service_texture.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
@Getter
@NoArgsConstructor
public class UserPoint {
    @Id @GeneratedValue
    private Long id;
    private String userEmail;
    private Long point;
    private LocalDate createdDate;
    public UserPoint(String userEmail, Long point) {
        this.userEmail = userEmail;
        this.point = point;
        this.createdDate = LocalDate.now();
    }
}
