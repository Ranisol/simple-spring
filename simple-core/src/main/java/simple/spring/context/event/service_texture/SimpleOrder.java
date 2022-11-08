package simple.spring.context.event.service_texture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SimpleOrder {
    private String userEmail;
    private Long itemId;
    private Long quantity;
}
