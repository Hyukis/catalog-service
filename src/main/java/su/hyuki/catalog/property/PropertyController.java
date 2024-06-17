package su.hyuki.catalog.property;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PropertyController {

    private final PolarProperties polarProperties;

    @GetMapping("/polar")
    public String getGreeting(){
        return polarProperties.getGreeting();
    }
}
