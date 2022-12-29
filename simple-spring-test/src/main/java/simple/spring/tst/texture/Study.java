package simple.spring.tst.texture;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "study")
@NoArgsConstructor
@AllArgsConstructor
public class Study {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private StudyStatus studyStatus;
    private int limits;
    private String name;

    @ManyToOne
    private Member owner;
    public Study(int limit) {
        if(limit < 0) {
            throw new IllegalArgumentException("limit은 0보다 커야 한다.");
        }
        this.limits = limit;
    }

    public Study(int limit, String name) {
        this.limits = limit;
        this.name = name;
    }

    public enum StudyStatus {
        A("A");
        final String name;
        StudyStatus(String name) {
            this.name = name;
        }
    }

}