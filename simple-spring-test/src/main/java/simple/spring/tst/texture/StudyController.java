package simple.spring.tst.texture;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {
    private final StudyService studyService;

    @GetMapping
    public String get() {
        return "study";
    }

    @PostMapping
    public Study create(@RequestBody StudyRequest request) {
        return studyService.createNewStudy(request.getName());
    }
}
