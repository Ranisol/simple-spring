package simple.spring.tst.study;

import lombok.Data;

@Data
public class StudyRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
