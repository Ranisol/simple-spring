package simple.spring.tst.texture;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {
    private final StudyService studyService;

    @GetMapping("/{id}")
    public Study get(
            @PathVariable Long id
    ) {
        return studyService.get(id);
    }

    @PostMapping
    public Study create(@RequestBody StudyRequest request) {
        return studyService.createNewStudy(request.getName());
    }
}
