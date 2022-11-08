package simple.spring.context.event.service_texture.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Orders {
    @Id @GeneratedValue
    private Long id;
    private String userEmail;
    private Long itemId;
    private Long quantity;
    public Orders(String userEmail, Long itemId, Long quantity) {
        this.userEmail = userEmail;
        this.itemId = itemId;
        this.quantity = quantity;
    }
}
