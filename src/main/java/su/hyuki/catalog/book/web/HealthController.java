package su.hyuki.catalog.book.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String getHealth() {
        return "Catalog-Service는 정상 동작하고 있습니다.";
    }
}
