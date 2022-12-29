package simple.spring.tst.texture;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Study {
    private StudyStatus studyStatus;
    private int limit;
    private String name;

    private Member owner;
    public Study(int limit) {
        if(limit < 0) {
            throw new IllegalArgumentException("limit은 0보다 커야 한다.");
        }
        this.limit = limit;
    }

    public Study(int limit, String name) {
        this.limit = limit;
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